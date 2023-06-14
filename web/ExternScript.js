import { Task } from './Project.js';
import { Category } from './Project.js';
import { Assignee } from './Project.js';
import { Project } from './Project.js';
import { ProjectManager } from './Project.js';


var category = new Category(1, "Project Preparation", "Preparation of a projects contains tasks like Mindmapping, Brainstorming, Writing down ideas");
var task1 = new Task(category, 1, "write down ideas", "search for ideas in teams using google and social media and find ideas in nature", 4, "2023-01-01", true);
var task2 = new Task(category, 2, "setup a project eg. with Gradle", "building the surrounding for the project, gradle structure, gradle build script", 20, "2023-01-05", true);
var tasklist1 = new Set([task1, task2]);
var assignee1 = new Assignee(1, "Lena", "Jaksties", "Sales", "lelee@gmail.com",true,"\assistantlogo.png");
var assigneelist1 = new Set([assignee1]);
var project1 = new Project(1, "Game structure of Puzzle Game", "The main structure and architecture for a 2D indie game will be designed and implemented.", "\project2.jpg", "2023-01-01", "2023-08-01", 0.5, assigneelist1, tasklist1);


var category2 = new Category(2, "Illustration", "All tasks related to character design and background design");
var task3 = new Task(category2, 3, "sketch objects and character", "with pen and paper", 15, "2023-06-01", true);
var task4 = new Task(category2, 4, "2D Illustration of character", "using illustration and photoshop", 80, "2023-08-05", true);
var tasklist2 = new Set([task3, task4]);
var assignee2 = new Assignee(2, "Lisa", "Jaksties", "3DAnimator", "risaaa@gmail.com",true,"\assistantlogo.png");
var assigneelist2 = new Set([assignee2,assignee1]);
var project2 = new Project(2, "3D Animation of Characters", "The main characters and objects for the surroundings are modelled in Blender and Adobe Aftereffects.", "\project1.jpg", "2023-06-01", "2024-01-01", 0.1, assigneelist2, tasklist2);

var category3 = new Category(3, "Story Writing", "All tasks related to preparations of Story Writing");
var task5 = new Task(category3, 5, "Set main theme", "Research and use creative techniques to find a main theme", 19, "2022-03-01", true);
var task6 = new Task(category3, 6, "Writing a story plot", "Digital Writing of the game story main plot", 12, "2022-04-05", true);
var tasklist3 = new Set([task5, task6]);
var project3 = new Project(3, "Storyline and Plot of the Point and Click Adventure", "Creating a thrilling and beautiful detailed story for the game.", "\project4.jpg", "2022-03-01", "2022-09-01", 1, assigneelist2, tasklist3);



function printProject(project){
    for(var i in project) {
        if(project[i]=== project.projectassignee){
            
            for(var x of project[i].values()){
                console.log("");
                console.log("Assignee : ");
                for(var j in x){
                    console.log(i + j +' = ' + x[j]);
                }
            }
            
             console.log("");
        }else if(project[i]=== project.projecttask){

            for (var x of project[i].values()) {
                console.log("");
                console.log("Task: ");
                for(var j in x){
                    console.log(i + j + " = " + x[j]); 
                }
            }
             console.log("");
        }else{
            console.log(i + ' = ' + project[i]);
        }
	
    }
}
console.log("Project number 1");
printProject(project1);

console.log("Project number 2");
printProject(project2);



//for(var i in task1){
//     console.log(i + " = " + task1[i]);
//    for(var j in tasklist[i]){
//        console.log("HAKUNA MATATA" +i + j + tasklist[i][j]); 
//    }
//}

//console.log(task1.categorysummary);
//
//for (const x of tasklist.values()) {
//                console.log(x +' = ' + tasklist[x]);
//    
//    }
console.log("WorkingTime Project 1:");
project1.calcWorkingtime();
console.log("WorkingTime Project 2:");
project2.calcWorkingtime();


var projectlist = [project1, project2, project3];
var projManager = new ProjectManager(projectlist);


console.log("Project unsorted");
for(var i in projectlist) {
    console.log(i + ' = ' + projectlist[i]);
    
    //printProject(projectlist[i]);
    
    for(var k in projectlist[i]) {
    console.log(i + k +' = ' + projectlist[i][k]);
    }
}

console.log("Project sorted by Duration");
projManager.sortByDuration();
for(var i in projectlist) {
    console.log(i + ' = ' + projectlist[i]);
    
    //printProject(projectlist[i]);
    
    for(var k in projectlist[i]) {
    console.log(i + k +' = ' + projectlist[i][k]);
    }
}
console.log("");
console.log("Project sorted by Startdate");
projManager.sortByStartDate();
for(var i in projectlist) {
    console.log(i + ' = ' + projectlist[i]);
    
    //printProject(projectlist[i]);
    
    for(var k in projectlist[i]) {
    console.log(i + k +' = ' + projectlist[i][k]);
    }
}