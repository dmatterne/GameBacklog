package be.david.controllers;

import be.david.dao.GameListRepository;
import be.david.dao.GameRepository;
import be.david.domain.Game;
import be.david.domain.GameList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

/**
 * Created by David on 19/09/2016.
 */
@Controller
public class GameListController {

    @Autowired
    GameListRepository glr;

    @Autowired
    GameRepository gr;


    @RequestMapping("/collection")
    public String gameLists(Model model) {
        model.addAttribute("gameList",glr.findAll());
        return "gamelists";
    }

    @RequestMapping(value = "/addgamelist", method = RequestMethod.GET)
    public String addGameList(Model model, @RequestParam(value = "gameListId", required = false) Integer gameListId) {
        if (gameListId != null) {
            GameList g = glr.findOne(gameListId);
            model.addAttribute("gamelist", g);
        } else {
            model.addAttribute("gamelist", new GameList());
        }
        return "addgamelist";
    }


    @RequestMapping(value = "/addgamelist", method = RequestMethod.POST)
    public String saveGameList(@Valid @ModelAttribute("gamelist") GameList gameList, BindingResult br) {

        if (br.hasErrors()) {

            return "redirect:" + fromMappingName("GLC#addGameList").build();

        } else {
            glr.save(gameList);
            return "redirect:" + fromMappingName("GLC#gameLists").build();
        }
    }

    @RequestMapping(value = "/gamesperlist", method = RequestMethod.GET)
//    public String gamesPerList(Model model, @PathVariable(value = "id") Integer id) {
    public String gamesPerList(Model model, @RequestParam(value = "gameListId", required = true) Integer gameListId) {

        model.addAttribute("gamesOfList", glr.findOne(gameListId).getGames());
        model.addAttribute("gameListId", gameListId);
        return "gamesperlist";
    }


    @RequestMapping(value = "/{id}/removeGameList")
    public String removeGameList(@PathVariable(value = "id") Integer id) {
        GameList g = glr.findOne(id);
        glr.delete(g);

        return "redirect:" + fromMappingName("GLC#gameLists").build();
    }

    @RequestMapping(value = "/{id}/removeGameFromList/{gameListId}")
    public String removeGameFromList(@PathVariable(value = "id") Integer id, @PathVariable(value = "gameListId") Integer gameListId) {
        GameList gl = glr.findOne(gameListId);
        Game g = gr.findOne(id);

        gl.removeGameFromList(g);

        glr.save(gl);
        return "redirect:" + fromMappingName("GLC#gameLists").arg(0,gameListId).build();

    }

    @RequestMapping(value = "/savegametolist")
    public String saveGameToList (@RequestParam(name = "gameId") Integer gameId, @RequestParam(name = "gameListId") Integer gameListId) {

        GameList gl = glr.findOne(gameListId);
        gl.addGameToList(gr.findOne(gameId));
        glr.save(gl)  ;

        return "redirect:" + fromMappingName("GLC#gameLists").arg(0,gameListId).build();

    }
}
