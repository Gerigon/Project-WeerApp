<?php
header('Content-Type: application/json');

$aResult = array();

if( !isset($_POST['functionname']) ) { $aResult['error'] = 'No function name!'; }

if( !isset($_POST['arguments']) ) { $aResult['error'] = 'No function arguments!'; }

if( !isset($aResult['error']) ) {

    switch($_POST['functionname']) {
        case 'getLastLines':
            $aResult['result'] = getLastLines($_POST['arguments'][0],$_POST['arguments'][1]);
            break;
        case'createFile':
            createFile($_POST['arguments']);
                break;
        default:
            $aResult['error'] = 'Not found function '.$_POST['functionname'].'!';
            break;
    }

}

echo json_encode($aResult);

function getLastLines($filename, $amountOfLines){
    if(file_exists($filename)){
    $lines=array();
    $fp = gzopen($filename, "r");
    while(!gzeof($fp))
    {
        $line = gzgets($fp, 1024);
        array_push($lines, $line);
        if (count($lines)>$amountOfLines)
            array_shift($lines);
    }
    gzclose($fp);

    return($lines);}else{return "error";}
}

function createFile($data){
        if(file_exists("download.txt")){unlink("download.txt");}
        $myfile = fopen("download.txt", "w");
        fwrite($myfile, $data);
        fclose($myfile);
    }