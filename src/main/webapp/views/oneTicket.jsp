<%--
  Created by IntelliJ IDEA.
  User: talgatomarov
  Date: 17/10/2019
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script type="text/javascript">

        function displayTicket(ticket) {
            $("#ticket").html("");
            $("#ticket").append("<h2>"
                + "Ticket ID: " + ticket.id + '<br>'
                + "Price: " + ticket.price + '<br>'
                + "User ID: " + ticket.userId + '<br>'
                + "Trip ID: " + ticket.tripId + '<br>'
                + "</h2>");
        }

        function getTicket() {
            $.ajax({
                url : 'api/tickets/' + ${id},
                dataType : 'json',
                success : function(result) {
                    console.log(result);
                    displayTicket(result);
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
            getTicket();

        });
    </script>
</head>
<body>
    <h1>Tickets</h1>
    <div id="ticket">Loading...</div>
    <a href="?">Back to all tickets</a>
</body>
</html>