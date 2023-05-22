package rest;

import classes.Assignee;
import classes.Project;
import classes.ProjectAssignee;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.annotation.Resource;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import jakarta.persistence.Query;

/**
 * REST interface for creating/updating ProjectAssignee Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("project_assignee")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProjectAssigneeResource implements Serializable {

    
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
        
    @Resource
    private UserTransaction utx;

    /**
     * POST method that creates a new Project Assignee
     *
     * @param pa new Project Assignee
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProjectAssignee(@QueryParam("projectId") Long projectId, @QueryParam("assigneeId") Long assigneeId) {
        
        try{
            this.utx.begin();
            
            Assignee a = this.em.find(Assignee.class, assigneeId);
            
            this.utx.commit();
            this.utx.begin();
            Project p = this.em.find(Project.class, projectId);
            
            this.utx.commit();
            this.utx.begin();
            ProjectAssignee pa = new ProjectAssignee();
            pa.setAssignee(a);
            pa.setProject(p);
            
            this.em.persist(pa);          // em saves to table tbl_ProjectAssignee
            this.utx.commit();

            URI location = URI.create("/category?id=" + pa.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/category/delete?id=" + pa.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(pa).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

//        URI location = URI.create("/category?id=" + pa.getId());   // retrieve object
//        Response.ResponseBuilder rb = Response.created(location);
//        // Example for createing a HATEOAS link
//        URI delLocLink = URI.create("/category/delete?id=" + pa.getId()); // delete object
//        rb.link(delLocLink, "delete");
//        return Response.ok(pa).build();
        //return rb.build();
    }

    /**
     * PUT method that updates a ProjectAssignee via the id
     *
     * @param id id of the ProjectAssignee
     * @param updatedPA updated ProjectAssignee
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectAssignee(@QueryParam("id") Long id, ProjectAssignee updatedPA) {
        
        
        try{
            this.utx.begin();
            ProjectAssignee projectAssigneeUpdate = this.em.find(ProjectAssignee.class, id);
            // Useing adapter to create a persistable object
            ProjectAssignee projInfo= updatedPA;
            projectAssigneeUpdate.setProject(projInfo.getProject());
            projectAssigneeUpdate.setAssignee(projInfo.getAssignee());
         
            this.em.persist(projectAssigneeUpdate);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();

            URI location = URI.create("/category?id=" + projectAssigneeUpdate.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/category/delete?id=" + projectAssigneeUpdate.getId());  // delete object
            rb.link(delLocLink, "delete");

            return Response.ok(projectAssigneeUpdate).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
       

//        ProjectAssignee originalPA = updatedPA;
//        URI location = URI.create("/category?id=" + originalPA.getId());   // retrieve object
//        Response.ResponseBuilder rb = Response.created(location);
//        // Example for createing a HATEOAS link
//        URI delLocLink = URI.create("/category/delete?id=" + originalPA.getId());  // delete object
//        rb.link(delLocLink, "delete");
//
//        return Response.ok(originalPA).build();
        //return rb.build();
    }

    /**
     * GET Method to return a specific Project Assignee
     *
     * @param id id of searched projectAssignee
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectAssignee(@QueryParam("id") Long id) {
        ProjectAssignee pa = this.em.find(ProjectAssignee.class, id);
        
//        pa.setId(1l);
//        pa.setProjectId(1l);
//        pa.setAssigneeId(1l);

        Response.ResponseBuilder rb = Response.ok(pa);

        return rb.build();
    }

//    /**
//     * GET Method to return all Project_Assignees
//     *
//     * @return ResponseBuilder
//     */
//    @GET
//    @Path("All") // -> project_assignee/All
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllProjectAssignees() {
//        
//        Query list = this.em.createNamedQuery("projectAssignee.findAll", ProjectAssignee.class);
//
//        List<Object> projectAssigneeList = new ArrayList<>();
//        projectAssigneeList = list.getResultList();
//        
////        ArrayList<ProjectAssignee> proAssignees = new ArrayList();
////
////        ProjectAssignee pa1 = new ProjectAssignee();
////        pa1.setId(1l);
////        pa1.setProjectId(1l);
////        pa1.setAssigneeId(1l);
////
////        ProjectAssignee pa2 = new ProjectAssignee();
////        pa2.setId(2l);
////        pa2.setProjectId(4l);
////        pa2.setAssigneeId(7l);
////
////        proAssignees.add(pa1);
////        proAssignees.add(pa2);
//
//        Response.ResponseBuilder rb = Response.ok(projectAssigneeList);
//
//        return rb.build();
//    }

}
