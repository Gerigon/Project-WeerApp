SichuanTemperature = temperature.data.datasets[0].data;
QinlinTemperature = temperature.data.datasets[1].data;
LabelsTemperature = temperature.data.labels;

let tmpSischuan= 0;
let tmpQinlin = 0;

//Sischuan
let tmpChengdu =  [];
let tmpEmei_Shan = [];
let tmpYa_an = [];
let tmpKanding_Dardo = [];
let tmpXiao_Jin = [];
let tmpMianyang = [];
let tmpLangzhong = [];

//Qinlin
let tmpAnkang_Xing =  [];
let tmpWanyuan = [];
let tmpChen_an = [];
let tmpWen_huang_ping = [];
let tmpHanzhong = [];
let tmpFengjie = [];


function refreshTemperatureStation(){
    setTimeout(function () {
        switch (tmpSischuan) {
            case 0:
                temperature.data.datasets[0].data = tmpChengdu;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Chengdu";
                break;
            case 1:
                temperature.data.datasets[0].data = tmpEmei_Shan;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Emei Shan";
                break;
            case 2:
                temperature.data.datasets[0].data = tmpYa_an;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Ya'an";
                break;
            case 3:
                temperature.data.datasets[0].data = tmpKanding_Dardo;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Kanding/Dardo";
                break;
            case 4:
                temperature.data.datasets[0].data = tmpXiao_Jin;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Xiao-Jin";
                break;
            case 5:
                temperature.data.datasets[0].data = tmpMianyang;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Mianyang";
                break;
            case 6:
                temperature.data.datasets[0].data = tmpLangzhong;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Langzhong";
                break;
        }

        switch (tmpQinlin) {
            case 0:
                temperature.data.datasets[1].data = tmpAnkang_Xing;
                document.getElementById("selectedStation_tmp_r").innerHTML = "Ankang/Xing'an";
                break;
            case 1:
                temperature.data.datasets[1].data = tmpWanyuan;
                document.getElementById("selectedStation_tmp_r").innerHTML = "Wanyuan";
                break;
            case 2:
                temperature.data.datasets[1].data = tmpChen_an;
                document.getElementById("selectedStation_tmp_r").innerHTML = "Chen-an";
                break;
            case 3:
                temperature.data.datasets[1].data = tmpWen_huang_ping;
                document.getElementById("selectedStation_tmp_r").innerHTML = "Wen-Huang-Ping";
                break;
            case 4:
                temperature.data.datasets[1].data = tmpHanzhong;
                document.getElementById("selectedStation_tmp_r").innerHTML = "Hanzhong";
                break;
            case 5:
                temperature.data.datasets[1].data = tmpFengjie;
                document.getElementById("selectedStation_tmp_r").innerHTML = "Fengjie";
                break;
        }
        refreshTemperatureStation();
    },1);
}

