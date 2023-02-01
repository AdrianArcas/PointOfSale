<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <img src="${pageContext.request.contextPath}/images/cover4.jpg" class="img-fluid" alt="Responsive image">
    <h1>Notifications</h1>
    <br>
    <c:if test="${empty notifications}">
    <div class="container text-center">
        <i><h3 style="color:grey">There are no notifications</h3></i>
    </div>
    </c:if>
    <div class="container text-center">
    <c:forEach var="notification" items="${notifications}">
        <hr class="mt-2 mb-2">
        <div class="row">
            <div class="col">
                <h3> ID:${notification.id}</h3>
            </div>
            <div class="col-6">
                <h3>${notification.who_modified_or_added_account_username} wants to ${notification.action} ${notification.modified_or_added_account_username}
                </h3>
            </div>
            <div class="col-2">
                <h3>
                    <a class="text-decoration-none" style="color:limegreen" href="${pageContext.request.contextPath}/ApproveDirectorNotification?action=${notification.action}&username=${notification.modified_or_added_account_username}&id=${notification.id}">Approve</a>
                </h3>
            </div>
            <div class="col">
                <h3>
                    <a class="text-decoration-none" style="color:red" href="${pageContext.request.contextPath}/DenyDirectorNotification?action=${notification.action}&username=${notification.modified_or_added_account_username}&id=${notification.id}">Deny</a>
                </h3>
            </div>
        </div>
    </c:forEach>
    </div>
</t:pageTemplate>