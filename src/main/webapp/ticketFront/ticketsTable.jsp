<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Table V04</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">

    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {
            $('#upcoming-trips').DataTable({
                "processing" : true,
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
<body>

<div class="limiter">
    <div class="container-table100">
        <div class="wrap-table100">

            <div class="table100 ver5 m-b-110">
                <div class="table100-body">
                    <table id="upcoming-trips" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th class="cell100 column1">Name</th>
                            <th class="cell100 column2">From-To</th>
                            <th class="cell100 column3">Departure time</th>
                            <th class="cell100 column4">Arrival time</th>
                            <th class="cell100 column5">Status</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




