let i = 1;
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

        for(let c = 0; c < array.length; c++){
            if(array[c][1] == "Australia"){array[c][0] = Chengdu[last(Chengdu)][3];}
            if(array[c][1] == "Austria"){array[c][0] = EmeiShan[last(EmeiShan)][3];}
            if(array[c][1] == "Thailand"){array[c][0] = Ya_an[last(Ya_an)][3];}
            if(array[c][1] == "Canada"){array[c][0] = Kangding_Dardo[last(Kangding_Dardo)][3];}
            if(array[c][1] == "Taiwan"){array[c][0] = Xiao_jin[last(Xiao_jin)][3];}
            if(array[c][1] == "Japan"){array[c][0] = Mianyang[last(Mianyang)][3];}
            if(array[c][1] == "Germany"){array[c][0] = Langzhong[last(Langzhong)][3];}
            if(array[c][1] == "Netherlands"){array[c][0] = Ankang_xing_an[last(Ankang_xing_an)][3];}
            if(array[c][1] == "France"){array[c][0] = Fengjie[last(Fengjie)][3];}
            if(array[c][1] == "USA"){array[c][0] = Chen_an[last(Chen_an)][3];}
        }

        //sort the double array because this way the data and keys stay together
        let sortedArray = array.sort(function(a, b) {
            return a[0]- b[0] ;
        });

        //put the averages in another array
        for(let c = 0; c < sortedArray.length; c++){
            for(let d = 0; d < sortedArray.length; d++){
                if(total[c][1] == sortedArray[d][1]){
                    total[c][0] +=  parseInt(sortedArray[d][0]);
                }
            }
        }
        //sort the double array because this way the data and keys stay together
        total= total.sort(function(a, b) {
            return a[0]- b[0] ;
        });
        averages_30_days[0][0] = getDate();
        averages_30_days[0][1][0] = getAverage(total[0][0],Chengdu.length-1);
        averages_30_days[0][1][1] = total[0][1];

        averages_30_days[0][2][0] = getAverage(total[1][0],Chengdu.length)-1;
        averages_30_days[0][2][1] = total[1][1];

        averages_30_days[0][3][0] = getAverage(total[2][0],Chengdu.length)-1;
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
        coldestCountry.update();
        refreshColdestCountries()
    },5000);}

function refreshTopThreeColdest(){
    setTimeout(function () {
        if(arrayPostition > (averages_30_days.length-1)){
            arrayPostition = 0;
        }
        if(arrayPostition < 0){
            arrayPostition = (averages_30_days.length-1);
        }
        document.getElementById("first_coldest").innerHTML = ("1. " + total[0][1] + " : " + (Math.round((total[0][0]/tmpChengdu.length) * 100) / 100));
        document.getElementById("second_coldest").innerHTML = ("2. " + total[1][1]+ " : " + (Math.round((total[1][0]/tmpChengdu.length) * 100) / 100));
        document.getElementById("third_coldest").innerHTML = ("3. " + total[2][1] + " : " + (Math.round((total[2][0]/tmpChengdu.length) * 100) / 100));

        refreshTopThreeColdest();
    },1);
}

document.getElementById("button_right").addEventListener("click", function() {arrayPostition -= 0;});
document.getElementById("button_left").addEventListener("click", function() {arrayPostition += 0;});