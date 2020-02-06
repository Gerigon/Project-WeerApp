let staticPath = "/samba/weerstation_share";

//when website open initialize graph data
//Sichuan
let Chengdu = getData(staticPath+"/100260/"+getDutchDate()+".gz",700);//562940
let EmeiShan = getData(staticPath+"/100280/"+getDutchDate()+".gz",700);//563850
let Ya_an = getData(staticPath+"/100290/"+getDutchDate()+".gz",700);//562870
let Kangding_Dardo = getData(staticPath+"/10030/"+getDutchDate()+".gz",700);//563740
let Xiao_jin = getData(staticPath+"/100330/"+getDutchDate()+".gz",700);//561780
let Mianyang = getData(staticPath+"/100020/"+getDutchDate()+".gz",700);//561960
let Langzhong = getData(staticPath+"/100040/"+getDutchDate()+".gz",700);//573060

//Qinglin
let Ankang_xing_an = getData(staticPath+"/100050/"+getDutchDate()+".gz",700);//572450
let wanyuan = getData(staticPath+"/100060/"+getDutchDate()+".gz",700);//572370
let Chen_an = getData(staticPath+"/10010/"+getDutchDate()+".gz",700);//571440
let Wen_huang_ping = getData(staticPath+"/100150/"+getDutchDate()+".gz",700);//571340
let Hanzhong = getData(staticPath+"/100200/"+getDutchDate()+".gz",700);//571270
let Fengjie = getData(staticPath+"/100220/"+getDutchDate()+".gz",700);//573480

//Countries
let Australia = getData(staticPath+"/948050/"+getDutchDate()+".gz",1);//948050
let Austria = getData(staticPath+"/110350/"+getDutchDate()+".gz",1);//110350
let Canada = getData(staticPath+"/716310/"+getDutchDate()+".gz",1);//716310
let France = getData(staticPath+"/073540/"+getDutchDate()+".gz",1);//073540
let Germany = getData(staticPath+"/103840/"+getDutchDate()+".gz",1);//103840
let Japan = getData(staticPath+"/476620/"+getDutchDate()+".gz",1);//476620
let Taiwan = getData(staticPath+"/466960/"+getDutchDate()+".gz",1);//466960
let Thailand = getData(staticPath+"/483270/"+getDutchDate()+".gz",1);//483270
let Netherlands = getData(staticPath+"/062750/"+getDutchDate()+".gz",1);//062750
let USA = getData(staticPath+"/722196/"+getDutchDate()+".gz",1);//722196

for(let i = 0; i < 3; i++){
    hengdu.push(getData(staticPath+"/100260/"+getDutchDate()+".gz",100));//562940
    EmeiShan.push(getData(staticPath+"/100280/"+getDutchDate()+".gz",100));//563850
    Ya_an.push(getData(staticPath+"/100290/"+getDutchDate()+".gz",100));//562870
    Kangding_Dardo.push(getData(staticPath+"/10030/"+getDutchDate()+".gz",100));//563740
    Xiao_jin.push(getData(staticPath+"/100330/"+getDutchDate()+".gz",100));//561780
    Mianyang.push(getData(staticPath+"/100020/"+getDutchDate()+".gz",100));//561960
    Langzhong.push(getData(staticPath+"/100040/"+getDutchDate()+".gz",100));//573060

//Qinglin
    Ankang_xing_an.push(getData(staticPath+"/100050/"+getDutchDate()+".gz",1));//572450
    wanyuan.push(getData(staticPath+"/100060/"+getDutchDate()+".gz",1));//572370
    Chen_an.push(getData(staticPath+"/10010/"+getDutchDate()+".gz",1));//571440
    Hanzhong.push(getData(staticPath+"/100150/"+getDutchDate()+".gz",1));//571270
    Fengjie.push(getData(staticPath+"/100200/"+getDutchDate()+".gz",1));//573480
    Wen_huang_ping.push(getData(staticPath+"/100220/"+getDutchDate()+".gz",1));//571340
}

function refreshData() {
    setTimeout( function () {
        //update graph data
        //Sichuan
         Chengdu.push(getData(staticPath+"/100260/"+getDutchDate()+".gz",1));//562940
         EmeiShan.push(getData(staticPath+"/100280/"+getDutchDate()+".gz",1));//563850
         Ya_an.push(getData(staticPath+"/100290/"+getDutchDate()+".gz",1));//562870
         Kangding_Dardo.push(getData(staticPath+"/10030/"+getDutchDate()+".gz",1));//563740
         Xiao_jin.push(getData(staticPath+"/100330/"+getDutchDate()+".gz",1));//561780
         Mianyang.push(getData(staticPath+"/100020/"+getDutchDate()+".gz",1));//561960
         Langzhong.push(getData(staticPath+"/100040/"+getDutchDate()+".gz",1));//573060

//Qinglin
         Ankang_xing_an.push(getData(staticPath+"/100050/"+getDutchDate()+".gz",1));//572450
         wanyuan.push(getData(staticPath+"/100060/"+getDutchDate()+".gz",1));//572370
         Chen_an.push(getData(staticPath+"/10010/"+getDutchDate()+".gz",1));//571440
         Hanzhong.push(getData(staticPath+"/100150/"+getDutchDate()+".gz",1));//571270
         Fengjie.push(getData(staticPath+"/100200/"+getDutchDate()+".gz",1));//573480
         Wen_huang_ping.push(getData(staticPath+"/100220/"+getDutchDate()+".gz",1));//571340

        //Countries
         Australia = getData(staticPath+"/948050/"+getDutchDate()+".gz",1);//948050
         Austria = getData(staticPath+"/110350/"+getDutchDate()+".gz",1);//110350
         Canada = getData(staticPath+"/716310/"+getDutchDate()+".gz",1);//716310
         France = getData(staticPath+"/073540/"+getDutchDate()+".gz",1);//073540
         Germany = getData(staticPath+"/103840/"+getDutchDate()+".gz",1);//103840
         Japan = getData(staticPath+"/476620/"+getDutchDate()+".gz",1);//476620
         Taiwan = getData(staticPath+"/466960/"+getDutchDate()+".gz",1);//466960
         Thailand = getData(staticPath+"/483270/"+getDutchDate()+".gz",1);//483270
         Netherlands = getData(staticPath+"/062750/"+getDutchDate()+".gz",1);//062750
         USA = getData(staticPath+"/722196/"+getDutchDate()+".gz",1);//722196

        refreshData();
    },1000);
}

function getData(path,rows){
    //return: 725025,2020-02-03,12:28:48,-0.5,-7.8,1005.0,997.8,16.8,23.4,0.07,0.2,111000,50.5,99
    // [0]number of the weather station // [1]date of sending // [2]time of sending// [3]temp in celcius// [4]dewpoint in celcius// [5]airpressure at station level in milibar//
    // [6]airpressure at sealevel in milibar// [7]visibility in kilomiters// [8]windspeed in km/h// [9]rainfall in centimeters// [10]snowfall in centimeters
    // [11] events of the day// [12] cloudyness in percentages// [13] wind direction in degrees
    var object = "error";
    jQuery.ajax({
        type: "POST",
        async: false,
        url: 'fileReader.php',
        dataType: 'json',
        data: {functionname: 'getLastLines', arguments: [path,rows]},

        success: function (obj, textstatus) {
            if( !('error' in obj) ) {
                object = obj.result;
            }
            else {
                object = obj.result;
            }
        }
    });
    let array = [];
    if(object.length > 1){
        for(let i = 0; i < object.length; i++){
            array.push(object[i].split(","))
        }
    }else{
        array.push(object.split(","));
    }
    return array;
}
