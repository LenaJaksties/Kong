import { Task } from './Project.js';
import { Category } from './Project.js';
import { Assignee } from './Project.js';
import { Project } from './Project.js';
import { ProjectManager } from './Project.js';

/*
 *  Get Methods for Tasks
 */

function getTaskById(id) {
    console.log("Get Task by id");
    const path = "http://localhost:8080/Kong/webresources/task?id=" + id;
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
                        //.then(response => console.log(JSON.stringify(response)));
    
            .then(response => {
        const taskData = response; // Assuming the response is an array of task objects
        // Convert the response data into JavaScript Project instances
        
        // Create a new Task instance    
        var cat = new Category(null,null,null);
        const task = new Task(
                       //taskData.category,
               cat,
            taskData.id,
            taskData.title,
            taskData.summary,

            taskData.scheduledWorkingTime,
            taskData.deadline,
            taskData.achieved,
        );
       
        console.log(task);

    });
}


function getTaskByTitle(title) {
    console.log("Get Task by title");
    const path = "http://localhost:8080/Kong/webresources/task/title?title=" + title;
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
                        //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const tasksData = response; // Assuming the response is an array of task objects
        // Convert the response data into JavaScript Project instances
        
        const tasks = tasksData.map(tasksData => {
            var cat = new Category(null,null,null);
            return new Task(
                    cat,
            tasksData.id,
            tasksData.title,
            tasksData.summary,
            //tasksData.category,
            tasksData.scheduledWorkingTime,
            tasksData.deadline,
            tasksData.achieved,
        );
        });
   
        console.log(tasks);

    });
}

function getAllTask() {
    console.log("Get All existing Tasks");
    const path = "http://localhost:8080/Kong/webresources/task/All";
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
                        //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const tasksData = response; // Assuming the response is an array of task objects
        // Convert the response data into JavaScript Project instances
        const tasks = tasksData.map(tasksData => {
                    var cat = new Category(null,null,null);
            return new Task(
                    cat,
            tasksData.id,
            tasksData.title,
            tasksData.summary,
            //tasksData.category,
            tasksData.scheduledWorkingTime,
            tasksData.deadline,
            tasksData.achieved,
        );
        });

        console.log(tasks);

    });
}

/*
 * Get Methods for Category
 */

function getCategoryById(id) {
    console.log("Get Category by id");
    const path = "http://localhost:8080/Kong/webresources/category?id=" + id;
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
            //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const categoryData = response; // Assuming the response is a single category object

        // Create a new Project instance
        const category = new Category(
            categoryData.id,
            categoryData.title,
            categoryData.summary,
        );

        console.log(category);

    });
}



function getCategoryByTitle(title) {
    console.log("Get Category by title");
    const path = "http://localhost:8080/Kong/webresources/category/title?title=" + title;
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
                        //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const categoriesData = response; // Assuming the response is an array of category objects
        // Convert the response data into JavaScript Project instances
        const categories = categoriesData.map(categoriesData => {
            return new Category(
            categoriesData.id,
            categoriesData.title,
            categoriesData.summary,
        );
        });

        console.log(categories);

    });
}

function getAllCategory() {
    console.log("Get All existing Categories");
    const path = "http://localhost:8080/Kong/webresources/category/All";
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
                        //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const categoriesData = response; // Assuming the response is an array of category objects
        // Convert the response data into JavaScript Project instances
        const categories = categoriesData.map(categoriesData => {
            return new Category(
            categoriesData.id,
            categoriesData.title,
            categoriesData.summary,
        );
        });

        console.log(categories);

    });
}


/*
 * Get Methods for Assignee
 */

function getAssigneeById(id) {
    console.log("Get Assignee by id");
    const path = "http://localhost:8080/Kong/webresources/assignee?id=" + id;
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
            //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const assigneeData = response; // Assuming the response is a single assignee object

        // Create a new Project instance
        const assignee = new Assignee(
            assigneeData.id,
            assigneeData.firstName,
            assigneeData.lastName,
            assigneeData.department,
            assigneeData.email,
            assigneeData.active,
            assigneeData.iconpath,
            assigneeData.assignee,
        );

        console.log(assignee);
        return assignee;

    });

}


