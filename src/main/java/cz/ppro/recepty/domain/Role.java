<<<<<<< HEAD
package cz.ppro.recepty.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by tynak_000 on 26/01/2016.
 */
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROLE")
    private Long idUser;

    @Column(name = "ROLE")  // USER, ADMIN
    private String username;


    public Role() {
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

    public Role(String role) {

    }
}
 * Uzivatelske role
 * 
 * <p>
 * <b>ROLE_USER</b> - Bezna uzivatelska role. Umoznuje pridavat recepty,
 * znamkovat je a komentovat. <br/>
 * <b>ROLE_ADMIN</b> - Administratorska role. Umozuje spravovat aplikaci
 * 
 * @author Katerina Hanusova
 *
 */

	ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

	

}
