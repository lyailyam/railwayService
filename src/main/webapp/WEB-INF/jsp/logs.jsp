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

        td.details-control {
            background-image: url("https://img.icons8.com/pastel-glyph/2x/plus.png");
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
    </style>

</head>

<body>
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf" %>
    <div id="page">
        <div class="container">
            <div class="row mt-5">
                <div class="col">
                    <h2>Logging Panel</h2>
                </div>
            </div>

            <!-- TODO More responsive design -->
            <div class="row">
                <div class="col-3">
                    <button type="button" class="btn btn-primary" id="api-log-btn">API logs</button>
                    <button type="button" class="btn btn-primary" id="user-log-btn">User logs</button>
                </div>
            </div>

            <!-- Table -->
            <div class="wrap-table100">
                <table id="logs" class="display" style="width:100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Log</th>
                    </tr>
                    </thead>
                </table>
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


    function format(d) {
        // `d` is the original data object for the row
        return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
            '<tr>' +
            '<td>Log:</td>' +
            '<td>' + d + '</td>' +
            '</tr>' +
            '</table>';
    }

    $(document).ready(function () {
        $("#api-log-btn").on('click', function() {
            showAPILogs();
        });

        $("#user-log-btn").on('click', function () {
            showUserLogs();
        });

        var table = $('#logs').DataTable({
            "ajax": {
                "url": 'api/logs,
                dataSrc: ''
            },
            "columns": [
                {
                    "class": "details-control",
                    "orderable": false,
                    "data": null,
                    "defaultContent": ""
                },
                {
                    data: null, render: function (data) {
                        return data;
                    }
                },
            ],

            "order": [[1, 'asc']]
        });

        $('#logs tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var row = table.row(tr);

            if (row.child.isShown()) {
                row.child.hide();
                tr.removeClass('shown');
            } else {
                row.child(format(row.data())).show();
                tr.addClass('shown');
            }
        });
    });

    function showAPILogs(){
        alert("API logs")
    }

    function showUserLogs() {
        alert("User logs")
    }
</script>


</body>
</html>