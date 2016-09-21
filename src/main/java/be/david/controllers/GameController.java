package be.david.controllers;

import be.david.dao.GameRepository;
import be.david.domain.Game;
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
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

/**
 * Created by David on 16/09/2016.
 */
@Controller
public class GameController {


    @Autowired
    GameRepository gameRepository;

    @RequestMapping("/gamelist")
    public String games(Model model) {
        model.addAttribute("gameList",gameRepository.findAll());
        return "games";
    }

    @RequestMapping(value="/{id}/remove")
    public String removeGame(@PathVariable("id") Integer id) {
        gameRepository.delete(id);
        return "redirect:" + fromMappingName("GC#games").build();
    }

    @RequestMapping(value = "/addgame", method = RequestMethod.GET)
   private String addGamePage(Model model, @RequestParam(value = "gameId", required = false) Integer gameId) {
        if(gameId != null) {
            model.addAttribute("game", gameRepository.findOne(gameId));
        } else {
            model.addAttribute("game", new Game());
        }
        return "addgame";
    }

    @RequestMapping(value = "/addgame", method = RequestMethod.POST)
    private String processGame (@Valid Game game, BindingResult br) {
        if (br.hasErrors()) {
            return "addgame";
        } else {
            gameRepository.save(game);
            return "redirect:/gamelist";

        }
    }


    @RequestMapping(value = "/addgametolist", method = RequestMethod.GET)
    private String searchGame(@RequestParam(name = "gameListId") Integer gameListId, Model model) {
        model.addAttribute("Search", new Search());
        model.addAttribute("gameListId", gameListId);
        return "addgametolist";
    }

    @RequestMapping(value = "/searchGameResults", method = RequestMethod.POST)
    private String searchGameResults(@Valid Search c,BindingResult br, Model model, @RequestParam(name = "gameListId") Integer gameListId) {

        if (br.hasErrors()) {
            System.out.println("Error");
        } else {
            model.addAttribute("result",gameRepository.findByTitleLikeWhereNotAlreadyInCurrentList(c.getName(),gameListId));
//            model.addAttribute("result",gameRepository.findByTitleLike(c.getName()));
//            System.out.println(gameRepository.findByTitleLike(c.getName()).isEmpty() + c.getName());
            model.addAttribute("Search", new Search());
            model.addAttribute("gameListId", gameListId);
        }

        return "addgametolist";
    }


}
