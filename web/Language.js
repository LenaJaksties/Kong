
// find language of Browser
var websiteLanguage = navigator.language || navigator.userlanguage;

console.log("Detected Language: " + websiteLanguage);

var dictionary = new Map();
// fill dictionary englisch and german
dictionary.set("Project List",["Project List","Projektliste"]);
dictionary.set("Project Detail",["Project Detail","Projekdetail"]);
dictionary.set("New Project",["New Project","Neues Projekt"]);
dictionary.set("Project Description",["Project Description","Projektbeschreibung"]);

// take values depending on website language
var indexNumber = 0;
if(websiteLanguage === "en" || websiteLanguage === "en-GB" || websiteLanguage === "en.US"){
    indexNumber = 0;
}else if(websiteLanguage === "de" ){
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