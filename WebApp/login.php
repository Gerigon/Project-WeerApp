<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    //check the userInput on any injections
    $username = checkInput($_POST["username"]);
    $password = checkInput($_POST["password"]);

    //check userInput with dataBase entries
    if ($username == "panda" && $password == 12345 ){
        header('Location: interface.php');
        session_start();
        $_SESSION["auth"]="true";
    }else{
        header('Location: index.php?error=true');
    }
}

function checkInput ($data) {
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);

    return $data;
}



