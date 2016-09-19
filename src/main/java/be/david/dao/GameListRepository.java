package be.david.dao;

import be.david.domain.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 19/09/2016.
 */
public interface GameListRepository extends JpaRepository<GameList,Integer> {
}
