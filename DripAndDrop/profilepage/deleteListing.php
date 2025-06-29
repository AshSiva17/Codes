<?php
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to delete listing from listing datatable 
 */

include "../config/connect.php";

$id = filter_input(INPUT_POST, 'id', FILTER_SANITIZE_NUMBER_INT);

$cmd = "DELETE FROM `listings` WHERE `id`=?";
$stmt = $dbh->prepare($cmd);
$params = [$id];
$sucess = $stmt->execute($params);

$cmd = "DELETE FROM `carts` WHERE `id`=?";
$stmt = $dbh->prepare($cmd);
$params = [$id];
$stmt->execute($params);

$cmd = "DELETE FROM `likes` WHERE `id`=?";
$stmt = $dbh->prepare($cmd);
$params = [$id];
$stmt->execute($params);

echo json_encode($sucess);
