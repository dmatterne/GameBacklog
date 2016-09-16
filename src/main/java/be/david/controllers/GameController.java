package be.david.controllers;

import be.david.dao.GameRepository;
import be.david.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

}
