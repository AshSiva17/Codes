<?php

/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
PHP to process transacitons,
-1 quantity for item, if quatity==0 then delet listing
remove item from user likes and carts
 */

session_start();

include "../config/connect.php";


$ids = filter_input(INPUT_POST, 'ids', FILTER_DEFAULT, FILTER_REQUIRE_ARRAY);
$email = $_SESSION['email'];

if (!empty($email) && is_array($ids)) {
    foreach ($ids as $id) {
        // Update quantity in listings
        $cmd = "UPDATE `listings` SET `quantity`=`quantity`-1 WHERE `id`=?";
        $stmt = $dbh->prepare($cmd);
        $success = $stmt->execute([$id]); // Note: fixed variable name from $sucess to $success

        if ($success) {
            $message = "Thank you for your purchase";

             // Check if quantity is now 0, and if so, delete the listing
             $checkCmd = "SELECT `quantity` FROM `listings` WHERE `id`=?";
             $checkStmt = $dbh->prepare($checkCmd);
             $checkStmt->execute([$id]);
             $currentQuantity = $checkStmt->fetchColumn();
             
             if ($currentQuantity <= 0) {
                 // Delete the listing if quantity is 0 or less
                 $deleteCmd = "DELETE FROM `listings` WHERE `id`=?";
                 $deleteStmt = $dbh->prepare($deleteCmd);
                 $deleteStmt->execute([$id]);

                 // Delete from likes
                $deleteLikesCmd = "DELETE FROM `likes` WHERE `id`=?";
                $deleteLikesStmt = $dbh->prepare($deleteLikesCmd);
                $deleteLikesStmt->execute([$id]);
                
                // Delete from carts (for all users who might have it)
                $deleteCartsCmd = "DELETE FROM `carts` WHERE `id`=?";
                $deleteCartsStmt = $dbh->prepare($deleteCartsCmd);
                $deleteCartsStmt->execute([$id]);
             }
        } else {
            $message = "Could not update database";
        }

        // Delete from cart
        $cmd = "DELETE FROM `carts` WHERE `email`=? AND `id`=?"; // Fixed parameter order
        $stmt = $dbh->prepare($cmd);
        $stmt->execute([$email, $id]); // Fixed parameter order
    }
} else {
    $message = "No IDs received or user not logged in.";
}

echo json_encode($message);


