package be.david.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;
import org.hibernate.annotations.Parameter;

/**
 * Created by David on 16/09/2016.
 */
@Entity
//@NamedQuery(name = "Game.findByTitleLikeWhereNotAlreadyInCurrentList",
//        query = "select g from Game g where g.title LIKE ?1 AND g.id NOT IN (SELECT x.id FROM GameList gl JOIN g1.games x WHERE gl.id = ?2)")
@Table( name = "GAME", uniqueConstraints = @UniqueConstraint(name = "pr_game_id", columnNames = {"id"}))
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GM_SEQ")
    @GenericGenerator(name = "GM_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "GM_SEQ"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")}
    )
    private Integer id;

    @Column(name = "TITLE", nullable = false, unique = true)
    @Size(min = 2, max = 100, message = "Size must be between 2 and 100")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLATFORM", nullable = false)
    private Platform platform;

    @Column(name = "DESCRIPTION", length = 4000)
    @Size(min = 2, max = 4000, message = "Size must be between 2 and 4000")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROGRESS", nullable = false)
    private Progress progress;

    @ManyToMany
    @JoinTable(name = "LIST_GAMES",
            inverseJoinColumns = @JoinColumn(name = "LIST_ID", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "id"))
    private List<GameList> gameLists;

    public Game() {
    }

    public Game(String title, Genre genre, Platform platform, String description, Progress progress, List<GameList> gameLists) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.description = description;
        this.progress = progress;
        this.gameLists = gameLists;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {

        this.platform = platform;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {

        return progress ==  Progress.Completed;
    }

    public List<GameList> getGameLists() {
        return gameLists;
    }

    public void setGameLists(List<GameList> gameLists) {
        this.gameLists = gameLists;
    }

    public void addListToGame(GameList l) {
        gameLists.add(l);
    }

    public void removeListFromGame(GameList l) {
        gameLists.remove(l);
    }
}
