
// find language of Browser
var websiteLanguage = navigator.language || navigator.userlanguage;

console.log("Detected Language: " + websiteLanguage);

var dictionary = new Map();
// fill dictionary in englisch and german
dictionary.set("projectList",["Project List","Projektliste"]);
dictionary.set("projectDetail",["Project Detail","Projekdetail"]);
dictionary.set("newProject",["New Project","Neues Projekt"]);
dictionary.set("projectDescription",["Project Description","Projektbeschreibung"]);
dictionary.set("content",["Content","Inhalt"]);
dictionary.set("latestProjects",["Latest Projects","Letzte Projekte"]);
dictionary.set("username",["Username","Benutzername"]);
dictionary.set("password",["Password","Passwort"]);
dictionary.set("submit",["Submit","Senden"]);
dictionary.set("newRegister",["New Register","Neuregistrierung"]);
dictionary.set("project1",["Project","Projekt"]);
dictionary.set("projectSummary1",["Project Summary","Projektübersicht"]);
dictionary.set("projectGoals",["Project Goals","Projektziele"]);
dictionary.set("send",["Send","Senden"]);
dictionary.set("reset",["Reset","Zurücksetzen"]);
dictionary.set("menu",["Menu","Menü"]);
dictionary.set("projectOverview",["Project Overview","Projektübersicht"]);
dictionary.set("goToProjectDetails",["Go to project details","Gehe zu Projekt Details"]);
dictionary.set("projectTitle1",["Project Title","Projekttitel"]);
dictionary.set("projectHead1",["Project Head","Projektleiter"]);
dictionary.set("startDate",["Start Date","Startdatum"]);
dictionary.set("deadline",["Deadline","Frist"]);

// take values depending on website language
var indexNumber = 0;
if(websiteLanguage === "en" || websiteLanguage === "en-GB" || websiteLanguage === "en-US" || websiteLanguage === "en-CA"|| websiteLanguage === "en-AU"){
    indexNumber = 0;
}else if(websiteLanguage === "de" || websiteLanguage === "de-DE" || websiteLanguage === "de-AT"|| websiteLanguage === "de-CH" ){
     indexNumber = 1;
}else{
    console.log("This Site is only available in english and german");
}

console.log("");
console.log("Current Dictionary for website language " + websiteLanguage + ":");
dictionary.forEach((values, key) => {
  console.log(`${key}: ${values[indexNumber]}`);
});
console.log("");

export function translateSite(){
 console.log("Window loaded");
   dictionary.forEach((values, key) => {
       
    const curElement = document.getElementById(key);

    if (curElement) {
      curElement.innerHTML = values[indexNumber];
      curElement.value = values[indexNumber];
    } else {
      const elements = document.getElementsByClassName(key);
      if (elements.length > 0) {
        for (let i = 0; i < elements.length; i++) {
          elements[i].innerHTML = values[indexNumber];
          elements[i].value = values[indexNumber];
        }
      }
    }
  });
       console.log("Site is being translated");
   
   
};



