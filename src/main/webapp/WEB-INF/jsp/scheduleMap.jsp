<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Schedule</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.auth0.com/js/auth0-samples-theme/1.0/css/auth0-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>

    <style>
        #chartdiv {
            width: 100%;
            height: 600px;
        }
    </style>
</head>


<body class="h-100">
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf" %>

    <div id="page">
        <div class="container">
            <div class="row mt-5">
                <div class="col">
                    <h2>Schedule</h2>
                </div>
            </div>

            <form id="date-form">
                <div class="row">
                    <div class="col">
                        <div class="input-group date" id="datepicker">
                            <input type="text" class="form-control" id="date-input" value=""/>
                            <div class="input-group-addon input-group-prepend">
                                <div class="input-group-text">
                                    <i class="fa fa-calendar fa-lg" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Select</button>
                    </div>
                </div>
            </form>

            <br>

            <table id="schedule" class="display">
                <thead>
                <tr>
                    <th>Route ID</th>
                    <th>Train ID</th>
                    <th>Departure Station</th>
                    <th>Arrival Station</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                </tr>
                </thead>
            </table>

            <div id="chartdiv"></div>
        </div>

    </div>

    <%@include file="fragments/footer.jspf" %>
</div>

<%@include file="fragments/scripts.jspf" %>
<script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/maps.js"></script>
<script src="https://www.amcharts.com/lib/4/geodata/worldLow.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>
<script src="./js/Map.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        let map = new Map("chartdiv");
        map.render();

        let datePicker = $('#datepicker').datepicker({
            format: 'yyyy/mm/dd',
        });

        let date = new Date().toISOString().split('T')[0];

        datePicker.datepicker("setDate", date);

        let datatable = $("#schedule").DataTable({
            "paging": false,
            "processing": true,
            "language": {
                processing: '<i class="fa fa-spinner fa-spin fa-2x fa-fw"></i><span class="sr-only">Loading...</span> '
            },
            "serverSide": true,
            ajax: {
                url: "api/schedule-map?date=" + date,
                dataSrc: function(schedule) {

                    getStations().then(function (stations) {
                        let mappedStations = mapStationsToCoord(schedule, stations);
                        map.update(mappedStations.originCities, mappedStations.destinationCities);
                    }).then(function () {
                        map.updateLines();
                    });

                    return schedule;
                },
            },
            "columns" : [
                { "data" : "routeId"},
                { "data" : "trainId"},
                { "data" : "departStationName" },
                { "data" : "arrivalStationName" },
                { "data" : "departScheduledTime" },
                { "data" : "arrivalScheduledTime" }
            ],
        });


        $('#date-form').submit(function (e) {
            e.preventDefault();
            date = $('#date-input').val();

            datatable.ajax.url("api/schedule-map?date=" + date);
            datatable.ajax.reload();

            map.update();
        })
    });

    function getStations() {
        return $.ajax({
            url: 'api/schedule-map/stations',
            success: function (results) {
                return results;
            }
        })
    }

    function mapStationsToCoord(schedule, stations) {
        let originCities = [];

        schedule.forEach(function (route) {
            let arrCity = stations.find(x => x.title == route.arrivalCity);
            let depCity = stations.find(x => x.title == route.departCity);

            if (originCities.find(x => x.id == route.departCity) === undefined) {
                let k =  {
                    "id": route.departStationName,
                    "title": depCity.title,
                    "destinations": [arrCity.title],
                    "latitude": depCity.latitude,
                    "longitude": depCity.longitude,
                    "scale": 1.5,
                    "zoomLevel": 7,
                    "zoomLongitude": depCity.longitude,
                    "zoomLatitude": depCity.latitude
                };
                originCities.push(k)
            } else {
                let k = originCities.find(x => x.id === route.departCity);
                if (k.destinations.find(x => x === route.arrivalCity) === undefined) {
                    k.destinations.push(route.arrivalCity);
                }
            }
        });

        let destinationCities = [];

        stations.forEach(function (city) {
            if (originCities.find(x => x.id === city.title) === undefined) {
                destinationCities.push(city);
            }
        });

        return {
            destinationCities: destinationCities,
            originCities: originCities
        }
    }


</script>

</body>
</html>

