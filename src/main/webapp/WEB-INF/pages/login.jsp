<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Login">
    <div class="position-sticky">
        <img class="position-absolute top-0 mt-3 end-0" src="${pageContext.request.contextPath}/images/market.png" alt=" " width="25%">
    </div>
    <div class="d-flex justify-content-center align-items-center" style="height: 450px;">
    <div class="w-50">
        <c:if test="${message != null}">
            <div class="alert alert-warning" role="alert">
                    ${message}
            </div>
        </c:if>
        <form class="form-signin" method="POST" action="j_security_check">
            <h1 class="h3 mb-3 text-center display-3" style="color: whitesmoke; font-family: Sofia, sans-serif;">Log in</h1>
            <input type="text" id="username" name="j_username" class="form-control rounded-pill mb-3" placeholder="Username" required autofocus/>
            <input type="password" id="password" name="j_password" class="form-control rounded-pill mb-3" placeholder="Password" required/>
            <button class="btn btn-lg btn-primary btn-block w-100" type="submit">Sign in</button>
        </form>
    </div>
    </div>
    <div class="position-relative">
        <img class="position-absolute bottom-0 start-0" src="${pageContext.request.contextPath}/images/client.png" alt=" " width="20%">
    </div>
</t:pageTemplate>