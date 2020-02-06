SichuanHumidity = humidity .data.datasets[0].data;
QinlinHumidity = humidity.data.datasets[1].data;
LabelsHumidity = humidity .data.labels;

let hmdSischuan= 0;
let hmdQinlin = 0;

//Sischuan
let hmdChengdu =  [];
let hmdEmei_Shan = [];
let hmdYa_an = [];
let hmdKanding_Dardo = [];
let hmdXiao_Jin = [];
let hmdMianyang = [];
let hmdLangzhong = [];

//Qinlin
let hmdAnkang_Xing =  [];
let hmdWanyuan = [];
let hmdChen_an = [];
let hmdWen_huang_ping = [];
let hmdHanzhong = [];
let hmdFengjie = [];


function refreshHumidityStation(){
    setTimeout(function () {
        switch (hmdSischuan) {
            case 0:
                humidity.data.datasets[0].data = hmdChengdu;
                document.getElementById("selectedStation_hmd_l").innerHTML = "Chengdu";
                break;
            case 1:
                humidity.data.datasets[0].data = hmdEmei_Shan;
                document.getElementById("selectedStation_hmd_l").innerHTML = "Emei Shan";
                break;
            case 2:
                humidity.data.datasets[0].data = hmdYa_an;
                document.getElementById("selectedStation_hmd_l").innerHTML = "Ya'an";
                break;
            case 3:
                humidity.data.datasets[0].data = hmdKanding_Dardo;
                document.getElementById("selectedStation_hmd_l").innerHTML = "Kanding/Dardo";
                break;
            case 4:
                humidity.data.datasets[0].data = hmdXiao_Jin;
                document.getElementById("selectedStation_hmd_l").innerHTML = "Xiao-Jin";
                break;
            case 5:
                humidity.data.datasets[0].data = hmdMianyang;
                document.getElementById("selectedStation_hmd_l").innerHTML = "Mianyang";
                break;
            case 6:
                humidity.data.datasets[0].data = hmdLangzhong;
                document.getElementById("selectedStation_hmd_l").innerHTML = "Langzhong";
                break;
        }

        switch (hmdQinlin) {
            case 0:
                humidity.data.datasets[1].data = hmdAnkang_Xing;
                document.getElementById("selectedStation_hmd_r").innerHTML = "Ankang/Xing'an";
                break;
            case 1:
                humidity.data.datasets[1].data = hmdWanyuan;
                document.getElementById("selectedStation_hmd_r").innerHTML = "Wanyuan";
                break;
            case 2:
                humidity.data.datasets[1].data = hmdChen_an;
                document.getElementById("selectedStation_hmd_r").innerHTML = "Chen-an";
                break;
            case 3:
                humidity.data.datasets[1].data = hmdWen_huang_ping;
                document.getElementById("selectedStation_hmd_r").innerHTML = "Wen-Huang-Ping";
                break;
            case 4:
                humidity.data.datasets[1].data = hmdHanzhong;
                document.getElementById("selectedStation_hmd_r").innerHTML = "Hanzhong";
                break;
            case 5:
                humidity.data.datasets[1].data = hmdFengjie;
                document.getElementById("selectedStation_hmd_r").innerHTML = "Fengjie";
                break;
        }
        refreshHumidityStation();
    },1);
}

