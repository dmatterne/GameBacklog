package be.david.dao;

import be.david.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedQuery;
import java.util.List;

/**
 * Created by David on 16/09/2016.
 */
public interface GameRepository extends JpaRepository<Game,Integer> {

    List<Game> findByTitleLike(String name);

    @Query("select g from Game g where g.title LIKE ?1 AND g.id NOT IN (SELECT x.id FROM GameList gl JOIN gl.games x WHERE gl.id = ?2)")
    List<Game> findByTitleLikeWhereNotAlreadyInCurrentList (String name, Integer listId);

    @Query("select g from Game g where g.title LIKE ?1 AND g.id NOT IN (SELECT x.id FROM GameDeveloper gd JOIN gd.games x WHERE gd.id = ?2)")
    List<Game> findByTitleLikeWhereNotAlreadyInCurrentDev (String name, Integer listId);

}
