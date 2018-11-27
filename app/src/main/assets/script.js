function setFonts() {
    if (innerWidth > innerHeight) {
        time.style = "font-size: 15vh";
        temp.style = "font-size: 50vh";
    } else {
        time.style = "font-size: 5vh";
        temp.style = "font-size: 15vh";
    }
}

function viewStuff() {
    texx =
    time = document.getElementById("time");
    temp = document.getElementById("temp");

    responseText = this.responseText;
    parser = new DOMParser();
    xmlDoc = parser.parseFromString(responseText,"text/xml");

    times = xmlDoc.getElementsByTagName("wml2:time");
    values = xmlDoc.getElementsByTagName("wml2:value");
    if (times.length != values.length) {
        time.innerText = "Parsing error: times.length != values.length";
        temp.innerHTML = "";
        return;
    }

    timestamp = times[times.length - 1].childNodes[0].nodeValue.split("T")[1].split("Z")[0];
    timestamp = {
        hours: ("0" + (parseInt(timestamp.substring(0, 2)) + 2)%24).slice(-2),
        minutes: timestamp.substring(2, 5),
        seconds: timestamp.substring(5, 8)
    };
    timestamp = timestamp.hours + timestamp.minutes + timestamp.seconds;

    setFonts();
    time.innerHTML = timestamp;
    temp.innerHTML = values[values.length - 1].childNodes[0].nodeValue + " &degC";
}

date = new Date();
utc = {
    year: date.getUTCFullYear(),
    month: ("0" + (date.getUTCMonth() + 1)).slice(-2),
    date: ("0" + date.getUTCDate()).slice(-2),
    hours: ("0" + (date.getUTCHours() - 1)).slice(-2),
    minutes: ("0" + date.getUTCMinutes()).slice(-2)
};
utcTime = utc.year + "-" + utc.month + "-" + utc.date + "T" + utc.hours + ":" + utc.minutes + ":" +  "00Z";

query = "http://opendata.fmi.fi/wfs/fin?service=WFS&version=2.0.0&request=getFeature&storedquery_id=fmi::observations::weather::timevaluepair&fmisid=100949&parameters=t2m&starttime=" + utcTime;

xmlStuff = new XMLHttpRequest();
xmlStuff.addEventListener("load", viewStuff);
xmlStuff.open("GET", query);
xmlStuff.send();
