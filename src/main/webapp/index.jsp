<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <h1>Select Products Index</h1>
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
                <div class="col mb-3" style="height:238px">
                    <div class="card border border-primary rounded text-center h-100">
                        <a class=".text-primary" href="${pageContext.request.contextPath}/AddProduct" style="text-decoration: none;font-size: 135px;font-family: 'Georgia Pro Black'">+</a>
                    </div>
                </div>
                <c:forEach var="product" items="${products}">
                <div class="col mb-3" style="height:238px">
                    <div class="card border border-primary rounded h-100 mb-">
                        <img class="card-img-top" src="${pageContext.request.contextPath}/ProductPhoto?product_id=${product.product_id}" height="130px" alt=""/>
                        <div class="card-body p-0">
                            <h4 class="card-title text-center">${product.name}</h4>
                            <div class="card-text text-end" style="font-size: 20px">
                                <b>${product.price}</b></div>
                            <div class="d-flex flex-row bd-highlight text-center">
                                <a href="${pageContext.request.contextPath}/AddProductPhoto?product_id=${product.product_id}" class="btn btn-primary btn-lg rounded-0 flex-fill" style="font-size: 10px" role="button"><b>Add Photo</b></a>
                                <a href="${pageContext.request.contextPath}/EditProduct?product_id=${product.product_id}" class="btn btn-warning btn-lg rounded-0 flex-fill" style="font-size: 10px" role="button"><b>Update</b></a>
                                <a href="${pageContext.request.contextPath}/Products?product_id=${product.product_id}" class="btn btn-danger btn-lg rounded-0 flex-fill" style="font-size: 10px" role="button"><b>Delete</b></a>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
</t:pageTemplate>