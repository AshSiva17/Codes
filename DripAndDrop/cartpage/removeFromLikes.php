<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to remove item from user likes
 */

session_start();

include "../config/connect.php";

$id = filter_input(INPUT_POST, 'id', FILTER_SANITIZE_NUMBER_INT);
$email = $_SESSION["email"];

$cmd = "DELETE FROM `likes` WHERE `id`=? AND `email`=?";
$stmt = $dbh->prepare($cmd);
$params = [$id, $email];
$sucess = $stmt->execute($params);

if ($sucess) {
    $message = "Removed";
} else {
    $message = "Error trying to remove";
}

echo json_encode($message);
