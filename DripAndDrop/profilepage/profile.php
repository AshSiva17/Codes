<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP obtain and display user information from database,
Allows to remove listing and remove payment logout and delte user
 */

session_start();

include "../config/connect.php";

$email = $_SESSION["email"];

$cmd = "SELECT * FROM `listings` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];
$success = $stmt->execute($params);


if ($success) {
    $listings = $stmt->fetchAll();
} else {
    echo "Error with executing for listings";
}

$cmd = "SELECT * FROM `payments` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];
$success = $stmt->execute($params);

if ($success) {
    $payments = $stmt->fetchAll();
} else {
    echo "Error with executing for listings";
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
<html>

<head>
    <title>Drip & Drop - My Account</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/profile.css">
    <script src="../js/profile.js"></script>
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
            <a href="../sellpage/sell.php">Sell</a>
            <a href="profile.php">My Account</a>
        </nav>
    </header>

    <div id="content">
        <h2>My Account</h2>
        <div id="profileHeader">
            <div>
                <img src="../images/user.png" id="profileImage">
            </div>
            <div id="information">
                <h1 id="profileName">Hello! <?= $_SESSION['firstName'] ?> <?= $_SESSION['lastName'] ?></h1>
                <p id="contact">Email address: <?= $_SESSION['email'] ?></p><br>
            </div>
            <div id="logoutDiv">
                <a href="../authen/logout.php" id="logout">logout</a>
            </div>
            <div id="logoutDiv">
                <button id="deleteUser">delete user</button>
            </div>
        </div>

        <div id="listings">
            <h3>Your Active Listings:</h3>
        </div>

        <div class="Products">
            <?php foreach ($listings as $item): ?>
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
                        <p> <br>
                            Condition: <?= ['1' => 'Poor', '2' => 'Fair', '3' =>
                                        'Good', '4' => 'Excellent', '5' => 'Like-New'][$item['cond']] ?? 'Unknown' ?><br>
                            Category: <?= $item['category'] ?> <br>
                            <br>
                            Price: $<?= $item['price'] ?> <br>
                            Quantity: <?= $item['quantity'] ?> <br>
                        </p>
                    </div>
                    <div id="btn">
                        <button class="remove" data-id="<?= $item['id'] ?>">remove</button>
                    </div>
                </div>
            <?php endforeach ?>
        </div>

        <h3>Payment Methods:</h3>
        <div id="savedCardDisplay">
            <?php foreach ($cards as $card): ?>
                <div class="card">
                    <img src="../images/visa.png">
                    <div>
                        <h4>Visa</h4>
                        <p>Card ending in **** <?= substr($card['cardnumber'], -4) ?></p>
                    </div>
                    <button class="removeCard" data-id="<?= $card['id'] ?>">remove</button>
                </div>
            <?php endforeach ?>
        </div>

        <div id="popUpOverlay">
            <form id="popUp">
                <h3>Confirmation</h3>
                <p>Do you want to remove this item?</p>
                <div id="popupButtons">
                    <input type="hidden" id="hiddenValue" value="">
                    <button class="popUpBtn" type="submit" id="yesButton">Yes</button>
                    <button class="popUpBtn" id="noButton">No</button>
                </div>
            </form>
        </div>

        <div id="popUpOverlayDelete">
            <form id="popUpDelete" method="POST" action="deleteUser.php">
                <h3>Confirmation</h3>
                <p>Do you want to delete user?</p>
                <div id="popupButtons">
                    <button class="popUpBtn" type="submit" id="yesButtonDelete">Yes</button>
                    <button class="popUpBtn" id="noButtonDelete">No</button>
                </div>
            </form>
        </div>
    </div>
</body>

</html>