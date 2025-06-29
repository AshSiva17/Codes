<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP replace old user password with new one,
display message if worked
 */

$email = filter_input(INPUT_POST, "email", FILTER_SANITIZE_EMAIL);
$rawPassword = filter_input(INPUT_POST, "password");

$hashedPassword = password_hash($rawPassword, PASSWORD_DEFAULT);

include "../config/connect.php";

$cmd = "SELECT * FROM users WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$insertParams = [$email];

if ($stmt->execute($insertParams)) {
    $user = $stmt->fetch(); // Fetch one result
    if ($user) {
        // Step 2: Update the password
        $updateCmd = "UPDATE users SET `password`=? WHERE `email`=?";
        $updateStmt = $dbh->prepare($updateCmd);
        $updateParams = [$hashedPassword, $email];

        if ($updateStmt->execute($updateParams)) {
            $message = "Password successfully updated for $email.";
        } else {
            $message = "Failed to update password. Please try again.";
        }
    } else {
        echo $user;
        $message = "Account doesn't exist for email $email.";
    }
} else {
    $message = "Database error.";
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