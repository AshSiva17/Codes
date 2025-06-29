<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to check if user already exists in the database
 */

$email = filter_input(INPUT_POST, "email", FILTER_SANITIZE_EMAIL);

include "../config/connect.php";

$cmd = "SELECT COUNT(*) FROM users WHERE `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$email];
$success = $stmt->execute($params);

if($success){
    $count = $stmt->fetchColumn();
    if ($count > 0) {
        // Account already exists
        $exists = true;
    } else {
        $exists = false;
    }

    echo json_encode($exists);
}
