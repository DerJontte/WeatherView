function viewStuff() {
    responseText = this.responseText;
    parser = new DOMParser();
    xmlDoc = parser.parseFromString(responseText,"text/xml");

    times = xmlDoc.getElementsByTagName("wml2:time");
    values = xmlDoc.getElementsByTagName("wml2:value");
    if (times.length != values.length) {
        return;
    }

    timestamp = times[times.length - 1].childNodes[0].nodeValue.split("T")[1].split("Z")[0];
    timestamp = {
        hours: ("0" + (parseInt(timestamp.substring(0, 2)) + 2)%24).slice(-2),
        minutes: ("0" + timestamp.substring(2, 5)).slice(-3),
        seconds: ("0" + timestamp.substring(5, 8)).slice(-3)
    };
    timestamp = timestamp.hours + timestamp.minutes + timestamp.seconds;
    temperature = values[values.length - 1].childNodes[0].nodeValue + " \u00b0C";

    window.android.setTimestamp(timestamp);
    window.android.setTemp(temperature);
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

query = buildQuery(utcTime);

xmlStuff = new XMLHttpRequest();
xmlStuff.addEventListener("load", viewStuff);
xmlStuff.open("GET", query);
xmlStuff.send();
