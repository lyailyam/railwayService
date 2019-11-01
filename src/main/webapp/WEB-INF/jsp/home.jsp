<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" class="h-100">

<%@include file="fragments/header.jspf"%>

<body class="h-100">
<div class="h-100 d-flex flex-column">

    <%@include file="fragments/navbar.jspf"%>

    <div id="page">
        <div class="container">
            <c:choose>
                <c:when test="${not empty profile}">
                    <c:choose>
                        <c:when test="${appMetadata
                                            .getJSONObject(\"app_metadata\")
                                            .getJSONObject(\"authorization\")
                                            .getJSONArray(\"roles\").get(0).equals(\"passenger\")}">
                            <div class="mt-5">
                                <div class="text-center hero my-5">
                                    <p class="lead">
                                        This is the Passenger Panel. From this panel you should be able to access
                                        all the admin privileges of the Railway Service.
                                    </p>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${appMetadata
                                            .getJSONObject(\"app_metadata\")
                                            .getJSONObject(\"authorization\")
                                            .getJSONArray(\"roles\").get(0).equals(\"station_manager\")}">
                            <div class="mt-5">
                                <div class="text-center hero my-5">
                                    <p class="lead">
                                        This is the Station Manager Panel. From this panel you should be able to access
                                        all the admin privileges of the Railway Service.
                                    </p>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="mt-5">
                                <div class="text-center hero my-5">
                                    <p class="lead">
                                        This is the Station Agent Panel. From this panel you should be able to change,
                                        cancel, and create tickets upon user requests.
                                    </p>
                                    <form action="${pageContext.request.contextPath}/admintickets" method="GET">
                                        <input type="submit" class="btn btn-primary btn-block" value="Manage Tickets"/>
                                    </form>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <div class="mt-5">
                        <div class="text-center hero my-5">
                            <p class="lead">
                                This is a Railway Service web application that is being implemented as a CSCI 361 - Software Engineering course project.
                            </p>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <%@include file="fragments/footer.jspf"%>
    <%@include file="fragments/scripts.jspf"%>

</div>
</body>
</html>