function getAssigneeByLastname(lastName) {
    console.log("Get Assignee by lastname");
    const path = "http://localhost:8080/Kong/webresources/assignee/lastName?lastName=" + lastName;
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
                        //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const assigneesData = response; // Assuming the response is an array of assignee objects
        // Convert the response data into JavaScript Project instances
        const assignees = assigneesData.map(assigneesData => {
            return new Assignee(
            assigneesData.id,
            assigneesData.firstName,
            assigneesData.lastName,
            assigneesData.department,
            assigneesData.email,
            assigneesData.active,
            assigneesData.iconpath,
            assigneesData.assignee,
        );
        });
        console.log(assignees);

    });
}

function getAllAssignee() {
    console.log("Get All existing Assignees");
    const path = "http://localhost:8080/Kong/webresources/assignee/All";
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
                        //.then(response => console.log(JSON.stringify(response)));
    .then(response => {
        const assigneesData = response; // Assuming the response is an array of assignee objects
        // Convert the response data into JavaScript Project instances
        const assignees = assigneesData.map(assigneesData => {
            return new Assignee(
            assigneesData.id,
            assigneesData.firstName,
            assigneesData.lastName,
            assigneesData.department,
            assigneesData.email,
            assigneesData.active,
            assigneesData.iconpath,
            assigneesData.assignee,
        );
    });

        console.log(assignees);

    });
}


/*
 * Get Methods for Project
 */

function getProjectById(id) {
    console.log("Get Project by id");
    const path = "http://localhost:8080/Kong/webresources/project?id=" + id;

    fetch(path, {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    })
    .then(response => response.json())
    .then(response => {
        const projectData = response; // Assuming the response is a single project object

        // Create a new Project instance
        const project = new Project(
            projectData.id,
            projectData.title,
            projectData.summary,
            projectData.logopath,
            projectData.startDate,
            projectData.deadline,
            projectData.status,
            projectData.projectassignee,
            projectData.projecttask
        );

        console.log(project);

    });
}


function getProjectByTitle(title) {
    console.log("Get Project by lastname");
    const path = "http://localhost:8080/Kong/webresources/project/title?title=" + title;
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
            .then(response => {
            const projectsData = response; // Assuming the response is an array of project objects
        // Convert the response data into JavaScript Project instances
        const projects = projectsData.map(projectData => {
            return new Project(
                projectData.id,
                projectData.title,
                projectData.summary,
                projectData.logopath,
                projectData.startDate,
                projectData.deadline,
                projectData.status,
                projectData.projectassignee,
                projectData.projecttask
            );
        });
        
        console.log(projects);

    });
}

function getAllProject() {
    console.log("Get All existing Projects");
    const path = "http://localhost:8080/Kong/webresources/project/All";
    
    fetch(path, {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    })
    .then(response => response.json())
    .then(response => {
        const projectsData = response; // Assuming the response is an array of project objects
        // Convert the response data into JavaScript Project instances
        const projects = projectsData.map(projectData => {
            return new Project(
                projectData.id,
                projectData.title,
                projectData.summary,
                projectData.logopath,
                projectData.startDate,
                projectData.deadline,
                projectData.status,
                projectData.projectassignee,
                projectData.projecttask
            );
        });
        
        console.log(projects);
        
    });
}


//getTaskById(1);
//getAssigneeById(1);
//getCategoryById(1);
//getProjectById(1);

//getAllTask();
//getAllAssignee();
//getAllCategory();
//getAllProject();


//getTaskByTitle("sketch objects and character");
//getAssigneeByLastname("Jaksties");
//getCategoryByTitle("Illustration");
//getProjectByTitle("3D Animation of Characters");
