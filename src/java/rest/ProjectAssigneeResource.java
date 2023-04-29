package rest;

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

/**
 * REST interface for creating/updating ProjectAssignee Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("project_assignee")
public class ProjectAssigneeResource implements Serializable {

    /**
     * POST method that creates a new Project Assignee
     *
     * @param pa new Project Assignee
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProjectAssignee(ProjectAssignee pa) {

        URI location = URI.create("/category?id=" + pa.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + pa.getId()); // delete object
        rb.link(delLocLink, "delete");
        return Response.ok(pa).build();
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

        ProjectAssignee originalPA = updatedPA;
        URI location = URI.create("/category?id=" + originalPA.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + originalPA.getId());  // delete object
        rb.link(delLocLink, "delete");

        return Response.ok(originalPA).build();
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
        ProjectAssignee pa = new ProjectAssignee();
        pa.setId(1l);
        pa.setProjectId(1l);
        pa.setAssigneeId(1l);

        Response.ResponseBuilder rb = Response.ok(pa);

        return rb.build();
    }

    /**
     * GET Method to return all Project_Assignees
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> project_assignee/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectAssignees() {
        ArrayList<ProjectAssignee> proAssignees = new ArrayList();

        ProjectAssignee pa1 = new ProjectAssignee();
        pa1.setId(1l);
        pa1.setProjectId(1l);
        pa1.setAssigneeId(1l);

        ProjectAssignee pa2 = new ProjectAssignee();
        pa2.setId(2l);
        pa2.setProjectId(4l);
        pa2.setAssigneeId(7l);

        proAssignees.add(pa1);
        proAssignees.add(pa2);

        Response.ResponseBuilder rb = Response.ok(proAssignees);

        return rb.build();
    }

}
