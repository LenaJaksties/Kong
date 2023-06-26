import { Project } from './Project.js';
import { ProjectManager } from './Project.js';
import {getAllProject} from './FetchAPIGet.js';

// Function to handle form submission
function handleSubmit(event) {
    // prevent the form submission event from triggering its default behavior
    event.preventDefault();

    // Get the entered username and password
    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;

    // Assuming login is successful
    showLogoutForm(username);
}

// Function to show the logout form and hide the login form
function showLogoutForm(username) {
    document.getElementById("loginForm").style.display = 'none';
    document.getElementById("logoutForm").style.display = 'block';
    document.getElementById("loggedInUsername").textContent = username;
}

// Function to show the login form and hide the logout form
function showLoginForm() {
    document.getElementById("loginForm").style.display = 'block';
    document.getElementById("logoutForm").style.display = 'none';
}


window.onload = function(){
    
    // Attach event listener to the login and logout button
    document.getElementById("login").addEventListener("submit", handleSubmit);
    document.getElementById("logout").addEventListener("click", showLoginForm);

};