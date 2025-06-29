<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP for sell page,
has form that uploads new listing to listings database
 */

session_start();

?>

<!DOCTYPE html>
<html>

<head>
    <title>Drip & Drop - sell</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/sell.css">
    <script src="../js/sell.js"></script>
</head>

<body>
    <header>
        <?php
        if (!(isset($_SESSION['loggedIn']))) {
            header("Location: ../authen/logout.php");
        }
        ?>
        <div class="logo">
            <h1>Drip & Drop</h1>
            <img src="../images/Drip.png" id="headImg">
        </div>
        <nav>
            <a href="../homepage/menu.php">Home</a>
            <a href="../cartpage/cart.php">Cart</a>
            <a href="sell.php">Sell</a>
            <a href="../profilepage/profile.php">My Account</a>
        </nav>
    </header>

    <div id="content">
        <h2 id="pageTitle">Create New Listing</h2>

        <div id="formContainer">
            <form id="listingForm" method="POST" action="uploadListing.php" enctype="multipart/form-data">
                <div class="formSection">
                    <h2 class="sectionTitle">Basic Information</h2>

                    <div class="formGroup">
                        <label for="title">Listing Title</label>
                        <input type="text" id="title" name="title" class="formControl" placeholder="Enter a clear, descriptive title" required>
                    </div>

                    <div class="formGroup">
                        <label for="category">Category</label>
                        <select id="category" name="category" class="selectControl" required>
                            <option value="">Select Category</option>
                            <option value="sweater">Sweater</option>
                            <option value="shirt">Shirt</option>
                            <option value="pants">Pants</option>
                            <option value="shorts">Shorts</option>
                            <option value="shoes">Shoes</option>
                            <option value="other">Other</option>
                        </select>
                    </div>

                    <div class="formGroup">
                        <label for="condition">Conditions</label>
                        <select id="condition" name="condition" class="selectControl" required>
                            <option value="">Select Conditions</option>
                            <option value="5">Like New</option>
                            <option value="4">Excellent</option>
                            <option value="3">Good</option>
                            <option value="2">Fair</option>
                            <option value="1">Poor</option>
                        </select>
                    </div>

                    <div class="formGroup">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" class="formControl" placeholder="Provide a short description about your item." required></textarea>
                    </div>

                    <div class="formSection">
                        <h2 class="sectionTitle">Images</h2>
                        <p class="subLabel">Upload a high-qulaity image.</p>

                        <div class="imageUploadContainer">
                            <div class="imageUploadBox">
                                <input type="file" accept="image/*" id="inputImg" name="image" required>
                                <img id="preview" src="#" alt="Image Preview" style="display:none; max-width:300px;" />
                                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                    <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4m14-7l-5-5-5 5m5-5v12" />
                                </svg>
                                <p>Click to upload</p>
                            </div>
                        </div>
                    </div>

                    <div class="formSection">
                        <h2 class="sectionTitle">Pricing & Inventory</h2>

                        <div class="fieldRow">
                            <div class="formGroup">
                                <label for="price">Price</label>
                                <div class="priceInputGroup">
                                    <span>$</span>
                                    <input type="number" id="price" name="price" class="formControl" step="0.01" min="0" placeholder="0.00" required>
                                </div>
                            </div>

                            <div class="formGroup">
                                <label for="quantity">Quantity Available</label>
                                <input type="number" id="quantity" name="quantity" class="formControl" min="1" value="1" required>
                            </div>
                        </div>
                    </div>

                    <div class="actionButtons">
                        <button type="submit" class="submitBtn">Publish Listing</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</body>

</html>