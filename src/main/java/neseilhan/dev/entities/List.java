package neseilhan.dev.entities;

public class List {
    private String id;
    private String name;
    private String idBoard;
    private int pos;

    public List() {}

    public List(String name, String idBoard) {
        this.name = name;
        this.idBoard = idBoard;
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

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
