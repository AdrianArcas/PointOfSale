<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <nav class="navbar navbar-expand-md navbar-dark <%--fixed-top--%> navbar-static-top" style="background-color: #080737;">
    <div class="container-fluid">
      <a class="navbar-brand" href="${pageContext.request.contextPath}" style="font-family:Sofia,sans-serif">Green Grocery</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse" style="font-family: 'Lora', cursive">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <c:if test="${pageContext.request.isUserInRole('Cashier')}">
            <a class="nav-link ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/"))eq'/index.jsp'?'active':''}"
               aria-current="page" href="${pageContext.request.contextPath}/cashier.jsp">
              <div>
                <img src="<%=request.getContextPath()%>/images/user.png" alt=" " width="38">
                Hello, Cashier!
              </div>
            </a>
            </c:if>
          </li>
          <li class="nav-item">
            <c:if test="${pageContext.request.isUserInRole('Director')}">
            <a class="nav-link" href="${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/"))eq'/director.jsp'?'active':''}" aria-current="page" href="${pageContext.request.contextPath}/director.jsp">
              <div>
                <img src="<%=request.getContextPath()%>/images/director.png" alt=" " width="38">
                Hello, Director!
              </div>
            </a>
          </c:if>
          </li>
          <li class="nav-item">
            <c:if test="${pageContext.request.isUserInRole('Manager')}">
            <a class="nav-link" href="${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/"))eq'/manager.jsp'?'active':''}" aria-current="page" href="${pageContext.request.contextPath}/manager.jsp">
              <div>
                <img src="<%=request.getContextPath()%>/images/manager.png" alt=" " width="38">
                Hello, Manager!
              </div>
            </a>
          </c:if>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li>
            <c:if test="${pageContext.request.isUserInRole('Director')}">
            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/DirectorNotifications">
              <img src="<%=request.getContextPath()%>/images/notification.png" alt="Notification" width="38">
            </a>
            </c:if>

            <c:if test="${pageContext.request.isUserInRole('Manager')}">
              <a class="nav-link"  aria-current="page" href="${pageContext.request.contextPath}/Notifications">
                <img src="<%=request.getContextPath()%>/images/notification.png" alt="Notification" width="38">
              </a>
            </c:if>
          </li>
          <li class="nav-item">
           <c:if test="${pageContext.request.isUserInRole('Director')}">
            <a class="nav-link " href="${pageContext.request.contextPath}/Statistics"  aria-current="page" >
              Statistics
            </a>
           </c:if>
          </li>
          <li class="nav-item">
            <c:if test="${pageContext.request.isUserInRole('Manager')}">
              <a class="nav-link " href="${pageContext.request.contextPath}/Accounts"  aria-current="page" >
                Accounts
              </a>
            </c:if>
          </li>
          <li class="nav-item">
            <c:if test="${pageContext.request.isUserInRole('Manager')}">
              <a class="nav-link " href="${pageContext.request.contextPath}/AddAccount"  aria-current="page" >
                Add Account
              </a>
            </c:if>
          </li>
          <li class="nav-item">
            <c:if test="${pageContext.request.isUserInRole('Manager')}">
              <a class="nav-link " href="${pageContext.request.contextPath}/AddProduct"  aria-current="page" >
                Add Product
              </a>
            </c:if>
          </li>
          <li class="nav-item">
            <c:if test="${pageContext.request.isUserInRole('Cashier')}">
              <a style="margin-right:10px;height: 38px" href="${pageContext.request.contextPath}/ClearInvoice?page=sale" class="btn btn-primary btn-lg" type="submit">Sell</a>
            </c:if>
          </li>
          <li class="nav-item">
          <c:if test="${pageContext.request.isUserInRole('Cashier')}">
              <a style="height: 38px" href="${pageContext.request.contextPath}/ClearInvoice?page=return" class="btn btn-primary btn-lg" type="submit">Return</a>
          </c:if>
          </li>
          <li class="nav-item">
            <c:choose>
              <c:when test="${pageContext.request.getRemoteUser() == null}">
                <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
              </c:when>
              <c:otherwise>
                <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
              </c:otherwise>
            </c:choose>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</header>
