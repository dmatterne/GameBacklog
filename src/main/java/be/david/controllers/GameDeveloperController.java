package be.david.controllers;

import be.david.dao.GameDeveloperRepository;
import be.david.dao.GameRepository;
import be.david.domain.Game;
import be.david.domain.GameDeveloper;
import be.david.domain.GameList;
import be.david.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

/**
 * Created by David on 16/09/2016.
 */
@Controller
public class GameDeveloperController {


    @Autowired
    GameDeveloperRepository gameDeveloperRepository;

    @Autowired
    GameRepository gameRepository;

    @RequestMapping("/gamedevlist")
    public String gamedevs(Model model) {
        model.addAttribute("gameDevList",gameDeveloperRepository.findAll());
        return "gamedevelopers";
    }

    @RequestMapping(value="/{id}/removedev")
    public String removeGameDev(@PathVariable("id") Integer id) {
        gameDeveloperRepository.delete(id);
        return "redirect:" + fromMappingName("GDC#gamedevs").build();
    }

    @RequestMapping(value = "/addgamedev", method = RequestMethod.GET)
   private String addGameDevPage(Model model, @RequestParam(value = "gameDevId", required = false) Integer gameDevId) {
        if(gameDevId != null) {
            model.addAttribute("gameDev", gameDeveloperRepository.findOne(gameDevId));
        } else {
            model.addAttribute("gameDev", new GameDeveloper());
        }
        return "addgamedev";
    }

    @RequestMapping(value = "/addgamedev", method = RequestMethod.POST)
    private String processGameDev (@Valid @ModelAttribute("gameDev") GameDeveloper gameDev, BindingResult br, @RequestParam("logo2") MultipartFile[] file) throws Exception {
        if (br.hasErrors()) {
            System.out.println(br.getFieldErrors());
            return "addgamedev";
        } else {
            System.out.println("noerrors");

            if (file != null && file.length > 0) {
                for (MultipartFile aFile : file){

                    System.out.println("Saving file: " + aFile.getOriginalFilename());

                    gameDev.setLogo(aFile.getBytes());
                    gameDev.setLogo_name(aFile.getOriginalFilename());
                    gameDeveloperRepository.save(gameDev);

                }
            }

            return "redirect:/gamedevlist";



        }
    }

//    @RequestMapping (value="/retrieveLogo/{gameDevId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    @RequestMapping (value="/retrieveLogo", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRImage(@RequestParam(value = "gameDevId") Integer gameDevId) {
        System.out.println("print");
        GameDeveloper gd = gameDeveloperRepository.findOne(gameDevId);

        byte[] bytes = gd.getLogo(); // Generate the image based on the id

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]> (bytes, headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/gamesperdev", method = RequestMethod.GET)
//    public String gamesPerList(Model model, @PathVariable(value = "id") Integer id) {
    public String gamesPerDev(Model model, @RequestParam(value = "gameDevId", required = true) Integer gameDevId) {

        model.addAttribute("gamesOfDev", gameDeveloperRepository.findOne(gameDevId).getGames());
        model.addAttribute("gameDevId", gameDevId);
        return "gamesperdev";
    }


    @RequestMapping(value = "/{id}/removeGameFromDev/{gameDevId}")
    public String removeGameFromDev(@PathVariable(value = "id") Integer id, @PathVariable(value = "gameDevId") Integer gameDevId) {
        GameDeveloper gl = gameDeveloperRepository.findOne(gameDevId);
        Game g = gameRepository.findOne(id);

        gl.removeGameFromDeveloper(g);

        gameDeveloperRepository.save(gl);
        return "redirect:" + fromMappingName("GDC#gamedevs").arg(0,gameDevId).build();

    }

}
