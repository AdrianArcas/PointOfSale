<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <h1>Select Notifications</h1>
    <div class="container border-info mb-3">
        <c:forEach var="notification" items="${notifications}">
        ${notification.who_modified_or_added_account_username}
        </c:forEach>
    </div>
</t:pageTemplate>