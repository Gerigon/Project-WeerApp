i = 1;
let arrayPostition = 0;
let total = [[0,"Australia"], [0,"Austria"], [0,"Canada"],[0,"France"],[0,"Germany"],[0,"Japan"],[0,"Taiwan"],[0,"Thailand"],[0,"Netherlands"],[0,"USA"]];

//make random data for the averages of 30 days
let averages_30_days = [];
for(let a = 0; a < 30; a++){
    averages_30_days.unshift([a +"-"+1+"-"+2020,[getRndInteger(-5,+9),total[getRndInteger(0,9)][1]],[getRndInteger(-5,+9),total[getRndInteger(0,9)][1]],[getRndInteger(-5,+9),total[getRndInteger(0,9)][1]]]);
}

function refreshColdestCountries() {
    setTimeout(function () {
        //fill graph with random data
        for (let a = 0; a < 10  ; a++){
            coldestCountry.data.datasets[0].data[a] = getRndInteger(-20,40);
        }
        let values = coldestCountry.data.datasets[0].data;
        let labels = coldestCountry.data.labels;
        let array = [];

        //put two arrays in one
        for(let b = 0; b<labels.length; b++){
            let temp = [values[b],labels[b]];
            array.push(temp);
        }

        //sort the double array because this way the data and keys stay together
        let sortedArray = array.sort(function(a, b) {
            return a[0]- b[0] ;
        });

        //put the averages in another array
        for(let c = 0; c < sortedArray.length; c++){
            for(let d = 0; d < sortedArray.length; d++){
                if(total[c][1] == sortedArray[d][1]){
                    total[c][0] += sortedArray[d][0];
                }
            }
        }

        //sort the double array because this way the data and keys stay together
        total= total.sort(function(a, b) {
            return a[0]- b[0] ;
        });

        averages_30_days[0][0] = getDate();
        averages_30_days[0][1][0] = getAverage(total[0][0],i);
        averages_30_days[0][1][1] = total[0][1];

        averages_30_days[0][2][0] = getAverage(total[1][0],i);
        averages_30_days[0][2][1] = total[1][1];

        averages_30_days[0][3][0] = getAverage(total[2][0],i);
        averages_30_days[0][3][1] = total[2][1];

        //put data in two separate arrays
        labels = [];
        values = [];
        for(let a = 0; a<sortedArray.length; a++){
            labels.push(sortedArray[a][1]);
            values.push(sortedArray[a][0]);
        }

        coldestCountry.data.datasets[0].data = values;
        coldestCountry.data.labels = labels;

        i++;
        refreshColdestCountries()
    },1000);}

function refreshTopThreeColdest(){
    setTimeout(function () {
        if(arrayPostition > (averages_30_days.length-1)){
            arrayPostition = 0;
        }
        if(arrayPostition < 0){
            arrayPostition = (averages_30_days.length-1);
        }
        document.getElementById("first_coldest").innerHTML = ("1. " + averages_30_days[arrayPostition][1][1] + " : " + averages_30_days[arrayPostition][1][0]);
        document.getElementById("second_coldest").innerHTML = ("2. " + averages_30_days[arrayPostition][2][1] + " : " + averages_30_days[arrayPostition][2][0]);
        document.getElementById("third_coldest").innerHTML = ("3. " + averages_30_days[arrayPostition][3][1] + " : " + averages_30_days[arrayPostition][3][0]);

        refreshTopThreeColdest();
    },1);
}

document.getElementById("button_right").addEventListener("click", function() {arrayPostition -= 1;});
document.getElementById("button_left").addEventListener("click", function() {arrayPostition += 1;});