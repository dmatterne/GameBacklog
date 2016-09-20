package be.david.dao;

import be.david.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by David on 16/09/2016.
 */
public interface GameRepository extends JpaRepository<Game,Integer> {

    List<Game> findByTitleLike(String name);

}
