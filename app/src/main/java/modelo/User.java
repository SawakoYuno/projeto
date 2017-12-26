package modelo;

/**
 * Created by Utilizador on 26/12/2017.
 */

public class User {

    /* ATRIBUTOS */
    private int id;
    private String username;
    private String email;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /* CONSTRUTOR */
    public User(int id, String username, String email, int status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
    }
}
