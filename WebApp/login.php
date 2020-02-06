<?php
$dir = "/samba/weerstation_share/users/";


if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    //check the userInput on any injections
    $username = checkInput($_POST["username"]);
    $password = checkInput($_POST["password"]);

    //get the hashed password from the file with username as name
    if(file_exists($dir.$username)) {
        $hash = file_get_contents($dir.$username);
    }
    
    //check userInput with hashed password
    if (password_verify($password, $hash)){
        header('Location: interface.php');
        session_start();
        $_SESSION["auth"]="true";
    } else {
        header('Location: index.php?error=true');
    }
}

function checkInput ($data) {
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);

    return $data;
}



