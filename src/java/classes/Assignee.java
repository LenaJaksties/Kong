package classes;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenaj
 */
@XmlRootElement
public class Assignee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long id;    
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private boolean active;
    private String iconpath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }
    
    
    
}
