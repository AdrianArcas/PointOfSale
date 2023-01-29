<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Green Grocery">
    <h1>Select Products Index</h1>
    <form method="POST" action="${pageContext.request.contextPath}/ProductCategory">
        <div class="d-flex justify-content-start">
            <select class="form-select custom-select d-block w-25" id="category" name="category" required>
                <option value="">Choose...</option>
                <option value="vegetable">Vegetable</option>
                <option value="fruit">Fruit</option>
            </select>
        <button class="btn btn-primary" type="submit">Choose Category</button>
        </div>
    </form>
    <div class="container-md">
            <div class="row row-cols-4 border-5 " style="height: 150px;border-width: 5px;">
                <div class="col mb-3" style="height:230px">
                    <div class="card border border-primary rounded text-center h-100">
                        <a class=".text-primary" href="${pageContext.request.contextPath}/AddProduct" style="text-decoration: none;font-size: 135px;font-family: 'Georgia Pro Black'">+</a>
                    </div>
                </div>
                <c:forEach var="product" items="${products}">
                <div class="col mb-3" style="height:230px">
                    <div class="card border border-primary rounded h-100 mb-">
                        <img class="card-img-top" src="${pageContext.request.contextPath}/ProductPhoto?product_id=${product.product_id}" height="130px" alt=""/>
                        <div class="card-body">
                            <h4 class="card-title text-center">${product.name}</h4>
                            <p class="card-text " style="font-size: 20px">
                                <a href="${pageContext.request.contextPath}/AddProductPhoto?product_id=${product.product_id}" class="btn btn-primary btn-lg w-50" style="font-size: 10px" role="button">Add Photo</a>
                                <b>${product.price}</b></p>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
</t:pageTemplate>