package rest;

import classes.ProjectCategory;
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
 * REST interface for creating/updating ProjectCategory Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("project_category")
public class ProjectCategoryResource implements Serializable{
    /**
     * POST method that creates a new Project Category
     *
     * @param pc new Project Category
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProjectCategory(ProjectCategory pc) {

        URI location = URI.create("/category?id=" + pc.getId());  // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + pc.getId()); // delete object
        rb.link(delLocLink, "delete");
        return Response.ok(pc).build();
        //return rb.build();
    }

    /**
     * PUT method that updates a ProjectCategory via the id
     *
     * @param id id of the ProjectCategory
     * @param updatedPC updated ProjectCategory
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProjectCategory(@QueryParam("id") Long id, ProjectCategory updatedPC) {

        ProjectCategory originalPC = updatedPC;
        URI location = URI.create("/category?id=" + originalPC.getId());  // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + originalPC.getId()); //delete object
        rb.link(delLocLink, "delete");

        return Response.ok(originalPC).build();
        //return rb.build();
    }

    /**
     * GET Method to return a specific Project Category
     *
     * @param id id of searched projectCategory
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectCategory(@QueryParam("id") Long id) {
        ProjectCategory pc = new ProjectCategory();
        pc.setId(1l);
        pc.setProjectId(1l);
        pc.setCategoryId(1l);

        Response.ResponseBuilder rb = Response.ok(pc);

        return rb.build();
    }

    /**
     * GET Method to return all ProjectCategories
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> project_category/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectCategories() {
        ArrayList<ProjectCategory> proCategories = new ArrayList();

        ProjectCategory pc1 = new ProjectCategory();
        pc1.setId(1l);
        pc1.setProjectId(1l);
        pc1.setCategoryId(1l);

        ProjectCategory pc2 = new ProjectCategory();
        pc2.setId(2l);
        pc2.setProjectId(4l);
        pc2.setCategoryId(7l);

        proCategories.add(pc1);
        proCategories.add(pc2);

        Response.ResponseBuilder rb = Response.ok(proCategories);

        return rb.build();
    }
}
