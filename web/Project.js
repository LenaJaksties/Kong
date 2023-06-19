export class Project{
    constructor(id,title,summary,logopath,startdate,deadline,status,projectassignee,projecttask){
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.logopath = logopath;
        this.startDate = startdate;
        this.deadline = deadline;
        this.status = status;
        this.projectassignee = projectassignee;
        this.projecttask = projecttask;
    }
    
//    get id(){
//        return this._id;
//    }
//    
//    set id(id){
//        this._id = id;
//    }
//    
//    get title(){
//        console.log('get title');
//        return this._title;
//    }
//    
//    set title(title){
//        this._title = title;
//    }
//    
//    get summary(){
//        return this._summary;
//    }
//    
//    set summary(summary){
//        this._summary = summary;
//    }
//    
//    get logopath(){
//        return this._logopath;
//    }
//    
//    set logopath(logopath){
//        this._logopath = logopath;
//    }
//
//    get startdate(){
//        return this._startdate;
//    }
//    
//    set startdate(startdate){
//        this._startdate = startdate;
//    }
//
//    get deadline(){
//        return this._deadline;
//    }
//    
//    set deadline(deadline){
//        this._deadline = deadline;
//    }
//
//    get status(){
//        return this._status;
//    }
//    
//    set status(status){
//        this._status = status;
//    }
    
//    get projectassignee(){
//        return this._projectassignee;
//    }
//    
//    set projectassignee(projectassignee){
//        projectassignee.forEach(function (a){
//            this._projectassignee.add(a);
//        });
//        
//    }
//    
//    get projecttask(){
//        return this._projecttask;
//    }
//    
//    set projecttask(projecttask){
//        projecttask.forEach(function (t){
//            this._projecttask.add(t);
//        });
//        
//    }
    
    calcWorkingtime(){
        let totalworkingtime = 0;
        this.projecttask.forEach(function(value){
            totalworkingtime += value.scheduledworkingtime;
        });
        console.log("Total time:"+totalworkingtime);
        return totalworkingtime;
    }
}

export class Assignee{
    constructor(id,firstname,lastname,department,email,active,iconpath){
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.department = department;
        this.email = email;
        this.active = active;
        this.iconpath = iconpath;
    }
    
//    get id(){
//        return this._id;
//    }
//    
//    set id(id){
//        this._id = id;
//    }
//    
//    get firstname(){
//        return this._firstname;
//    }
//    
//    set firstname(firstname){
//        this._firstname = firstname;
//    }
//    
//    get lastname(){
//        return this._lastname;
//    }
//    
//    set lastname(lastname){
//        this._lastname = lastname;
//    }
//    
//    get department(){
//        return this._department;
//    }
//    
//    set department(department){
//        this._department = department;
//    }
//    
//    get email(){
//        return this._email;
//    }
//    
//    set email(email){
//        this._email = email;
//    }
//    
//    get active(){
//        return this._active;
//    }
//    
//    set active(active){
//        this._active = active;
//    }
//    
//    get iconpath(){
//        return this._iconpath;
//    }
//    
//    set iconpath(iconpath){
//        this._iconpath = iconpath;
//    }
}

export class Category{
    constructor(categoryid,categorytitle,categorysummary){
        this.categoryid = categoryid;
        this.title = categorytitle;
        this.summary = categorysummary;
    }
    
//    get categoryid(){
//        return this._categoryid;
//    }
//    
//    set categoryid(categoryid){
//        this._categoryid = categoryid;
//    }
//    
//    get categorytitle(){
//        return this._categorytitle;
//    }
//    
//    set categorytitle(categorytitle){
//        this._categorytitle = categorytitle;
//    }
//    
//    get categorysummary(){
//        return this._categorysummary;
//    }
//    
//    set categorysummary(categorysummary){
//        this._categorysummary = categorysummary;
//    }
}

export class Task extends Category{
    constructor(category = null,id,title,summary,scheduledworkingtime,deadline,achieved){
        super(category._categoryid,category._categorytitle, category._categorysummary);
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.scheduledWorkingTime = scheduledworkingtime;
        this.deadline = deadline;
        this.achieved = achieved;
        
    }
    
//    get id(){
//        return this._id;
//    }
//    
//    set id(id){
//        this._id = id;
//    }
//    
//    get title(){
//        return this._title;
//    }
//    
//    set title(title){
//        this._title = title;
//    }
//    
//    get summary(){
//        return this._summary;
//    }
//    
//    set summary(summary){
//        this._summary = summary;
//    }
//    
//    get scheduledworkingtime(){
//        return this._scheduledworkingtime;
//    }
//    
//    set scheduledworkingtime(scheduledworkingtime){
//        this._scheduledworkingtime = scheduledworkingtime;
//    }
//    
//    get deadline(){
//        return this._deadline;
//    }
//    
//    set deadline(deadline){
//        this._deadline = deadline;
//    }
//    
//    get achieved(){
//        return this._achieved;
//    }
//    
//    set achieved(achieved){
//        this._achieved = achieved;
//    }

}

export class ProjectManager {
  constructor(projects) {
    this.projects = projects;
  }
  
  sortByStartDate() {
    const n = this.projects.length;
    for (let i = 0; i < n - 1; i++) {
      for (let j = 0; j < n - i - 1; j++) {
        if (this.projects[j].startdate > this.projects[j + 1].startdate) {
          const temp = this.projects[j];
          this.projects[j] = this.projects[j + 1];
          this.projects[j + 1] = temp;
        }
      }
    }
  }

  sortByDuration() {
    const n = this.projects.length;
    for (let i = 0; i < n - 1; i++) {
      for (let j = 0; j < n - i - 1; j++) {
        const durationA = this.projects[j].calcWorkingtime();
        const durationB = this.projects[j + 1].calcWorkingtime();
        if (durationA > durationB) {
          const temp = this.projects[j];
          this.projects[j] = this.projects[j + 1];
          this.projects[j + 1] = temp;
        }
      }
    }
  }

}

export class ProjectTask{
    
    constructor(id,time,projectId, taskId){
        this.id = id;
        this.expendedWorkingTime = time;
        this.projectId = projectId;
        this.taskId = taskId;
    }
}


export class ProjectCategory{
    constructor(id,projectId, categoryId){
        this.id = id;
        this.projectId = projectId;
        this.categoryId = categoryId;
    }
}

export class ProjectAssignee{
    constructor(id,projectId,assigneeId){
        this.id = id;
        this.projectId = projectId;
        this.assigneeId = assigneeId;
    }
}

export class TaskAssignee{
    constructor(id,taskId,assigneeId){
        this.id = id;
        this.taskId = taskId;
        this.assigneeId = assigneeId;
    }
}