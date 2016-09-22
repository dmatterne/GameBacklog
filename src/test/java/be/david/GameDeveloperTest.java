package be.david;

import be.david.dao.GameDeveloperRepository;
import be.david.domain.Game;
import be.david.domain.GameDeveloper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by David on 22/09/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class GameDeveloperTest {

    @Autowired
    GameDeveloperRepository gameDeveloperRepository;

    @Test
    public void findGameByTitle() {

        List<GameDeveloper> gameDevelopers = gameDeveloperRepository.findByGameIdReference(-1);

        Assert.assertEquals(1, gameDevelopers.size());



    }
}
