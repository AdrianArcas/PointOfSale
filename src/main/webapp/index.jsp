<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <jsp:include page="/CheckAccount"/>
    <h1>Products Catalog</h1>
    <form method="POST" action="${pageContext.request.contextPath}/ProductCategory">
        <div class="d-flex justify-content-start">
            <select class="form-select custom-select d-block w-25" id="category" name="category" required>
                <option value="">Choose...</option>
                <option value="Vegetable">Vegetable</option>
                <option value="Fruit">Fruit</option>
            </select>
        <button class="btn btn-primary" type="submit">Choose Category</button>
        </div>
    </form>
    <div class="container-md">
            <div class="row row-cols-4 border-5 " style="height: 150px;border-width: 5px;">
                <c:if test="${pageContext.request.isUserInRole('Manager')}">
                <div class="col mb-3" style="height:238px">
                    <div class="card border border-primary rounded text-center h-100">
                        <a class=".text-primary" href="${pageContext.request.contextPath}/AddProduct" style="text-decoration: none;font-size: 135px;font-family: 'Georgia Pro Black'">+</a>
                    </div>
                </div>
                </c:if>
                <c:forEach var="product" items="${products}">
                <div class="col mb-3" style="height:238px">
                    <div class="card border border-primary rounded h-100 mb-">
                        <img class="card-img-top" src="${pageContext.request.contextPath}/ProductPhoto?product_id=${product.product_id}" height="130px" alt=""/>
                        <div class="card-body p-0">
                            <h4 class="card-title text-center">${product.name}</h4>
                            <div class="card-text d-flex bd-highlight" style="font-size: 20px">
                                <c:if test="${pageContext.request.isUserInRole('Manager')}">
                                <i class="bd-highlight">${product.quantity} kg</i>
                                </c:if>
                                <b class="ms-auto bd-highlight">${product.price} lei</b>
                            </div>
                            <c:if test="${pageContext.request.isUserInRole('Manager')}">
                            <form method="POST" class="m-0" action="${pageContext.request.contextPath}/Products">
                                <input type="hidden" name="product_id" value="${product.product_id}">
                            <div class="d-flex flex-row d-highlight text-center">
                                <a href="${pageContext.request.contextPath}/AddProductPhoto?product_id=${product.product_id}" class="btn btn-primary btn-lg rounded-0 flex-fill" style="font-size: 10px" role="button"><b>Add Photo</b></a>
                                <a href="${pageContext.request.contextPath}/EditProduct?product_id=${product.product_id}" class="btn btn-warning btn-lg rounded-0 flex-fill" style="font-size: 10px" role="button"><b>Update</b></a>
                                <button class="btn btn-danger btn-lg rounded-0 flex-fill" style="font-size: 10px" type="submit"><b>Delete</b></button>
                            </div>
                            </form>
                            </c:if>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
</t:pageTemplate>