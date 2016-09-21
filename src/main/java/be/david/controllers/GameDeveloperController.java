package be.david.controllers;

import be.david.dao.GameDeveloperRepository;
import be.david.dao.GameRepository;
import be.david.domain.Game;
import be.david.domain.GameDeveloper;
import be.david.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

/**
 * Created by David on 16/09/2016.
 */
@Controller
public class GameDeveloperController {


    @Autowired
    GameDeveloperRepository gameDeveloperRepository;

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
    private String processGameDev (@Valid GameDeveloper gamedev, BindingResult br) {
        if (br.hasErrors()) {
            return "addgamedev";
        } else {
            gameDeveloperRepository.save(gamedev);
            return "redirect:/gamedevlist";

        }
    }


}
