
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for profile page
 */

window.addEventListener("load", function (event) {

    let removeBtn = document.querySelectorAll(".remove");
    let popUpOverlay = document.getElementById('popUpOverlay');
    let hiddenInput = document.getElementById("hiddenValue");
    let submit = document.getElementById('yesButton');
    let no = document.getElementById('noButton');
    let noDelete = document.getElementById("noButtonDelete");
    let removeCard = document.querySelectorAll(".removeCard");
    let forCard = false;
    let deleteUser = document.getElementById("deleteUser");
    let deleteOverlay = document.getElementById("popUpOverlayDelete");

    deleteUser.addEventListener("click", function (event) {
        showPopUpDelete();
    });

    // remove card from database
    removeCard.forEach(function (button) {
        button.addEventListener("click", function () {
            forCard = true;
            showPopUp();
            let itemId = this.getAttribute('data-id');
            hiddenInput.value = itemId;
        });
    });

    // remove listing from database
    removeBtn.forEach(function (button) {
        button.addEventListener("click", function () {
            showPopUp();
            let itemId = this.getAttribute('data-id');
            hiddenInput.value = itemId;
        });
        forCard = false;
    });

    // hide popup when no is clicked
    no.addEventListener('click', function (event) {
        hidePopUp();
    });

    // hide popup when no is clicked
    noDelete.addEventListener('click', function (event) {
        event.preventDefault(); 
        hidePopUpDelete();
    });


    // AJAX to remove card or listing from database
    submit.addEventListener("click", function (event) {
        event.preventDefault();
        hidePopUp();

        let params = "id=" + hiddenInput.value;

        let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
        }

        // remove card or listing
        if (forCard === true) {
            fetch("deletePayment.php", config)
                .then(response => response.text())
                .then(success)
        } else if (forCard === false) {
            fetch("deleteListing.php", config)
                .then(response => response.text())
                .then(success)
        }
    });

    /**
     * reload page
     * 
     * @param {*} valid upload worked
     * 
     */
    function success(valid) {
        if (valid) {
            window.location.reload();
        }
    }

    // show pop up
    function showPopUp() {
        popUpOverlay.style.display = 'flex';
    }

    // hide pop up
    function hidePopUp() {
        popUpOverlay.style.display = "";
    }

    // show pop up
    function showPopUpDelete() {
        deleteOverlay.style.display = 'flex';
    }

    // hide pop up
    function hidePopUpDelete() {
        deleteOverlay.style.display = "";
    }

});