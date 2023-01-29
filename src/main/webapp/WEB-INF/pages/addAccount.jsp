<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="AddProduct">
  <h1>Add Account</h1>
  <div class="col position-fi" >
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddAccount">
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="username">Username</label>
          <input type="text" class="form-control" id="username" name="username" placeholder="" value="" required>
          <div class="invalid-feedback">
            Username is required.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="" value="" required>
          <div class="invalid-feedback">
            Email is required.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="password">Password</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="" value="" required>
          <div class="invalid-feedback">
            Password is required.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="user_groups">UserGroup</label>
          <select class="custom-select d-block w-100" id="user_groups" name="user_group" required>
            <c:forEach var="user_group" items="${userGroups}" varStatus="status">
              <option value="${user_group}">${user_group}</option>
            </c:forEach>
          </select>
          <div class="invalid-feedback">
            UserGroup is required.
          </div>
        </div>
      </div>
      <hr class="mb-4">
      <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
    </form>
    <img class="position-absolute mt-3 end-0" src="${pageContext.request.contextPath}/images/addProduct.jpg" alt="Addproduct" style="bottom: 15%" width="50%">
  </div>

</t:pageTemplate>
