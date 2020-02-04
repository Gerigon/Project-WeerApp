function refreshHumidityGraph() {
    setTimeout(function () {
        //fill graph with random data
        humidity.data.datasets[0].data.push(getRndInteger(0,20));
        humidity.data.datasets[1].data.push(getRndInteger(0,20));
        humidity.data.labels.push(getTime().toLocaleTimeString());

        //remove data after 2 minutes
        if (new Date() > getStop()){
            humidity.data.labels.shift();
            humidity.data.datasets[0].data.shift();
            humidity.data.datasets[1].data.shift();
        }
        humidity.update();
        refreshHumidityGraph()
    },1000);
}