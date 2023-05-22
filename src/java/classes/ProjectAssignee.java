package classes;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author lenaj
 */
@XmlRootElement
@Entity
@Table(name="tbl_ProjectAssignee")
public class ProjectAssignee implements Serializable{
    
    public ProjectAssignee(){
        this.project = new Project();
        this.assignee = new Assignee();
    }
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JsonbTransient
    @JoinColumn(name = "projectId")
    Project project;
    
    @ManyToOne
    @JsonbTransient
    @JoinColumn(name = "assigneeId")
    Assignee assignee;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
 
    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }
    
    
    
}
