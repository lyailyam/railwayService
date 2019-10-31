<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" class="h-100">

<%@include file="fragments/header.jspf"%>

<body class="h-100">
<div class="h-100 d-flex flex-column">
    <%@include file="fragments/navbar.jspf"%>

        <div id="page" layout:fragment="content">
            <div class="container">
                <div class="mt-5">
                    <div class="container">
                        <form class="form-inline">
                            <div class="form-row align-items-center">
                                <div class="col-auto">
                                    <input type="text" class="form-control" id="from" placeholder="From">
                                </div>
                                <div class="col-auto">
                                    <input type="text" class="form-control" id="to" placeholder="To">
                                </div>
                                <div class="col-auto">
                                    <input type="date" class="form-control" placeholder="Departure Date">
                                </div>
                                <div class="col-auto">
                                    <input type="submit" class="btn btn-primary btn-block" value="Search"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    <%@include file="fragments/scripts.jspf"%>
</div>
</body>
</html>
