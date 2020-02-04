<?php
session_start();
if ($_SESSION["auth"] != "true") {
    header('Location: index.php');
}else {
    ?>


<!DOCTYPE html>
<html lang="nl">
    <head>
        <?php include "head.php" ?>
        <title>interface</title>
    </head>
    <body>
        <div id="background" class="container-fluid padding-zero">
            <div id="nav_bar" class="col-12 col-md-5 row">
                <div class="col padding-zero"><a  type="button" class="btn" id="logout_button" href="logout.php"><img class="icon" src="images/icons8-shutdown.png">Log Out</a></div>
                <div class=" col theme-switch-wrapper">
                    <label class="theme-switch" for="checkbox">
                        <input type="checkbox" id="checkbox" />
                        <div class="slider round"></div>
                    </label>
                </div>
                <div  id="date" class="col padding-zero text-right"></div>
            </div>

                <div id="content" class="row container-fluid">
                    <div class="col-0 col-md-8 d-inline-block">
                        <div  class="col graph h-100">
                            <div class="text-center w-100 btn-group">
                                <div class="w-100">
                                    <button class="btn orangeButton btn-sm dropdown-toggle dropDown_left" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Ninchan Linqui Stations: <a id="selectedStation_tmp_l">selected</a>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" id="chong_tmp_l">chong</a>
                                        <a class="dropdown-item" id="ching_tmp_l">ching</a>
                                        <a class="dropdown-item" id="ping_tmp_l">ping</a>
                                    </div>
                                </div>
                                <div class="w-100">
                                    <button class="btn orangeButton btn-sm dropdown-toggle dropDown_right" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Chengdu Stations: <a id="selectedStation_tmp_r">selected</a>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" id="chingchong_tmp_r">chingchong</a>
                                        <a class="dropdown-item" id="pingping_tmp_r">Pingping</a>
                                        <a class="dropdown-item" id="winkong_tmp_r">winkong</a>
                                    </div>
                                </div>
                            </div>
                            <canvas class="line-graph" id="temperature" width="" height="100"></canvas>
                        </div>
                    </div>
                    <div class="col-12 col-md-4 d-inline-block">
                        <div  class="col graph h-100">
                            <canvas id="coldestCountry" width="" height="200"></canvas>
                        </div>
                    </div>
                </div>

                <div class="content row container-fluid">
                    <div class="col-12 col-md-4 d-inline-block">
                        <div class="row" style="height: 54%;">
                            <div  id="three_coldest" class="col graph h-100">
                                <h2> Top Three Coldest Average Windchill Corrected Temperatures </h2>
                                <div id="three_coldest_date">00-00-0000</div>
                                <div class="row">
                                    <div style="padding-top: 1vw" class="next-button col-2"><a id="button_left" type="button" class="btn orangeButton"><img class="icon" src="images/icons8-chevron-left-96.png"></a></div>
                                    <div class="col-8">
                                        <div id="first_coldest"></div>
                                        <div id="second_coldest"></div>
                                        <div id="third_coldest"></div>
                                    </div>
                                    <div style="padding-top: 1vw" class="next-button col-2"><a id="button_right" type="button" class="btn orangeButton"><img class="icon" src="images/icons8-chevron-right-96.png"></a></div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="height: 4%"></div>
                        <div class="row" style="height: 40%">
                            <div class="col graph h-100 text-center" id="download">
                                <h2 style="padding-bottom: 1vw">Download Recent Data</h2>
                                <a type="button" class="btn orangeButton" href="stylesheets/style.css" download><img class="icon" src="images/icons8-download-96.png">Download  </a>
                            </div>
                        </div>


                    </div>
                    <div class="col-0 col-md-8 d-inline-block">
                        <div  class="col graph">
                            <div class="text-center w-100 btn-group">
                                <div class="w-100">
                                    <button class="btn orangeButton btn-sm dropdown-toggle dropDown_left" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Ninchan Linqui Stations: <a id="selectedStation_tmp_l">selected</a>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" id="tmp_chong">chong</a>
                                        <a class="dropdown-item" id="tmp_ching">ching</a>
                                        <a class="dropdown-item" id="tmp_ping">ping</a>
                                    </div>
                                </div>
                                <div class="w-100">
                                    <button class="btn orangeButton btn-sm dropdown-toggle dropDown_right" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Chengdu Stations
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="#">chingchong</a>
                                        <a class="dropdown-item" href="#">Pingping</a>
                                        <a class="dropdown-item" href="#">winkong</a>
                                    </div>
                                </div>
                            </div>
                            <canvas class="line-graph" id="humidity" width="" height="100"></canvas>
                        </div>
                    </div>

                </div>
        </div>

        <!-- JavaScripts -->
        <?php include "scripts.php" ?>
    </body>
    <?php } ?>
</html>



