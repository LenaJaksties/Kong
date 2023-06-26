
import { Task } from './Project.js';
import { Category } from './Project.js';
import { Assignee } from './Project.js';
import { Project } from './Project.js';
import { ProjectManager } from './Project.js';
import { ProjectTask } from './Project.js';
import { ProjectCategory } from './Project.js';
import { ProjectAssignee } from './Project.js';
import { TaskAssignee } from './Project.js';



function postAssignee(firstName, lastName, department, email, active, iconpath){
   const path = "http://localhost:8080/Kong/webresources/assignee";
    //const pathNew = "http://localhost:8080/Kong/webresources/project_task?projectId=" + projectId + "&taskId=" + taskId;
    
    var assignee = new Assignee(null,firstName,lastName, department, email, active, iconpath);
   
    console.log(assignee);
    console.log("POST assignee");
    var data = assignee;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
}


function postCategory(title, summary){
   const path = "http://localhost:8080/Kong/webresources/category";
    //const pathNew = "http://localhost:8080/Kong/webresources/project_task?projectId=" + projectId + "&taskId=" + taskId;
    
    var category = new Category(null,title,summary);
   
    console.log(category);
    console.log("POST category");
    var data = category;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
}


function postTask(categoryId, category, title,summary,scheduledworkingtime,deadline,achieved){
    const path = "http://localhost:8080/Kong/webresources/task?categoryId=" + categoryId;
    
    var task = new Task(category,null,title,summary,scheduledworkingtime,deadline,achieved);
   
    console.log(task);
    console.log("POST task");
    var data = task;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
}


function postProject(title,summary,logopath,startdate,deadline,status){
    const path = "http://localhost:8080/Kong/webresources/project";
    //const pathNew = "http://localhost:8080/Kong/webresources/project_task?projectId=" + projectId + "&taskId=" + taskId;
    
    var project = new Project(null,title,summary,logopath,startdate,deadline,status,null,null);
   
    console.log(project);
    console.log("POST project");
    var data = project;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
}

function postProjectTask(projectId, taskId, updatedTime){
    
    const path = "http://localhost:8080/Kong/webresources/project_task";
    const pathNew = "http://localhost:8080/Kong/webresources/project_task?projectId=" + projectId + "&taskId=" + taskId;
    
    var projectTaskObj = new ProjectTask(null,updatedTime, null, null);
   
    console.log(projectTaskObj);
    console.log("POST project_task");
    var data = projectTaskObj;
    
    	return fetch(pathNew, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
    
}


function postProjectAssignee(projectId, assigneeId){
    
    const path = "http://localhost:8080/Kong/webresources/project_assignee?projectId=" + projectId + "&assigneeId=" + assigneeId;
    
    var projectAssigneeObj = new ProjectAssignee(null,projectId,assigneeId);
   
    console.log(projectAssigneeObj);
    console.log("POST project_assignee");
    var data = projectAssigneeObj;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
    
}

function postProjectCategory(projectId, categoryId){
    
    const path = "http://localhost:8080/Kong/webresources/project_task";
    const pathNew = "http://localhost:8080/Kong/webresources/project_category?projectId=" + projectId + "&categoryId=" + categoryId;
    
    var projectCategoryObj = new ProjectCategory(null,projectId, categoryId);
   
    console.log(projectCategoryObj);
    console.log("POST project_category");
    var data = projectCategoryObj;
    
    	return fetch(pathNew, {
                        body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
    
}
function postTaskAssignee(taskId, assigneeId){
    
    const path = "http://localhost:8080/Kong/webresources/task_assignee?taskId=" + taskId + "&assigneeId=" + assigneeId;
    
    var taskAssigneeObj = new TaskAssignee(null,taskId, assigneeId);
   
    console.log(taskAssigneeObj);
    console.log("POST task_assignee");
    var data = taskAssigneeObj;
    
    	return fetch(path, {
			body: JSON.stringify(data), // must match 'Content-Type' header
			cache: 'no-cache',
			credentials: 'same-origin',
			headers: {
					'content-type': 'application/json'
			},
			method: 'POST',
			mode: 'cors',
			redirect: 'follow',
			referrer: 'no-referrer'
	})
	.then(response => response.json()); // parses response to JSON
    
}


//postAssignee( "Lisa", "Jaksties", "3DAnimator", "risaaa@gmail.com",true,"\assistantlogo.png");
//
//postCategory("Illustration", "All tasks related to character design and background design");
//
//
//var cat = new Category(null,null,null);
//
//postTask(1,cat,"sketch objects and character", "with pen and paper", 15, "2023-06-01T00:00:00", true);
//
//postProject("3D Animation of Characters", "The main characters and objects for the surroundings are modelled in Blender and Adobe Aftereffects.", "\project1.jpg", "2023-06-01T00:00:00", "2024-01-01T00:00:00", 0.1);


//postProjectTask(1,1,14129999);
//postProjectAssignee(1,1);
//postProjectCategory(1,1);
//postTaskAssignee(1,1);