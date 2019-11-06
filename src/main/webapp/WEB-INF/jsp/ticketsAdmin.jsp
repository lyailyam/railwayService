<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Railway Service</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.auth0.com/js/auth0-samples-theme/1.0/css/auth0-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
    <style>
        .button {
            background-color: #D32A08;
        }
    </style>
</head>

<body class="h-100">
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf"%>
    <div id="page">
        <div class="container">
            <div class="mt-5">
                <div class="text-center hero my-5">
                    <p class="lead">
                        Manage Tickets as a Station Agent
                    </p>
                    <div id="container">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createTicketsModal">
                            Create Tickets
                        </button>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cancelTicketsModal">
                            Cancel/Change Tickets
                        </button>
                    </div>

                    <%-- Create tickets modal --%>
                    <div class="modal" id="createTicketsModal" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Create Tickets</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form>
                                        User ID:<br>
                                        <input type="text" name="userId" id="user_id">
                                        <br>
                                        Name:<br>
                                        <input type="text" name="name" id="user_name">
                                        <br>
                                        Surname:<br>
                                        <input type="text" name="surname" id="user_surname">
                                        <br>
                                        NationalID:<br>
                                        <input type="text" name="nationalId" id="national_id">
                                        <br>
                                        RailcarNum:<br>
                                        <input type="text" name="railcarNum" id="railcar_num">
                                        <br>
                                        TrainId:<br>
                                        <input type="text" name="trainId" id="train_id">
                                        <br>
                                        SeatNum:<br>
                                        <input type="text" name="seatNum" id="seat_num">
                                        <br><br>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="createTicketBtn">Create</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%-- Cancel/change tickets modal --%>
                    <div class="modal" id="cancelTicketsModal" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Cancel/Change Tickets</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="get-ticket-form">
                                        Ticket id:<br>
                                        <input type="number" name="ticket_id" id="ticket_id_input">
                                        <br><br>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary" id="cancelTicketBtn">Submit</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%-- Ticket modal --%>
                    <div class="modal hide" id="ticketModal" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Ticket</h5>
                                </div>
                                <div class="modal-body">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="deleteTicketBtn">Delete</button>
                                    <button type="button" class="btn btn-primary" id="applyChangesBtn">Apply changes</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="fragments/footer.jspf"%>
    <%@include file="fragments/scripts.jspf"%>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        // Cancel or change tickets
        $('#get-ticket-form').on('submit', function(e) {
            e.preventDefault();
            var ticketId = $("#ticket_id_input").val();
            getIndividualTicket(ticketId);
        });

        // Create Tickets
        $('#createTicketBtn').click( function() {
            console.log("createTicketBtn clicked");
            var jsonData = '{'
               +'"userId": ' + $("#user_id").val()
               +', "name": "' + $("#user_name").val()
               +'", "surname": "' + $("#user_surname").val()
               +'", "nationalId": "' + $("#national_id").val()
               +'", "railcarNum": ' + $("#railcar_num").val()
               +', "trainId": ' + $("#train_id").val()
               +', "seatNum": ' + $("#seat_num").val()
           + '}';

            console.log("json data = " + jsonData);

            createTicket(jsonData);
        });

        // Cancel ticket: action buttons
        $('#deleteTicketBtn').click( function() {
            var ticketId = $("#ticket_id_input").val();
            deleteTicket(ticketId);
        });
        $('#applyChangesBtn').click( function() {
            var ticketId = $("#ticket_id_input").val();
            applyChangesTicket(ticketId);
        });
    });

    function createTicket(data) {
        $.ajax({
            type: 'POST',
            url: 'api/tickets',
            data: data,
            contentType: 'application/json',
            success: function() {
                alert("Success");
                $('#createTicketsModal').modal('hide');
            },
            error: function (jqXHR, status, error) {
                alert("Could not create a ticket. Status: " + jqXHR.status);
            }
        });
    }


    function getIndividualTicket(ticketId) {
        $.ajax({
            url: 'api/tickets/' + ticketId,
            type: 'GET',
            success: function(res) {
                $('#cancelTicketsModal').modal('hide');
                populateModal(res);
                $("#ticketModal").modal('show');
            },
            error: function (jqXHR, status, error) {
                alert("Could not get the ticket. Status: " + jqXHR.status);
            }
        });
    }

    function deleteTicket(ticketId) {
        $.ajax({
            url: 'api/tickets/' + ticketId,
            type: 'DELETE',
            success: function() {
                $("#ticketModal").modal('hide');
                alert("Ticket successfully deleted");
            },
            error: function (jqXHR, status, error) {
                alert("Could not delete the ticket. Status: " + jqXHR.status);
            }
        });
    }

    function applyChangesTicket(ticketId) {
        console.log("Apply changes");
    }

    function populateModal(res) {
        console.log(res);
        $('#ticketModal').find('.modal-body').html(
            "<form>\n" +
            "Ticket ID:<br>\n" +
            "<input type=\"text\" name=\"ticketId\" value=" + res.id + ">\n" +
            "<br>\n" +
            "Status:<br>\n" +
            "<input type=\"text\" name=\"status\" value=" + res.status + ">\n" +
            "<br>\n" +
            "User ID:<br>\n" +
            "<input type=\"text\" name=\"userId\" value=" + res.userId + ">\n" +
            "<br>\n" +
            "Name:<br>\n" +
            "<input type=\"text\" name=\"name\" value=" + res.name + ">\n" +
            "<br>\n" +
            "Surname:<br>\n" +
            "<input type=\"text\" name=\"surname\" value=" + res.surname + ">\n" +
            "<br>\n" +
            "National ID:<br>\n" +
            "<input type=\"number\" name=\"nationalId\" value=" + res.nationalId + ">\n" +
            "<br>\n" +
            "Train ID:<br>\n" +
            "<input type=\"number\" name=\"trainId\" value=" + res.trainId + ">\n" +
            "<br>\n" +
            "Seat Num:<br>\n" +
            "<input type=\"number\" name=\"seatNum\" value=" + res.seatNum + ">\n" +
            "<br><br>\n" +
            "</form>"
        );
    }
</script>
</body>
</html>
