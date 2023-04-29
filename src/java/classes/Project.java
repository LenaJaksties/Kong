package classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a project
 * @version 1.0
 * @author lenaj, lisaj
 */
@XmlRootElement
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String summary;
    private String logopath;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
    // current workstatus in percent (MAX 100)
    private int status;
    
    
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