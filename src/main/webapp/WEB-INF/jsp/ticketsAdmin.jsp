<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" class="h-100">

<%@include file="fragments/header.jspf"%>

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
                        <button
                                type="button"
                                class="btn btn-primary"
                                data-toggle="modal"
                                data-target="#createTickets">
                            Create Tickets
                        </button>
                        <button
                                type="button"
                                class="btn btn-primary"
                                data-toggle="modal"
                                data-target="#cancelTickets">
                            Cancel/Change Tickets
                        </button>
                    </div>

                    <%-- Create tickets modal --%>
                    <div class="modal" id="createTickets" tabindex="-1" role="dialog">
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
                                        <input type="text" name="userid">
                                        <br>
                                        Name:<br>
                                        <input type="text" name="name">
                                        <br>
                                        Surname:<br>
                                        <input type="text" name="surname">
                                        <br>
                                        Date:<br>
                                        <input type="date" id="start" value="2018-11-01"
                                               min="2019-11-01" max="2022-12-31" name="date">
                                        <br>
                                        Route ID:<br>
                                        <input type="number" name="route_id">
                                        <br>
                                        LegNum:<br>
                                        <input type="number" name="legnum">
                                        <br><br>
                                        <input type="submit" value="Submit">
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%-- Cancel/change tickets modal --%>
                    <div class="modal" id="cancelTickets" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Cancel/Change Tickets</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/tickets" method="get">
                                        Ticket id:<br>
                                        <input type="number" name="ticket_id" id="ticket_id_input">
                                        <br><br>
                                        <input type="submit" value="Submit" id="cancelTicketsBtn">
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%-- Ticket modal --%>
                    <div class="modal hide" id="individualTicket">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">×</button>
                                <h3>Modal header</h3>
                            </div>
                            <div class="modal-body">
                                <p>some content</p>
                                <input type="text" name="bookId" id="bookId" value=""/>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script type="text/javascript">
    function getIndividualTicket(ticketId) {
        $.ajax({
            url: 'api/tickets/' + ticketId,
            type: 'GET',
            success: function(res) {
                $('#cancelTickets').modal('hide');
            }
        });
    }

    $(document).ready(function() {
        document.getElementById("cancelTicketsBtn").addEventListener("click", function(){
            var ticketId = $("#ticket_id_input").val();
            getIndividualTicket(ticketId);
        });
    });
</script>
</body>
</html>
