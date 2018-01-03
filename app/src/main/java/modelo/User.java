package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class User {

    /* ATRIBUTOS */
    private int id;
    private String username;
    private String email;

    /* MÉTODOS */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /* CONSTRUTOR */
    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
