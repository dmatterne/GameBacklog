package be.david.controllers;

import be.david.dao.GameDeveloperRepository;
import be.david.dao.GameListRepository;
import be.david.dao.GameRepository;
import be.david.domain.Game;
import be.david.domain.GameDeveloper;
import be.david.domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameDeveloperRepository gameDeveloperRepository;

    @RequestMapping("/gamelist")
    public String games(Model model) {
        model.addAttribute("gameList",gameRepository.findAll());
        return "games";
    }

    @RequestMapping(value="/{id}/remove")
    public String removeGame(@PathVariable("id") Integer id) {

        Game g = gameRepository.findOne(id);

        List<GameDeveloper> gds = gameDeveloperRepository.findByGameIdReference(id);

        for (GameDeveloper gd : gds ) {
            gd.removeGameFromDeveloper(g);
            gameDeveloperRepository.save(gd);
        }

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
    private String processGame (@Valid @ModelAttribute("game") Game game, BindingResult br) {
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

    @RequestMapping(value = "/addgametodev", method = RequestMethod.GET)
    private String searchGameForDev(@RequestParam(name = "gameDevId") Integer gameDevId, Model model) {
        model.addAttribute("Search", new Search());
        model.addAttribute("gameDevId", gameDevId);
        return "addgametodev";
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


    @RequestMapping(value = "/searchGameResultsForDev", method = RequestMethod.POST)
    private String searchGameResultsForDev(@Valid Search c,BindingResult br, Model model, @RequestParam(name = "gameDevId") Integer gameDevId) {

        if (br.hasErrors()) {
            System.out.println("Error");
        } else {
            model.addAttribute("result",gameRepository.findByTitleLikeWhereNotAlreadyInCurrentDev(c.getName(),gameDevId));
            model.addAttribute("Search", new Search());
            model.addAttribute("gameDevId", gameDevId);
        }

        return "addgametodev";
    }


    @RequestMapping("/sortGameAsc")
    public String sortGameAsc(Model model,@RequestParam(value="field") String string) {

        List<Game> fs = gameRepository.findAll(sortByAsc(string));
        System.out.println(fs);
        model.addAttribute("gameList", fs);
        return "games";
    }


    private Sort sortByAsc(String by) {
        return new Sort(Sort.Direction.ASC, by);
    }

}
