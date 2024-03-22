setInterval(getData, 1000);

function getData() {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4) {
            if (request.status == 200) {
                var data = JSON.parse(request.responseText);
                var averageSpeed = data.averageSpeed;
                var trafficPattern = data.trafficPattern;
                console.log(averageSpeed);
                console.log(trafficPattern);
                document.getElementById("average_speed").innerText = "Average Speed : " + averageSpeed;
                document.getElementById("traffic_pattern").innerText="Traffic Pattern : "+trafficPattern;

            } else {
                console.error('Error fetching data:', request.status);
            }

        }
    };
    request.open("GET", "traffic", true);
    request.send();
}