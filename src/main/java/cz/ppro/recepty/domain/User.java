package cz.ppro.recepty.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

import cz.ppro.recepty.service.UserService;

import java.util.List;

/**
 * Reprezentace registrovaneho uzivatele
 */
@Entity(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;

    @Size(min = 5, max = 20)
    @Column(name = "USER_USERNAME")
    private String username;

    @Size(min = 5, max = 20)
    @Column(name = "USER_NAME")
    private String name;

    @Size(min = 5, max = 20)
    @Column(name = "USER_SURNAME")
    private String surname;

    @Size(min = 8)
    @Column(name = "USER_PASSWORD")
    private String password;

    private String email;

    @Column(name = "USER_RATING")
    private float rating;

    @ElementCollection
    @CollectionTable(name = "RECIPE", joinColumns = { @JoinColumn(name = "ID_RECIPE") })
    @Column(name = "USER_RECIPE")
    private List<String> recipes;

    //@O
    //@Table(name = "ROLE", joinColumns = { @JoinColumn(name = "ID_ROLE") })
    //@Column(name = "USER_ROLE")
    private String role;



    public User() {
    }

    public User(String username, String name, String surname, String password, String email, float rating, String role, List<String> recipes) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.rating = rating;
        this.role = role;
        this.recipes = recipes;
    }


    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String username, String name, String surname, String password, String email, String role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        // TODO: implements this shit

        return hashcode;
    }

}
