<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 24.12.2018
  Time: 1:14 AM
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
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css' integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
    <title>Clients</title>
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

    <a href="/main" class="w3-bar-item w3-button w3-padding-large w3-black">
        <i class="fa fa-home w3-xxlarge"></i>
        <p>HOME</p>
    </a>
    <a href="/creatAccPage" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-user w3-xxlarge"></i>
        <p>NEW ACCOUNT</p>
    </a>
    <a href="/transactionByManagerPage" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-credit-card w3-xxlarge"></i>
        <p>Transaction</p>
    </a>
    <a href="/ExchangePageBtn" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-money w3-xxlarge"></i>
        <p>EXCHAGE MONEY</p>
    </a>
    <a href="/clientsPage" class="w3-bar-item w3-button w3-padding-large w3-hover-black">
        <i class="fa fa-eye w3-xxlarge"></i>
        <p>Clients</p>
    </a>
    <form class="w3-bar-item w3-padding-large w3-hover-black w3-button" action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <i class="fa fa-bed w3-xxlarge"></i>
        <input type="submit"  class="btn btn-primary btn-sm" value="LOGOUT">
    </form>
</nav>

<div class="container" style="margin-top: 10px">
    <div class="row">

        <div class="w3-content w3-justify w3-text-grey w3-padding-64">
            <hr style="width:200px" class="w3-opacity">
            <p><h4 class="w3-text-light-grey"> All Accaunts</h4></p>
            <hr style="width:200px" class="w3-opacity">
            <table class="table table-hover table-dark">
                <%--id, name, first_name, last_name, birth_date, iin_number, currency_id, amount, created_date, is_blocked--%>
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Full Name</th>
                    <th scope="col">Birth date</th>
                    <th scope="col">UIN</th>
                    <th scope="col">Currency</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Created Date</th>
                    <th scope="col">Block Status</th>
                    <th scope="col">Transactions</th>
                    <th scope="col">BTN</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${accounts}" var="acc">

                    <tr>
                        <td>${acc.id}</td>
                        <td>${acc.name}</td>
                        <td>${acc.firstName}</td>
                        <td>${acc.birth_date}</td>
                        <td>${acc.uniqueIdentificationNumber}</td>
                        <td>${acc.currency_id.shortname}</td>
                        <td>${acc.amount}</td>
                        <td>${acc.created_date}</td>
                        <td>${acc.is_blocked}</td>
                        <td>
                            <form action="showallTransactionPage" method="get">
                            <input type="hidden" name="id" value="${acc.id}">
                            <button class="far fa-file-alt" style='font-size:22px' type="submit">  </button>
                        </form>
                        </td>

                        <td> <form action="/editAccBtn" method="get">
                            <input type="hidden" name="id" value="${acc.id}">
                            <button class="far fa-edit" style='font-size:22px' type="submit">  </button>
                        </form></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
    </body>
</html>
