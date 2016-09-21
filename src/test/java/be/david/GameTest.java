package be.david;

import be.david.dao.GameRepository;
import be.david.domain.Game;
import groovy.transform.ASTTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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

    @Test
    public void findGameByTitle() {

        List<Game> games = gameRepository.findByTitleLikeWhereNotAlreadyInCurrentList("%",-1);

        Assert.assertEquals(3, games.size());


    }

}
