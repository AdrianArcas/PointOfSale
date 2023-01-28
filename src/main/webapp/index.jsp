<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <h1>Select Products</h1>
    <div class="container border-info mb-3 border-5" style="height: 150px;">
        <c:forEach var="product" items="${product}">
            <div class="row row-cols-4 border-5" style="height: 150px;border-width: 5px;">
                <div class="col">
                    <div class="card h-100 border border-primary rounded text-center">
                        <img src="${pageContext.request.contextPath}/Products?id=${car.id}" width="48"/>
                        <div class="card-body">
                            <h5 class="card-title">Tomato</h5>
                            <p class="card-text">6 lei/kg</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="container border-info mb-3 border-5" style="height: 150px;">
            <div class="row row-cols-4 border-5" style="height: 150px;border-width: 5px;">
                <div class="col">
                    <div class="card h-100 border border-primary rounded text-center">
                        <a class=".text-primary" style="text-decoration: none;font-size: 90px;font-family: 'Georgia Pro Black'">+</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:pageTemplate>