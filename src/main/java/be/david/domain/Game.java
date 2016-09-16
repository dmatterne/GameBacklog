package be.david.domain;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

/**
 * Created by David on 16/09/2016.
 */
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GM_ID")
    @SequenceGenerator(name = "GM_ID",sequenceName = "GM_ID", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(name = "TITLE", nullable = false, unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLATFORM", nullable = false)
    private Platform platform;

    @Column(name = "DESCRIPTION")
    public String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROGRESS", nullable = false)
    public Progress progress;

    public Game() {
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
}
