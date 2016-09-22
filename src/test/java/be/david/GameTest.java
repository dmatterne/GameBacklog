package be.david;

import be.david.dao.GameDeveloperRepository;
import be.david.dao.GameRepository;
import be.david.domain.Game;
import be.david.domain.GameDeveloper;
import groovy.transform.ASTTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by David on 20/09/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class GameTest {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameDeveloperRepository gameDeveloperRepository;

    @Test
    public void findGameByTitleList() {

        List<Game> games = gameRepository.findByTitleLikeWhereNotAlreadyInCurrentList("%",-1);

        Assert.assertEquals(3, games.size());


    }

    @Test
    public void findGameByTitleDev() {

        List<Game> games = gameRepository.findByTitleLikeWhereNotAlreadyInCurrentDev("%",-1);

        Assert.assertEquals(3, games.size());

//        GameDeveloper gd = gameDeveloperRepository.findOne(-1);
//
//        Game a = gameRepository.findOne(-4);
//        Game b = gameRepository.findOne(-2);
//        Game c = gameRepository.findOne(-3);
//
//        gd.addGameFromDeveloper(a);
//        gd.addGameFromDeveloper(b);
//        gd.addGameFromDeveloper(c);
//
//        gameDeveloperRepository.save(gd);
//
//        List<Game> games = gameRepository.findByTitleLikeWhereNotAlreadyInCurrentDev("%",-1);
//
//        Assert.assertEquals(0, games.size());


    }

}
