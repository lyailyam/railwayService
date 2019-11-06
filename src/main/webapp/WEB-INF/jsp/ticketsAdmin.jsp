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
                                    <form id="create-ticket-form">
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
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" id="createTicketBtn">Create</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- END Create tickets modal --%>

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
                                        <input type="number" name="ticketId" id="ticket_id">
                                        <br><br>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="cancelTicketBtn">Submit</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- END Cancel/change tickets modal --%>

                    <%-- Ticket modal --%>
                    <div class="modal" id="ticketModal" tabindex="-1" role="dialog">
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
                    <%-- END Ticket modal --%>

                </div>
            </div>
        </div>
    </div>
    <%@include file="fragments/footer.jspf"%>
    <%@include file="fragments/scripts.jspf"%>
</div>
<script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        // Create tickets modal -> Create ticket
        $('#createTicketBtn').click( function() {
            var jsonData = '{'
                +'"userId": ' + $("#user_id").val()
                +', "name": "' + $("#user_name").val()
                +'", "surname": "' + $("#user_surname").val()
                +'", "nationalId": ' + $("#national_id").val()
                +', "railcarNum": ' + $("#railcar_num").val()
                +', "trainId": ' + $("#train_id").val()
                +', "seatNum": ' + $("#seat_num").val()
                + '}';
            createTicket(jsonData);
        });

        // Cancel/change tickets modal -> Retrieve individual ticket to change or delete it
        $('#cancelTicketBtn').click(function() {
            var ticketId = $("#ticket_id").val();
            getIndividualTicket(ticketId);
        });

        // TicketModal -> Delete ticket
        $('#deleteTicketBtn').click( function() {
            var ticketId = $("#ticket_id").val();
            deleteTicket(ticketId);
        });

        // TicketModal -> Change ticket
        $('#applyChangesBtn').click(function() {
            var ticketId = $("#ticket_id").val();
            var jsonData = '{'
                +'"userId": ' + $("#m_user_id").val()
                +', "id": ' + $("#m_ticket_id").val()
                +', "name": "' + $("#m_user_name").val()
                +'", "surname": "' + $("#m_user_surname").val()
                +'", "nationalId": ' + $("#m_national_id").val()
                +', "railcarNum": ' + $("#m_railcarNum").val()
                +', "trainId": ' + $("#m_train_id").val()
                +', "seatNum": ' + $("#m_seat_num").val()
                + '}';
            modifyTicket(ticketId, jsonData);
        });

        // Remove backdrops when modals are closed
        $('#ticketModal').on('hidden.bs.modal', function(){
            $('.modal-backdrop').remove();
        })
        $('#cancelTicketsModal').on('hidden.bs.modal', function(){
            $('.modal-backdrop').remove();
        })
        $('#createTicketsModal').on('hidden.bs.modal', function(){
            $('.modal-backdrop').remove();
        })
    });

    function getIndividualTicket(ticketId) {
        $.ajax({
            url: 'api/tickets/' + ticketId,
            type: 'GET',
            success: function(result) {
                $('#cancelTicketsModal').hide();
                populateTicketModal(result);
                $("#ticketModal").modal('show');
            },
            error: function (jqXHR, status, error) {
                console.log(jqXHR);
                alert("Could not get the ticket with id=" + ticketId + ". Status: " + jqXHR.status);
            }
        });
    }

    function createTicket(data) {
        $.ajax({
            type: 'POST',
            url: 'api/tickets',
            data: data,
            contentType: 'application/json',
            success: function() {
                alert("Ticket successfully created!");
                $('#createTicketsModal').hide();
                $('.modal-backdrop').remove();
            },
            error: function (jqXHR, status, error) {
                console.log(jqXHR);
                alert("Could not create a ticket. Status: " + jqXHR.status);
            }
        });
    }

    function deleteTicket(ticketId) {
        $.ajax({
            url: 'api/tickets/' + ticketId,
            type: 'DELETE',
            success: function() {
                alert("Ticket with id=" + ticketId + " successfully deleted.");
                $("#ticketModal").hide();
                $('.modal-backdrop').remove();
            },
            error: function (jqXHR, status, error) {
                console.log(jqXHR);
                alert("Could not delete the ticket with id=" + ticketId + ". Status: " + jqXHR.status);
            }
        });
    }

    function modifyTicket(ticketId, jsonData) {
        $.ajax({
            type: 'PUT',
            url: 'api/tickets',
            data: jsonData,
            contentType: 'application/json',
            success: function() {
                alert("Ticket with id=" + ticketId + " successfully modified.");
                $('#ticketModal').hide();
                $('.modal-backdrop').remove();
            },
            error: function (jqXHR, status, error) {
                console.log(jqXHR);
                alert("Could not modify a ticket with id=" + ticketId + ". Status: " + jqXHR.status);
            }
        });
    }

    function populateTicketModal(res) {
        console.log(res);
        $('#ticketModal').find('.modal-body').html(
            "<form>\n" +
            "Ticket ID:<br>\n" +
            "<input type=\"text\" name=\"ticketId\" id=\"m_ticket_id\" value=" + res.id + ">\n" +
            "<br>\n" +
            "Status:<br>\n" +
            "<input type=\"text\" name=\"status\" id=\"m_ticket_status\" value=" + res.status + ">\n" +
            "<br>\n" +
            "User ID:<br>\n" +
            "<input type=\"text\" name=\"userId\" id=\"m_user_id\" value=" + res.userId + ">\n" +
            "<br>\n" +
            "Name:<br>\n" +
            "<input type=\"text\" name=\"name\" id=\"m_user_name\" value=" + res.name + ">\n" +
            "<br>\n" +
            "Surname:<br>\n" +
            "<input type=\"text\" name=\"surname\" id=\"m_user_surname\" value=" + res.surname + ">\n" +
            "<br>\n" +
            "National ID:<br>\n" +
            "<input type=\"text\" name=\"nationalId\" id=\"m_national_id\" value=" + res.nationalId + ">\n" +
            "<br>\n" +
            "Railcar Num:<br>\n" +
            "<input type=\"number\" name=\"railcarNum\" id=\"m_railcarNum\" value=" + res.railcarNum + ">\n" +
            "<br>\n" +
            "Train ID:<br>\n" +
            "<input type=\"number\" name=\"trainId\" id=\"m_train_id\" value=" + res.trainId + ">\n" +
            "<br>\n" +
            "Seat Num:<br>\n" +
            "<input type=\"number\" name=\"seatNum\" id=\"m_seat_num\" value=" + res.seatNum + ">\n" +
            "<br><br>\n" +
            "</form>"
        );
    }
</script>
</body>
</html>
