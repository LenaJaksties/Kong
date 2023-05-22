package rest;

import classes.Assignee;
import classes.Project;
import classes.ProjectAssignee;
import classes.ProjectTask;
import classes.Task;
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

/**
 * REST interface for creating/updating ProjectTask Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("project_task")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProjectTaskResource  implements Serializable {
    
        
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
        
    @Resource
    private UserTransaction utx;
    
    /**
     * POST method that creates a new Project Task
     *
     * @param pt new Project Task
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProjectTask(@QueryParam("projectId") Long projectId, @QueryParam("taskId") Long taskId, ProjectTask pt) {

       
        try{
            this.utx.begin();
            
            Task t = this.em.find(Task.class, taskId);
            
            this.utx.commit();
            this.utx.begin();
            Project p = this.em.find(Project.class, projectId);
            
            this.utx.commit();
            this.utx.begin();

            pt.setTask(t);
            pt.setProject(p);
            
            this.em.persist(pt);          // em saves to table tbl_ProjectAssignee
            this.utx.commit();

            URI location = URI.create("/category?id=" + pt.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/category/delete?id=" + pt.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(pt).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * PUT method that updates a ProjectTask via the id
     *
     * @param id id of the ProjectTask
     * @param updatedPT updated ProjectTask
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectTask(@QueryParam("id") Long id, ProjectTask updatedPT) {

        ProjectTask originalPT = updatedPT;
        URI location = URI.create("/category?id=" + originalPT.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + originalPT.getId());  // delete object
        rb.link(delLocLink, "delete");

        return Response.ok(originalPT).build();
        //return rb.build();
    }

    /**
     * GET Method to return a specific Project Task
     *
     * @param id id of searched projectTask
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectTask(@QueryParam("id") Long id) {
        ProjectTask pt = this.em.find(ProjectTask.class, id);

        Response.ResponseBuilder rb = Response.ok(pt);

        return rb.build();
    }

//    /**
//     * GET Method to return all Project_Tasks
//     *
//     * @return ResponseBuilder
//     */
//    @GET
//    @Path("All") // -> project_task/All
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllProjectTasks() {
//        ArrayList<ProjectTask> proTasks = new ArrayList();
//
//        ProjectTask pt1 = new ProjectTask();
//        pt1.setId(1l);
//        pt1.setProjectId(1l);
//        pt1.setTaskId(1l);
//        pt1.setExpendedWorkingTime(6000l);
//
//        ProjectTask pt2 = new ProjectTask();
//        pt2.setId(2l);
//        pt2.setProjectId(4l);
//        pt2.setTaskId(7l);
//        pt2.setExpendedWorkingTime(900000l);
//
//        proTasks.add(pt1);
//        proTasks.add(pt2);
//
//        Response.ResponseBuilder rb = Response.ok(proTasks);
//
//        return rb.build();
//    }
    
}