function refreshTemperatureGraph() {
    setTimeout( function () {

        tmpChengdu.push(calculateWindchill(Chengdu[last(Chengdu)][3] ,Chengdu[last(Chengdu)][8]));
        tmpEmei_Shan.push(calculateWindchill(EmeiShan [last(EmeiShan)][3] ,EmeiShan[last(EmeiShan)][8]));
        tmpYa_an.push(calculateWindchill(Ya_an[last(Ya_an )][3] ,Ya_an [last(Ya_an )][8]));
        tmpKanding_Dardo.push(calculateWindchill(Kangding_Dardo [last(Kangding_Dardo )][3] ,Kangding_Dardo [last(Kangding_Dardo )][8]));
        tmpXiao_Jin.push(calculateWindchill(Xiao_jin [last(Xiao_jin )][3] ,Xiao_jin [last(Xiao_jin )][8]));
        tmpMianyang.push(calculateWindchill(Mianyang [last(Mianyang )][3] ,Mianyang [last(Mianyang )][8]));
        tmpLangzhong.push(calculateWindchill(Langzhong [last(Langzhong )][3] ,Langzhong [last(Langzhong )][8]));

        tmpAnkang_Xing.push(calculateWindchill(Ankang_xing_an [last(Ankang_xing_an )][3] ,Ankang_xing_an [last(Ankang_xing_an )][8]));
        tmpWanyuan.push(calculateWindchill(wanyuan [last(wanyuan )][3] ,wanyuan [last(wanyuan )][8]));
        tmpChen_an.push(calculateWindchill(Chen_an [last(Chen_an )][3] ,Chen_an [last(Chen_an )][8]));
        tmpWen_huang_ping.push(calculateWindchill(Wen_huang_ping[last(Wen_huang_ping)][3] ,Wen_huang_ping[last(Wen_huang_ping)][8]));
        tmpHanzhong.push(calculateWindchill(Hanzhong [last(Hanzhong )][3] ,Hanzhong [last(Hanzhong )][8]));
        tmpFengjie.push(calculateWindchill(Fengjie [last(Fengjie )][3] ,Fengjie [last(Fengjie )][8]));

        temperature.data.labels.push(getTime().toLocaleTimeString());

        //remove data if above 120 values
        if(tmpChengdu.length > 200){tmpChengdu.shift();}
        if(tmpEmei_Shan.length > 200){tmpEmei_Shan.shift();}
        if(tmpYa_an.length > 200){tmpYa_an.shift();}
        if(tmpKanding_Dardo.length > 200){tmpKanding_Dardo.shift();}
        if(tmpXiao_Jin.length > 200){tmpXiao_Jin.shift();}
        if(tmpMianyang.length > 200){tmpMianyang.shift();}
        if(tmpLangzhong.length > 200){tmpLangzhong.shift();}

        if(tmpAnkang_Xing.length > 200){tmpAnkang_Xing.shift();}
        if(tmpWanyuan.length > 200){tmpWanyuan.shift();}
        if(tmpChen_an.length > 200){tmpChen_an.shift();}
        if(tmpHanzhong.length > 200){tmpHanzhong.shift();}
        if(tmpFengjie.length > 200){tmpFengjie.shift();}
        if(tmpWen_huang_ping.length > 200){tmpWen_huang_ping.shift();}

        if(temperature.data.labels.length > 200){temperature.data.labels.shift();}

        temperature.update();
        refreshTemperatureGraph();
    },5000);
}

function last(array){
    return (array.length -1);
}

function calculateWindchill(T,V){
    //T is temperature, W in windspeed
    let mph = 0.621371 * V;
    let fah = 1.8 * T + 32;
    let windChill = (0.0817*(3.71*(Math.pow(mph, 0.5))+ 5.81-0.25*mph)*(fah-91.4)+91.4);
    return (windChill - 32) / 1.8;
}

//Sischuan
document.getElementById("tmpChengdu").addEventListener("click", function() {tmpSischuan = 0;});
document.getElementById("tmpEmei_Shan").addEventListener("click", function() {tmpSischuan = 1;});
document.getElementById("tmpYa_an").addEventListener("click", function() {tmpSischuan = 2;});
document.getElementById("tmpKanding_Dardo").addEventListener("click", function() {tmpSischuan = 3;});
document.getElementById("tmpXiao_Jin").addEventListener("click", function() {tmpSischuan = 4;});
document.getElementById("tmpMianyang").addEventListener("click", function() {tmpSischuan = 5;});
document.getElementById("tmpLangzhong").addEventListener("click", function() {tmpSischuan = 6;});

//Sischuan
document.getElementById("tmpAnkang_Xing").addEventListener("click", function() {tmpQinlin = 0;});
document.getElementById("tmpWanyuan").addEventListener("click", function() {tmpQinlin = 1;});
document.getElementById("tmpChen_an").addEventListener("click", function() {tmpQinlin = 2;});
document.getElementById("tmpWen_huang_ping").addEventListener("click", function() {tmpQinlin = 3;});
document.getElementById("tmpHanzhong").addEventListener("click", function() {tmpQinlin = 4;});
document.getElementById("tmpFengjie").addEventListener("click", function() {tmpQinlin = 5;});
