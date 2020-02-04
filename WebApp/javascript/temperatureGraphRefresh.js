let graph_tmp_l = 0;
let graph_tmp_r = 0;

let chong =  [];
let ching = [];
let ping = [];
let chingchong = [];
let pingping = [];
let winkong = [];

function refreshTemperatureStation(){
    setTimeout(function () {
        switch (graph_tmp_l) {
            case 0:
                temperature.data.datasets[0].data = chong;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Chong";
                break;
            case 1:
                temperature.data.datasets[0].data = ching;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Ching";
                break;
            case 2:
                temperature.data.datasets[0].data = ping;
                document.getElementById("selectedStation_tmp_l").innerHTML = "Ping";
                break;
        }

        switch (graph_tmp_r) {
            case 0:
                temperature.data.datasets[1].data = chingchong;
                document.getElementById("selectedStation_tmp_r").innerHTML = "Chingchong";
                break;
            case 1:
                temperature.data.datasets[1].data = pingping;
                document.getElementById("selectedStation_tmp_r").innerHTML = "pingping";
                break;
            case 2:
                temperature.data.datasets[1].data = winkong;
                document.getElementById("selectedStation_tmp_r").innerHTML = "winkong";
                break;
        }

        refreshTemperatureStation();
    },1);
}

function refreshTemperatureGraph() {
    setTimeout( function () {

        //temperature graph
        ching .push(getRndInteger(0,10));
        chong.push(getRndInteger(0,10));
        ping.push(getRndInteger(0,10));

        chingchong.push(getRndInteger(0,10));
        pingping.push(getRndInteger(0,10));
        winkong.push(getRndInteger(0,10));

        temperature.data.labels.push(getTime().toLocaleTimeString());

        //remove data when 2 minutes limit reached
        if(new Date() > getStop()){
            //temperature graph
            ching.shift();
            chong.shift();
            ping.shift();

            chingchong.shift();
            pingping.shift();
            winkong.shift();

            temperature.data.labels.shift();

        }
        coldestCountry.update();
        temperature.update();

        refreshTemperatureGraph();
    },1000);
}

document.getElementById("chong_tmp_l").addEventListener("click", function() {graph_tmp_l = 0;});
document.getElementById("ching_tmp_l").addEventListener("click", function() {graph_tmp_l = 1;});
document.getElementById("ping_tmp_l").addEventListener("click", function() {graph_tmp_l = 2;});
document.getElementById("chingchong_tmp_r").addEventListener("click", function() {graph_tmp_r = 0;});
document.getElementById("pingping_tmp_r").addEventListener("click", function() {graph_tmp_r = 1;});
document.getElementById("winkong_tmp_r").addEventListener("click", function() {graph_tmp_r = 2;});