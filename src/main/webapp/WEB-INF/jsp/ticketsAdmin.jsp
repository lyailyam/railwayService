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
                                    <form class="form-i" id="create-ticket-form">
                                        <div class="form-group">
                                            <label class="control-label">User ID</label>
                                            <input class="form-control" type="text" name="userId" id="user_id" value=""
                                                placeholder="Enter User ID"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Name</label>
                                            <input class="form-control" type="text" name="name" id="user_name" value=""
                                                   placeholder="Enter first name of the user"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Surname</label>
                                            <input class="form-control" type="text" name="surname" id="user_surname" value=""
                                                   placeholder="Enter last name of the user"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">National ID</label>
                                            <input class="form-control" type="text" name="nationalId" id="national_id" value=""
                                                   placeholder="Enter National ID number of the user"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Railcar</label>
                                            <input class="form-control" type="number" name="railcarNum" id="railcar_num" value=""
                                                   placeholder="Enter railcar number"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Train</label>
                                            <input class="form-control" type="number" name="trainId" id="train_id" value=""
                                                   placeholder="Enter Train ID"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Seat</label>
                                            <input class="form-control" type="number" name="seatNum" id="seat_num" value=""
                                                   placeholder="Enter seat number"/>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="createTicketBtn">Create</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
                                    <form class="form-i" id="get-ticket-form">
                                        <div class="form-group">
                                            <label class="control-label">Ticket ID</label>
                                            <input class="form-control" type="number" id="ticket_id" value=""
                                                   placeholder="Enter Ticket ID">
                                        </div>
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
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
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
            var jsonData2 = '{'
                +'"userId": ' + $("#user_id").val()
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

    function createTicket(data, date2) {
        $.ajax({
            type: 'POST',
            url: 'api/tickets',
            data: data,
            contentType: 'application/json',
            success: function() {
                alert("Ticket successfully created!");
                $('#createTicketsModal').hide();
                $('.modal-backdrop').remove();
                $.ajax({
                        type: 'POST',
                        url: 'api/ticket_route',
                        data: data2,
                        contentType: 'application/json'
                });
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
            "<form class=\"form-i\">\n" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Ticket ID</label>\n" +
                    "<input class=\"form-control\" id=\"m_ticket_id\" type=\"text\" value=" + res.id + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Status</label>\n" +
                    "<input class=\"form-control\" id=\"m_ticket_status\" type=\"text\" value=" + res.status + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">User ID</label>\n" +
                    "<input class=\"form-control\" id=\"m_user_id\" type=\"text\" value=" + res.userId + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Name</label>\n" +
                    "<input class=\"form-control\" id=\"m_user_name\" type=\"text\" value=" + res.name + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Surname</label>\n" +
                    "<input class=\"form-control\" id=\"m_user_surname\" type=\"text\" value=" + res.surname + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">National ID</label>\n" +
                    "<input class=\"form-control\" id=\"m_national_id\" type=\"text\" value=" + res.nationalId + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Railcar</label>\n" +
                    "<input class=\"form-control\" id=\"m_railcarNum\" type=\"number\" value=" + res.railcarNum + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Train ID</label>\n" +
                    "<input class=\"form-control\" id=\"m_train_id\" type=\"number\" value=" + res.trainId + ">\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Seat</label>\n" +
                    "<input class=\"form-control\" id=\"m_seat_num\" type=\"number\" value=" + res.seatNum + ">\n" +
                "</div>" +
            "</form>"
        );
    }
</script>
</body>
</html>
