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
                    <h1 class="mb-4">Railway Service</h1>
                    <p class="lead">
                        This is a Railway Service web application that is being implemented as a CSCI 361 - Software Engineering course project. 
                    </p>
                </div>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf"%>
    <%@include file="fragments/scripts.jspf"%>

</div>
</body>
</html>
