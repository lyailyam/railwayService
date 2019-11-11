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

<script type="text/javascript">

    var data = [];
    var date = null;
    var destinationCities = [];
    var chart = null;

    $(document).ready(function () {
        let datePicker = $('#datepicker').datepicker({
            format: 'yyyy/mm/dd',
        });
        date = new Date().toISOString().split('T')[0];

        console.log(date);

        datePicker.datepicker("setDate", date);

        initializeDataTable();

        getStations().then(function (r) {
            renderMap();
        });

        $('#date-form').submit(function (e) {
            e.preventDefault();

            date = $('#date-input').val();
            reloadDataTable();
            console.log(date);
        })
    });

    function reloadDataTable() {
        $("#schedule").DataTable().destroy();
        initializeDataTable();
    }

    function initializeDataTable() {
        $("#schedule").DataTable({
            "destroy": true,
            "ajax" : {
                "type": "GET",
                "url": "api/schedule-map?date=" + date,
                "dataSrc": function (json) {
                    data = json;
                    return json;
                }
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
    }

    function getStations() {
        return $.ajax({
            url: 'api/schedule-map/stations',
            success: function (results) {
                destinationCities = results;
            }
        })
    }

    function renderMap() {
        am4core.ready(function() {

            // Themes begin
            am4core.useTheme(am4themes_animated);
            // Themes end

            // Create map instance
            chart = am4core.create("chartdiv", am4maps.MapChart);

            var interfaceColors = new am4core.InterfaceColorSet();

            // Set map definition
            chart.geodata = am4geodata_worldLow;

            // Set projection
            chart.projection = new am4maps.projections.Mercator();

            // Export
            chart.exporting.menu = new am4core.ExportMenu();

            // Zoom control
            chart.zoomControl = new am4maps.ZoomControl();

            // Data for general and map use
            var originCities = [
                {
                    "id": "test",
                    "title": "Astana",
                    "destinations": [],
                    "latitude": 51.1605,
                    "longitude": 71.4704,
                    "scale": 1.5,
                    "zoomLevel": 7,
                    "zoomLongitude": 71.4704,
                    "zoomLatitude": 51.1605
                }
            ];


            // Default to Astana view
            chart.homeGeoPoint = { "longitude": 71.4704, "latitude": 51.1605 };
            chart.homeZoomLevel = 3;

            var targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";
            var planeSVG = "m2,106h28l24,30h72l-44,-133h35l80,132h98c21,0 21,34 0,34l-98,0 -80,134h-35l43,-133h-71l-24,30h-28l15,-47";

            // Texts
            var labelsContainer = chart.createChild(am4core.Container);
            labelsContainer.isMeasured = false;
            labelsContainer.x = 80;
            labelsContainer.y = 27;
            labelsContainer.layout = "horizontal";
            labelsContainer.zIndex = 10;


            var title = labelsContainer.createChild(am4core.Label);
            title.text = "Train routes";
            title.fill = am4core.color("#cc0000");
            title.fontSize = 20;
            title.valign = "middle";
            title.dy = 2;
            title.marginLeft = 15;

            var changeLink = chart.createChild(am4core.TextLink);
            changeLink.text = "Click to change origin city";
            changeLink.isMeasured = false;

            changeLink.events.on("hit", function() {
                if (currentOrigin == originImageSeries.dataItems.getIndex(0)) {
                    showLines(originImageSeries.dataItems.getIndex(1));
                }
                else {
                    showLines(originImageSeries.dataItems.getIndex(0));
                }
            })

            changeLink.x = 142;
            changeLink.y = 72;
            changeLink.fontSize = 13;


// The world
            var worldPolygonSeries = chart.series.push(new am4maps.MapPolygonSeries());
            worldPolygonSeries.useGeodata = true;
            worldPolygonSeries.fillOpacity = 0.6;
            worldPolygonSeries.exclude = ["AQ"];

// Origin series (big targets, London and Vilnius)
            var originImageSeries = chart.series.push(new am4maps.MapImageSeries());

            var originImageTemplate = originImageSeries.mapImages.template;

            originImageTemplate.propertyFields.latitude = "latitude";
            originImageTemplate.propertyFields.longitude = "longitude";
            originImageTemplate.propertyFields.id = "id";

            originImageTemplate.cursorOverStyle = am4core.MouseCursorStyle.pointer;
            originImageTemplate.nonScaling = true;
            originImageTemplate.tooltipText = "{title}";

            originImageTemplate.setStateOnChildren = true;
            originImageTemplate.states.create("hover");

            originImageTemplate.horizontalCenter = "middle";
            originImageTemplate.verticalCenter = "middle";

            var originHitCircle = originImageTemplate.createChild(am4core.Circle);
            originHitCircle.radius = 11;
            originHitCircle.fill = interfaceColors.getFor("background");

            var originTargetIcon = originImageTemplate.createChild(am4core.Sprite);
            originTargetIcon.fill = interfaceColors.getFor("alternativeBackground");
            originTargetIcon.strokeWidth = 0;
            originTargetIcon.scale = 1.3;
            originTargetIcon.horizontalCenter = "middle";
            originTargetIcon.verticalCenter = "middle";
            originTargetIcon.path = targetSVG;

            var originHoverState = originTargetIcon.states.create("hover");
            originHoverState.properties.fill = chart.colors.getIndex(1);

// when hit on city, change lines
            originImageTemplate.events.on("hit", function(event) {
                showLines(event.target.dataItem);
            })

// destination series (small targets)
            var destinationImageSeries = chart.series.push(new am4maps.MapImageSeries());
            var destinationImageTemplate = destinationImageSeries.mapImages.template;

            destinationImageTemplate.nonScaling = true;
            destinationImageTemplate.tooltipText = "{title}";
            destinationImageTemplate.fill = interfaceColors.getFor("alternativeBackground");
            destinationImageTemplate.setStateOnChildren = true;
            destinationImageTemplate.states.create("hover");

            destinationImageTemplate.propertyFields.latitude = "latitude";
            destinationImageTemplate.propertyFields.longitude = "longitude";
            destinationImageTemplate.propertyFields.id = "id";

            var destinationHitCircle = destinationImageTemplate.createChild(am4core.Circle);
            destinationHitCircle.radius = 7;
            destinationHitCircle.fillOpacity = 1;
            destinationHitCircle.fill = interfaceColors.getFor("background");

            var destinationTargetIcon = destinationImageTemplate.createChild(am4core.Sprite);
            destinationTargetIcon.scale = 0.7;
            destinationTargetIcon.path = targetSVG;
            destinationTargetIcon.horizontalCenter = "middle";
            destinationTargetIcon.verticalCenter = "middle";

            originImageSeries.data = originCities;
            destinationImageSeries.data = destinationCities;

// Line series
            var lineSeries = chart.series.push(new am4maps.MapLineSeries());
            lineSeries.mapLines.template.line.strokeOpacity = 0.5;

            chart.events.on("ready", function() {
                showLines(originImageSeries.dataItems.getIndex(0));
            })


            var currentOrigin;

            function showLines(origin) {

                var dataContext = origin.dataContext;
                var destinations = dataContext.destinations;
                // clear old
                lineSeries.mapLines.clear();
                lineSeries.toBack();
                worldPolygonSeries.toBack();

                currentOrigin = origin;

                if (destinations) {
                    for (var i = 0; i < destinations.length; i++) {
                        var line = lineSeries.mapLines.create();
                        line.imagesToConnect = [origin.mapImage.id, destinations[i]];
                    }
                }

                title.text = "Routes from " + dataContext.title;

                chart.zoomToGeoPoint({ latitude: dataContext.zoomLatitude, longitude: dataContext.zoomLongitude }, dataContext.zoomLevel, true);
            }

            var graticuleSeries = chart.series.push(new am4maps.GraticuleSeries());
            graticuleSeries.mapLines.template.line.strokeOpacity = 0.05;


        });
    }

</script>

</body>
</html>

