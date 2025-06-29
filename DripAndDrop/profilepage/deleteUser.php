<?php

/**
 author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP delete user from users and remove user carts likes and listings
 */

include "../config/connect.php";

session_start();

$email = $_SESSION['email'];

// Get all listing IDs belonging to this user
$cmd = "SELECT `id` FROM `listings` WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$stmt->execute([$email]);
$listing_Ids = $stmt->fetchAll(PDO::FETCH_COLUMN);

try {
    $dbh->beginTransaction();

    // delete all of user listings from listings, carts, and likes
    foreach ($listing_Ids as $id) {
        $cmd = "DELETE FROM `carts` WHERE `id`=?";
        $stmt = $dbh->prepare($cmd);
        $stmt->execute([$id]);

        $cmd = "DELETE FROM `likes` WHERE `id`=?";
        $stmt = $dbh->prepare($cmd);
        $stmt->execute([$id]);

        $cmd = "DELETE FROM `listings` WHERE `id`=?";
        $stmt = $dbh->prepare($cmd);
        $stmt->execute([$id]);
    }

    // Delete the users payments
    $cmd = "DELETE FROM `payments` WHERE `email`=?";
    $stmt = $dbh->prepare($cmd);
    $stmt->execute([$email]);

    // Delete the users likes
    $cmd = "DELETE FROM `likes` WHERE `email`=?";
    $stmt = $dbh->prepare($cmd);
    $stmt->execute([$email]);

    // Delete the users cart
    $cmd = "DELETE FROM `carts` WHERE `email`=?";
    $stmt = $dbh->prepare($cmd);
    $stmt->execute([$email]);

    // Delete the user
    $cmd = "DELETE FROM `users` WHERE `email`=?";
    $stmt = $dbh->prepare($cmd);
    $stmt->execute([$email]);

    $dbh->commit();
    session_unset();
    session_destroy();
} catch (Exception $e) {
    $dbh->rollBack();
    echo "Error: " . $e->getMessage();
}
?>
<!DOCTYPE html>
<html>

<!DOCTYPE html>

<html>

<head>
    <title>Drip & Drop</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/createAccount.css">
</head>

<body>
    <div id="header">
        <h1>Drip&Drop</h1>
        <img src="../images/Drip.png" id="headerImg">
    </div>

    <div id="content">
        <p id="message">User was deleted</p>
        <a href="../authen/login.html" id="logBtn">Exit</a>
    </div>
    <div id="footer">
        &copy; Jemuel, Ashwin, Omar, Shourya, McMaster University 2025
    </div>
</body>

</html>