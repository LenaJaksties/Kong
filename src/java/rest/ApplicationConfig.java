/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.Set;
import jakarta.ws.rs.core.Application;

/**
 *
 * @author lenaj
 */
@jakarta.ws.rs.ApplicationPath("webresources")  
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.ProjektResource.class);
        resources.add(rest.CategoryResource.class);
        resources.add(rest.ProjectResource.class);
        resources.add(rest.TaskResource.class);
        resources.add(rest.AssigneeResource.class);
        resources.add(rest.ProjectAssigneeResource.class);
        resources.add(rest.ProjectCategoryResource.class);
        resources.add(rest.ProjectTaskResource.class);
        resources.add(rest.TaskAssigneeResource.class);
    }
    
}
