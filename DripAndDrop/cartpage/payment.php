<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
Payment and checkout page,
obtain and display payments and carts of user from database
have a form to upload payments
 */

session_start();

include "../config/connect.php";

$email = $_SESSION["email"];

$cmd = "SELECT * FROM `listings`";
$stmt = $dbh->prepare($cmd);
$success = $stmt->execute();

if ($success) {
  $listings = $stmt->fetchAll();
} else {
  echo "Error with executing for listings";
}

$cmd = "SELECT `id` FROM `carts` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];
$totalPrice = 0;

if ($stmt->execute($params)) {
  $carts = $stmt->fetchAll();
} else {
  echo "Error with executing for carts";
}

$ids = []; // ids in cart

foreach ($listings as $l) {
  foreach ($carts as $c) {
    if ($c['id'] == $l['id']) {
      $totalPrice += $l['price'];
      $ids[] = $l['id'];
    }
  }
}


$cmd = "SELECT `cardnumber`, `id` FROM `payments` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];

if ($stmt->execute($params)) {
  $cards = $stmt->fetchAll();
} else {
  echo "Error with executing for carts";
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Drip and Drop - Checkout</title>
  <link rel="stylesheet" href="../css/payment.css">
  <script src="../js/payment.js"></script>
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
      <a href="cart.php">Cart</a>
      <a href="../sellpage/sell.php">Sell</a>
      <a href="../profilepage/profile.php">My Account</a>
    </nav>
  </header>
  <main>
    <h2>Checkout</h2>
    <div class="checkoutcontainer" id="checkoutcontainer">
      <section class="cartsummary">
        <h3>Order Summary</h3>
        <div id="cartitems"></div>
        <div class="total">
          <p>Total: <span id="carttotal">$<?= $totalPrice ?></span></p>
        </div>
        <div id="Products">
          <?php foreach ($listings as $item): ?>

            <?php
            // check if item is already in user cart
            $inCart = false;
            foreach ($carts as $cartItem) {
              if ($cartItem['id'] == $item['id']) {
                $inCart = true;
                break;
              }
            }


            if ($inCart == true) { ?>

              <div class="prod">
                <div class="imageContent">
                  <?php
                  $filename = $item['filename'];
                  $imageUrl = '../images/' . $filename;
                  ?>
                  <img src="<?= $imageUrl ?>">
                </div>

                <div class="productContent">
                  <h3><?= $item['title'] ?></h3>
                  <p>
                    Condition: <?= ['1' => 'Poor', '2' => 'Fair', '3' =>
                                'Good', '4' => 'Excellent', '5' => 'Like-New'][$item['cond']] ?? 'Unknown' ?><br>
                    Category: <?= $item['category'] ?> <br>
                    <br>
                    Price: $<?= $item['price'] ?> <br>
                    <br>
                    Seller Contact: <?= $item['email'] ?>
                    <br>
                  </p>
                </div>
                <button class="remove" data-id="<?= $item['id'] ?>">remove</button>
              </div>

            <?php } ?>

          <?php endforeach ?>
        </div>
      </section>
      <section class="paymentform" id="paymentForm">
        <h3>Payment Details</h3>
        <div id="paymentChoice">
          <label for="savedCard">Saved Cards</label>
          <input type="checkbox" id="savedCard">
          <label for="newCard">New Cards</label>
          <input type="checkbox" id="newCard">
        </div>

        <div id="savedCardDisplay">
          <?php foreach ($cards as $card): ?>
            <div class="card">
              <img src="../images/visa.png">
              <div>
                <h4>Visa</h4>
                <p>Card ending in **** <?= substr($card['cardnumber'], -4) ?></p>
              </div>
              <button type="submit" class="useCard" data-id="<?php $card['id'] ?>">use</button>
            </div>
          <?php endforeach ?>
        </div>

        <form id="paymentform">
          <label for="cardnumber">Card Number</label>
          <input type="text" id="cardnumber" placeholder="1234 5678 9012 3456" required>
          <div class="formrow">
            <div class="formgroup">
              <label for="expiry">Expiry Date</label>
              <input type="text" id="expiry" placeholder="MM/YY" required>
            </div>
            <div class="formgroup">
              <label for="cvv">CVV</label>
              <input type="text" id="cvv" placeholder="123" required>
            </div>
          </div>
          <h4>Billing Address</h4>
          <div id="billingAddress">
            <label for="street">Street</label>
            <input type="text" id="street" placeholder="123 Main St" required>
            <label for="city">City</label>
            <input type="text" id="city" placeholder="City" required>
            <label for="zip">ZIP</label>
            <input type="text" id="zip" placeholder="A9A 9A9" required>
          </div>
          <div class="savepayment">
            <input type="checkbox" id="savepaymentmethod" name="savepayment">
            <label for="savepaymentmethod">Save payment method for future purchases</label>
          </div>
          <input type="hidden" id="cart_ids" value='<?= json_encode($ids) ?>'>
          <button type="submit" id="btnprimary">Confirm Purchase</button>
        </form>
      </section>
    </div>
    <div id="checkOutMessage"></div>
    <div id="popUpOverlay"></div>
  </main>

</body>

</html>