package rest;

import classes.Assignee;
import classes.Category;
import classes.Task;
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

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.annotation.Resource;
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
 * REST interface for creating/updating Category Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("category") 
public class CategoryResource  implements Serializable {
    
    
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
        
    @Resource
    private UserTransaction utx;
    
   /**
     * POST method that creates a new Category
     *
     * @param ca new Category
     * @return REsponseBuilder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(Category ca) {
        try{
            this.utx.begin();
            
            this.em.persist(ca);          // em saves to table tbl_ProjectAssignee
            this.utx.commit();

            URI location = URI.create("/category?id=" + ca.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/category/delete?id=" + ca.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(ca).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * PUT method that updates a CategoryAssignee via the id
     *
     * @param id id of the Category
     * @param updatedCA updated Category
     * @return ResponseBuilder
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@QueryParam("id") Long id, Category updatedCA) {
        try{
            this.utx.begin();
            Category categoryUpdate = this.em.find(Category.class, id);
            // Useing adapter to create a persistable object
            Category categoryInfo= updatedCA;
            categoryUpdate.setSummary(categoryInfo.getSummary());
            categoryUpdate.setTitle(categoryInfo.getTitle());
            
            this.em.persist(categoryUpdate);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();

            URI location = URI.create("/category?id=" + categoryUpdate.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/category/delete?id=" + categoryUpdate.getId());  // delete object
            rb.link(delLocLink, "delete");

            return Response.ok(categoryUpdate).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * GET Method to return a specific Category
     *
     * @param id id of searched Category
     * @return ResponseBuilder
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@QueryParam("id") Long id) {
        
        Category ca = this.em.find(Category.class, id);
        

        Response.ResponseBuilder rb = Response.ok(ca);
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
        
        TypedQuery<Category> query = em.createNamedQuery("category.findByTitle", Category.class);
        query.setParameter("title", name); // Set the value of the lastName parameter

        List<Category> categoryList = query.getResultList();
   
        Response.ResponseBuilder rb = Response.ok(categoryList);

        return rb.build();
    }
    
    /**
     * GET Method to return all Category
     *
     * @return ResponseBuilder
     */
    @GET
    @Path("All") // -> category/All
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        
        Query list = this.em.createNamedQuery("category.findAll", Category.class);

        List<Object> categoryList = new ArrayList<>();
        categoryList = list.getResultList();
        
        
//        ArrayList<Category> categories = new ArrayList();
//
//        Category ca1 = new Category();
//        ca1.setId(1l);
//        ca1.setSummary("Work related to cats");
//        ca1.setTitle("CatCare");
//        
//        Category ca2 = new Category();
//        ca2.setId(2l);
//        ca2.setSummary("Tasks related to maintenance of applications. That can be bugfixes or new updates");
//        ca2.setTitle("Maintenance");
//        
//        categories.add(ca1);
//        categories.add(ca2);

        Response.ResponseBuilder rb = Response.ok(categoryList);

        return rb.build();
    }
    
}
