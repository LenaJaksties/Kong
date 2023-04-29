package rest;

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



/**
 * REST interface for creating/updating Project Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("project") // path of resource

public class ProjectResource implements Serializable {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(ProjectAdapter pa){
       
            Project proj= pa.toProject();
            URI location = URI.create("/project?id=" + proj.getId());  // retrieve object
            ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/project/delete?id=" + proj.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(proj).build();
            //return rb.build();
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
       
        Project originalP = updatedPA.toProject();
        URI location = URI.create("/project?id=" + originalP.getId());  // retrieve object
        ResponseBuilder rb = Response.created(location);
        // Example for createing a HATEOAS link
        URI delLocLink = URI.create("/project/delete?id=" + originalP.getId()); // delete object
        rb.link(delLocLink, "delete");
        return Response.ok(originalP).build();
         //return rb.build();
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
        
        ProjectAdapter pa = new ProjectAdapter();
        pa.setDeadline("2023-06-27T00:00:00");
        pa.setStartDate("2023-04-30T00:00:00");
        
        Project proj = pa.toProject();
        proj.setId(1l);
        proj.setTitle("Web Application Setup");
        proj.setLogopath("/bin/templogo.png");
        proj.setStatus(0);
        proj.setSummary("This project focuses on creating a web application that helps with organizing tasks.");
        
        ResponseBuilder rb = Response.ok(proj);
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
        ArrayList<Project> projects = new ArrayList();
        
        
        ProjectAdapter pa1 = new ProjectAdapter();
        pa1.setDeadline("2023-06-27T00:00:00");
        pa1.setStartDate("2023-04-30T00:00:00");
        
        Project proj1 = pa1.toProject();
        proj1.setId(1l);
        proj1.setTitle("Web Application Setup");
        proj1.setLogopath("/bin/templogo.png");
        proj1.setStatus(0);
        proj1.setSummary("This project focuses on creating a web application that helps with organizing tasks.");
        
        
        ProjectAdapter pa2 = new ProjectAdapter();
        pa2.setDeadline("2023-05-14T03:09:00");
        pa2.setStartDate("2023-05-19T07:00:59");
        
        Project proj2 = pa2.toProject();
        proj2.setId(2l);
        proj2.setTitle("Online Voting System");
        proj2.setLogopath("/bin/templogo.png");
        proj2.setStatus(10);
        proj2.setSummary("The project aims to develop an online voting system for small to medium-sized organizations or communities. The system will enable authorized users to log in and vote on various topics, such as electing officials or making decisions on community issues.");
        
        projects.add(proj1);
        projects.add(proj2);
        
        Response.ResponseBuilder rb = Response.ok(projects);

        return rb.build();
    }
}
