
package rest;


import classes.Assignee;
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
 * REST interface for creating/updating Assignee Objects or return
 * specific/ all Objects
 *
 * @author lenaj lisaj
 */
@Path("assignee")

public class AssigneeResource implements Serializable {
    
    @PersistenceContext(unitName = "JPA_ExamplePU")
    private EntityManager em;
        
    @Resource
    private UserTransaction utx;
    
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
        
        try{
            this.utx.begin();
            
            this.em.persist(a);          // em saves to table tbl_ProjectAssignee
            this.utx.commit();

            URI location = URI.create("/category?id=" + a.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/category/delete?id=" + a.getId()); // delete object
            rb.link(delLocLink, "delete");
            return Response.ok(a).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

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
        try{
            this.utx.begin();
            Assignee assigneeUpdate = this.em.find(Assignee.class, id);
            // Useing adapter to create a persistable object
            Assignee assigneeInfo= updatedA;
            assigneeUpdate.setActive(assigneeInfo.isActive());
            assigneeUpdate.setDepartment(assigneeInfo.getDepartment());
            assigneeUpdate.setEmail(assigneeInfo.getEmail());
            assigneeUpdate.setFirstName(assigneeInfo.getFirstName());
            assigneeUpdate.setIconpath(assigneeInfo.getIconpath());
            assigneeUpdate.setLastName(assigneeInfo.getLastName());
            assigneeUpdate.setProjectAssignees(assigneeInfo.getProjectAssignees());
            
            this.em.persist(assigneeUpdate);          // em speichert in die Tabelle tbl_Project
            this.utx.commit();

            URI location = URI.create("/category?id=" + assigneeUpdate.getId());   // retrieve object
            Response.ResponseBuilder rb = Response.created(location);
            // Example for createing a HATEOAS link
            URI delLocLink = URI.create("/category/delete?id=" + assigneeUpdate.getId());  // delete object
            rb.link(delLocLink, "delete");

            return Response.ok(assigneeUpdate).build();
            //return rb.build();

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            // Better to add a error message here...
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }


//        Assignee originalA = updatedA;
//        URI location = URI.create("/category?id=" + originalA.getId());   // retrieve object
//        Response.ResponseBuilder rb = Response.created(location);
//        // Example for createing a HATEOAS link
//        URI delLocLink = URI.create("/category/delete?id=" + originalA.getId());  // delete object
//        rb.link(delLocLink, "delete");
//
//        return Response.ok(originalA).build();
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
        
        
        
        Assignee a = this.em.find(Assignee.class, id);
//        Assignee a = new Assignee();
//        a.setId(1l);
//        a.setActive(true);
//        a.setDepartment("software-engineering");
//        a.setEmail("lena.j@gmail.com");
//        a.setFirstName("Lena");
//        a.setIconpath("/bin/assistantlogo.png");
//        a.setLastName("J");
        

        Response.ResponseBuilder rb = Response.ok(a);

        return rb.build();
    }
    
    /**
     * GET Method to return a specific Assignee
     *
     * @param lastName of searched Assignee
     * @return ResponseBuilder
     */
    @GET
    @Path("lastName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssignee(@QueryParam("lastName") String name) {
        
        TypedQuery<Assignee> query = em.createNamedQuery("assignee.findByLastname", Assignee.class);
        query.setParameter("lastName", name); // Set the value of the lastName parameter

        List<Assignee> assigneeList = query.getResultList();

//        Assignee a = new Assignee();
//        a.setId(1l);
//        a.setActive(true);
//        a.setDepartment("software-engineering");
//        a.setEmail("lena.j@gmail.com");
//        a.setFirstName("Lena");
//        a.setIconpath("/bin/assistantlogo.png");
//        a.setLastName("J");
        

        Response.ResponseBuilder rb = Response.ok(assigneeList);

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
        
        
        Query list = this.em.createNamedQuery("assignee.findAll", Assignee.class);

        List<Object> assigneeList = new ArrayList<>();
        assigneeList = list.getResultList();
        
        
        
//        ArrayList<Assignee> assignees = new ArrayList();
//
//        Assignee a1 = new Assignee();
//        a1.setId(1l);
//        a1.setActive(true);
//        a1.setDepartment("software-engineering");
//        a1.setEmail("lena.j@gmail.com");
//        a1.setFirstName("Lena");
//        a1.setIconpath("/bin/assistantlogo.png");
//        a1.setLastName("J");
//
//        
//        Assignee a2 = new Assignee();
//        a2.setId(2l);
//        a2.setActive(true);
//        a2.setDepartment("software-engineering");
//        a2.setEmail("lisa.j@gmail.com");
//        a2.setFirstName("Lisa");
//        a2.setIconpath("/bin/assistantlogo.png");
//        a2.setLastName("J");
//      
//
//        assignees.add(a1);
//        assignees.add(a2);

        Response.ResponseBuilder rb = Response.ok(assigneeList);

        return rb.build();
    }
}
