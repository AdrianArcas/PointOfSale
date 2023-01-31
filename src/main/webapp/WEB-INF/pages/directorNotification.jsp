<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <h1>DirectorNotifications</h1>

    <c:forEach var="notification" items="${notifications}">
     <h1> ${notification.who_modified_or_added_account_username} wants to ${notification.action} ${notification.modified_or_added_account_username}</h1>
    </c:forEach>
</t:pageTemplate>