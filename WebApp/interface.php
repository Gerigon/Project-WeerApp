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
                                        Sichuan Stations: <a id="selectedStation_tmp_l">selected</a>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" id="tmpChengdu">Chengdu</a>
                                        <a class="dropdown-item" id="tmpEmei_Shan">Emei Shan</a>
                                        <a class="dropdown-item" id="tmpYa_an">Ya'an</a>
                                        <a class="dropdown-item" id="tmpKanding_Dardo">Kanding/Dardo</a>
                                        <a class="dropdown-item" id="tmpXiao_Jin">Xiao-Jin</a>
                                        <a class="dropdown-item" id="tmpMianyang">Mianyang</a>
                                        <a class="dropdown-item" id="tmpLangzhong">Langzhong</a>
                                    </div>
                                </div>
                                <div class="w-100">
                                    <button class="btn orangeButton btn-sm dropdown-toggle dropDown_right" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Qinlin Stations: <a id="selectedStation_tmp_r">selected</a>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" id="tmpAnkang_Xing">Ankang/Xing'an</a>
                                        <a class="dropdown-item" id="tmpWanyuan">Wanyuan</a>
                                        <a class="dropdown-item" id="tmpChen_an">Chen-an</a>
                                        <a class="dropdown-item" id="tmpWen_huang_ping">Wen-Huang-Ping</a>
                                        <a class="dropdown-item" id="tmpHanzhong">Hanzhong</a>
                                        <a class="dropdown-item" id="tmpFengjie">Fengjie</a>
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
<!--                                <a type="button" class="btn orangeButton" id="downloadButton" download><img class="icon" src="images/refresh.png"></a>-->
                                <a type="button" class="btn orangeButton" href="download.txt" download><img class="icon" src="images/icons8-download-96.png">Download  </a>
                            </div>
                        </div>


                    </div>
                    <div class="col-0 col-md-8 d-inline-block">
                        <div  class="col graph">
                            <div class="text-center w-100 btn-group">
                                <div class="w-100">
                                    <button class="btn orangeButton btn-sm dropdown-toggle dropDown_left" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Sichuan Stations: <a id="selectedStation_hmd_l">selected</a>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" id="hmdChengdu">Chengdu</a>
                                        <a class="dropdown-item" id="hmdEmei_Shan">Emei Shan</a>
                                        <a class="dropdown-item" id="hmdYa_an">Ya'an</a>
                                        <a class="dropdown-item" id="hmdKanding_Dardo">Kanding/Dardo</a>
                                        <a class="dropdown-item" id="hmdXiao_Jin">Xiao-Jin</a>
                                        <a class="dropdown-item" id="hmdMianyang">Mianyang</a>
                                        <a class="dropdown-item" id="hmdLangzhong">Langzhong</a>
                                    </div>
                                </div>
                                <div class="w-100">
                                    <button class="btn orangeButton btn-sm dropdown-toggle dropDown_right" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Qinlin Stations: <a id="selectedStation_hmd_r">selected</a>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" id="hmdAnkang_Xing">Ankang/Xing'an</a>
                                        <a class="dropdown-item" id="hmdWanyuan">Wanyuan</a>
                                        <a class="dropdown-item" id="hmdChen_an">Chen-an</a>
                                        <a class="dropdown-item" id="hmdWen_huang_ping">Wen-Huang-Ping</a>
                                        <a class="dropdown-item" id="hmdHanzhong">Hanzhong</a>
                                        <a class="dropdown-item" id="hmdFengjie">Fengjie</a>
                                    </div>
                                </div>
                            </div>
                            <canvas class="line-graph" id="humidity" width="" height="100"></canvas>
                        </div>
                    </div>
                </div>
            <div id="testing"></div>
        </div>
        <!-- JavaScripts -->
        <?php include "scripts.php" ?>
    </body>
    <?php } ?>
</html>



