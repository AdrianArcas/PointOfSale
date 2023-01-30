<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Manage Return">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <div class="row row-cols-2 border-5 position-relative" style="height: 92%;border-width: 5px;">

        <div class="col position-relative">
                <%-- search bar  --%>
            <br>
            <form class="needs-validation" novalidate method="POST"
                  action="${pageContext.request.contextPath}/ManageReturn">
                <input type="hidden" id="form1" name="form1" value="form1">
                <div class="input-group " style="height: 50px">
                    <input type="text" class="form-control border border-2 border-primary" id="search_input"
                           name="search_input" placeholder="Search code" value="">
                    <button class="btn btn-primary btn-lg btn-block input-group-append" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </form>

                <%-- de afisat bon --%>
            <br>
            <div class="container">
                <div class="row bg-primary" style="font-size: 25px">
                    <div class="col-sm">
                        checkbox
                    </div>
                    <div class="col-sm">
                        numar bon
                    </div>
                    <div class="col-sm">
                        pret total
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm">
                        <input type="checkbox" name="receipt_ids" value="${receipt.id}"/>
                    </div>
                    <div class="col-sm">
                        ${receipt.id}
                    </div>
                    <div class="col-sm">
                        ${receipt.total}
                    </div>
                </div>
            </div>
            <br>

                <%-- form pentru adaugare produse in bon --%>
            <div class="container position-absolute bottom-0 start-0">

                <form class="needs-validation" novalidate method="POST"
                      action="${pageContext.request.contextPath}/ManageReturn">
                    <input type="hidden" id="form2" name="form1" value="form2">
                    <div class="row border-5" style="font-size: 30px">
                        <div class="col col-8 fst-italic d-flex align-items-center w-75">
                            <input type="hidden" id="productName" name="productName" value="${product.name}">
                        </div>
                        <div class="col col-4 text-center w-25 fst-italic">
                        </div>
                        <input type="hidden" id="product-id" name="product-id" value="${product.product_id}">
                    </div>

                    <div class="container">
                        <div class="row fst-italic" style="font-size: 25px">
                            <div class="col">

                            </div>
                        </div>
                        <br>
                        <div class="row" style="font-size: 25px">
                            <div class="col col-8 fst-italic d-flex align-items-center">

                            </div>
                            <div class="col col-4 text-center">
                                <button class="btn btn-primary input-group-append" type="submit"
                                        style="border-radius: 50%; font-size: 50px">
                                    <i class="fa fa-refresh"></i>
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
            <h2 class="text-center">Receipt no. </h2>

            <div>
                <c:if test="${not empty invoices}">
                    <h2>Invoices</h2>
                    <c:forEach var="productname" items="${invoices}" varStatus="status">
                        <div class="row">
                            <div class="col">
                                    ${status.index+1}.${productname}
                            </div>
                            <div class="col">
                                    ${productQuantity}X${product.price}
                            </div>
                            <div class="col text-end">
                                pret total
                            </div>
                        </div>

                        <br/>
                    </c:forEach>
                </c:if>
            </div>


            <div class="container position-absolute bottom-0 start-0">

                <div class="row border-5" style="font-size: 25px;width: 100%">
                    <div class="col fst-italic fw-bold">
                        <p>Total: </p>
                            <%-- suma calculata --%>
                    </div>
                </div>

                <div class="container">
                    <div class="row text-center">
                        <div class="col-lg">
                            <button class="btn btn-primary" type="button" style="font-size: 40px;width: 100%">
                                Return All
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
