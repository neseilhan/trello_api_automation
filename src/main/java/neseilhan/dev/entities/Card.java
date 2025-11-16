package neseilhan.dev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("desc")
    private String description;

    @JsonProperty("closed")
    private boolean closed;

    @JsonProperty("idBoard")
    private String boardId;

    @JsonProperty("idList")
    private String listId;

    @JsonProperty("url")
    private String url;

    @JsonProperty("pos")
    private double position;

    public Card() {}

    public Card(String name) {
        this.name = name;
    }

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Card(String name, String listId, String boardId) {
        this.name = name;
        this.listId = listId;
        this.boardId = boardId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", closed=" + closed +
                ", boardId='" + boardId + '\'' +
                ", listId='" + listId + '\'' +
                ", url='" + url + '\'' +
                ", position=" + position +
                '}';
    }
}
