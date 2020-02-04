$(document).ready(function () {
    document.getElementById("date").innerHTML = getTime().toLocaleString();
    const currentTheme = localStorage.getItem('theme') ? localStorage.getItem('theme') : null;

    if (currentTheme) {
        document.documentElement.setAttribute('data-theme', currentTheme);

        if (currentTheme === 'dark') {
            toggleSwitch.checked = true;
        }
    }
    refreshHumidityGraph();
    refreshTemperatureStation();
    refreshTemperatureGraph()
    refreshTopThreeColdest();
    refreshColdestCountries();
    refreshTime();
});

function refreshTime(){
    setTimeout(function () {
        document.getElementById("date").innerHTML = getTime().toLocaleString();
        document.getElementById("three_coldest_date").innerHTML = averages_30_days[arrayPostition][0];
        refreshTime();
    },1000);
}