package rest;

import classes.ProjectTask;
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
public class ProjectTaskResource  implements Serializable {
    
    /**
     * POST method that creates a new Project Task
     *
     * @param pt new Project Task
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProjectTask(ProjectTask pt) {

        URI location = URI.create("/category?id=" + pt.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + pt.getId()); // delete object
        rb.link(delLocLink, "delete");
        return Response.ok(pt).build();
        //return rb.build();
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
        ProjectTask pt = new ProjectTask();
        pt.setId(1l);
        pt.setProjectId(1l);
        pt.setTaskId(1l);
        pt.setExpendedWorkingTime(400l);

        Response.ResponseBuilder rb = Response.ok(pt);

        return rb.build();
    }

    /**
     * GET Method to return all Project_Tasks
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> project_task/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectTasks() {
        ArrayList<ProjectTask> proTasks = new ArrayList();

        ProjectTask pt1 = new ProjectTask();
        pt1.setId(1l);
        pt1.setProjectId(1l);
        pt1.setTaskId(1l);
        pt1.setExpendedWorkingTime(6000l);

        ProjectTask pt2 = new ProjectTask();
        pt2.setId(2l);
        pt2.setProjectId(4l);
        pt2.setTaskId(7l);
        pt2.setExpendedWorkingTime(900000l);

        proTasks.add(pt1);
        proTasks.add(pt2);

        Response.ResponseBuilder rb = Response.ok(proTasks);

        return rb.build();
    }
    
}
