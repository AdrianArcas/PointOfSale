<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Notifications">
    <h1>Notifications</h1>
    <c:if test="${empty products}">
        <div class="container text-center">
            <i><h3 style="color:grey">There are no products that need to be restocked.</h3></i>
        </div>
    </c:if>
    <div class="container-md">
    <div class="row row-cols-4 border-5 " style="height: 150px;border-width: 5px;">
    <c:forEach var="product" items="${products}">
        <div class="col mb-3" style="height:238px">
            <div class="card border border-primary rounded h-100 mb-">
                <img class="card-img-top" src="${pageContext.request.contextPath}/ProductPhoto?product_id=${product.product_id}" height="130px" alt=""/>
                <div class="card-body p-0">
                    <h4 class="card-title text-center">${product.name}</h4>
                    <b>Cantitate: ${product.quantity}</b>
                    <div class="d-flex flex-row d-highlight text-center">
                        <a href="${pageContext.request.contextPath}/RestockProduct?product_id=${product.product_id}" class="btn btn-warning btn-lg rounded-0 flex-fill" style="font-size: 14px" role="button"><b>Restock</b></a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
    </div>

</t:pageTemplate>
