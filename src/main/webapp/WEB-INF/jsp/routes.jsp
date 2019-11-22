<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <title>Routes</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.auth0.com/js/auth0-samples-theme/1.0/css/auth0-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <style>
        td.details-control {
            background-image: url("https://img.icons8.com/pastel-glyph/2x/plus.png") ;
            background-position: center;
            background-size: 28px 28px;
            background-repeat: no-repeat;
            cursor: pointer;
        }
        tr.shown td.details-control {
            background-image: url("https://img.icons8.com/pastel-glyph/2x/minus.png");
            background-size: 28px 28px;
            background-repeat: no-repeat;
            background-position: center;
        }
    </style>

</head>


<body>
<div class="page">
    <%@include file="fragments/navbar.jspf"%>
    <div class="container">
                <button type="button" class="btn btn-primary" id="add-route">Add a new route</button>
                <hr>
                <table id="all-routes" class="display" style="width:100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Route ID</th>
                        <th>From-To</th>
                        <th>Departure time</th>
                        <th>Arrival time</th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
                <div id="myModal" class="modal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Update a trip</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="alert-success" class="alert alert-success" role="alert" style="display: none">
                                    You have successfully created an instance.
                                </div>
                                <div id="alert-error" class="alert alert-danger" role="alert" style="display: none">
                                    Error occurred while creating an instance.
                                </div>
                                <div id="you-fucked-up-time-alert" class="alert alert-danger" role="alert" style="display: none">
                                    Invalid time inputs!
                                </div>
                                <div id="you-fucked-up-date-alert" class="alert alert-danger" role="alert" style="display: none">
                                    Invalid date input!
                                </div>
                                <form class="form-i" id="form">
                                    <div class="form-group">
                                        <label class="control-label">Route ID</label>
                                        <input class="form-control" id="route-id" type="text" value="" disabled/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Date</label>
                                        <input class="form-control" id="date" name="date" type="text" value="" required/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Train ID</label>
                                        <select class="form-control" id="train-id" type="text" value="" required></select>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <h5>From</h5>
                                        </div>
                                        <div class="col">
                                            <h5>To</h5>
                                        </div>
                                    </div>
                                    <div id="legs"></div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="create-instance">Create Instance</button>
                                <button type="button" class="btn btn-secondary" id="cancel-update-ticket" data-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="addRouteModal" class="modal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Update a trip</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="alert-success2" class="alert alert-success" role="alert" style="display: none">
                                    You have successfully created a route.
                                </div>
                                <div id="alert-error2" class="alert alert-danger" role="alert" style="display: none">
                                    Error occurred while creating a route.
                                </div>
                                <div id="you-fucked-up-time-alert2" class="alert alert-danger" role="alert" style="display: none">
                                    Invalid time inputs!
                                </div>
                                <div id="you-fucked-up-stations-alert2" class="alert alert-danger" role="alert" style="display: none">
                                    Invalid station inputs!
                                </div>
                                <form class="form-i" id="form2">
                                    <div class="row">
                                        <div class="col">
                                            <h5>From</h5>
                                        </div>
                                        <div class="col">
                                            <h5>To</h5>
                                        </div>
                                    </div>
                                    <div id="legs2"></div>
                                    <button type="button" class="btn btn-primary" id="add-new-leg">Add new leg</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="add-new-route">Add route</button>
                                <button type="button" class="btn btn-secondary" id="cancel-add-new-route" data-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/highlight.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript">


    function format ( d ) {
        var str = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
            '<tr>' +
            '<th>Leg #</th>' +
            '<th>Departure station</th>' +
            '<th>Arrival station</th>' +
            '<th>Scheduled departure time</th>' +
            '<th>Scheduled Arrival time</th>' +
            '</tr>';
        for (index = 0; index < d.legs.length; index++) {
            str += '<tr>' +
                '<td>' + d.legs[index].leg_num + '</td>' +
                '<td>' + d.legs[index].depart_station_name + '</td>' +
                '<td>' + d.legs[index].arrival_station_name + '</td>' +
                '<td>' + d.legs[index].depart_scheduled_time + '</td>' +
                '<td>' + d.legs[index].arrival_scheduled_time + '</td>' +
                '</tr>';
        }
        str += '</table>';
        return str;
    }
    //<div class="row">
    // <div class="col">
    //     <div class="form-group">
    //     <select class="control-label" id="from-station-1" required></select>
    // <input class="form-control" id="from-time-1" type="text" value="00:00:00" required/>
    // </div>
    // </div>
    // <div class="col">
    //     <div class="form-group">
    //     <select class="control-label" id="to-station-1" required></select>
    // <input class="form-control" id="to-time-1" type="text" value="00:00:00" required/>
    // </div>
    // </div>
    // </div>
    $(document).ready(function() {
        $('#add-route').click(function() {

            $('#addRouteModal').modal('show');
            $.ajax({
                type: 'GET',
                url: 'api/stations'
            }).then(function (result) {
                var stations = "";
                result.forEach(function (e) {
                    stations += '<option>' + e.id + ': ' + e.name + '</option>';
                });
                return stations;
            }).then( function(stations) {
                console.log(stations);
                $("#legs2").html(
                    '<div class="row">'+
                    '<div class="col">'+
                    '<div class="form-group">'+
                    '<select class="control-label" id="from-station-1" required>'+stations+'</select>'+
                    '<input class="form-control" id="from-time-1" type="text" value="00:00:00" required/>'+
                    '</div>'+
                    '</div>'+
                    '<div class="col">'+
                    '<div class="form-group">'+
                    '<select class="control-label" id="to-station-1" required>'+stations+'</select>'+
                    '<input class="form-control" id="to-time-1" type="text" value="00:00:00" required/>'+
                    '</div>'+
                    '</div>'+
                    '</div>'
                );

                $('#add-new-leg').click(function() {
                    var legsNum = document.getElementById("legs2").childElementCount;

                    var str = '<div class="row">'+
                        '<div class="col">'+
                        '<div class="form-group">'+
                        '<select class="control-label" id="from-station-'+(legsNum+1)+'" required>'+stations+'</select>'+
                        '<input class="form-control" id="from-time-'+(legsNum+1)+'" type="text" value="00:00:00" required/>'+
                        '</div>'+
                        '</div>'+
                        '<div class="col">'+
                        '<div class="form-group">'+
                        '<select class="control-label" id="to-station-'+(legsNum+1)+'" required>'+stations+'</select>'+
                        '<input class="form-control" id="to-time-'+(legsNum+1)+'" type="text" value="00:00:00" required/>'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                    $("#legs2").append(str);

                })
            });

        })
        $('#add-new-route').click(function() {
            var json = "{";
            json += '"route_id": ' + 1 + ",";
            json += '"legs": [';
            var toTime = 0;
            var invalidTime = false;
            var invalidStation = false;
            var legsNum = document.getElementById("legs2").childElementCount;
            console.log(legsNum);
            var toStation = "";

            for (var i = 1; i <= legsNum; i++) {
                var leg_num = i;
                var fromTime = $("#from-time-" + leg_num).val();
                if (fromTime < toTime)
                    invalidTime = true;
                toTime = $("#to-time-" + leg_num).val();
                if (toTime <= fromTime)
                    invalidTime = true;
                if ((!/^\d{1,2}:\d{1,2}:\d{1,2}$/.test(fromTime)))
                    invalidTime = true;

                var fromStation = $("#from-station-" + leg_num).val();
                if (toStation !== "" && fromStation !== toStation)
                    invalidStation = true;
                toStation = $("#to-station-" + leg_num).val();
                if (toStation === fromStation)
                    invalidStation = true;
                var fromStationId = fromStation.split(':')[0];
                var toStationId = toStation.split(':')[0];
                json += '{'
                    +'"leg_num": ' + leg_num
                    +', "depart_scheduled_time": "' + fromTime
                    +'", "arrival_scheduled_time": "' + toTime
                    +'", "depart_station_name": "' + fromStation
                    +'", "arrival_station_name": "' + toStation
                    +'", "depart_station_id": ' + fromStationId
                    +', "arrival_station_id": ' + toStationId
                    +'},';
            }

            json = json.substring(0, json.length - 1);
            json += "]}";
            if (invalidTime) {
                $("#you-fucked-up-time-alert2").show();
                setTimeout(function () {
                    $("#you-fucked-up-time-alert2").remove();
                }, 5000);
            }
            else if (invalidStation) {
                $("#you-fucked-up-stations-alert2").show();
                setTimeout(function () {
                    $("#you-fucked-up-stations-alert2").remove();
                }, 5000);
            }
            else {
                console.log(json);
                addRoute(json);
            }
        });
        var table = $('#all-routes').DataTable({
            "ajax" : {
                "url" : 'api/routes',
                dataSrc : ''
            },
            "columns" : [
                {
                    "class":          "details-control",
                    "orderable":      false,
                    "data":           null,
                    "defaultContent": ""
                },
                {
                    "data" : "route_id"
                },
                { data: null, render: function ( data) {
                        return data.legs[0].depart_station_name+' - '+data.legs[data.legs.length-1].arrival_station_name;
                }},
                { data: null, render: function ( data) {
                        return data.legs[0].depart_scheduled_time;
                }},
                { data: null, render: function ( data) {
                        return data.legs[data.legs.length-1].arrival_scheduled_time;
                }},
                {
                    orderable: false,
                    "data": null,
                    "render": function ( data ) {
                        return "<button class='create-instance' data-toggle='modal'>Create Instance</button>";
                    }
                }
            ],

            "order": [[1, 'asc']]
        });


        $('#all-routes tbody').on( 'click', 'button', function () {

            var data = table.row($(this).parents('tr')).data();

            $('#myModal').modal('show');
            $('.modal-body #route-id').val(data['route_id']);
            $("#legs").html("");
            for (var i = 0; i < data['legs'].length; i++) {
                var leg = data['legs'][i];
                var string =
                    '<div class="row">' +
                    '<div class="col">' +
                    '<div class="form-group">' +
                    '<label class="control-label">'+leg['depart_station_name']+'</label>' +
                    '<input class="form-control" id="from-time-'+leg['leg_num']+'" type="text" value="'+leg['depart_scheduled_time']+'" required/>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col">' +
                    '<div class="form-group">' +
                    '<label class="control-label">'+leg['arrival_station_name']+'</label>' +
                    '<input class="form-control" id="to-time-'+leg['leg_num']+'" type="text" value="'+leg['arrival_scheduled_time']+'" required/>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $("#legs").append(string);
            }
            $.ajax({
                type: 'GET',
                url: 'api/trains',
                success: function(result) {
                    $("#train-id").html("");
                    result.forEach(function (e) {
                        $("#train-id").append('<option>'+e+'</option>');
                    })
                }
            });
            var date_input = $('input#date');
            var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
            var options = {
                format: 'yyyy-mm-dd',
                container: container,
                todayHighlight: true,
                autoclose: true,
                startDate: "new Date()",
                defaultViewDate: new Date()
            };
            date_input.datepicker(options);
            $('#create-instance').click(function() {
                var json = "{";
                json += '"date": "' + $("#date").val() + '",';
                json += '"route_id": ' + $("#route-id").val() + ",";
                json += '"legs": [';
                var toTime = 0;
                var invalidTime = false;
                var legs = data['legs'];
                for (var i = 0; i < legs.length; i++) {
                    var leg = data['legs'][i];
                    var leg_num = leg['leg_num'];
                    var fromTime = $("#from-time-" + leg_num).val();
                    if (fromTime < toTime)
                        invalidTime = true;
                    toTime = $("#to-time-" + leg_num).val();
                    if (toTime <= fromTime)
                        invalidTime = true;
                    if ((!/^\d{1,2}:\d{1,2}:\d{1,2}$/.test(fromTime)))
                        invalidTime = true;

                    json += '{'
                        +'"leg_num": ' + leg_num
                        +', "depart_actual_time": "' + fromTime
                        +'", "arrival_actual_time": "' + toTime
                        +'", "available_seats": ' + 20
                        +', "depart_station_name": "' + leg['depart_station_name']
                        +'", "arrival_station_name": "' + leg['arrival_station_name']
                        +'", "depart_station_id": ' + leg['depart_station_id']
                        +', "arrival_station_id": ' + leg['arrival_station_id']
                        +'},';
                }

                json = json.substring(0, json.length - 1);
                json += "],";
                var train_id = $("#train-id").val();
                json += '"train_id": ' + train_id + ', "fuckPassengers": false}'; //todo check if train is not occupied this date
                if (invalidTime) {
                    $("#you-fucked-up-time-alert").show();
                    setTimeout(function () {
                        $("#you-fucked-up-time-alert").remove();
                    }, 5000);
                }
                else if (!/^\d{4}-\d{1,2}-\d{1,2}$/.test($("#date").val())) {
                    $("#you-fucked-up-date-alert").show();
                    setTimeout(function () {
                        $("#you-fucked-up-date-alert").remove();
                    }, 5000);
                }
                else {
                    console.log(json);
                    update(json);
                }
            });

        });

        $('#all-routes tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var row = table.row( tr );

            if ( row.child.isShown() ) {
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                row.child( format(row.data()) ).show();
                tr.addClass('shown');
            }
        } );
    });
    function update(data) {
        $.ajax({
            type: 'POST',
            url: 'api/route_instances',
            data: data,
            contentType: 'application/json',
            success: function() {
                $("#alert-success").show();
                setTimeout(function() {
                    $("#alert-success").remove();
                    $("#myModal").modal('hide');
                }, 5000);
                $('#all-route-instances').DataTable().ajax.reload();
            },
            error: function (jqXHR) {
                console.log("AAAAAA");
                console.log(jqXHR);
                $("#alert-error").show();
                setTimeout(function() {
                    $("#alert-error").remove();
                }, 10000);
            }
        })
    }
    function addRoute(data) {
        $.ajax({
            type: 'POST',
            url: 'api/routes',
            data: data,
            contentType: 'application/json',
            success: function() {
                $("#alert-success2").show();
                setTimeout(function() {
                    $("#alert-success2").remove();
                    $("#addRouteModal").modal('hide');
                }, 5000);
                $('#all-routes').DataTable().ajax.reload();
            },
            error: function (jqXHR) {
                console.log("AAAAAA");
                console.log(jqXHR);
                $("#alert-error2").show();
                setTimeout(function() {
                    $("#alert-error2").remove();
                }, 10000);
            }
        })
    }
</script>


</body>
</html>




