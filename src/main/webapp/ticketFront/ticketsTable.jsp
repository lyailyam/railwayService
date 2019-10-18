<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <title>Table V04</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
<%--    <link rel="stylesheet" type="text/css" href="css/main.css">--%>
    <!--===============================================================================================-->
    <style>


        /*//////////////////////////////////////////////////////////////////
        [ FONT ]*/
        td.details-control {
            /*background: url('../resources/details_open.png') no-repeat center center;*/
            cursor: pointer;
        }
        tr.shown td.details-control {
            /*background: url('../resources/details_close.png') no-repeat center center;*/
        }
        @font-face {
            font-family: Lato-Regular;
            src: url('../../fonts/Lato/Lato-Regular.ttf');
        }

        @font-face {
            font-family: Lato-Bold;
            src: url('../../fonts/Lato/Lato-Bold.ttf');
        }

        /*//////////////////////////////////////////////////////////////////
        [ RESTYLE TAG ]*/
        * {
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }

        body, html {
            height: 100%;
            font-family: sans-serif;
        }

        /* ------------------------------------ */
        a {
            margin: 0px;
            transition: all 0.4s;
            -webkit-transition: all 0.4s;
            -o-transition: all 0.4s;
            -moz-transition: all 0.4s;
        }

        a:focus {
            outline: none !important;
        }

        a:hover {
            text-decoration: none;
        }

        /* ------------------------------------ */
        h1,h2,h3,h4,h5,h6 {margin: 0px;}

        p {margin: 0px;}

        ul, li {
            margin: 0px;
            list-style-type: none;
        }


        /* ------------------------------------ */
        input {
            display: block;
            outline: none;
            border: none !important;
        }

        textarea {
            display: block;
            outline: none;
        }

        textarea:focus, input:focus {
            border-color: transparent !important;
        }

        /* ------------------------------------ */
        button {
            outline: none !important;
            border: none;
            background: transparent;
        }

        button:hover {
            cursor: pointer;
        }

        iframe {
            border: none !important;
        }

        /*//////////////////////////////////////////////////////////////////
        [ Scroll bar ]*/
        .js-pscroll {
            position: relative;
            overflow: hidden;
        }

        .table100 .ps__rail-y {
            width: 9px;
            background-color: transparent;
            opacity: 1 !important;
            right: 5px;
        }

        .table100 .ps__rail-y::before {
            content: "";
            display: block;
            position: absolute;
            background-color: #ebebeb;
            border-radius: 5px;
            width: 100%;
            height: calc(100% - 30px);
            left: 0;
            top: 15px;
        }

        .table100 .ps__rail-y .ps__thumb-y {
            width: 100%;
            right: 0;
            background-color: transparent;
            opacity: 1 !important;
        }

        .table100 .ps__rail-y .ps__thumb-y::before {
            content: "";
            display: block;
            position: absolute;
            background-color: #cccccc;
            border-radius: 5px;
            width: 100%;
            height: calc(100% - 30px);
            left: 0;
            top: 15px;
        }


        /*//////////////////////////////////////////////////////////////////
        [ Table ]*/

        .limiter {
            width: 1366px;
            margin: 0 auto;
        }

        .container-table100 {
            width: 100%;
            min-height: 100vh;
            background: #fff;

            display: -webkit-box;
            display: -webkit-flex;
            display: -moz-box;
            display: -ms-flexbox;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-wrap: wrap;
            padding: 33px 30px;
        }

        .wrap-table100 {
            width: 1170px;
        }

        /*//////////////////////////////////////////////////////////////////
        [ Table ]*/
        .table100 {
            background-color: #fff;
        }

        table {
            width: 100%;
        }

        th, td {
            font-weight: unset;
            padding-right: 10px;
        }

        .column1 {
            width: 23%;
            padding-left: 40px;
        }

        .column2 {
            width: 23%;
        }

        .column3 {
            width: 22%;
        }

        .column4 {
            width: 22%;
        }

        .column5 {
            width: 10%;
        }

        .table100-head th {
            padding-top: 5px;
            padding-bottom: 5px;
        }

        .table100-body td {
            padding-top: 5px;
            padding-bottom: 5px;
        }

        /*==================================================================
        [ Fix header ]*/
        .table100 {
            position: relative;
            padding-top: 0px;
        }

        .table100-head {
            position: absolute;
            width: 100%;
            top: 0;
            left: 0;
        }

        .table100-body {
            max-height: 600px;
            overflow: auto;
        }


        /*==================================================================
        [ Ver4 ]*/
        .table100.ver4 {
            margin-right: -20px;
        }

        .table100.ver4 .table100-head {
            padding-right: 20px;
        }

        .table100.ver4 th {
            font-family: Lato-Bold;
            font-size: 18px;
            color: #4272d7;
            line-height: 1.4;

            background-color: transparent;
            border-bottom: 2px solid #f2f2f2;
        }

        .table100.ver4 .column1 {
            padding-left: 7px;
        }

        .table100.ver4 td {
            font-family: Lato-Regular;
            font-size: 17px;
            color: #808080;
            line-height: 1.4;
        }

        .table100.ver4 .table100-body tr {
            border-bottom: 1px solid #f2f2f2;
        }

        /*---------------------------------------------*/

        .table100.ver4 {
            overflow: hidden;
        }

        .table100.ver4 .table100-body{
            padding-right: 20px;
        }

        .table100.ver4 .ps__rail-y {
            right: 0px;
        }

        .table100.ver4 .ps__rail-y::before {
            background-color: #ebebeb;
        }

        .table100.ver4 .ps__rail-y .ps__thumb-y::before {
            background-color: #cccccc;
        }



    </style>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">

    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {
            $('#upcoming-trips').DataTable({
                "processing" : true,
                "ajax" : {
                    "url" : 'api/tickets?user_id=5&limit=1000&offset=0',
                    dataSrc : ''
                },
                "columns" : [{ data: null, render: function ( data) {
                        return data.firstname+' '+data.surname;
                    } }, { data: null, render: function ( data) {
                        return data.st1_name+' - '+data.st2_name;
                    } }, {
                    "data" : "dep_time"
                }, {
                    "data" : "arr_time"
                }, {
                    "data" : "ticket_status"
                }]
            });
        });

    </script>

</head>

<%@include file="../WEB-INF/jsp/fragments/header.jspf"%>

<body class="h-100">
<div class="h-100 d-flex flex-column">

    <%@include file="../WEB-INF/jsp/fragments/navbar.jspf"%>

<div class="limiter">
    <div class="container-table100">
        <div class="wrap-table100">

            <div class="table100 ver4 m-b-110">
                <div class="table100-body">
                    <table id="upcoming-trips" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th class="cell100 column1">Name</th>
                            <th class="cell100 column2">From-To</th>
                            <th class="cell100 column3">Departure time</th>
                            <th class="cell100 column4">Arrival time</th>
                            <th class="cell100 column5">Status</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>




