
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for create account page
 */

window.addEventListener("load", function (event) {

    let password = document.getElementById("password");
    let checkPassword = document.getElementById("checkPassword");
    let toggle = document.getElementById("togglePassword");
    let toggleCheck = document.getElementById("toggleCheck");

    let email = document.getElementById("email");
    let emailError = document.getElementById("emailError");
    let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // email pattern must have TLD

    let birthday = document.getElementById("birthday");

    let submitBtn = document.getElementById("newAccountBtn");


    let validPassword = false;
    let reEnterPassword = false;
    let validEmail = false;
    let validBirthday = false;

    updateSubmitButton()
    
    /**
     * disable button if inputs are not filled
     */
    function updateSubmitButton() {
        submitBtn.disabled = !(validBirthday && validEmail && validPassword && reEnterPassword);

        if (!(validBirthday && validEmail && validPassword && reEnterPassword)) {
            submitBtn.style.backgroundColor = 'rgba(66, 183, 42, 0.53)';
        } else {
            submitBtn.style.backgroundColor = 'rgba(66, 183, 42, 1)';
        }
    };

    /**
     * check if user already exists in database
     * 
     * @param {user} user 
     * 
     */
    function success_2(user) {
        let isValid = JSON.parse(user);

        if (isValid === true) {
            emailError.innerHTML = "Account already exists"
            validEmail = false;
            email.style.backgroundColor = "rgba(182, 48, 48, 0.1)"
        }
    }

    // check if valid email
    email.addEventListener("blur", function (event) {
        if (!emailPattern.test(this.value)) {
            emailError.innerHTML = "Please enter a valid email address <br> (name@example.com or name123@example.com)";
            validEmail = false;
            this.style.backgroundColor = "rgba(182, 48, 48, 0.1)";
        } else {
            emailError.innerHTML = "";
            validEmail = true;
            this.style.backgroundColor = "";
        }

        let params = "email=" + this.value;

        let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
        }

        // check if user exists 
        fetch("userExists.php", config)
            .then(response => response.text())
            .then(success_2)

        updateSubmitButton()
    });

    // check if user is old enough
    birthday.addEventListener("input", function (event) {
        let error = document.getElementById("birthdayError");
        let today = new Date();
        let minimumAge = 18;
        let birthDate = new Date(this.value);

        let age = today.getFullYear() - birthDate.getFullYear();
        let monthDiff = today.getMonth() - birthDate.getMonth();

        // adjust age if birthday hasn't occured yet 
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }

        if (age < minimumAge) {
            error.innerHTML = `You must be at least ${minimumAge} years old`;
            validBirthday = false;
        } else {
            error.innerHTML = "";
            validBirthday = true;
        }

        updateSubmitButton()
    });

    /**
     * Check if password is valid format
     * 
     * @param {text} text 
     * 
     */
    function success_1(text) {
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


    // toggle to view password
    toggle.addEventListener("mousedown", function (event) {
        password.type = 'text';
    });
    toggle.addEventListener("mouseup", function (event) {
        password.type = 'password';
    });

    toggleCheck.addEventListener("mousedown", function (event) {
        checkPassword.type = 'text';
    });
    toggleCheck.addEventListener("mouseup", function (event) {
        checkPassword.type = 'password';
    });

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
            .then(success_1)

        updateSubmitButton()
    });
});