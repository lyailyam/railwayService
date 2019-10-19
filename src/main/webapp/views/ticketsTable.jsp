<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>My trips</title>
    <meta charset="UTF-8">
    <style>
        .wrap-table100 {
            margin: 40px;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">

    <script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {
            $('#upcoming-trips').DataTable({
                "ajax" : {
                    "url" : 'api/tickets?user_id=5&limit=1000&offset=0',
                    dataSrc : ''
                },
                "columns" : [{ data: null, render: function ( data) {
                        return data.firstname+' '+data.surname;
                    } }, { data: null, render: function ( data) {
                        return data.st1_name+' - '+data.st2_name;
                    } }, {
                    "data" : "dep_time"
                }, {
                    "data" : "arr_time"
                }, {
                    "data" : "ticket_status"
                }]
            });
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
                <th>Name</th>
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




