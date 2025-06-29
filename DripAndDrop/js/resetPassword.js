
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for reset password page
 */

window.addEventListener("load", function (event) {


    let form = document.getElementById("form");
    let form2 = document.getElementById("form2");

    let email = document.getElementById("email");


    let password = document.getElementById("password");
    let checkPassword = document.getElementById("checkPassword");
    let toggle = document.getElementById("togglePassword");
    let toggleCheck = document.getElementById("toggleCheck");

    let change = document.getElementById("change");

    let validPassword = false;
    let reEnterPassword = false;

    updateSubmitButton()

    /**
     * diabled sumbit button if inputs aren't filled in
     */
    function updateSubmitButton() {
        change.disabled = !(validPassword && reEnterPassword);

        if (!(validPassword && reEnterPassword)) {
            change.style.backgroundColor = 'rgba(42, 160, 183, 0.59)';
        } else {
            change.style.backgroundColor = ' #2aa0b7';
        }
    };


    // toggle to view password
    toggle.addEventListener("mousedown", function (event) {
        password.type = 'text';
    });
    toggle.addEventListener("mouseup", function (event) {
        password.type = 'password';
    });

    // toggle to view reenter of password
    toggleCheck.addEventListener("mousedown", function (event) {
        checkPassword.type = 'text';
    });
    toggleCheck.addEventListener("mouseup", function (event) {
        checkPassword.type = 'password';
    });

    // check if passwords match
    checkPassword.addEventListener("input", function (event) {
        let error = document.getElementById("checkError");
        if (this.value !== password.value) {
            this.style.backgroundColor = "rgba(182, 48, 48, 0.1)";
            error.innerHTML = "Passwords don't match";
            reEnterPassword = false;
        } else {
            error.innerHTML = "";
            this.style.backgroundColor = "";
            reEnterPassword = true;
        }

        updateSubmitButton()
    })

    /**
     * Display if password is in valid format
     * 
     * @param {text} text 
     * 
     */
    function check(text) {
        let error = document.getElementById("passwordError");
        let isValid = JSON.parse(text);

        let html = `<ul>
            <li>at least 6 characters</li>
            <li>contains uppercase</li>
            <li>contains lowercase</li>
            <li>at least 1 digit</li>
            <li>at least 1 symbol</li>
        </ul>`;

        if (isValid === true) {
            password.style.backgroundColor = "";
            error.innerHTML = '';
        } else {
            password.style.backgroundColor = "rgba(182, 48, 48, 0.1)";
            error.innerHTML = html;
        }

        validPassword = isValid;
    }

    // check if valid password
    password.addEventListener("input", function (event) {

        let params = "input=" + this.value;

        let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
        }

        fetch("password.php", config)
            .then(response => response.text())
            .then(check)

        updateSubmitButton()
    });

    /**
     *  Display if user already exists 
     * 
     * @param {user} user
     *
     */
    function success(user) {
        let isValid = JSON.parse(user);

        let error = document.getElementById("emailError");

        if (isValid === true) {
            error.innerHTML = "User found";
            form2.style.display = 'flex';
            let hidden = document.getElementById("hiddenEmail");
            hidden.value = email.value;

        } else {
            error.innerHTML = "User not found";
            form2.style.display = 'none';
        }

    }

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        let params = "email=" + email.value;

        let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
        }

        fetch("userExists.php", config)
            .then(response => response.text())
            .then(success)
    });
});