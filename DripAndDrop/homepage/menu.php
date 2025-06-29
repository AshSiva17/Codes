<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
Main menu for web page,
on load, get inforamtion and display for listings, likes, and carts depending on user
 */

session_start();

include "../config/connect.php";

// get sorting method

if (isset($_POST['sort_filter']) || isset($_POST['sort_category'])) {
  $_SESSION['sort_filter'] = $_POST['sort_filter'] ?? '';
  $_SESSION['sort_category'] = $_POST['sort_category'] ?? '';
  header("Location: menu.php");
  exit();
}


$sort = $_SESSION['sort_filter'] ?? '';
$category = $_SESSION['sort_category'] ?? '';
$validCategories = ['sweater', 'shirt', 'pants', 'shorts', 'shoes', 'other'];
$cmd = "SELECT * FROM `listings`";

// Add WHERE clause 
if (in_array($category, $validCategories)) {
  $cmd .= " WHERE `category` = :category";
}

// Then add ORDER BY clause
if ($sort === 'newest') {
  $cmd .= " ORDER BY `date` DESC";
} else if ($sort === 'oldest') {
  $cmd .= " ORDER BY `date` ASC";
} else if ($sort === 'priceDesc') {
  $cmd .= " ORDER BY `price` DESC";
} else if ($sort === 'priceAsc') {
  $cmd .= " ORDER BY `price` ASC";
} else if ($sort === 'condAsc') {
  $cmd .= " ORDER BY `cond` DESC";
} else if ($sort === 'condDes') {
  $cmd .= " ORDER BY `cond` ASC";
}

$stmt = $dbh->prepare($cmd);

if (in_array($category, $validCategories)) {
  $stmt->bindParam(':category', $category);
}

$success = $stmt->execute();

if ($success) {
  $listings = $stmt->fetchAll();
} else {
  echo "Error with executing for listings";
}

$email = $_SESSION["email"];

$cmd = "SELECT `id` FROM `carts` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];

if ($stmt->execute($params)) {
  $carts = $stmt->fetchAll();
} else {
  echo "Error with executing for carts";
}

$cmd = "SELECT `id` FROM `likes` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];

