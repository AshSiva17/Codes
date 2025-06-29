
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for login page
 */

window.addEventListener("load", function (event) {

    let toggle = document.getElementById("togglePassword");
    let password = document.getElementById("password");
    let form = document.getElementById("form");
    let email = document.getElementById("email");

    // Show password when clicked
    toggle.addEventListener("mousedown", function (event) {
        password.type = 'text';
    });
    toggle.addEventListener("mouseup", function (event) {
        password.type = 'password';
    });

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        errorMessage.textContent = "";

        let params = "email=" + email.value + "&password=" + password.value;

        let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
        }

        // Show loading indicator (optional)
        errorMessage.textContent = "Logging in...";

        fetch("login.php", config)
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Successful login - redirect
                    window.location.href = data.redirect;
                } else {
                    // Failed login - show error
                    errorMessage.textContent = data.message;
                }
            })
            .catch(error => {
                // Handle fetch errors
                errorMessage.textContent = "Login error. Please try again.";
                console.error("Login error:", error);
            });
    });
});