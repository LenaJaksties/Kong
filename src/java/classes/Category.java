package classes;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a work task category
 * @version 1.0
 * @author lenaj, lisaj
 */
@XmlRootElement
public class Category implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long id;    
    private String title;
    private String summary;

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
    
    
}
