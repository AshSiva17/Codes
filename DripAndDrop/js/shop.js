/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for main menu page
 */

window.addEventListener("load", function () {

  let favs = document.querySelectorAll(".fav");
  let addToCart = document.querySelectorAll(".addToCart");
  let popUpOverlay = document.getElementById("popUpOverlay");
  let filters = document.getElementById("filters");
  let category = document.getElementById("category");
  let resetFilter = document.getElementById("resetFilter");
  let search = document.querySelector(".searchBar");

  let currentSearchResults = [];

  search.addEventListener("input", function (event) {
    let url = "search.php?text=" + search.value + "&len=" + search.value.length;

    fetch(url)
      .then(response => response.json())
      .then(display)
  })

  search.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
      fetch("searchProds.php?text=" + encodeURIComponent(search.value))
        .then(response => response.json())
        .then(products => {
          currentSearchResults = products;
          renderFilteredProducts(products);
          document.querySelector(".searchResults ul").style.display = "none";
        });
    }
  });


  //learned the following code from here: https://flaviocopes.com/how-to-add-event-listener-multiple-elements-javascript/
  favs.forEach(fav => {
    fav.addEventListener("click", function (event) {
      event.preventDefault();
      let itemId = this.getAttribute("data-id");

      // add to or remove intem from likes database
      if (fav.getAttribute("data-state") === "no") {
        fav.src = "../images/heartF.png";
        fav.setAttribute("data-state", "yes");

        let params = "id=" + itemId;

        let config = {
          method: 'POST',
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: params
        };

        fetch("addToLikes.php", config)

      } else {
        fav.src = "../images/heart.png";
        fav.setAttribute("data-state", "no");


        let params = "id=" + itemId;

        let config = {
          method: 'POST',
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: params
        };

        fetch("removeFromLikes.php", config)

      }
    });
  });

  // reset filters and categories
  resetFilter.addEventListener("click", function (event) {
    event.preventDefault(); // prevent default button behavior if it's in a form

    // Reset selects to default
    category.selectedIndex = 0;
    filters.selectedIndex = 0;

    location.reload();
  });

  // category and filter functions
  category.addEventListener("change", function (event) {
    document.getElementById("selections").submit();
  });

  filters.addEventListener("change", function (event) {
    document.getElementById("selections").submit();
  });

  // add item to user cart
  addToCart.forEach(function (button) {
    button.addEventListener("click", function () {
      let itemId = this.getAttribute("data-id");

      showPopUp();

      // add to carts database
      if (this.getAttribute("data-state") === "no") {
        this.setAttribute("data-state", "yes");
        this.innerHTML = "Remove from cart";
        this.classList.add("remove");


        let params = "id=" + itemId;

        let config = {
          method: 'POST',
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: params
        };

        fetch("addToCart.php", config)
          .then(response => response.text())
          .then(success)


      } else if (this.getAttribute("data-state") === "yes") {
        this.setAttribute("data-state", "no");
        this.classList.remove("remove");
        this.innerHTML = "Add to cart"


        let params = "id=" + itemId;

        let config = {
          method: 'POST',
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: params
        };

        fetch("removeFromCart.php", config)
          .then(response => response.text())
          .then(success)
      }
    });
  });

  /**
   * Show pop up message
   */
  function showPopUp() {
    popUpOverlay.style.display = "flex";

    let html = `<div id="popUp">
        <h3>Loading</h3>
        <img src="../images/loading.gif">
      </div>`;

    popUpOverlay.innerHTML = html;
  }

  /**
   * hide popUo messsage
   */
  function hidePopUp() {
    popUpOverlay.style.display = "none"
    popUpOverlay.innerHTML = "";
  }

  /**
   * Show confimation message
   * 
   * @param {text} text 
   * 
   */
  function success(text) {
    let html = `<div id="popUp">
        <h3>${text}</h3>
        <img src="../images/checkMark.png">
      </div>`;
    popUpOverlay.innerHTML = html;

    setTimeout(hidePopUp, 400);
  }
  /**
   * Display title of listing under search bar
   * 
   * @param {products} products 
   * 
   */
  function display(products) {
    //store products in array
    currentSearchResults = products;
    console.log(products);
    let resultsList = document.querySelector(".searchResults ul");
    resultsList.innerHTML = "";

    //nothing in the search bar
    if (search.value === "") {
      resultsList.style.display = "none";
      return
    }

    //display the ul containing search boxes
    resultsList.style.display = "block";
    resultsList.style.width = search.offsetWidth + "px";
    window.addEventListener("resize", () => {
      resultsList.style.width = search.offsetWidth + "px";
    });

    //text in the search bar does not match any items
    if (products.length === 0) {
      let li = document.createElement("li");
      li.textContent = "No results found";
      resultsList.appendChild(li);
    } else {
      //display items that match the searchs
      products.forEach(product => {
        let li = document.createElement("li");
        li.textContent = product.title;
        li.setAttribute("data-id", product.id);
        resultsList.appendChild(li);

        //for autofilling search bar
        li.addEventListener("click", function () {
          search.value = li.textContent;
          resultsList.style.display = "none";
          fetch("searchProds.php?text=" + encodeURIComponent(search.value))
            .then(response => response.json())
            .then(products => {
              currentSearchResults = products;
              renderFilteredProducts(products);
              document.querySelector(".searchResults ul").style.display = "none";
            });
        });
      });
    }
  }

  /**
   * Clear Producst then render them
   * 
   * @param {products} products 
   */
  function renderFilteredProducts(products) {
    let container = document.querySelector(".Products");
    container.innerHTML = ""; // clear existing products
    renderProducts(products); // load new products
  }

  /**
   * Render items 
   * 
   * @param {Array} products 
   */
  function renderProducts(products) {
    let container = document.querySelector(".Products");
    console.log(products);

    products.forEach(product => {
      console.log(product);
      let prodDiv = document.createElement("div");
      prodDiv.className = "prod";

      // Determine like button state
      let likeButtonState = product.inLikes ? "yes" : "no";
      let likeButtonImg = product.inLikes ? "../images/heartF.png" : "../images/heart.png";

      // Determine cart button state
      let cartButtonState = product.inCart ? "yes" : "no";
      let cartButtonText = product.inCart ? "Remove from cart" : "Add to cart";
      let cartButtonClass = product.inCart ? "addToCart remove" : "addToCart";

      // Format condition text
      let condition = {
        '1': 'Poor',
        '2': 'Fair',
        '3': 'Good',
        '4': 'Excellent',
        '5': 'Like-New'
      }[product.cond] || 'Unknown';

      prodDiv.innerHTML = `
      <div class="imageContent">
        <input type="image" src="${likeButtonImg}" class="fav" data-id="${product.id}" data-state="${likeButtonState}">
        <img src="${product.imageURL}">
      </div>
      <div class="productContent">
        <h3>${product.title}</h3>
        <p>
          <br>
          <strong>Condition:</strong> ${condition}<br>
          <strong>Category:</strong> ${product.category} <br>
          <br>
          <strong>Description:</strong> <br>
          ${product.description} <br>
          <br>
          <strong>Price:</strong> $${product.price} <br>
          <strong>Quantity:</strong> ${product.quantity} <br>
          <br>
          <strong>Seller Contact:</strong> ${product.email}
          <br>
        </p>
        <button class="${cartButtonClass}" data-id="${product.id}" data-state="${cartButtonState}">${cartButtonText}</button>
      </div>
      `;

      container.appendChild(prodDiv);
    });

    // Add event listeners to newly created like buttons
    let newFavs = document.querySelectorAll(".fav");
    newFavs.forEach(fav => {
      fav.addEventListener("click", function (event) {
        event.preventDefault();
        let itemId = this.getAttribute("data-id");

        // add to or remove item from likes database
        if (fav.getAttribute("data-state") === "no") {
          fav.src = "../images/heartF.png";
          fav.setAttribute("data-state", "yes");

          let params = "id=" + itemId;
          let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
          };

          fetch("addToLikes.php", config);
        } else {
          fav.src = "../images/heart.png";
          fav.setAttribute("data-state", "no");

          let params = "id=" + itemId;
          let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
          };

          fetch("removeFromLikes.php", config);
        }
      });
    });

    // Add event listeners to newly created cart buttons
    let newAddToCart = document.querySelectorAll(".addToCart");
    newAddToCart.forEach(function (button) {
      button.addEventListener("click", function () {
        let itemId = this.getAttribute("data-id");

        showPopUp();

        // add to carts database
        if (this.getAttribute("data-state") === "no") {
          this.setAttribute("data-state", "yes");
          this.innerHTML = "Remove from cart";
          this.classList.add("remove");

          let params = "id=" + itemId;
          let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
          };

          fetch("addToCart.php", config)
            .then(response => response.text())
            .then(success);
        } else if (this.getAttribute("data-state") === "yes") {
          this.setAttribute("data-state", "no");
          this.classList.remove("remove");
          this.innerHTML = "Add to cart";

          let params = "id=" + itemId;
          let config = {
            method: 'POST',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: params
          };

          fetch("removeFromCart.php", config)
            .then(response => response.text())
            .then(success);
        }
      });
    });
  }
});
