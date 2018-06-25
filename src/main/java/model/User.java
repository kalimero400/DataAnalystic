package model;

/**
 * Created by arthur on 16.06.18.
 */
public class User {

    private Integer id;
    private String name;
    private int platform;

    public User(Integer id, String name, int platform) {
        this.id = id;
        this.name = name;
        this.platform = platform;
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

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }


}
