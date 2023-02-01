<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pageTemplate pageTitle="Manage Return">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <div class="row row-cols-2 border-5 position-relative" style="height: 93%;border-width: 5px;">

        <div class="col position-relative">
                <%-- search bar  --%>
            <br>
            <form class="needs-validation" novalidate method="POST"
                  action="${pageContext.request.contextPath}/ManageReturn">
                <input type="hidden" id="form1" name="form1" value="form1">
                <div class="input-group " style="height: 50px">
                    <input type="text" class="form-control border border-2 border-primary" id="search_input"
                           name="search_input" placeholder="Search code" value="" required>
                    <button class="btn btn-primary btn-lg btn-block input-group-append" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                    <div class="invalid-feedback">
                        Receipt code is required.
                    </div>
                </div>
                <div>
                        ${message}
                </div>
            </form>

                <%-- de afisat bon --%>
            <br>
            <div class="container border border-2 border-primary" style="background-color: #99ccff">
                <div class="row bg-primary fst-italic" style="font-size: 25px">
                    <div class="col col-6 border border-white">
                        Number
                    </div>
                    <div class="col col-6 border border-white">
                        Total
                    </div>
                </div>

                <div class="row ">
                    <div class="col col-6 border border-white">
                            ${receipt.id}
                    </div>
                    <div class="col col-6 border border-white">
                        <fmt:formatNumber type="number"
                                          maxIntegerDigits="3"
                                          value="${receipt.total}"/>

                    </div>
                </div>
            </div>
            <br>

                <%-- form pentru adaugare produse in bon --%>
            <div class="container bottom-0 start-0">

                <form class="needs-validation" novalidate method="POST"
                      action="${pageContext.request.contextPath}/ManageReturn">
                    <input type="hidden" id="form2" name="form1" value="form2">
                    <input type="hidden" name="receipt_ids" value="${receipt.id}"/>

                    <div class="container">
                        <div class="row" style="font-size: 25px">
                            <div class="col col-8 fst-italic d-flex align-items-center">

                            </div>
                            <div class="col col-4 text-center">
                                <c:if test="${not empty receipt}">
                                    <button class="btn btn-primary input-group-append" type="submit"
                                            style="border-radius: 50%; font-size: 50px">
                                        <i class="fa fa-refresh"></i>
                                    </button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </form>

            </div>

        </div>

            <%-- afisare bon --%>
        <div class="col position-relative" style="background-color: #99ccff">
            <br>
            <h2 class="text-center">Receipt</h2>
            <br>

            <form class="needs-validation" novalidate method="POST"
                  action="${pageContext.request.contextPath}/ManageReturn">
                <input type="hidden" id="form3" name="form1" value="form3">
                <input type="hidden" name="receipt_id" value="${receipt.id}">
                <div style="min-height: 65%;">
                    <c:if test="${not empty invoices}">
                        <c:forEach var="invoice" items="${invoices}" varStatus="status">
                            <div class="row">
                                <div class="col col-1 d-flex justify-content-center">
                                    <input type="checkbox" name="products_ids" value="${invoice.key.product_id}"/>

                                </div>
<%--                                <div class="col col-1 d-flex justify-content-center">--%>
<%--                                   ${invoice.key.product_id}--%>

<%--                                </div>--%>
                                <div class="col">
                                        ${invoice.key.name}
                                </div>
                                <div class="col">
                                    Quantity : ${invoice.value} kg
                                </div>
                                <div class="col">
                                    Price : ${invoice.key.price} lei/kg
                                </div>
                                <div class="col">
                                    Total price:
                                    <fmt:formatNumber type="number"
                                                      maxIntegerDigits="3"
                                                      value="${invoice.value * invoice.key.price}"/>
                                    lei
                                </div>
                            </div>
                            <br/>
                        </c:forEach>
                    </c:if>

                </div>


                <div class="container bottom-0 start-0">

                    <div class="row border-5" style="font-size: 25px;width: 100%">
                        <div class="col fst-italic fw-bold">
                            <p>Total: <fmt:formatNumber type="number"
                                                        maxIntegerDigits="3" value="${total}"/>
                            </p>
                        </div>
                    </div>

                    <div class="container">
                        <div class="row text-center">
                            <div class="col-lg">
                                <button class="btn btn-primary" type="submit" style="font-size: 40px;width: 100%">
                                    Return
                                </button>
                            </div>

                        </div>
                    </div>

                </div>
            </form>
        </div>

    </div>

</t:pageTemplate>
