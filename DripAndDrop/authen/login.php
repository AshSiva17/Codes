<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to validate user information and login information
 */

session_start();

// Check login information
$email = filter_input(INPUT_POST, "email", FILTER_SANITIZE_SPECIAL_CHARS);
$password = filter_input(INPUT_POST, "password");

// Initialize response array for JSON
$response = [
    'success' => false,
    'message' => '',
    'redirect' => ''
];

if ($email && $password) {
    include "../config/connect.php";

    $cmd = "SELECT * FROM `users` WHERE `email`=?";
    $stmt = $dbh->prepare($cmd);
    $stmt->execute([$email]);

    if ($user = $stmt->fetch()) {
        // Email was found
        if (password_verify($password, $user['password'])) {
            // Access granted
            $_SESSION["email"] = $email;
            $_SESSION["firstName"] = $user["firstName"];
            $_SESSION["lastName"] = $user["lastName"];
            $_SESSION["loggedIn"] = true;

            // set sort
            $_SESSION["sort_bookmarks"] = 'newest';
            $_SESSION["sort_filter"] = '';
            $_SESSION['sort_category'] = '*';

             // Set success response
             $response['success'] = true;
             $response['redirect'] = '../homepage/menu.php';
        } else {
            // Invalid password
            session_unset();
            session_destroy();
            $response['message'] = "Invalid email or password";
        }
    } else {
        // Email not found
        session_unset();
        session_destroy();
        $response['message'] = "Invalid email or password";
    }
} else {
    // Missing email or password
    $response['message'] = "Please enter both email and password";
}

// Always return JSON response
header('Content-Type: application/json');
echo json_encode($response);
exit();
