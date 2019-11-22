<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Employees Schedule</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.auth0.com/js/auth0-samples-theme/1.0/css/auth0-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>

    <style>
        .wrap-table100 {
            margin-right: 164px;
            margin-left: 164px;
        }
        td.details-control {
            background-image: url("https://img.icons8.com/pastel-glyph/2x/plus.png") ;
            background-position: center;
            background-size: 28px 28px;
            background-repeat: no-repeat;
            cursor: pointer;
        }
        tr.shown td.details-control {
            background-image: url("https://img.icons8.com/pastel-glyph/2x/minus.png");
            background-size: 28px 28px;
            background-repeat: no-repeat;
            background-position: center;
        }

        .issue-paycheck {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .adjust-hours {
            background-color: #1659FF;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>

</head>

<body class="h-100">
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf"%>

    <div id="page" layout:fragment="content">
        <div class="container">
            <div class="mt-5">
                <div id="mainContainer" class="container">
                    <h2>Employees Schedule</h2>
                    <table id="employees-table" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Station ID</th>
                            <th>Salary</th>
                            <th>Employed Since</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <%--Make Payroll modal--%>
        <div class="modal" id="makePayrollModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Make Payroll</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="alert-success" class="alert alert-success" role="alert" style="display: none">
                            Paycheck has been issued successfully.
                        </div>
                        <div id="alert-error" class="alert alert-danger" role="alert" style="display: none">
                            Error occurred while issuing paycheck.
                        </div>
                        <form class="form-i" id="make-payroll-form">
                            <div class="form-group">
                                <label class="control-label">Employee ID</label>
                                <input class="form-control" id="employeeId" type="text" value="" disabled/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Name</label>
                                <input class="form-control" id="firstName" type="text" value="" disabled/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Surname</label>
                                <input class="form-control" id="surname" type="text" value="" disabled/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Salary</label>
                                <input class="form-control" id="salary" type="text" value="" disabled/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Role</label>
                                <input class="form-control" id="role" type="text" value="" disabled/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Station ID</label>
                                <input class="form-control" id="stationId" type="text" value="" disabled/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="date">Date</label>
                                <input class="form-control" id="date" name="date" placeholder="Pick today's date" type="text"/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="make-payroll-btn">Make Paycheck</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
        <%--Adjust Hours modal--%>
        <div class="modal" id="adjustHoursModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Adjust Hours</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="adjust-hours-btn">Apply Changes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
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
<script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">

    var weekDays = {1: 'Monday', 2: 'Tuesday', 3: 'Wednesday', 4: 'Thursday', 5: 'Friday', 6: 'Saturday', 7: 'Sunday'};
    var weekNums = [1, 2, 3, 4, 5, 6, 7];
    var numDays = 0;

    function formatSchedule(data) {
        var result = "<table cellpadding=\"5\" cellspacing=\"0\" border=\"0\" style=\"padding-left:50px;\">" +
            "<thead>\n" +
            "<tr>\n" +
            "<th>Day</th>\n" +
            "<th>Time</th>\n" +
            "<th>Number of hours</th>\n" +
            "</tr>\n" +
            "</thead>";
        var totalHours = 0;
        for (var key in data.schedule) {
            if (data.schedule.hasOwnProperty(key)) {
                var times = data.schedule[key];
                var hours = parseInt(times[1].split(":")[0]) - parseInt(times[0].split(":")[0])
                totalHours += hours;
                result += "<tr><td><b>" + weekDays[key] + ":</b></td><td>From " + times[0] + "\nTo "
                    + times[1] + "</td><td>" + hours + "</td></tr>";
            }
        }
        result += "<tr><td><b>Total hours:</b></td><td></td><td><b>" + totalHours + "</b></td></tr>";
        result += "</table>";
        return result;
    }

    $(document).ready(function() {
        var date_input = $('input[name="date"]');
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        var options = {
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
            startDate: "new Date()",
        };
        date_input.datepicker(options);

        var table = $('#employees-table').DataTable({
            "ajax": {
                url : 'api/employees',
                dataSrc : ''
            },
            "columns": [
                {
                    "class":          "details-control",
                    "orderable":      false,
                    "data":           null,
                    "defaultContent": ""
                },
                { "data" : "id"},
                { "data" : "firstName"},
                { "data" : "surname"},
                { "data" : "email" },
                {
                    data: null, render: function (data) {
                        if (data.roleId === 1) return "Agent";
                        if (data.roleId === 2) return "Manager";
                    }
                },
                { "data" : "stationId" },
                { "data" : "salary" },
                { "data" : "employedDate" },
                {
                    "targets": -2,
                    "data": "adjust_hours",
                    "defaultContent":
                        "<button type='button' class='adjust-hours' data-toggle='modal'>Adjust Hours</button>"
                },
                {
                    "targets": -1,
                    "data": "issue_paycheck",
                    "defaultContent":
                        "<button type='button' class='issue-paycheck' data-toggle='modal'>Issue paycheck</button>"
                }]
        });

        $('#employees-table tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var row = table.row(tr);

            if (row.child.isShown()) {
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                row.child(formatSchedule(row.data())).show();
                tr.addClass('shown');
            }
        });

        $('#employees-table tbody').on('click', '.issue-paycheck', function () {
            var data = table.row( $(this).parents('tr') ).data();
            $('#makePayrollModal').modal('show');
            $('.modal-body #employeeId').val(data['id']);
            $('.modal-body #firstName').val(data['firstName']);
            $('.modal-body #surname').val(data['surname']);
            $('.modal-body #salary').val(data['salary']);//
            if (data['roleId'] === 2) {
                $('.modal-body #role').val("Manager");
            } else if (data['roleId'] === 1) {
                $('.modal-body #role').val("Station Agent");
            }
            $('.modal-body #stationId').val(data['stationId']);
        });

        $('#employees-table tbody').on('click', '.adjust-hours', function () {
            var data = table.row( $(this).parents('tr') ).data();
            var role = "undefined role";
            if (data['roleId'] === 2) {
                role = "Manager";
            } else if (data['roleId'] === 1) {
                role = "Station Agent";
            }

            $('#adjustHoursModal').modal('show');
            var body =
                "<form class=\"form-i\" id=\"daysForm\">\n" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Employee ID</label>\n" +
                    "<input class=\"form-control\" id=\"m_employeeId\" type=\"text\" value=" + data['id'] + " disabled />\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">First Name</label>\n" +
                    "<input class=\"form-control\" id=\"m_firstName\" type=\"text\" value=" + data['firstName'] + " disabled />\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Surname</label>\n" +
                    "<input class=\"form-control\" id=\"m_surname\" type=\"text\" value=" + data['surname'] + " disabled />\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Salary</label>\n" +
                    "<input class=\"form-control\" id=\"m_role\" type=\"text\" value=" + data['salary'] + " disabled />\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Role</label>\n" +
                    "<input class=\"form-control\" id=\"m_role\" type=\"text\" value=" + role + " disabled />\n" +
                "</div>" +
                "<div class=\"form-group\">" +
                    "<label class=\"control-label\">Station ID</label>\n" +
                    "<input class=\"form-control\" id=\"m_stationId\" type=\"text\" value=" + data['stationId'] + " disabled />\n" +
                "</div>" +
                "<hr><div class=\"form-group\">" +
                    "<label class=\"control-label\"><b>Schedule</b></label>\n" +
                "</div>";

            var schedule = data['schedule'];
            for (var key in schedule) {
                if (schedule.hasOwnProperty(key)) {
                    var time = schedule[key];
                    var fromId = "from" + key;
                    var toId = "to" + key;
                    numDays += 1;

                    var select =
                        "<div class=\"form-group\">\n" +
                        "<label for=" + key + "><b>Select day</b></label>\n" +
                        "<select class=\"form-control\" id=" + key + ">\n";

                    select += "<option value=" + key + " selected=\"selected\">" + weekDays[key] + "</option>";
                    for (var num in weekNums) {
                        if (weekNums[num] !== parseInt(key)) {
                            select += "<option value=" + weekNums[num] + ">" + weekDays[weekNums[num]] + "</option>";
                        }
                    }
                    select += "</select></div>";

                    body +=
                        select +
                        "<div class=\"form-row\">\n" +
                            "<div class=\"form-group col-md-6\">\n" +
                                "<label for=\"from\">From</label>\n" +
                                "<input type=\"text\" class=\"form-control\" id=" + fromId + " placeholder=\"Enter start time\" value=" + time[0] + " />\n" +
                            "</div>\n" +
                            "<div class=\"form-group col-md-6\">\n" +
                                "<label for=\"to\">To</label>\n" +
                                "<input type=\"text\" class=\"form-control\" id=" + toId + " placeholder=\"Enter end time\" value=" + time[1] + " />\n" +
                            "</div>\n" +
                        "</div>";
                }
            }
            body += "</form>";

            $('#adjustHoursModal').find('.modal-body').html(body);
        });

        $('#make-payroll-btn').click(function() {
            var date = document.getElementById("date").value;
            if (date === "") {
                alert("Date cannot be empty");
                return false;
            }
            var jsonData = '{'
                +'"employeeId": ' + $("#employeeId").val()
                +', "paymentAmount": ' + $("#salary").val()
                +', "paymentDate": "' + date
                + '"}';

            makePayroll(jsonData);
        });

        $('#adjust-hours-btn').click(function() {
            var totalHours = 0;
            var jsonList = "[";

            for (var num in weekNums) {
                var workingDay = $("#" + weekNums[num]).val();
                if (typeof workingDay !== "undefined") {
                    var startTime = $("#from" +  weekNums[num]).val();
                    var endTime = $("#to" +  weekNums[num]).val();
                    var employeeId = $("#m_employeeId").val();

                    var hours = parseInt(endTime.split(":")[0]) - parseInt(startTime.split(":")[0]);
                    totalHours += hours;

                    jsonList += '{'
                        +'"employeeId": ' + employeeId
                        +', "workingDay": ' + workingDay
                        +', "startTime": "' + startTime
                        +'", "endTime": "' + endTime
                        + '"},';
                }
            }
            jsonList = jsonList.substring(0, jsonList.length - 1);
            jsonList += "]";

            if (totalHours > 45) {
                alert("Cannot set a working week with more than 45 hours");
                return false;
            }

            console.log(jsonList);
            adjustHours(jsonList);
        });
    });

    function makePayroll(data) {
        $.ajax({
            type: 'POST',
            url: 'api/payroll',
            data: data,
            contentType: 'application/json',
            success: function () {
                console.log("paycheck made");
                $("#alert-success").show();
                setTimeout(function() {
                    $("#alert-success").remove();
                    $('#makePayrollModal').modal('hide');
                    $('#employees-table').DataTable().ajax.reload();
                }, 5000);
            },
            error: function (error) {
                console.log(error);
                $("#alert-error").show();
                setTimeout(function() {
                    $("#alert-error").remove();
                }, 5000);
            }
        });
    }

    function adjustHours(data) {
        console.log(data);
        $.ajax({
            type: 'PUT',
            url: 'api/employees',
            data: data,
            contentType: 'application/json',
            success: function() {
                alert("Schedule for employee with ID=" + data['employeeId'] + " successfully modified!");
                $("#adjustHoursModal").modal('hide');
                $('#employees-table').DataTable().ajax.reload();
            },
            error: function (jqXHR, status, error) {
                console.log(jqXHR);
                alert("Could not modify a schedule for employee with ID=" + data['employeeId']
                    + ". Status: " + jqXHR.status);
            }
        });
    }
</script>
</body>
</html>
