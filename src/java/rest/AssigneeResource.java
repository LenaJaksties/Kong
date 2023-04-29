
package rest;


import classes.Assignee;
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
 * REST interface for creating/updating Assignee Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("assignee")
public class AssigneeResource implements Serializable {
    
    
    /**
     * POST method that creates a new Assignee
     *
     * @param a new Assignee
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAssignee(Assignee a) {

        URI location = URI.create("/category?id=" + a.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + a.getId()); // delete object
        rb.link(delLocLink, "delete");
        return Response.ok(a).build();
        //return rb.build();
    }

    /**
     * PUT method that updates a Assignee via the id
     *
     * @param id id of the Assignee
     * @param updatedA updated Assignee
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAssignee(@QueryParam("id") Long id, Assignee updatedA) {

        Assignee originalA = updatedA;
        URI location = URI.create("/category?id=" + originalA.getId());   // retrieve object
        Response.ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/category/delete?id=" + originalA.getId());  // delete object
        rb.link(delLocLink, "delete");

        return Response.ok(originalA).build();
        //return rb.build();
    }

    /**
     * GET Method to return a specific Assignee
     *
     * @param id id of searched Assignee
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssignee(@QueryParam("id") Long id) {
        Assignee a = new Assignee();
        a.setId(1l);
        a.setActive(true);
        a.setDepartment("software-engineering");
        a.setEmail("lena.j@gmail.com");
        a.setFirstName("Lena");
        a.setIconpath("/bin/assistantlogo.png");
        a.setLastName("J");
        

        Response.ResponseBuilder rb = Response.ok(a);

        return rb.build();
    }

    /**
     * GET Method to return all Assignees
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> assignee/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAssignees() {
        ArrayList<Assignee> assignees = new ArrayList();

        Assignee a1 = new Assignee();
        a1.setId(1l);
        a1.setActive(true);
        a1.setDepartment("software-engineering");
        a1.setEmail("lena.j@gmail.com");
        a1.setFirstName("Lena");
        a1.setIconpath("/bin/assistantlogo.png");
        a1.setLastName("J");

        
        Assignee a2 = new Assignee();
        a2.setId(2l);
        a2.setActive(true);
        a2.setDepartment("software-engineering");
        a2.setEmail("lisa.j@gmail.com");
        a2.setFirstName("Lisa");
        a2.setIconpath("/bin/assistantlogo.png");
        a2.setLastName("J");
      

        assignees.add(a1);
        assignees.add(a2);

        Response.ResponseBuilder rb = Response.ok(assignees);

        return rb.build();
    }
}
