package be.david.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by David on 20/09/2016.
 */
@Entity
@Table(name = "GAME_DEVELOPER", uniqueConstraints = @UniqueConstraint(name = "pk_gamedeveloper", columnNames = {"id"}))
public class GameDeveloper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GD_SEQ")
    @SequenceGenerator(name = "GD_SEQ", sequenceName = "GD_SEQ", initialValue = 1, allocationSize = 1)
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @OneToMany
    @JoinTable(name = "GAME_DEV_GAMES",
            joinColumns = @JoinColumn(name = "GAME_DEV_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "id")
    )
    private List<Game> games;

    @Lob
    @Column(name = "LOGO", unique = true, nullable = false)
    private byte[] logo;

    @Column(name = "HOME_PAGE", unique = true, nullable = false)
    private String homePage;

    public GameDeveloper() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
}
