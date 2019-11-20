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
                <table id="all-routes" class="display" style="width:100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Route ID</th>
                        <th>From-To</th>
                        <th>Departure time</th>
                        <th>Arrival time</th>
                    </tr>
                    </thead>
                </table>
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
    $(document).ready(function() {
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
                }}
            ],

            "order": [[1, 'asc']]
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
</script>


</body>
</html>




