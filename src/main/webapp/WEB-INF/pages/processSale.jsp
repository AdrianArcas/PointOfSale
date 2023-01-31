<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Process Sale">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <div class="row row-cols-2 border-5 position-relative" style="height: 93%;border-width: 5px;">

        <div class="col position-relative">
                <%-- search bar  --%>
            <br>
            <form class="needs-validation" novalidate method="POST"
                  action="${pageContext.request.contextPath}/ProcessSale">
                <input type="hidden" id="form1" name="form1" value="form1">
                <div class="input-group " style="height: 50px">
                    <input type="text" class="form-control border border-2 border-primary" id="search_input"
                           name="search_input" placeholder="Search product" value="">
                    <button class="btn btn-primary btn-lg btn-block input-group-append" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </form>

                <%-- de afisat poza --%>
            <br>
            <div class="container">
                <div class="row h-25">
                    <div class="col-sm">
                    </div>
                    <div class="col-sm">
                        <img class="card-img-top"
                             src="${pageContext.request.contextPath}/ProductPhoto?product_id=${product.product_id}"
                             alt=""/>
                    </div>
                    <div class="col-sm">
                    </div>
                </div>
            </div>
            <br>

                <%-- form pentru adaugare produse in bon --%>
            <div class="container bottom-0 start-0">

                <form class="needs-validation" novalidate method="POST"
                      action="${pageContext.request.contextPath}/ProcessSale">
                    <input type="hidden" id="form2" name="form1" value="form2">
                    <div class="row border-5" style="font-size: 30px">
                        <div class="col col-8 fst-italic d-flex align-items-center w-75">
                            <p>${product.name} </p>
                            <input type="hidden" id="productName" name="productName" value="${product.name}">
                        </div>
                        <div class="col col-4 text-center w-25 fst-italic">
                            <p> ${product.price} lei/kg</p>
                        </div>
                        <input type="hidden" id="product-id" name="product-id" value="${product.product_id}">
                    </div>

                    <div class="container">
                        <div class="row fst-italic" style="font-size: 25px">
                            <div class="col">
                                <label for="quantity">Quantity:</label>
                                <input type="number" id="quantity" name="quantity" min="0" max="${product.quantity}"
                                       style="width: 100px">
                            </div>
                        </div>
                        <br>
                        <div class="row" style="font-size: 25px">
                            <div class="col col-8 fst-italic d-flex align-items-center">
                                <p>Quantity available: ${product.quantity} kg </p>
                            </div>
                            <div class="col col-4 text-center">
                                <button class="btn btn-primary input-group-append" type="submit"
                                        style="border-radius: 50%; font-size: 50px">
                                    <i class="fa fa-plus"></i>
                                </button>
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

            <div style="min-height: 53%;">
                <c:if test="${not empty invoices}">
                    <c:forEach var="invoice" items="${invoices}" varStatus="status">
                        <div class="row">
                            <div class="col">
                                    ${status.index+1}.${invoice.key.name}
                            </div>
                            <div class="col">
                                Quantity :  ${invoice.value}
                            </div>
                            <div class="col">
                                Price : ${invoice.key.price} lei/kg
                            </div>
                            <div class="col text-end">
                                Total price: ${invoice.value * invoice.key.price}
                            </div>
                        </div>
                        <br/>
                    </c:forEach>
                </c:if>

            </div>


            <div class="container bottom-0 start-0">

                <div class="row border-5" style="font-size: 25px;width: 100%">
                    <div class="col fst-italic fw-bold">
                        <p>Total: <c:out value="${total}"/> </p>
                    </div>
                </div>

                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg">
                            <button class="btn btn-primary" type="button" style="font-size: 40px;width: 100%">
                                Submit
                            </button>
                        </div>
                        <div class="col-lg">
                            <button class="btn btn-primary" type="button"
                                    style="font-size: 40px; background-color: #080737;width: 100%">
                                Change
                            </button>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

</t:pageTemplate>
