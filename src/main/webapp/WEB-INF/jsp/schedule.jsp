<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Find Ticket</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.auth0.com/js/auth0-samples-theme/1.0/css/auth0-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
</head>


<body class="h-100">
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf"%>

    <div id="page" layout:fragment="content">
        <div class="container">
            <div class="mt-5">
                <div id="appendToMe" class="container">
                    <h2>Search for tickets</h2>
                    <form class = "form-i">
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label class="control-label">From</label>
                                    <input class="form-control" id="fromCity" placeholder="Search for departure city" type="text"/>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label class="control-label">To</label>
                                    <input class="form-control" id="toCity" placeholder="Search for arrival city" type="text"/>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label class="control-label" for="date">Date</label>
                                    <input class="form-control" id="date" name="date" placeholder="Pick a departure date" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary" name="submit" type="submit">Search</button>
                        </div>
                    </form>
                    <div id="result" style="display: none">
                    <h3>Search results</h3>
                    <table id="trip-options" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th>Departure Station</th>
                            <th>Arrival Station</th>
                            <th>Departure time</th>
                            <th>Arrival time</th>
                            <th></th>
                            <th></th>
                            </tr>
                        </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
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
                        <form class="form-i">
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
                                <input class="form-control" id="nationalID" placeholder="Enter national ID of the person" type="text"/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Buy</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@include file="fragments/footer.jspf"%>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">

    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        var options={
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
            startDate: "new Date()",
        };
        date_input.datepicker(options);
    });

    $(".form-i").submit(function(e) {
        e.preventDefault();

        document.getElementById("result").style.display = 'block'
        var from = document.getElementById("fromCity").value;
        var to = document.getElementById("toCity").value;
        var date = document.getElementById("date").value;
        var table = $('#trip-options').DataTable({
            "destroy" : true,
            "ajax" : {
                "url" : 'api/schedule?from='+from+'&to='+to+'&date='+date,
                dataSrc : ''
            },

            "columns" : [
                { "data" : "depStationName" },
                { "data" : "arrStationName" },
                {
                    data: null, render: function ( data) {
                        return data.depDate+' - '+data.depSchedTime;
                    }
                },
                {
                    data: null, render: function ( data) {
                        return data.arrDate+' - '+data.arrSchedTime;
                    }
                },
                {
                    "targets": -1,
                    "data": null,
                    "defaultContent": "<button>Buy</button>"
                }]
        });

        $('#trip-options tbody').on( 'click', 'button', function () {
            $('#myModal').modal('show');
            var data = table.row( $(this).parents('tr') ).data();
            alert( "The ticket for train " + data[0] + "-" + data[1] + " is bought" );
        } );
    });

</script>

</body>
</html>

