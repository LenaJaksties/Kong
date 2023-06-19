

import { Task } from './Project.js';
import { Category } from './Project.js';
import { Assignee } from './Project.js';
import { Project } from './Project.js';
import { ProjectManager } from './Project.js';
import { ProjectTask } from './Project.js';
import { ProjectCategory } from './Project.js';
import { ProjectAssignee } from './Project.js';
import { TaskAssignee } from './Project.js';




function updateAssignee(id, firstName, lastName, department, email, active, iconpath){
    const path = "http://localhost:8080/Kong/webresources/assignee?id=" + id;
   
   var assignee = new Assignee(null,null,null,null,null,null,null);
    
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
            //.then(response => console.log(JSON.stringify(response)));
    .then(response => assignee = JSON.parse(JSON.stringify(response)));

    console.log(assignee);
    assignee.firstName = firstName;
    assignee.lastName = lastName;
    assignee.department = department;
    assignee.email = email;
    assignee.active = active;
    assignee.iconpath = iconpath;

    console.log(assignee);
    console.log("PUT assignee");
    var data = assignee;

    return fetch(path, {
        body: JSON.stringify(data),
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'content-type': 'application/json'
        },
        method: 'PUT',
        mode: 'cors',
        redirect: 'follow',
        referrer: 'no-referrer'
    });
         
}

function updateCategory(id, title, summary){
   const path = "http://localhost:8080/Kong/webresources/category?id=" + id;
    
    var category = new Category(null,null,null);
   
  
    fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
            .then(response => category = JSON.parse(JSON.stringify(response)));

    console.log(category);
    category.title = title;
    category.summary = summary;
   
   
    console.log(category);
    console.log("PUT category");
    var data = category;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'PUT',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
}


function updateTask(id,title,summary,scheduledworkingtime,deadline,achieved){
    const path = "http://localhost:8080/Kong/webresources/task?id=" + id;
    
    var cat = new Category(null,null,null);
    var task = new Task(cat,null,null,null,null,null,null);
   
   fetch(path, {
        method: "GET",
        headers:{
            "Accept": "application/json"
        }
    })
            .then(response => response.json())
            .then(response => task = JSON.parse(JSON.stringify(response)));
   
   console.log(task);
   task.title = title;
   task.summary = summary;
   task.scheduledWorkingTime = scheduledworkingtime;
   task.deadline = deadline;
   task.achieved = achieved;
   
   
    console.log(task);
    console.log("PUT task");
    var data = task;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'PUT',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
}


function updateProject(id, title,summary,logopath,startdate,deadline,status){
    const path = "http://localhost:8080/Kong/webresources/project?id=" + id;
    
    var project = new Project(null,null,null,null,null,null,null,null,null);
   
   
   fetch(path, {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    })
    .then(response => response.json())
    .then(response => project  = JSON.parse(JSON.stringify(response)));
   
    console.log(project);
   
    project.title = title;
    project.summary = summary;
    project.logopath = logopath;
    project.startDate = startdate;
    project.deadline = deadline;
    project.status = status;
    
    console.log(project);
    console.log("PUT project");
    var data = project;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'PUT',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
}

function updateProjectTask(id, projectId, taskId, updatedTime){
    
    const path = "http://localhost:8080/Kong/webresources/project_task?id=" + id;
    
    
    var projectTaskObj = new ProjectTask(null,null, null, null);
    
    fetch(path, {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    })
    .then(response => response.json())
    .then(response => projectTaskObj  = JSON.parse(JSON.stringify(response)));
   
    
    console.log(projectTaskObj);
    projectTaskObj.projectId = projectId;
    projectTaskObj.taskId = taskId;
    projectTaskObj.expendedWorkingTime = updatedTime;
   
    console.log(projectTaskObj);
    console.log("PUT project_task");
    var data = projectTaskObj;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'PUT',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
    
}


//updateAssignee(1, "Lisa", "Jaksties", "3DAnimator", "risaaa@gmail.com",false,"\assistantlogo.png");
//
//updateCategory(1,"Illustration All", "All tasks related to character design and background design and design in general");
//
//updateTask(1,"sketch objects and character", "with pen and paper", 60, "2024-06-01T00:00:00", true);
//
//updateProject(1,"3D Animation of Buildings", "The main buildings and objects for the surroundings are modelled in Blender and Adobe Aftereffects.", "\project1.jpg", "2023-06-01T00:00:00", "2024-01-01T00:00:00", 0.3);
//
//updateProjectTask(1,1,1,1412);
