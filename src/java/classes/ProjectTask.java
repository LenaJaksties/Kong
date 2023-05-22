package classes;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author lenaj
 */
@XmlRootElement
@Entity
@Table(name="tbl_ProjectTask")
public class ProjectTask implements Serializable{
    
    public ProjectTask(){
        this.project = new Project();
        this.task = new Task();
        
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
    @JoinColumn(name = "taskId")
    Task task;

    
//    @Column(name = "projectId")
//    private Long projectId;
//    @Column(name = "taskId")
//    private Long taskId;
    
    
    @Column(name = "expendedWorkingTime")
    private Long expendedWorkingTime;

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
//    public Long getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(Long projectId) {
//        this.projectId = projectId;
//    }
//
//    public Long getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(Long taskId) {
//        this.taskId = taskId;
//    }

    public Long getExpendedWorkingTime() {
        return expendedWorkingTime;
    }

    public void setExpendedWorkingTime(Long expendedWorkingTime) {
        this.expendedWorkingTime = expendedWorkingTime;
    }
    
    
    
}
