package classes;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Representation of a work task
 * @author lenaj, lisaj
 */
@XmlRootElement
@Entity
@Table(name="tbl_Task")
@NamedQueries({
    @NamedQuery( name="task.findAll",
            query="SELECT t FROM Task t"),
    @NamedQuery(  name="task.findByTitle",
            query="SELECT t FROM Task t WHERE t.title =:title")
})
public class Task implements Serializable{
    
    public Task(){
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
    @Column(name = "category")
    private Category category;
    @Column(name = "scheduledWorkingTime")
    private long scheduledWorkingTime;
    @Column(name = "deadline")
    private LocalDateTime deadline;
    @Column(name = "achieved")
    private boolean achieved;
    
        
    @OneToMany(mappedBy = "task")
    @JsonbTransient
    Set<ProjectTask> projecttask;
    
    public Set<ProjectTask> getProjects() {
        return projecttask;
    }
    
    public void setProjects(ProjectTask pt) {
        this.projecttask.add(pt) ;
        pt.setTask(this);
    }
    
    public void setProjects(Set<ProjectTask> pt) {
        
        this.projecttask.addAll(pt) ;
        Iterator<ProjectTask> iterator = pt.iterator();
        while(iterator.hasNext()){
            ProjectTask a = iterator.next();
            a.setTask(this);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getScheduledWorkingTime() {
        return scheduledWorkingTime;
    }

    public void setScheduledWorkingTime(long scheduledWorkingTime) {
        this.scheduledWorkingTime = scheduledWorkingTime;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
    
    
    
}
