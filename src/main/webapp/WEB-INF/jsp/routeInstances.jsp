<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <title>Route Instances</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.auth0.com/js/auth0-samples-theme/1.0/css/auth0-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
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
        <c:choose>
            <c:when test="${not empty profile}">
                <c:choose>
                    <c:when test="${appMetadata
                                            .getJSONObject(\"app_metadata\")
                                            .getJSONObject(\"authorization\")
                                            .getJSONArray(\"roles\").get(0).equals(\"passenger\")}">
                        <div class="mt-5">
                            <div class="text-center hero my-5">
                                <p class="lead">
                                    This is the Passenger Panel. From this panel you should be able to access
                                    all the admin privileges of the Railway Service.
                                </p>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${appMetadata
                                            .getJSONObject(\"app_metadata\")
                                            .getJSONObject(\"authorization\")
                                            .getJSONArray(\"roles\").get(0).equals(\"station_manager\")}">
                        <div class="mt-5">
                            <div class="text-center hero my-5">
                                <p class="lead">
                                    This is the Station Manager Panel. From this panel you should be able to access
                                    all the admin privileges of the Railway Service.
                                </p>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="mt-5">
                            <div class="text-center hero my-5">
                                <p class="lead">
                                    This is the Station Agent Panel. From this panel you should be able to change,
                                    cancel, and create tickets upon user requests.
                                </p>
                                <form action="${pageContext.request.contextPath}/admintickets" method="GET">
                                    <input type="submit" class="btn btn-primary btn-block" value="Manage Tickets"/>
                                </form>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <table id="all-route-instances" class="display" style="width:100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Route ID</th>
                        <th>From-To</th>
                        <th>Date</th>
                        <th>Departure time</th>
                        <th>Arrival time</th>
                        <th>Train #</th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
                <div id="myModal" class="modal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Buy a ticket</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="alert-success" class="alert alert-success" role="alert" style="display: none">
                                    You have successfully updated a trip. The passengers will be notified via out advisories.
                                </div>
                                <div id="alert-error" class="alert alert-danger" role="alert" style="display: none">
                                    Error occurred while updating a trip.
                                </div>
                                <form class="form-i">
                                    <div class="form-group">
                                        <label class="control-label">Route ID</label>
                                        <input class="form-control" id="depStat" type="text" value="" disabled/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Date</label>
                                        <input class="form-control" id="arrStat" type="text" value="" disabled/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">First Name</label>
                                        <input class="form-control" id="name" placeholder="Enter first name of the person" type="text"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Last Name</label>
                                        <input class="form-control" id="surname" placeholder="Enter last name of the person" type="text"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">National ID</label>
                                        <input class="form-control" id="national-id" placeholder="Enter national ID of the person" type="text"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Seat</label>
                                        <select class="form-control" name="seat-num" id="select-seat-num"></select>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="buy-ticket">Buy</button>
                                <button type="button" class="btn btn-secondary" id="cancel-buy-ticket" data-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript">

    function format ( d ) {
        var str = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
            '<tr>' +
            '<th>Leg #</th>' +
            '<th>Departure station</th>' +
            '<th>Arrival station</th>' +
            '<th>Departure time</th>' +
            '<th>Arrival time</th>' +
            '<th>Available Seats</th>' +
            '</tr>';
        for (index = 0; index < d.legs.length; index++) {
            str += '<tr>' +
                '<td>' + d.legs[index].leg_num + '</td>' +
                '<td>' + d.legs[index].depart_station_name + '</td>' +
                '<td>' + d.legs[index].arrival_station_name + '</td>' +
                '<td>' + d.legs[index].depart_actual_time + '</td>' +
                '<td>' + d.legs[index].arrival_actual_time + '</td>' +
                '<td>' + d.legs[index].available_seats + '</td>' +
                '</tr>';
        }
        str += '</table>';
        return str;
    }

    $(document).ready(function() {
        var table = $('#all-route-instances').DataTable({
            "ajax" : {
                "url" : 'api/route_instances',
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
                {   data: null,
                    render: function ( data) {
                        return data.legs[0].depart_station_name+' - '+data.legs[data.legs.length-1].arrival_station_name;
                }},
                {
                    "data" : "date"
                },
                {   data: null,
                    render: function ( data ) {
                        return data.legs[0].depart_actual_time;
                }},
                {   data: null,
                    render: function ( data) {
                        return data.legs[data.legs.length-1].arrival_actual_time;
                }},
                {
                    "data" : "train_id"
                },
                {
                    orderable: false,
                    "data": null,
                    "render": function ( data, type, row, meta  ) {
                        var d = new Date(),
                            month = '' + (d.getMonth() + 1),
                            day = '' + d.getDate(),
                            year = d.getFullYear();

                        if (month.length < 2)
                            month = '0' + month;
                        if (day.length < 2)
                            day = '0' + day;

                        var currentDate = [year, month, day].join('-');
                            if (data.date > currentDate)
                                return "<button class='update' data-toggle='modal'>Update</button>" +
                                "<button class='cancel'>Cancel</button>";
                            else
                                return null;
                    }
                }
            ],
            "order": [[1, 'asc']]
        });

        $('#all-route-instances tbody').on( 'click', 'button', function () {

            var action = this.className;
            var data = table.row($(this).parents('tr')).data();

            if (action == 'cancel')
                var r = confirm("Are you sure you want to cancel trip with route ID = " + data.route_id + " for " + data.date + "?");
            if (r) {
                deleteRouteInstance(data.route_id, data.date);
            }

            if (action == 'update') {

                $('#myModal').modal('show');
                $('.modal-body #depStat').val(data['route_id']);
                $('.modal-body #arrStat').val(data['date']);
                <%--$.ajax({--%>
                <%--    type: 'GET',--%>
                <%--    url: 'api/leg_instances/'+data['routeId']+'/'+data['firstStatLegNum']+'/'+data['depDate'],--%>
                <%--    success: function(result) {--%>
                <%--        $.ajax({--%>
                <%--            type: 'GET',--%>
                <%--            url: '${pageContext.request.contextPath}/api/seats/?trainId='+result['trainId'],--%>
                <%--            success: function(seats) {--%>
                <%--                console.log(seats);--%>
                <%--                var select = document.getElementById('select-seat-num');--%>
                <%--                $(select).empty();--%>
                <%--                for(var i in seats) {--%>
                <%--                    $(select).append('<option value=' + i + '>' + seats[i]['num'] + '</option>');--%>
                <%--                }--%>
                <%--                $(select).val(0);--%>
                <%--                $('#buy-ticket').on('click', function () {--%>
                <%--                    var seatNum = $("#select-seat-num :selected").text();--%>
                <%--                    var seatVal = $("#select-seat-num :selected").val();--%>
                <%--                    var json = '{'--%>
                <%--                        +'"name": "' + $("#name").val() + '"'--%>
                <%--                        +', "nationalId": ' + $("#national-id").val()--%>
                <%--                        +', "railcarNum": ' + seats[seatVal]['railcarNum']--%>
                <%--                        +', "seatNum": ' + parseInt(seatNum)--%>
                <%--                        +', "surname": "' + $("#surname").val() + '"'--%>
                <%--                        +', "status": "bought"'--%>
                <%--                        +', "trainId": ' + seats[seatVal]['trainId']--%>
                <%--                        +', "userId": ${userId}'--%>
                <%--                        +'}';--%>
                <%--                    console.log(json)--%>
                <%--                    $.ajax({--%>
                <%--                        type: 'POST',--%>
                <%--                        url: 'api/tickets/',--%>
                <%--                        data: json,--%>
                <%--                        contentType: 'application/json',--%>
                <%--                        success: function (result) {--%>
                <%--                            console.log(result);--%>
                <%--                            $("#alert-success").show();--%>
                <%--                            setTimeout(function() {--%>
                <%--                                $("#alert-success").remove();--%>
                <%--                                $("#name").val("");--%>
                <%--                                $("#surname").val("");--%>
                <%--                                $("#national-id").val("");--%>
                <%--                                $("#myModal").modal('hide');--%>
                <%--                            }, 5000);--%>
                <%--                        },--%>
                <%--                        error: function (error) {--%>
                <%--                            console.log(error);--%>
                <%--                            $("#alert-error").show();--%>
                <%--                            setTimeout(function() {--%>
                <%--                                $("#alert-error").remove();--%>
                <%--                            }, 5000);--%>
                <%--                        }--%>
                <%--                    });--%>
                <%--                });--%>
                <%--                $("#cancel-buy-ticket").on('click', function () {--%>
                <%--                    $("#name").val("");--%>
                <%--                    $("#surname").val("");--%>
                <%--                    $("#national-id").val("");--%>
                <%--                    $("#myModal").modal('hide');--%>
                <%--                });--%>
                <%--            },--%>
                <%--        });--%>
                <%--    },--%>
                <%--    error: function(error) {--%>
                <%--        console.log(error);--%>
                <%--    }--%>
                // })
            }
        });

        $('#all-route-instances tbody').on('click', 'td.details-control', function () {
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

    function deleteRouteInstance(route_id, date) {
        $.ajax({
            url: 'api/route_instances/'+route_id+'/'+date,
            type: 'DELETE',
            success: function() {
                alert("The trip was successfully canceled. The passengers will be notified via out advisories.")
                $('#all-route-instances').DataTable().ajax.reload();
            },
            error: function (jqXHR) {
                alert("Could not delete the trip. Status: " + jqXHR.status);
            }
        });
    }
</script>


</body>
</html>



