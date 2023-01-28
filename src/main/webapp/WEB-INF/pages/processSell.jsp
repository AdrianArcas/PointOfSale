<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Process Sell">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <div class="row row-cols-2 border-5 position-relative" style="height:93%;border-width: 5px;">

    <div class="col position-relative" >
      <br>
      <div class="input-group border border-3 border-primary rounded-3" style="height: 50px">
        <input type="search" class="form-control" placeholder="Search product" aria-label="Search" aria-describedby="search-addon" style="background-color: #acdcee;font-size: 20px" />
        <button class="btn btn-primary input-group-append" type="button">
            <i class="fa fa-search"></i>
        </button>
      </div>

      <div class="container position-absolute bottom-0 start-0">
        <div class="row border-5 align-bottom fst-italic " style="font-size: 25px">
          <div class="col">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="0" max="" style="width: 100px"> <%--la max vine pus ${product.quantityAvailable}--%>
          </div>
        </div>
        <br>
        <div class="row border-5" style="font-size: 25px">
          <div class="col fst-italic w-75">
            <p>Quantity available: </p>
              <%--          ${product.quantityAvailable}--%>
          </div>

          <div class="col text-center w-25">
            <button class="btn btn-primary input-group-append" type="button" style="border-radius: 50%; font-size: 50px">
              <i class="fa fa-plus"></i>
            </button>
          </div>
        </div>
        <br>
      </div>

    </div>

    <div class="col position-relative" style=" background-color: #99ccff">
      <br>
      <h2 class="text-center">Receipt</h2>

      <div class="container position-absolute bottom-0 start-0">

        <div class="row border-5 " style="font-size: 25px;width: 100%">
        <div class="col fst-italic fw-bold">
          <p>Total: </p>
            <%--          suma calculata--%>
        </div>
        </div>

        <div class="row w-100 text-center " style="font-size: 25px ">
          <div class="col " >
            <button class="btn btn-primary" type="button" style="font-size: 50px;width: 100%">
              Submit
            </button>
          </div>

          <div class="col" >
            <button class="btn btn-primary" type="button" style="font-size: 50px; background-color: #080737;width: 100%">
              Change
            </button>
          </div>
      </div>
      </div>
  </div>

  </div>

</t:pageTemplate>
