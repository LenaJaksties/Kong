package classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a work task category
 * @version 1.0
 * @author lenaj, lisaj
 */
@XmlRootElement
@Entity
@Table(name="tbl_Category")
@NamedQueries({
    @NamedQuery( name="category.findAll",
            query="SELECT c FROM Category c"),
    @NamedQuery(  name="category.findByTitle",
            query="SELECT c FROM Category c WHERE c.title =:title")
})
public class Category implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Column(name = "title")
    private String title;
    @Column(name = "summary")
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
