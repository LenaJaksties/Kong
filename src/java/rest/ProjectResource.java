package rest;

import classes.Assignee;
import classes.Project;
import java.io.Serializable;
import java.net.URI;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


/**
 * REST interface for creating/updating Project Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */

@Path("project") // path of resource

public class ProjectResource implements Serializable {
    
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
    
    @Resource
    private UserTransaction utx;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(ProjectAdapter pa){
        
        try{
            this.utx.begin();
            // Useing adapter to create a persistable object
            Project proj= pa.toProject();
            this.em.persist(proj);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();
            
            URI location = URI.create("/project?id=" + proj.getId());  // retrieve object
            ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/project/delete?id=" + proj.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(proj).build();
            //return rb.build();
        
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
       
    }
    
    /**
     * PUT method that updates a Project via the id
     *
     * @param id id of the Project
     * @param updatedPA updated Project
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("id")String id, ProjectAdapter updatedPA){
       
        try{
            this.utx.begin();
            Project projectUpdate = this.em.find(Project.class, id);
            // Useing adapter to create a persistable object
            Project projInfo= updatedPA.toProject();
            projectUpdate.setTitle(projInfo.getTitle());
            projectUpdate.setSummary(projInfo.getSummary());
            projectUpdate.setStatus(projInfo.getStatus());
            projectUpdate.setLogopath(projInfo.getLogopath());
            projectUpdate.setStartDate(projInfo.getStartDate());
            projectUpdate.setDeadline(projInfo.getDeadline());
            projectUpdate.setAssignees(projInfo.getAssignees());
            this.em.persist(projectUpdate);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();
            
            URI location = URI.create("/project?id=" + projectUpdate.getId());  // retrieve object
            ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/project/delete?id=" + projectUpdate.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(projectUpdate).build();
        
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        
    }
    
    /**
     * GET Method to return a specific Project
     *
     * @param id id of searched project
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id){
        
        Project proj = this.em.find(Project.class, id); // holt JPA Object mit id = ? aus DB
        
        ResponseBuilder rb = Response.ok(proj);
        return rb.build();
    }
    
    /**
     * GET Method to return a specific Project
     *
     * @param title of searched Project
     * @return ResponseBuilder
     */
    @GET
    @Path("title")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssignee(@QueryParam("title") String name) {
        
        TypedQuery<Project> query = em.createNamedQuery("project.findByTitle", Project.class);
        query.setParameter("title", name); // Set the value of the lastName parameter

        List<Project> projectList = query.getResultList();
   
        Response.ResponseBuilder rb = Response.ok(projectList);

        return rb.build();
    }
    
    
    /**
     * GET Method to return all Project_Assignees
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> project/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectAssignees() {
        Query list = this.em.createNamedQuery("project.findAll", Project.class);
        
        List<Object> projectList = new ArrayList<>();
        projectList = list.getResultList();
        
        Response.ResponseBuilder rb = Response.ok(projectList);

        return rb.build();
    }
}
