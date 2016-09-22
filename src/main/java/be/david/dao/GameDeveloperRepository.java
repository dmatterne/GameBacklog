package be.david.dao;

import be.david.domain.Game;
import be.david.domain.GameDeveloper;
import be.david.domain.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by David on 19/09/2016.
 */
public interface GameDeveloperRepository extends JpaRepository<GameDeveloper,Integer> {

    @Query("select gd from GameDeveloper gd JOIN gd.games g WHERE g.id = ?1")
    List<GameDeveloper> findByGameIdReference (Integer listId);

}
