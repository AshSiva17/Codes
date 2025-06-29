<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP upload listing information to database,
display message if it worked
 */

session_start();

if (!isset($_SESSION['loggedIn'])) {
    header("Location: logout.php");
    exit;
}

include "../config/connect.php";

$email = $_SESSION['email'];
$currentDate = date("Y-m-d");

$title = filter_input(INPUT_POST, 'title', FILTER_SANITIZE_SPECIAL_CHARS);
$category = filter_input(INPUT_POST, 'category', FILTER_SANITIZE_SPECIAL_CHARS);
$condition = filter_input(INPUT_POST, 'condition', FILTER_SANITIZE_SPECIAL_CHARS);
$description = filter_input(INPUT_POST, 'description', FILTER_SANITIZE_SPECIAL_CHARS);
$price = filter_input(INPUT_POST, 'price', FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION);
$quantity = filter_input(INPUT_POST, 'quantity', FILTER_SANITIZE_NUMBER_INT);

$fileName = $_FILES["image"]["name"];
$ext = pathinfo($fileName, PATHINFO_EXTENSION);
$tempName = $_FILES["image"]["tmp_name"];
$targetPath = "../images/" . $fileName;

if (move_uploaded_file($tempName, $targetPath)) {
    $cmd = "INSERT INTO `listings`(`email`, `title`, `description`, `category`, `cond`, `filename`, `price`, `quantity`, `date`) VALUES (?,?,?,?,?,?,?,?,?)";
    $stmt = $dbh->prepare($cmd);
    $params = [$email, $title, $description, $category, $condition, $fileName, $price, $quantity, $currentDate];
    $sucess = $stmt->execute($params);

    if ($sucess) {
        $message = "Your listing was uploaded";
    } else {
        $message = "Your listing couln't uplaod to database";
    }
} else {
    $message =  "file was not uploaded ";
}

?>

<!DOCTYPE html>
<html>

<head>
    <title>Create New Listing</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/uploadListing.css">
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
            <a href="/cartpage/cart.php">Cart</a>
            <a href="sell.php">Sell</a>
            <a href="../profilepage/profile.php">My Account</a>
        </nav>
    </header>

    <div id="content">
        <h2 class="sectionTitle">Basic Information</h2>
        <div id="show">
            <p id="message"><?= $message ?></p>
            <a href="sell.php" id="sellBtn">Return</a>
        </div>
    </div>

</body>

</html>