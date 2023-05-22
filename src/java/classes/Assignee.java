package classes;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Set;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author lenaj
 */
@XmlRootElement
@Entity
@Table(name="tbl_Assignee")
@NamedQueries({
    @NamedQuery( name="assignee.findAll",
            query="SELECT a FROM Assignee a"),
    @NamedQuery(  name="assignee.findByLastname",
            query="SELECT a FROM Assignee a WHERE a.lastName = :lastName ")
})
public class Assignee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "department")
    private String department;
    @Column(name = "email")
    private String email;
    @Column(name = "active")
    private boolean active;
    @Column(name = "iconpath")
    private String iconpath;
    
    @OneToMany(mappedBy = "assignee")
    @JsonbTransient
    Set<ProjectAssignee> projectassignee;
    
    public Set<ProjectAssignee> getProjectAssignees() {
        return projectassignee;
    }
    
    public void setProjectAssignee(ProjectAssignee pa) {
        this.projectassignee.add(pa) ;
    }
    
    public void setProjectAssignees(Set<ProjectAssignee> pa) {
        
        this.projectassignee.addAll(pa) ;
        
    }

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
