<?php
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
html for cart page
display user cart and likes 
 */

session_start();

include "../config/connect.php";

// get sorting method
if (isset($_POST['sort_bookmarks'])) {
    $_SESSION['sort_bookmarks'] = $_POST['sort_bookmarks'];
    header("Location: cart.php");
    exit();
}

// get sorted listings
$sort = $_SESSION['sort_bookmarks'] ?? 'newest';

if ($sort === 'newest') {
    $cmd = "SELECT * FROM `listings` ORDER BY `date` DESC";
} else {
    $cmd = "SELECT * FROM `listings` ORDER BY `date` ASC";
}

$stmt = $dbh->prepare($cmd);
$success = $stmt->execute();

if ($success) {
    $listingsSorted = $stmt->fetchAll();
} else {
    echo "Error with executing for listings";
}

$cmd = "SELECT * FROM `listings`";
$stmt = $dbh->prepare($cmd);
$success = $stmt->execute();

if ($success) {
    $listings = $stmt->fetchAll();
} else {
    echo "Error with executing for listings";
}

$email = $_SESSION["email"];

// get carts
$cmd = "SELECT `id` FROM `carts` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];
$totalPrice = 0;

if ($stmt->execute($params)) {
    $carts = $stmt->fetchAll();
} else {
    echo "Error with executing for carts";
}

foreach ($listings as $l) {
    foreach ($carts as $c) {
        if ($c['id'] == $l['id']) {
            $totalPrice += $l['price'];
        }
    }
}

// get liked items
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
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Drip and Drop - Cart & Bookmarks</title>
    <link rel="stylesheet" href="../css/cart.css">
    <script src="../js/cart.js"></script>
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
        <h2>Your Cart & Bookmarks</h2>
        <div class="cart-bookmark-container">
            <!-- Shopping Cart Section -->
            <section class="cart-section">
                <h3>Shopping Cart</h3>
                <div id="cart-items"></div>
                <div class="cart-actions">
                    <p>Total: <span id="cart-total">$<?= $totalPrice ?></span></p>
                    <a href="payment.php" class="btn-primary" id="proceed-to-checkout">Proceed to Checkout</a>
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
                                        Quantity: <?= $item['quantity'] ?> <br>
                                        <br>
                                        Seller Contact: <?= $item['email'] ?>
                                        <br>
                                    </p>
                                </div>
                                <button class="remove carts" data-id="<?= $item['id'] ?>">remove</button>
                            </div>
                        <?php } ?>
                    <?php endforeach ?>
                </div>
            </section>

            <!-- Bookmarked Items Section -->
            <section class="bookmark-section">
                <h3>Bookmarked Items</h3>
                <div class="sort-options">
                    <form id="sortForm" method="post" action="cart.php">
                        <label for="sort-bookmarks">Sort by: </label>
                        <select id="sort-bookmarks" name="sort_bookmarks" onchange="this.form.submit()">
                            <option value="newest" <?= ($_SESSION['sort_bookmarks'] === 'newest') ? 'selected' : '' ?>>Newest</option>
                            <option value="oldest" <?= ($_SESSION['sort_bookmarks'] === 'oldest') ? 'selected' : '' ?>>Oldest</option>
                        </select>
                    </form>
                </div>
                <div class="Products">
                    <?php foreach ($listingsSorted as $item): ?>

                        <?php
                        // check if item is already in user cart
                        $inLikes = false;
                        foreach ($likes as $likedItem) {
                            if ($likedItem['id'] == $item['id']) {
                                $inLikes = true;
                                break;
                            }
                        }

                        if ($inLikes == true) { ?>
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
                                        Quantity: <?= $item['quantity'] ?> <br>
                                        <br>
                                        Seller Contact: <?= $item['email'] ?>
                                        <br>
                                    </p>
                                </div>
                                <button class="remove likes" data-id="<?= $item['id'] ?>">remove</button>
                            </div>
                        <?php } ?>
                    <?php endforeach ?>
                </div>
            </section>
        </div>
        <div id="popUpOverlay"></div>
    </main>
</body>

</html>