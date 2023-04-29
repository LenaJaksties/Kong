package rest;

import classes.TaskAssignee;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;

/**
 * REST interface for creating/updating TaskAssignee Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("task_assignee")
public class TaskAssigneeResource {
    
     /**
     * POST method that creates a new Task Assignee
     *
     * @param ta new Task Assignee
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTaskAssignee(TaskAssignee ta) {

        URI location = URI.create("/category?id=" + ta.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + ta.getId()); // delete object
        rb.link(delLocLink, "delete");
        return Response.ok(ta).build();
        //return rb.build();
    }

    /**
     * PUT method that updates a TaskAssignee via the id
     *
     * @param id id of the TaskAssignee
     * @param updatedTA updated TaskAssignee
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTaskAssignee(@QueryParam("id") Long id, TaskAssignee updatedTA) {

        TaskAssignee originalTA = updatedTA;
        URI location = URI.create("/category?id=" + originalTA.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + originalTA.getId());  // delete object
        rb.link(delLocLink, "delete");

        return Response.ok(originalTA).build();
        //return rb.build();
    }

    /**
     * GET Method to return a specific Task Assignee
     *
     * @param id id of searched taskAssignee
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskAssignee(@QueryParam("id") Long id) {
        TaskAssignee ta = new TaskAssignee();
        ta.setId(1l);
        ta.setTaskId(1l);
        ta.setAssigneeId(1l);

        Response.ResponseBuilder rb = Response.ok(ta);

        return rb.build();
    }

    /**
     * GET Method to return all Task_Assignees
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> task_assignee/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTaskAssignees() {
        ArrayList<TaskAssignee> taskAssignees = new ArrayList();

        TaskAssignee ta1 = new TaskAssignee();
        ta1.setId(1l);
        ta1.setTaskId(1l);
        ta1.setAssigneeId(1l);

        TaskAssignee ta2 = new TaskAssignee();
        ta2.setId(2l);
        ta2.setTaskId(4l);
        ta2.setAssigneeId(7l);

        taskAssignees.add(ta1);
        taskAssignees.add(ta2);

        Response.ResponseBuilder rb = Response.ok(taskAssignees);

        return rb.build();
    }
    
}
