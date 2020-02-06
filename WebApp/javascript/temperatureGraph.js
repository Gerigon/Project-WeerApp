var ctx = document.getElementById('temperature').getContext('2d');
var temperature = new Chart(ctx, {
    type: 'line',
    data: {

        labels: [] ,
        datasets: [{
            label: 'Sichuan',
            data: [],
            fill: true,
            borderColor: [
                'orange',
            ],backgroundColor: [
                'rgba(255, 165, 0, 0.1)'
            ],
            borderWidth: 1
        },{
            label: 'Qinlin',
            data: [],
            fill: true,
            borderColor: [
                '#1f8ef1',
            ],backgroundColor: [
            'rgba(31, 142, 241, 0.1)'
        ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        title: {
            display: true,
            text: 'Wind Chill Corrected Temperature'
        },
        animation:{
            duration: 400
        },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true,
                },
                scaleLabel: {
                    display: true,
                    labelString: 'Temperature'
                }
            }],
            xAxes: [{
                ticks: {
                    maxTicksLimit: 11
                },
                scaleLabel: {
                    display: true,
                    labelString: 'Time'
                }
            }]
        }
    }
});