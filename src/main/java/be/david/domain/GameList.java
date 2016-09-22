package be.david.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Parameter;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by David on 19/09/2016.
 */
@Entity
@Table(name = "GAMELIST", uniqueConstraints = @UniqueConstraint(name = "pk_gamelist", columnNames = {"id"}))
public class GameList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GL_SEQ")
    @GenericGenerator(name = "GL_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GL_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")}
    )
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", unique = false, nullable = true)
    private String description;

    @ManyToMany(mappedBy = "gameLists")
    private List<Game> games;

//    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    @Transient
    private int amount;

    public GameList() {
    }

    public GameList(String name, List<Game> games, LocalDate creationDate, int amount, String description) {
        this.name = name;
        this.games = games;
        this.creationDate = creationDate;
        this.amount = amount;
        this.description = description;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addGameToList(Game g) {
        games.add(g);
    }

    public void removeGameFromList(Game g) {
        games.remove(g);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
