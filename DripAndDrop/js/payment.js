
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for payment page
 */

window.addEventListener("load", function (event) {

    let remove_carts = document.querySelectorAll(".remove");
    let popUpOverlay = document.getElementById("popUpOverlay");
    let products = document.getElementById("Products");

    let cardnumber = document.getElementById("cardnumber");
    let expiry = document.getElementById("expiry");
    let cvv = document.getElementById("cvv");
    let street = document.getElementById("street");
    let city = document.getElementById("city");
    let zip = document.getElementById("zip");

    let submitBtn = document.getElementById("btnprimary");
    let checkbox = document.getElementById("savepaymentmethod");

    let savedCard = document.getElementById("savedCard");
    let newCard = document.getElementById("newCard");
    let form = document.getElementById("paymentform");
    let saveCardDisplay = document.getElementById("savedCardDisplay");
    let paymentForm = document.getElementById("paymentForm");
    let cartIds = JSON.parse(document.getElementById("cart_ids").value);

    let useCard = document.querySelectorAll(".useCard");
    let checkoutcontainer = document.getElementById("checkoutcontainer");

    savedCard.checked = true; // default is saved cart

    // switch checkboxes
    savedCard.addEventListener('change', () => {
        if (savedCard.checked) {
            newCard.checked = false;
        }

        form.style.display = "none";
        saveCardDisplay.style.display = "flex";

    });

    newCard.addEventListener('change', () => {
        if (newCard.checked) {
            savedCard.checked = false;
        }

        form.style.display = "flex";
        saveCardDisplay.style.display = "none";

    });

    // check formating
    cardnumber.addEventListener("input", function (event) {
        let value = cardnumber.value.replace(/\D/g, "");

        let formattedValue = '';
        for (let i = 0; i < value.length; i++) {
            if (i > 0 && i % 4 === 0) {
                formattedValue += ' ';
            }
            formattedValue += value[i];
        }

        this.value = formattedValue;

        let maxLength = 23;
        if (this.value.length > maxLength) {
            this.value = this.value.substring(0, maxLength);
        }
    });

    // check formating
    expiry.addEventListener("input", function (event) {
        let value = expiry.value.replace(/\D/g, "");

        if (value.length > 2) {
            value = value.slice(0, 2) + "/" + value.slice(2, 4);
        }

        expiry.value = value;
    });

    // check formating
    cvv.addEventListener("input", function (event) {
        let value = cvv.value.replace(/\D/g, "");
        this.value = value;
        let maxLength = 3;
        if (this.value.length > maxLength) {
            this.value = this.value.substring(0, maxLength);
        }
    });

    // check formating
    zip.addEventListener("input", function (event) {
        let value = zip.value.toUpperCase().replace(/[^A-Z0-9]/g, "");

        if (value.length > 3) {
            value = value.slice(0, 3) + " " + value.slice(3, 6);
        }

        zip.value = value;
    });

    // when clicked use card asociated with the button
    useCard.forEach(function (button) {
        button.addEventListener("click", function () {
            processTransaction();
        });
    });

    submitBtn.addEventListener("click", function (event) {
        event.preventDefault();

        // if checkbox is check then call the function to save information
        if (checkbox.checked) {
            savePaymentMethodToDatabase();
        }

        processTransaction();
    });

    // proccess transationc
    function processTransaction() {

        checkoutcontainer.style.display = "none";

        let params = cartIds.map(id => "ids[]=" + encodeURIComponent(id)).join("&");

        let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
        };

        fetch("transaction.php", config)
            .then(response => response.text())
            .then(checkOut)
    }

    /**
     * display checkout message
     * 
     * @param {text} text 
     * 
     */
    function checkOut(text) {
        let checkOutMessage = document.getElementById("checkOutMessage");
        let html = ` <div id="show">
            <p id="message">${text}</p>
            <a href="../homepage/menu.php" id="homeBtn">Return</a>
        </div>`;

        checkOutMessage.innerHTML = html;
    }

    /**
     * save paymnet information to the database
     */
    function savePaymentMethodToDatabase() {

        let params = "cardnumber=" + cardnumber.value +
            "&expiry=" + expiry.value +
            "&cvv=" + cvv.value +
            "&street=" + street.value +
            "&city=" + city.value +
            "&zip=" + zip.value;

        let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
        };


        fetch("uploadPayment.php", config)
            .then(response => response.text())
            .then(data => {
                console.log("Server response:", data);
            })
            .catch(error => {
                console.error("Error uploading payment method:", error);
            });
    }

    // hide payment if nothing is in the cart
    if (products.querySelectorAll(".prod").length === 0) {
        paymentForm.style.visibility = "hidden";
    } else {
        paymentForm.style.visibility = "visible";
    }

    /**
     * remove item from cart
     */
    remove_carts.forEach(function (button) {
        button.addEventListener("click", function () {
            let itemId = this.getAttribute("data-id");
            let params = "id=" + itemId;

            let config = {
                method: 'POST',
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params
            };

            fetch("removeFromCart.php", config)
                .then(response => response.text())
                .then(success)
        });
    });

    /**
     * hide popup
     */
    function hidePopUp() {
        popUpOverlay.style.display = "none"
        popUpOverlay.innerHTML = "";
        window.location.reload();
    }

    /**
     * display message in popup
     * 
     * @param {text} text 
     * 
     */
    function success(text) {
        popUpOverlay.style.display = "flex"
        let html = `<div id="popUp">
            <h3>${text}</h3>
            <img src="../images/checkMark.png">
          </div>`;
        popUpOverlay.innerHTML = html;

        setTimeout(hidePopUp, 400);
    }
});