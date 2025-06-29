<?php
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
Upload new user to users datatable and display html response
 */

// check login information first
$email = filter_input(INPUT_POST, "email", FILTER_SANITIZE_EMAIL);
$firstName = filter_input(INPUT_POST, 'firstName', FILTER_SANITIZE_SPECIAL_CHARS);
$lastName = filter_input(INPUT_POST, 'lastName', FILTER_SANITIZE_SPECIAL_CHARS);
$birthday = filter_input(INPUT_POST, 'birthday', FILTER_SANITIZE_SPECIAL_CHARS);
$rawPassword = filter_input(INPUT_POST, "password");

$hashedPassword = password_hash($rawPassword, PASSWORD_DEFAULT);

include "../config/connect.php";

$cmd = "INSERT INTO `users`(`email`, `firstName`, `lastName`, `birthday`, `password`) VALUES (?, ?, ?, ?, ?)";
$stmt = $dbh->prepare($cmd);
$insertParams = [$email, $firstName, $lastName, $birthday, $hashedPassword];

if ($stmt->execute($insertParams)) {
    $message = "Success in making account";
} else {
    $message = "Error in creating account";
}
?>
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
        <p id="message"><?= $message ?></p>
        <a href="login.html" id="logBtn">Exit</a>
    </div>
    <div id="footer">
        &copy; Jemuel, Ashwin, Omar, Shourya, McMaster University 2025
    </div>
</body>

</html>