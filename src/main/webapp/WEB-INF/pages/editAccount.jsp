<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="EditProduct">
  <h1>Edit Product</h1>
  <div class="col position-fi" >
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditAccount">
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="username">Username</label>
          <input type="text" class="form-control" id="username" name="username" placeholder="" value=${account.username} required>
          <div class="invalid-feedback">
            Username is required.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="" value=${account.email} required>
          <div class="invalid-feedback">
            Email is required.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="password">Password</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="" value=${account.password} required>
          <div class="invalid-feedback">
            Password is required.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="user_groups">UserGroup</label>
          <select class="form-select custom-select d-block" id="user_groups" name="user_group" required>
            <c:forEach var="user_group" items="${userGroups}" varStatus="status">
              <option value="${user_group}">${user_group}</option>
            </c:forEach>
          </select>
          <div class="invalid-feedback">
            UserGroup is required.
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="is_active">Is active</label>
        <select class="form-select custom-select d-block" id="is_active" name="is_active" required>
          <option value="true">True</option>
          <option value="false">False</option>
        </select>
          <div class="invalid-feedback">
            Is Active is required.
          </div>
      </div>
      </div>
      <input type="hidden" name="account_id" value="${account.account_id}"/>
      <hr class="mb-4">
      <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
    </form>
    <img class="position-absolute mt-3 end-0" src="${pageContext.request.contextPath}/images/addAccounts.png" alt="AddAccountsPhoto" style="bottom: 5%" width="40%">
  </div>
</t:pageTemplate>