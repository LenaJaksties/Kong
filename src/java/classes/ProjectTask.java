package classes;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author lenaj
 */
@XmlRootElement
public class ProjectTask implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private Long taskId;
    private Long expendedWorkingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getExpendedWorkingTime() {
        return expendedWorkingTime;
    }

    public void setExpendedWorkingTime(Long expendedWorkingTime) {
        this.expendedWorkingTime = expendedWorkingTime;
    }
    
    
    
}
