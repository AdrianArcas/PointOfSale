<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="EditProduct">
    <h1>Edit Product</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditProduct">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="" value="${product.name}" required>
                <div class="invalid-feedback">
                    Name is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="price">Price</label>
                <input type="text" class="form-control" id="price" name="price" placeholder="" value="${product.price}" required>
                <div class="invalid-feedback">
                    Price is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="quantity">Quantity</label>
                <input type="text" class="form-control" id="quantity" name="quantity" placeholder="" value="${product.quantity}" required>
                <div class="invalid-feedback">
                    Quantity is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="category">Category</label>
                <select class="form-select custom-select d-block" id="category" name="category" required>
                    <option value="1">Vegetable</option>
                    <option value="2">Fruit</option>
                </select>
                <div class="invalid-feedback">
                    Category is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="tva">TVA</label>
                <input type="text" class="form-control" id="tva" name="tva" placeholder="" value="${product.tva}" required>
                <div class="invalid-feedback">
                    TVA is required.
                </div>
            </div>
        </div>
        <hr class="mb-4">
        <input type="hidden" name="product_id" value="${product.product_id}"/>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
    </form>
    <img class="position-absolute mt-3 end-0" src="${pageContext.request.contextPath}/images/editProduct.jpg" alt="Addproduct" style="bottom: 15%" width="50%">
</t:pageTemplate>