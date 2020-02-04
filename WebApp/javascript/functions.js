let stop = new Date();
stop.setMinutes(stop.getMinutes() + 2);
let updateTime = 1000;  //changes update time for graphs, 1000 is 1 second

function getAverage(value,amount){
    return Math.round(value/amount  * 100) / 100
}

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min) ) + min;
}

function getDate() {
    return getTime().getDate()+"-"+(getTime().getMonth()+1)+"-"+getTime().getFullYear();
}

function getTime() {
    var asiaTime = new Date().toLocaleString("en-US", {timeZone: "Asia/Shanghai"});
    asiaTime = new Date(asiaTime);
    return(asiaTime);
}

const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');

function getStop() {
    return stop;
}

function switchTheme(e) {
    if (e.target.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        localStorage.setItem('theme', 'dark');
    }
    else {
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('theme', 'light');
    }
}

function getUpdatetime(){
    return updateTime;
}

toggleSwitch.addEventListener('change', switchTheme, false);

