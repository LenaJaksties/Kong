
package rest;
import classes.Category;
import classes.Task;
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


/**
 * REST interface for creating/updating Task Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("task")
public class TaskResource implements Serializable{
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TaskAdapter ta) {

            Task task = ta.toProject();
            URI location = URI.create("/projekt?id=" + task.getId()); // retrieve object
            ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/projekt/delete?id=" + task.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(task).build();
            //return rb.build();
      
    }
    
    /**
     * PUT method that updates a Task via the id
     *
     * @param id id of the task
     * @param updatedT updated task
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("id")String id, TaskAdapter updatedT){
       
        Task originalT = updatedT.toProject();
        URI location = URI.create("/project?id=" + originalT.getId());  // retrieve object
        ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/project/delete?id=" + originalT.getId()); // delete object
        rb.link(delLocLink, "delete");
        return Response.ok(originalT).build();
         //return rb.build();
    }
    
    
    /**
     * GET Method to return a specific Task
     *
     * @param id id of searched task
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id) {
        
        TaskAdapter ta = new TaskAdapter();
        ta.setDeadline("2023-05-27T00:00:00");
        
        Category cat = new Category();
        cat.setId(1l);
        cat.setTitle("Catwork");
        cat.setSummary("Task Related to work with cat talents");
        
        Task task = ta.toProject();
        
        task.setId(2l);
        task.setTitle("Feed Cat");
        task.setCategory(cat);
        task.setScheduledWorkingTime(200);
        task.setSummary("Feed a cat every day");
        task.setAchieved(false);
        
        ResponseBuilder rb = Response.ok(task);
        return rb.build();
    }
    
    /**
     * GET Method to return all Tasks
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> task/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectAssignees() {
        ArrayList<Task> tasks = new ArrayList();
    
        TaskAdapter ta1 = new TaskAdapter();
        ta1.setDeadline("2023-05-27T00:00:00");
        
        Category cat = new Category();
        cat.setId(1l);
        cat.setTitle("Catwork");
        cat.setSummary("Task Related to work with cat talents");
        
        Task task1 = ta1.toProject();
        
        task1.setId(2l);
        task1.setTitle("Feed Cat");
        task1.setCategory(cat);
        task1.setScheduledWorkingTime(10);
        task1.setSummary("Feed a cat every day");
        task1.setAchieved(false);
    
    
        
        TaskAdapter ta2 = new TaskAdapter();
        ta2.setDeadline("2029-09-20T00:00:00");
        
        Category cat2 = new Category();
        cat2.setId(90l);
        cat2.setTitle("Language: Korean/English");
        cat2.setSummary("Tasks related to languages");
      
        
        Task task2 = ta2.toProject();
        
        task2.setId(2l);
        task2.setTitle("Translate a book");
        task2.setCategory(cat2);
        task2.setScheduledWorkingTime(900);
        task2.setSummary("The book needs to be translated from Korean to English");
        task2.setAchieved(false);
        
        tasks.add(task1);
        tasks.add(task2);
        
        Response.ResponseBuilder rb = Response.ok(tasks);

        return rb.build();
        
    }
    
    
}
