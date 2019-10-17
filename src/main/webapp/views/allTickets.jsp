<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <script type="text/javascript">
        var tickets = {
            items : [],
        };

        var limit = 50;


        function displayTicketList() {
            $("#ticket-list").html("");
            tickets.items.forEach(function (e) {
                $("#ticket-list").append("<li>"
                    + "Ticket ID: " + e.id + ' '
                    + "Price: " + e.price + ' '
                    + "User ID: " + e.userId + ' '
                    + "Trip ID: " + e.tripId + ' '
                    + '<a href="?id=' + e.id + '">link</a>'
                    + "</li>");
            });
        }

        function getTicketList(page) {
            $.ajax({
                url : 'api/tickets?limit='+limit+'10&offset=' + 0,
                dataType : 'json',
                success : function(result) {
                    tickets.items = result;
                    displayTicketList();
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
    <ul id="ticket-list">Loading...</ul>
</body>
</html>