if ($stmt->execute($params)) {
  $likes = $stmt->fetchAll();
} else {
  echo "Error with executing for carts";
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/style.css">
  <script src="../js/shop.js"></script>
  <title>Drip & Drop - Home</title>
</head>

<body>
  <?php
  if (!(isset($_SESSION['loggedIn']))) {
    header("Location: ../authen/logout.php");
  }
  ?>
  <header>
    <div class="logo">
      <h1>Drip & Drop</h1>
      <img src="../images/Drip.png" id="headImg">
    </div>
    <nav>
      <a href="menu.php">Home</a>
      <a href="../cartpage/cart.php">Cart</a>
      <a href="../sellpage/sell.php">Sell</a>
      <a href="../profilepage/profile.php">My Account</a>
    </nav>
  </header>

  <div class="filters">
    <div class="search">
      <div class="sBox">
        <input class="searchBar" type="text" placeholder="Search" name="search" minlength="2">
        <div class="searchResults">
          <ul>
            <li></li>
          </ul>
        </div>
      </div>
    </div>
    <form id="selections" method="POST" action="menu.php">
      <div id="filterSelections">
        <label for="filters">Filters: </label>
        <select id="filters" name="sort_filter" class="selectControl">
          <option value="" <?= ($_SESSION['sort_filter'] === '') ? 'selected' : '' ?>>Select Filter</option>
          <option value="priceDesc" <?= ($_SESSION['sort_filter'] === 'priceDesc') ? 'selected' : '' ?>>Price: High to Low</option>
          <option value="priceAsc" <?= ($_SESSION['sort_filter'] === 'priceAsc') ? 'selected' : '' ?>>Price: Low to High</option>
          <option value="condAsc" <?= ($_SESSION['sort_filter'] === 'condAsc') ? 'selected' : '' ?>>Condition: High to Low</option>
          <option value="condDes" <?= ($_SESSION['sort_filter'] === 'condDes') ? 'selected' : '' ?>>Condition: Low to High</option>
          <option value="newest" <?= ($_SESSION['sort_filter'] === 'newest') ? 'selected' : '' ?>>Newest</option>
          <option value="oldest" <?= ($_SESSION['sort_filter'] === 'oldest') ? 'selected' : '' ?>>Oldest</option>
        </select>
      </div>

      <div id="categorySelections">
        <label for="category">Category: </label>
        <select id="category" name="sort_category" class="selectControl">
          <option value="" <?= ($_SESSION['sort_category'] === '') ? 'selected' : '' ?>>Select Category</option>
          <option value="sweater" <?= ($_SESSION['sort_category'] === 'sweater') ? 'selected' : '' ?>>Sweater</option>
          <option value="shirt" <?= ($_SESSION['sort_category'] === 'shirt') ? 'selected' : '' ?>>Shirt</option>
          <option value="pants" <?= ($_SESSION['sort_category'] === 'pants') ? 'selected' : '' ?>>Pants</option>
          <option value="shorts" <?= ($_SESSION['sort_category'] === 'shorts') ? 'selected' : '' ?>>Shorts</option>
          <option value="shoes" <?= ($_SESSION['sort_category'] === 'shoes') ? 'selected' : '' ?>>Shoes</option>
          <option value="other" <?= ($_SESSION['sort_category'] === 'other') ? 'selected' : '' ?>>Other</option>
        </select>
      </div>

      <button id="resetFilter" <?= $_SESSION['sort_category'] = '' ?>
        <?= $_SESSION['sort_filter'] = '' ?>type="button">Reset</button>
    </form>

  </div>


  <div class="Products" id="Products">
    <h1>Featured Listings</h1>

    <?php foreach ($listings as $item): ?>
      <div class="prod">

        <div class="imageContent">
          <?php
          $filename = $item['filename'];
          $imageUrl = '../images/' . $filename;

          // check if item is already user likes
          $inLikes = false;
          foreach ($likes as $likedItem) {
            if ($likedItem['id'] == $item['id']) {
              $inLikes = true;
              break;
            }
          }

          if ($inLikes) {
            $buttonState = "yes";
            $buttonImg = "../images/heartF.png";
          } else {
            $buttonState = "no";
            $buttonImg = "../images/heart.png";
          }

          ?>
          <input type="image" src="<?= $buttonImg ?>" class="fav" data-id="<?= $item['id'] ?>" data-state="<?= $buttonState ?>">
          <img src="<?= $imageUrl ?>">
        </div>

        <div class="productContent">
          <h3><?= $item['title'] ?></h3>
          <p>
            <br>
            <strong>Condition:</strong> <?= ['1' => 'Poor', '2' => 'Fair', '3' =>
                                        'Good', '4' => 'Excellent', '5' => 'Like-New'][$item['cond']] ?? 'Unknown' ?><br>
            <strong>Category:</strong> <?= $item['category'] ?> <br>
            <br>
            <strong>Description:</strong> <br>
            <?= $item['description'] ?> <br>
            <br>
            <strong>Price:</strong> $<?= $item['price'] ?> <br>
            <strong>Quantity</strong> <?= $item['quantity'] ?> <br>

            <br>
            <strong>Seller Contact:</strong> <?= $item['email'] ?>
            <br>
          </p>

          <?php
          // check if item is already in user cart
          $inCart = false;
          foreach ($carts as $cartItem) {
            if ($cartItem['id'] == $item['id']) {
              $inCart = true;
              break;
            }
          }

          if ($inCart) {
            $buttonState = "yes";
            $buttonText = "Remove from cart";
            $buttonClass = "addToCart remove";
          } else {
            $buttonState = "no";
            $buttonText = "Add to cart";
            $buttonClass = "addToCart";
          }
          ?>
          <button class="<?= $buttonClass ?>" data-id="<?= $item['id'] ?>" data-state="<?= $buttonState ?>"><?= $buttonText ?></button>
        </div>
      </div>
    <?php endforeach ?>
    <div id="popUpOverlay"></div>
  </div>

</body>

</html>