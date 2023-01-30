<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Hello, Cashier">
    <h1>Cashier</h1>

    <div class="container">

        <div class="row text-center">
            <div class="col-lg" style="font-size: 40px;width: 100%">
                <a href="${pageContext.request.contextPath}/ProcessSale" class="btn btn-primary btn-lg" type="submit">Sell</a>
            </div>
            <div class="col-lg" style="font-size: 40px;width: 100%">
                <a href="${pageContext.request.contextPath}/ManageReturn" class="btn btn-primary btn-lg" type="submit">Return</a>
            </div>
        </div>

    </div>

</t:pageTemplate>
