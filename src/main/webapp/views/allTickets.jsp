<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tickets</title>
    <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
          rel = "stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


    <script type="text/javascript">


        var limit = 50;

        function displayTicketList(tickets) {
            $("#ticket-list").html("");
            tickets.forEach(function (e) {
                $("#ticket-list").append("<li>"
                    + "Ticket ID: " + e.id + " "
                    + "Departure from " + e.st1_name + " "
                    + "Departure time&date: " + e.dep_time + " "
                    + "Arrival to " + e.st2_name + " "
                    + "Arrival time&date: " + e.arr_time + " "
                    + "Ticket Status: " + e.ticket_status);
                    //+ " "
                    //+ "<a href = \"api/tickets/" + e.id + "\">link</a></li>");
            });
        }

        function getTicketList(page) {
            $.ajax({
                url : '../api/tickets?user_id=5&limit='+limit+'&offset=' + page,
                dataType : 'json',
                success : function(result) {
                    displayTicketList(result);
                },
                error: function (jqXHR, status, error) {
                    if (jqXHR.status == 500) {
                        alert("Internal server error");
                    } else if (jqXHR.status == 404) {
                        alert("Query error");
                    } else {
                        alert("Unknown error");
                    }
                    displayTicketList([]);
                }
            });
        }


        $(document).ready(function () {

            getTicketList(0);

        });
    </script>
</head>
<body>
    <h1>Tickets</h1>
    <ol id="ticket-list">Loading...</ol>
</body>
</html>
