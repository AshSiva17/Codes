<?php

include '../config/connect.php'; 

session_start();

$text = filter_input(INPUT_GET, "text", FILTER_SANITIZE_SPECIAL_CHARS);
$text = '%' . $text . '%';  

$userId = $_SESSION['email'] ?? null;
$likes = [];
$carts = [];

if ($userId) {
    // Get user likes
    $likesCommand = "SELECT `id` FROM `likes` WHERE `email`=?";
    $likesStmt = $dbh->prepare($likesCommand);
    $likesStmt->execute([$userId]);
    $likes = $likesStmt->fetchAll(PDO::FETCH_ASSOC);
    
    // Get user cart items
    $cartsCommand = "SELECT `id` FROM `carts` WHERE `email`=?";
    $cartsStmt = $dbh->prepare($cartsCommand);
    $cartsStmt->execute([$userId]);
    $carts = $cartsStmt->fetchAll(PDO::FETCH_ASSOC);
}

// Search for products
$command = "SELECT * FROM `listings` WHERE LOWER(`title`) LIKE LOWER(?)";
$stmt = $dbh->prepare($command);
$args = [$text];
$stmt->execute($args);

$products = $stmt->fetchAll(PDO::FETCH_ASSOC);
if (empty($products)) {
    echo json_encode([]);
    exit();
}


// Process product data
foreach ($products as &$product) {
    if (isset($product['filename'])) {
        $product['imageURL'] = '../images/' . $product['filename'];
    } else {
        $product['imageURL'] = '../images/default.jpg'; // Provide a default image if none exists
    }
    
    // Check if product is in user likes
    $product['inLikes'] = false;
    foreach ($likes as $likedItem) {
        if ($likedItem['id'] == $product['id']) {
            $product['inLikes'] = true;
            break;
        }
    }
    
    // Check if product is in user cart
    $product['inCart'] = false;
    foreach ($carts as $cartItem) {
        if ($cartItem['id'] == $product['id']) {
            $product['inCart'] = true;
            break;
        }
    }
}

echo json_encode($products);

?>