function refreshHumidityGraph() {
    setTimeout( function () {

        hmdChengdu.push(calculateHumidity(Chengdu.pop()[3] ,Chengdu.pop()[8]));
        hmdEmei_Shan.push(calculateHumidity(EmeiShan.pop()[3] ,EmeiShan.pop()[8]));
        hmdYa_an.push(calculateHumidity(Ya_an.pop()[3] ,Ya_an.pop()[8]));
        hmdKanding_Dardo.push(calculateHumidity(Kangding_Dardo.pop()[3] ,Kangding_Dardo.pop()[8]));
        hmdXiao_Jin.push(calculateHumidity(Xiao_jin.pop()[3] ,Xiao_jin.pop()[8]));
        hmdMianyang.push(calculateHumidity(Mianyang.pop()[3] ,Mianyang.pop()[8]));
        hmdLangzhong.push(calculateHumidity(Langzhong.pop()[3] ,Langzhong.pop()[8]));

        hmdAnkang_Xing.push(calculateHumidity(Ankang_xing_an.pop()[3] ,Ankang_xing_an.pop()[8]));
        hmdWanyuan.push(calculateHumidity(wanyuan.pop()[3] ,wanyuan.pop()[8]));
        hmdChen_an.push(calculateHumidity(Chen_an.pop()[3] ,Chen_an.pop()[8]));
        hmdWen_huang_ping.push(calculateHumidity(Wen_huang_ping.pop()[3] ,Wen_huang_ping.pop()[8]));
        hmdHanzhong.push(calculateHumidity(Hanzhong.pop()[3] ,Hanzhong.pop()[8]));
        hmdFengjie.push(calculateHumidity(Fengjie.pop()[3] ,Fengjie.pop()[8]));

        humidity.data.labels.push(getTime().toLocaleTimeString());

        //remove data if above 120 values
        if(hmdChengdu.length > 200){hmdChengdu.pop();}
        if(hmdEmei_Shan.length > 200){hmdEmei_Shan.pop();}
        if(hmdYa_an.length > 200){hmdYa_an.pop();}
        if(hmdKanding_Dardo.length > 200){hmdKanding_Dardo.pop();}
        if(hmdXiao_Jin.length > 200){hmdXiao_Jin.pop();}
        if(hmdMianyang.length > 200){hmdMianyang.pop();}
        if(hmdLangzhong.length > 200){hmdLangzhong.pop();}

        if(hmdAnkang_Xing.length > 200){hmdAnkang_Xing.pop();}
        if(hmdWanyuan.length > 200){hmdWanyuan.pop();}
        if(hmdChen_an.length > 200){hmdChen_an.pop();}
        if(hmdHanzhong.length > 200){hmdHanzhong.pop();}
        if(hmdFengjie.length > 200){hmdFengjie.pop();}
        if(hmdWen_huang_ping.length > 200){hmdWen_huang_ping.pop();}

        if(humidity.data.labels.length > 200){humidity.data.labels.shift();}

        humidity.update();
        refreshHumidityGraph();
    },5000);
}

function last(array){
    return (array.length -1);
}

function calculateHumidity(T,V){
    //T is temperature, W in windspeed
    if(T == 0){T = 1;}
    if(V == 0){V = 1;}
    let humid = 100*((17*V)/(243+V))/((17*T)/(243+T));
    return humid/10;
}

//Sischuan
document.getElementById("hmdChengdu").addEventListener("click", function() {hmdSischuan = 0;});
document.getElementById("hmdEmei_Shan").addEventListener("click", function() {hmdSischuan = 1;});
document.getElementById("hmdYa_an").addEventListener("click", function() {hmdSischuan = 2;});
document.getElementById("hmdKanding_Dardo").addEventListener("click", function() {hmdSischuan = 3;});
document.getElementById("hmdXiao_Jin").addEventListener("click", function() {hmdSischuan = 4;});
document.getElementById("hmdMianyang").addEventListener("click", function() {hmdSischuan = 5;});
document.getElementById("hmdLangzhong").addEventListener("click", function() {hmdSischuan = 6;});

//Sischuan
document.getElementById("hmdAnkang_Xing").addEventListener("click", function() {hmdQinlin = 0;});
document.getElementById("hmdWanyuan").addEventListener("click", function() {hmdQinlin = 1;});
document.getElementById("hmdChen_an").addEventListener("click", function() {hmdQinlin = 2;});
document.getElementById("hmdWen_huang_ping").addEventListener("click", function() {hmdQinlin = 3;});
document.getElementById("hmdHanzhong").addEventListener("click", function() {hmdQinlin = 4;});
document.getElementById("hmdFengjie").addEventListener("click", function() {hmdQinlin = 5;});
