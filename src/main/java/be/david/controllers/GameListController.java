package be.david.controllers;

import be.david.dao.GameListRepository;
import be.david.domain.GameList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

/**
 * Created by David on 19/09/2016.
 */
@Controller
public class GameListController {

    @Autowired
    GameListRepository glr;


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
    public String saveGameList(@Valid GameList gameList, BindingResult br) {

        if (br.hasErrors()) {

            return "redirect:" + fromMappingName("GLC#addGameList").build();

        } else {
            glr.save(gameList);
            return "redirect:" + fromMappingName("GLC#gameLists").build();
        }
    }


    @RequestMapping(value = "/{id}/removeGameList")
    public String removeGameList(@PathVariable(value = "id") Integer id) {
        GameList g = glr.findOne(id);
        glr.delete(g);

        return "redirect:" + fromMappingName("GLC#gameLists").build();
    }
}
