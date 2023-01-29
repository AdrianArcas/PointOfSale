<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Process Sale">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <div class="row row-cols-2 border-5 position-relative " style="height:92%;border-width: 5px;">
        <div class="col position-relative">
            <br>
                <%-- search bar  --%>
            <form class="needs-validation" novalidate method="POST"
                  action="${pageContext.request.contextPath}/ProcessSale">
                <input type="hidden" id="form1" name="form1" value="form1">
                <div class="input-group " style="height: 50px">
                    <input type="text" class="form-control border border-2 border-primary" id="search_input"
                           name="search_input" placeholder="" value="">
                    <button class="btn btn-primary btn-lg btn-block input-group-append" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </form>

            <br>
                <%-- de afisat poza --%>
            <div class="h-25 w-25 d-flex justify-content-center">
                <img class="card-img-top"
                     src="${pageContext.request.contextPath}/ProductPhoto?product_id=${product.product_id}" alt=""/>
            </div>


            <br><br>
            <div class="container position-absolute bottom-0 start-0">

                <form class="needs-validation" novalidate method="POST"
                      action="${pageContext.request.contextPath}/ProcessSale">
                    <input type="hidden" id="form2" name="form1" value="form2">
                    <div class="row border-5" style="font-size: 25px">
                        <div class="col col-8 fst-italic d-flex align-items-center w-75">
                            <p>${product.name} </p>
                        </div>
                        <div class="col col-4 text-center w-25 fst-italic">
                            <p> ${product.price} lei/kg</p>
                        </div>
                        <input type="hidden" id="product-id" name="product-id" value="${product.product_id}">
                    </div>

                    <div class="row border-5 align-bottom fst-italic " style="font-size: 25px">
                        <div class="col">
                            <label for="quantity">Quantity:</label>
                            <input type="number" id="quantity" name="quantity" min="0" max="${product.quantity}"
                                   style="width: 100px"> <%--la max vine pus ${product.quantityAvailable}--%>
                        </div>
                    </div>
                    <br>
                    <div class="row border-5" style="font-size: 25px">
                        <div class="col col-8 fst-italic d-flex align-items-center w-75">
                            <p>Quantity available: ${product.quantity} kg </p>
                        </div>

                        <div class="col col-4 text-center w-25">
                            <button class="btn btn-primary input-group-append" type="submit"
                                    style="border-radius: 50%; font-size: 50px">
                                <i class="fa fa-plus"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>

        </div>

        <div class="col position-relative" style=" background-color: #99ccff">
            <br>
            <h2 class="text-center">Receipt</h2>

            <div>

                <c:if test="${not empty invoices}">
                    <h2>Invoices</h2>
                    <c:forEach var="productname" items="${invoices}" varStatus="status">
                        <div class="row">
                            <div class="col">
                              ${status.index+1}.${productname}
                            </div>
                            <div class="col">
                              cantitate dorita
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

                <div class="row border-5 " style="font-size: 25px;width: 100%">
                    <div class="col fst-italic fw-bold">
                        <p>Total: </p>
                            <%--          suma calculata--%>
                    </div>
                </div>

                <div class="row w-100 text-center " style="font-size: 25px ">
                    <div class="col ">
                        <button class="btn btn-primary" type="button" style="font-size: 40px;width: 100%">
                            Submit
                        </button>
                    </div>

                    <div class="col">
                        <button class="btn btn-primary" type="button"
                                style="font-size: 40px; background-color: #080737;width: 100%">
                            Change
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>

</t:pageTemplate>
