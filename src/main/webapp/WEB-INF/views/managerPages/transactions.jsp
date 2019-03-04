<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 20.12.2018
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>Transaction Page</title>
    <style>
        body, h1,h2,h3,h4,h5,h6 {font-family: "Montserrat", sans-serif}
        .w3-row-padding img {margin-bottom: 12px}
        /* Set the width of the sidebar to 120px */
        .w3-sidebar {width: 120px;background: #222;}
        /* Add a left margin to the "page content" that matches the width of the sidebar (120px) */
        #main {margin-left: 120px}
        /* Remove margins from "page content" on small screens */
        @media only screen and (max-width: 600px) {#main {margin-left: 0}}
    </style>

</head>
<body class="w3-black">
<nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center">
    <!-- Avatar image in top left corner -->
    <a href="/main"><img src="/resources/123.jpg" style="width:100%" ></a>

    <a href="/main" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-home w3-xxlarge"></i>
        <p>HOME</p>
    </a>
    <a href="/creatAccPage" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-user w3-xxlarge"></i>
        <p>NEW ACCOUNT</p>
    </a>
    <a href="transactionByManagerPage" class="w3-bar-item w3-button w3-padding-large w3-black">
        <i class="fa fa-credit-card w3-xxlarge"></i>
        <p>Transaction</p>
    </a>
    <a href="/ExchangePageBtn" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-money w3-xxlarge"></i>
        <p>EXCHAGE MONEY</p>
    </a>
    <a href="clientsPage" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-eye w3-xxlarge"></i>
        <p>Clients</p>
    </a>
    <form class="w3-bar-item w3-padding-large w3-hover-black w3-button" action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <i class="fa fa-bed w3-xxlarge"></i>
        <input type="submit"  class="btn btn-primary btn-sm" value="LOGOUT">
    </form>
</nav>


        <%--TRANSACTION--%>
<div class="w3-content w3-justify w3-text-grey w3-padding-64">
    <p><h4 class="w3-text-light-grey">Transaction</h4></p>
    <hr style="width:200px" class="w3-opacity">

    <form action="/sendMoney" method="get">
        <div class="form-group">
       <label>Sender</label>
        <select name="accSender" class="selectpicker">
            <c:forEach items="${accaunts}" var="accs">
            <c:choose>
                <c:when test="${accs.is_blocked.equals('NO')}">
                    <option value="${accs.id}">
                          ${accs.id} ${accs.firstName} ${accs.currency_id.shortname}
                    </option>
                </c:when>
            </c:choose>
            </c:forEach>
        </select>
        </div>
        <div class="form-group">
            <label>Reciver</label>
            <select name="accResiver" class="selectpicker">
                <c:forEach items="${accaunts}" var="accs">
                    <c:choose>
                        <c:when test="${accs.is_blocked.equals('NO')}">
                            <option value="${accs.id}">
                                    ${accs.id} ${accs.firstName} ${accs.currency_id.shortname}
                            </option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Amount</label>
            <input type="number" class="form-control" name="amount">
        </div>
        <div class="form-group">
            <label>Reciver UIN</label>
            <input type="text" class="form-control" name="uniqueIdentificationNumber" minlength="12" maxlength="12">
        </div>
        <button type="submit" class="btn btn-primary btn-block">ADD</button>
    </form>
</div>




    <%--DEBIT--%>
<div class="w3-content w3-justify w3-text-grey w3-padding-64">
    <p><h4 class="w3-text-light-grey">DEBIT</h4></p>
    <hr style="width:200px" class="w3-opacity">

    <form action="/debit" method="get">
        <div class="form-group">
            <label>Client DEBITTED</label>
            <select name="accSender" class="selectpicker">
                <c:forEach items="${accaunts}" var="accs">
                    <c:choose>
                        <c:when test="${accs.is_blocked.equals('NO')}">
                            <option value="${accs.id}">
                                    ${accs.id} ${accs.firstName} ${accs.currency_id.shortname}
                            </option>
                        </c:when>
                    </c:choose>

                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Amount</label>
            <input type="number" class="form-control" name="amount">
        </div>
        <div class="form-group">
            <label>Persons UIN</label>
            <input type="text" class="form-control" name="uniqueIdentificationNumber" minlength="12" maxlength="12">
        </div>
        <button type="submit" class="btn btn-primary btn-block">DEBIT</button>
    </form>
</div>

<%--CREDIT--%>
<div class="w3-content w3-justify w3-text-grey w3-padding-64">
    <p><h4 class="w3-text-light-grey">CREDIT</h4></p>
    <hr style="width:200px" class="w3-opacity">

    <form action="/credit" method="get">
        <div class="form-group">
            <label>Client CREDITED</label>
            <select name="accSender" class="selectpicker">
                <c:forEach items="${accaunts}" var="accs">
                    <c:choose>
                        <c:when test="${accs.is_blocked.equals('NO')}">
                            <option value="${accs.id}">
                                    ${accs.id} ${accs.firstName} ${accs.currency_id.shortname}
                            </option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Amount</label>
            <input type="number" class="form-control" name="amount">
        </div>
        <div class="form-group">
            <label>Persons UIN</label>
            <input type="text" class="form-control" name="uniqueIdentificationNumber" minlength="12" maxlength="12">
        </div>
        <button type="submit" class="btn btn-primary btn-block">CREDIT</button>
    </form>
</div>


</body>
</html>
