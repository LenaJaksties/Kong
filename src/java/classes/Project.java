package classes;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Representation of a project
 * @version 2.0
 * @author lenaj, lisaj
 */
@XmlRootElement
@Entity
@Table(name="tbl_Project")
@NamedQueries({
    @NamedQuery( name="project.findAll",
            query="SELECT p FROM Project p"),
    @NamedQuery(  name="project.findByTitle",
            query="SELECT p FROM Project p WHERE p.title =:title")
})
public class Project implements Serializable {
    
    public Project(){
        projectassignee = new HashSet<>();
        projecttask = new HashSet<>();
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "summary")
    private String summary;
    @Column(name = "logopath")
    private String logopath;
    @Column(name = "startDate")
    private LocalDateTime startDate;
    @Column(name = "deadline")
    private LocalDateTime deadline;
    // current workstatus in percent (MAX 100)
    @Column(name = "status")
    private int status;
    
    @OneToMany(mappedBy = "project")
    @JsonbTransient
    Set<ProjectAssignee> projectassignee;
    
    public Set<ProjectAssignee> getAssignees() {
        return projectassignee;
    }
    
    public void setAssignees(ProjectAssignee pa) {
        this.projectassignee.add(pa) ;
        pa.setProject(this);
    }
    
    public void setAssignees(Set<ProjectAssignee> pa) {
        
        this.projectassignee.addAll(pa) ;
        Iterator<ProjectAssignee> iterator = pa.iterator();
        while(iterator.hasNext()){
            ProjectAssignee a = iterator.next();
            a.setProject(this);
        }
        
        
    }
    
    @OneToMany(mappedBy = "project")
    @JsonbTransient
    Set<ProjectTask> projecttask;
    
    public Set<ProjectTask> getTasks() {
        return projecttask;
    }
    
    public void setTasks(ProjectTask pt) {
        this.projecttask.add(pt) ;
        pt.setProject(this);
    }
    
    public void setTasks(Set<ProjectTask> pt) {
        
        this.projecttask.addAll(pt) ;
        Iterator<ProjectTask> iterator = pt.iterator();
        while(iterator.hasNext()){
            ProjectTask a = iterator.next();
            a.setProject(this);
        }
        
        
    }
    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    
}