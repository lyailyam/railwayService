<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <title>Logs</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.auth0.com/js/auth0-samples-theme/1.0/css/auth0-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/styles/monokai-sublime.min.css"/>
    <style>
        .wrap-table100 {
            margin-right: 164px;
            margin-left: 164px;
        }
    </style>
</head>

<body>
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf" %>
    <div id="page">
        <div class="container">
            <div class="row mt-5">
                <div class="col">
                    <h2>Administration Panel</h2>
                        <input class="btn btn-secondary active">
                            <input type="radio" name="options" id="logs_enabled" autocomplete="off">Enable Logging
                        </input>
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" id="logs_disabled" autocomplete="off">Disable Logging
                        </label>
                    <br>
                    <br>
                    <h3>API Requests Logs</h3>
                    <div id="alert-success-api" class="alert alert-success" role="alert" style="display: none">
                        You have successfully cleared API request logs.
                    </div>
                    <div id="alert-error-api" class="alert alert-danger" role="alert" style="display: none">
                        Error occurred while clearing API request logs.
                    </div>
                    <button type="submit" id="api_logs_clear" class="btn btn-link">Clear API requests logs</button>
                    <table id="log_api" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Timestamp</th>
                            <th>Method</th>
                            <th>URI</th>
                        </tr>
                        </thead>
                    </table>
                    <br>
                    <h3>LogIn/LogOut Logs</h3>
                    <div id="alert-success-users" class="alert alert-success" role="alert" style="display: none">
                        You have successfully cleared login/logout logs.
                    </div>
                    <div id="alert-error-users" class="alert alert-danger" role="alert" style="display: none">
                        Error occurred while clearing login/logout logs.
                    </div>
                    <button type="submit" id="api_users_clear" class="btn btn-link">Clear login/logout logs</button>
                    <table id="log_users" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Timestamp</th>
                            <th>Activity</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.8/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<script charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'api/logs/status',
            success: function(result) {
                console.log(result);
                if (result == "OFF") {
                    $("#logs_disabled").button('toggle');
                } else {
                    $("#logs_enabled").button('toggle');
                }
            }
        });

        var table_api = $('#log_api').DataTable({
            "destroy": true,
            "lengthMenu": [ 5, 10 ],
            "ajax": {
                "url": 'api/logs/api',
                dataSrc: '',
            },
            "columns": [
                { "data" : "firstname"},
                { "data" : "surname"},
                { "data" : "email"},
                { "data" : "role"},
                { "data" : "timestamp"},
                { "data" : "method" },
                { "data" : "uri"}
                ],

            "order": [[1, 'asc']]
        });

        var table_users = $('#log_users').DataTable({
            "destroy": true,
            "lengthMenu": [ 5, 10 ],
            "ajax": {
                "url": 'api/logs/users',
                dataSrc: '',
            },
            "columns": [
                { "data" : "firstname"},
                { "data" : "surname"},
                { "data" : "email"},
                { "data" : "timestamp"},
                { "data" : "activity" },
                ],

            "order": [[1, 'asc']]
        });

        $("#api_logs_clear").on('click', function(){
            $.ajax({
                url: 'api/logs/api/clear',
                method: "DELETE"
            })
                .then(function (value) {
                    $("#alert-success-api").show();
                    table_api.ajax.reload();
                    setTimeout(function() {
                        $("#alert-success-api").remove();
                    }, 3000);
                })
                .catch(function (err) {
                    console.log("error while deleting log apis: ", err);
                    $("#alert-error-api").show();
                    setTimeout(function() {
                        $("#alert-error-api").remove();
                    }, 3000);
                });
        });

        $("#api_users_clear").on('click', function(){
            $.ajax({
                url: 'api/logs/users/clear',
                method: 'DELETE'
            })
                .then(function (value) {
                    $("#alert-success-users").show();
                    table_users.ajax.reload();
                    setTimeout(function() {
                        $("#alert-success-users").remove();
                    }, 3000);
                })
                .catch(function (err) {
                    console.log("error while deleting log apis: ", err);
                    $("#alert-error-users").show();
                    setTimeout(function() {
                        $("#alert-error-users").remove();
                    }, 3000);
                });
        });

        $("#logs_enabled").on('click', function(){
            $("#logs_enabled").button('toggle');
            $.ajax({
                url: 'api/logs/enable',
                method: 'POST',
                contentType: 'application/json',
                success: function(result) {
                    console.log(result);
                },
                error: function(error) {
                    console.log(error);
                }
            });
        });

        $("#logs_disabled").on('click', function(){
            $("#logs_disabled").button('toggle');
            $.ajax({
                url: 'api/logs/disable',
                method: 'POST',
                contentType: 'application/json',
                success: function(result) {
                    console.log(result);
                },
                error: function(error) {
                    console.log(error);
                }
            });
        });
    });
</script>


</body>
</html>