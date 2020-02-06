var ctx = document.getElementById('coldestCountry').getContext('2d');
var coldestCountry = new Chart(ctx, {
    type: 'bar',
    data: {

        labels: ["Australia", "Austria", "Canada","France","Germany","Japan","Taiwan","Thailand","Netherlands","USA"] ,
        datasets: [{
            data: [34,30,-4,12,7,23,21,19,6,18],
            fill: false,
            backgroundColor: [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(153, 102, 255, 0.6)',
                'rgba(255, 159, 64, 0.6)',
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)'
            ],borderColor:[
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        legend: {
            display: false
        },
        title: {
            display: true,
            text: 'Windchill Corrected Temperature'
        },
        animation:{
            duration: 400
        },
        scales: {
            yAxes: [{
                ticks: {
                    suggestedMin: -10,
                    suggestedMax: 20,
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
                    labelString: 'Country'
                }
            }]
        }
    }
});