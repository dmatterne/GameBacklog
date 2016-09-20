package be.david.domain;

/**
 * Created by David on 20/09/2016.
 */
public class Search {

    private String id;

    private String name;

    private Integer gameListId;

    public Search() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGameListId() {
        return gameListId;
    }

    public void setGameListId(Integer gameListId) {
        this.gameListId = gameListId;
    }
}
