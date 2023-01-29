<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Hello, Cashier">
    <h1>Cashier</h1>
    <div class="row row-cols-2 border-5" style="height: 150px;border-width: 5px;">
        <div class="col">
                <a href="${pageContext.request.contextPath}/ProcessSale" class="btn btn-primary btn-lg" type="submit">Sell</a>
        </div>

        <div class="col">
                <a href="${pageContext.request.contextPath}/Return" class="btn btn-primary btn-lg" type="submit">Return</a>
        </div>
    </div>
</t:pageTemplate>
