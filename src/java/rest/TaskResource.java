
package rest;
import classes.Category;
import classes.Project;
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
 * REST interface for creating/updating Task Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("task")
public class TaskResource implements Serializable{
    
    
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
    
    @Resource
    private UserTransaction utx;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("categoryId") Long categoryId, TaskAdapter ta) {

        try{
            this.utx.begin();
            Category cat = this.em.find(Category.class, categoryId);
            this.utx.commit();
            
            
            this.utx.begin();
            // Useing adapter to create a persistable object
            Task task= ta.toProject();
            task.setCategory(cat);
            this.em.persist(task);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();
            
            URI location = URI.create("/project?id=" + task.getId());  // retrieve object
            ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/project/delete?id=" + task.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(task).build();
            //return rb.build();
        
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

      
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
        try{
            this.utx.begin();
            Task taskUpdate = this.em.find(Task.class, id);
            // Useing adapter to create a persistable object
            Task taskInfo= updatedT.toProject();
            taskUpdate.setTitle(taskInfo.getTitle());
            taskUpdate.setSummary(taskInfo.getSummary());
            taskUpdate.setCategory(taskInfo.getCategory());
            taskUpdate.setDeadline(taskInfo.getDeadline());
            taskUpdate.setScheduledWorkingTime(taskInfo.getScheduledWorkingTime());
            //projectUpdate.setAssignees(projInfo.getAssignees());
            this.em.persist(taskUpdate);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();
            
            URI location = URI.create("/project?id=" + taskUpdate.getId());  // retrieve object
            ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/project/delete?id=" + taskUpdate.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(taskUpdate).build();
        
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        
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
        
        Task task = this.em.find(Task.class, id);
        
        
        ResponseBuilder rb = Response.ok(task);
        return rb.build();
    }
    /**
     * GET Method to return a specific Task
     *
     * @param title of searched Task
     * @return ResponseBuilder
     */
    @GET
    @Path("title")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssignee(@QueryParam("title") String name) {
        
        TypedQuery<Task> query = em.createNamedQuery("task.findByTitle", Task.class);
        query.setParameter("title", name); // Set the value of the lastName parameter

        List<Task> taskList = query.getResultList();
   
        Response.ResponseBuilder rb = Response.ok(taskList);

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
        
        Query list = this.em.createNamedQuery("task.findAll", Task.class);
        
        List<Object> taskList = new ArrayList<>();
        taskList = list.getResultList();
        
        Response.ResponseBuilder rb = Response.ok(taskList);
        
//        ArrayList<Task> tasks = new ArrayList();
//    
//        TaskAdapter ta1 = new TaskAdapter();
//        ta1.setDeadline("2023-05-27T00:00:00");
//        
//        Category cat = new Category();
//        cat.setId(1l);
//        cat.setTitle("Catwork");
//        cat.setSummary("Task Related to work with cat talents");
//        
//        Task task1 = ta1.toProject();
//        
//        task1.setId(2l);
//        task1.setTitle("Feed Cat");
//        task1.setCategory(cat);
//        task1.setScheduledWorkingTime(10);
//        task1.setSummary("Feed a cat every day");
//        task1.setAchieved(false);
//    
//    
//        
//        TaskAdapter ta2 = new TaskAdapter();
//        ta2.setDeadline("2029-09-20T00:00:00");
//        
//        Category cat2 = new Category();
//        cat2.setId(90l);
//        cat2.setTitle("Language: Korean/English");
//        cat2.setSummary("Tasks related to languages");
//      
//        
//        Task task2 = ta2.toProject();
//        
//        task2.setId(2l);
//        task2.setTitle("Translate a book");
//        task2.setCategory(cat2);
//        task2.setScheduledWorkingTime(900);
//        task2.setSummary("The book needs to be translated from Korean to English");
//        task2.setAchieved(false);
//        
//        tasks.add(task1);
//        tasks.add(task2);
//        
//        Response.ResponseBuilder rb = Response.ok(tasks);

        return rb.build();
        
    }
    
    
}
