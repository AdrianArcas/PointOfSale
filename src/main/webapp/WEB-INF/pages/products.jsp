<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <h1>Select Products</h1>
    <div class="container border-info mb-3">
        <c:forEach var="product" items="${product}">
        <div class="row row-cols-3">
            <div class="col">
                <div class="card h-100">
                    <img src="${pageContext.request.contextPath}/CarPhotos?id=${car.id}" width="48"/>
                    <div class="card-body">
                        <h5 class="card-title">Tomato</h5>
                        <p class="card-text">6 lei/kg</p>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</t:pageTemplate>