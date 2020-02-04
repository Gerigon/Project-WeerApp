<!DOCTYPE html>
<html lang="nl">
<head>
    <?php include "head.php" ?>
    <title>Welcome</title>
</head>
<body>
    <div class="container-fluid padding-zero">
        <div class="row">
            <div class="d-none d-lg-block col-md-1 col-md-9 padding-zero" id="home_screen_picture">

            </div>
            <div class="col-11 col-md-3 d-inline-block" id="form_holder">
                <div class="" id="login_form">
                    <div id="logo_holder" class="text-center"><img class="img-fluid" src="images/breedingCenter.jpg"></div>
                    <h1>Welcome</h1><br>
                    <div style="width: 20vw; height: 1px"></div>
                    <?php if(isset($_GET["error"])){if($_GET["error"] == "true"){echo "<div id=\"error\"> username or password is wrong </div>";}}; ?>
                <form method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input name="username" type="text" class="form-control form-control-lg" id="exampleInputEmail1" aria-describedby="emailHelp" autofocus>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input name="password" type="password" class="form-control form-control-lg" id="exampleInputPassword1">
                    </div>
                    <button type="submit" class="btn orangeButton" formaction="login.php">Log In</button>
                </form>
                </div>
            </div>
        </div>
    </div>
</body>

