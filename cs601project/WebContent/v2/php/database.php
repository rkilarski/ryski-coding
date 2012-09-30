<?php
    $dsn = 'mysql:host=localhost;dbname=chickenrice';
    $username = 'web_user';
    $password = 'doyoufeellikechickentonight';

    try {
        $db = new PDO($dsn, $username, $password);
    } catch (PDOException $e) {
        $error_message = $e->getMessage();
        include('database_error.php');
        exit();
    }
?>