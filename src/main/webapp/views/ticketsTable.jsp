<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>My trips</title>
    <meta charset="UTF-8">
    <style>
        .wrap-table100 {
            margin-right: 164px;
            margin-left: 164px;
        }
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">

    <script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script type="text/javascript">

        function format ( d ) {
            // `d` is the original data object for the row
            return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
                '<tr>'+
                '<td>Passenger name:</td>'+
                '<td>'+d.firstname+' '+d.surname+'</td>'+
                '</tr>'+
                '<tr>'+
                '<td>Status:</td>'+
                '<td>'+d.ticket_status+'</td>'+
                '</tr>'+
                '<tr>'+
                '<td>Extra info:</td>'+
                '<td>And any further details here (images etc)...</td>'+
                '</tr>'+
                '</table>';
        }
        $(document).ready(function() {
            var table = $('#upcoming-trips').DataTable({
                "ajax" : {
                    "url" : 'api/tickets?user_id=5&limit=1000&offset=0',
                    dataSrc : ''
                },
                "columns" : [
                {
                    "class":          "details-control",
                    "orderable":      false,
                    "data":           null,
                    "defaultContent": ""
                },
                { data: null, render: function ( data) {
                    return data.firstname+' '+data.surname;
                } }, { data: null, render: function ( data) {
                    return data.st1_name+' - '+data.st2_name;
                } }, {
                "data" : "dep_time"
                }, {
                    "data" : "arr_time"
                }, {
                    "data" : "ticket_status"
                }],

                "order": [[1, 'asc']]
            });

            $('#upcoming-trips tbody').on('click', 'td.details-control', function () {
                var tr = $(this).closest('tr');
                var row = table.row( tr );

                if ( row.child.isShown() ) {
                    // This row is already open - close it
                    row.child.hide();
                    tr.removeClass('shown');
                }
                else {
                    // Open this row
                    row.child( format(row.data()) ).show();
                    tr.addClass('shown');
                }
            } );
        });
    </script>

</head>

<%@include file="../WEB-INF/jsp/fragments/header.jspf"%>

<body>
<div class="h-100 d-flex flex-column">
    <%@include file="../WEB-INF/jsp/fragments/navbar.jspf"%>
    <div class="wrap-table100">
        <table id="upcoming-trips" class="display" style="width:100%">
            <thead>
                <tr>
                    <th></th>
                    <th>Passenger name</th>
                    <th>From-To</th>
                    <th>Departure time</th>
                    <th>Arrival time</th>
                    <th>Status</th>
                </tr>
            </thead>
        </table>
    </div>
</div>>
</body>
</html>




