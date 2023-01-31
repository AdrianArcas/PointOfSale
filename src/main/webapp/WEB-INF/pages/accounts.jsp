<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <jsp:include page="/CheckAccount" />
    <h1>Accounts</h1>
    <div class="container-md">
        <div class="row row-cols-4 border-5 " style="height: 150px;border-width: 5px;">
            <div class="col mb-3" style="height:238px">
                <div class="card border border-primary rounded text-center h-100">
                    <a class=".text-primary" href="${pageContext.request.contextPath}/AddAccount" style="text-decoration: none;font-size: 135px;font-family: 'Georgia Pro Black'">+</a>
                </div>
            </div>
            <c:forEach var="account" items="${accounts}">
                <c:if test="${account.is_active}">
                <div class="col mb-3" style="height:238px">
                    <div class="card border border-primary rounded h-100 mb-">
                        <img class="card-img-top" src="${pageContext.request.contextPath}/AccountPhoto?account_id=${account.account_id}" height="130px" alt=""/>
                        <div class="card-body p-0">
                            <h4 class="card-title text-center">${account.username}</h4>
                            <div class="card-text text-end" style="font-size: 20px">
                                <b>${account.email}</b>
                            </div>
                            <form method="POST" class="m-0" action="${pageContext.request.contextPath}/Accounts">
                                <input type="hidden" name="account_id" value="${account.account_id}">
                                <div class="d-flex flex-row d-highlight text-center">
                                    <a href="${pageContext.request.contextPath}/AddAccountPhoto?account_id=${account.account_id}" class="btn btn-primary btn-lg rounded-0 flex-fill" style="font-size: 10px" role="button"><b>Add Photo</b></a>
                                    <a href="${pageContext.request.contextPath}/EditAccount?account_id=${account.account_id}" class="btn btn-warning btn-lg rounded-0 flex-fill" style="font-size: 10px" role="button"><b>Update</b></a>
                                    <button class="btn btn-danger btn-lg rounded-0 flex-fill" style="font-size: 10px" type="submit"><b>Delete</b></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</t:pageTemplate>