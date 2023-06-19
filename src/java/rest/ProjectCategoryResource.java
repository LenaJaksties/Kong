package rest;

import classes.Category;
import classes.Project;
import classes.ProjectCategory;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
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
    
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
        
    @Resource
    private UserTransaction utx;

    
    /**
     * POST method that creates a new Project Category
     *
     * @param pc new Project Category
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProjectCategory(@QueryParam("projectId") Long projectId, @QueryParam("categoryId") Long categoryId) {

        try{
            this.utx.begin();
            
            Category a = this.em.find(Category.class, categoryId);
            
            this.utx.commit();
            this.utx.begin();
            Project p = this.em.find(Project.class, projectId);
            
            this.utx.commit();
            this.utx.begin();
            ProjectCategory pa = new ProjectCategory();
            pa.setCategoryId(a.getId());
            pa.setProjectId(p.getId());
            
            this.em.persist(pa);          // em saves to table tbl_ProjectAssignee
            this.utx.commit();

            URI location = URI.create("/project_category?id=" + pa.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/project_category/delete?id=" + pa.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(pa).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
       
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

    private class utx {

        public utx() {
        }
    }
}
