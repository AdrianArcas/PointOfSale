<%@tag description="base page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<%@attribute name="pageTitle"%>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}.</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lora">
    <style>
        .navbar-nav .nav-item:hover .nav-link{
            color: #4599F2;
        }
        .navbar-nav .nav-item .nav-link {
            color:#C1D1FC;
        }
        <c:if test="${color != null}">
        body{
            background-color:${color};
        }
        </c:if>
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/pages/menu.jsp"/>
<main class="container-fluid">
    <jsp:doBody/>
</main>
</body>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
<script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
</html>