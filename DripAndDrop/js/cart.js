
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for cart page
 */

window.addEventListener("load", function (event) {

    let remove_carts = document.querySelectorAll(".carts");
    let remove_likes = document.querySelectorAll(".likes");
    let popUpOverlay = document.getElementById("popUpOverlay");
    let products = document.getElementById("Products");
    let proceed = document.getElementById("proceed-to-checkout");
    let sort_bookmarks = document.getElementById("sort-bookmarks");

    // disable proceed to checkout button if nothing is in cart
    if (products.querySelectorAll(".prod").length === 0) {
        proceed.classList.add("disabled");
        proceed.style.pointerEvents = "none";
        proceed.href = "#";
    } else {
        proceed.classList.remove("disabled");
        proceed.style.pointerEvents = "auto";
        proceed.href = "payment.php";
    }

    // relaod when sort is changed
    sort_bookmarks.addEventListener("change", function (event) {
        document.getElementById("sortForm").submit();
    });

    // remove from cart
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

    // remove from likes
    remove_likes.forEach(function (button) {
        button.addEventListener("click", function () {
            let itemId = this.getAttribute("data-id");
            let params = "id=" + itemId;

            let config = {
                method: 'POST',
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params
            };

            fetch("removeFromLikes.php", config)
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
     * Show popup message
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