package be.david.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Parameter;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by David on 20/09/2016.
 */
@Entity
@Table(name = "GAME_DEVELOPER", uniqueConstraints = @UniqueConstraint(name = "pk_gamedeveloper", columnNames = {"id"}))
public class GameDeveloper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GD_SEQ")
    @GenericGenerator(name = "GD_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GD_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
    )
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @OneToMany
    @JoinTable(name = "GAME_DEV_GAMES",
            joinColumns = @JoinColumn(name = "GAME_DEV_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "id")
    )
    private List<Game> games;

    @Column(name = "LOGO_NAME", unique = false, nullable = true)
    private String logo_name;

    @Lob
    @Column(name = "LOGO", unique = false, nullable = true)
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

    public String getLogo_name() {
        return logo_name;
    }

    public void setLogo_name(String logo_name) {
        this.logo_name = logo_name;
    }

    public void addGameFromDeveloper (Game g) {
        this.games.add(g);
    }

    public void removeGameFromDeveloper(Game g) {
        this.games.remove(g);
    }
}
