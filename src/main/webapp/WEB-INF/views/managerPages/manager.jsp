<%--
  Created by IntelliJ IDEA.
  User: ansar
  Date: 12/6/18
  Time: 3:32 PM
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

    <title>Manager Main Page</title>
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


    <div class="w3-content w3-justify w3-text-grey w3-padding-64" style="margin-top: 10px">
        <div class="row">

            <div class="col-lg-5">
                <label><h3>All MY Transactions</h3></label>
            </div>

            <c:if test="${goodTransactions!=null}">
                <div class="container">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>ManagerID Name</th>
                            <th>Sender Name</th>
                            <th>Receiver Name</th>
                            <th>Operation_ID</th>
                            <th>Currency </th>
                            <th>Amount</th>
                            <th>Date</th>
                            <th>Reciept</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${goodTransactions}" var="transaction">
                            <%--change data here--%>
                            <tr class="text-center">
                                <td>${transaction.id}</td>
                                <td>${transaction.manager_id.fullname}</td>
                                <td>${transaction.sender_account_id.firstName} </td>
                                <td>${transaction.receiver_account_id.firstName} </td>
                                <td>${transaction.operation_id}</td>
                                <td>${transaction.currency_id.shortname} </td>
                                <td>${transaction.amount}</td>
                                <td>${transaction.operation_time}</td>
                                <td>
                                    <form action="reciepts" method="get">
                                        <input type="hidden" name="id" value="${transaction.id}">
                                        <button class="far fa-file-alt" style='font-size:22px' type="submit">  </button>
                                    </form>
                                </td>                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>


    <div class="w3-content w3-justify w3-text-grey w3-padding-64" id="about">
                <h6 class="w3-text-light-grey">KuanyshBANK</h6>
                <hr style="width:200px" class="w3-opacity">
                <p>This material does not take into account your particular investment objectives, financial situations or needs and is not
                    intended as a recommendation, offer or solicitation for the purchase or sale of any security,
                    financial instrument, or strategy. Before acting on any information in this material, you should consider whether it
                    is suitable for your particular circumstances and,
                    if necessary, seek professional advice. Any opinions expressed herein are given in good faith,
                    are subject to change without notice, and are only correct as of the stated date of their issue.

                    Content contained herein may have been produced by an outside party that is not affiliated with Bank of Kuanysh
                    or any of its affiliates (Bank of Kuanysh). Opinions or ideas expressed are not necessarily
                    those of Bank of Kuanysh nor do they reflect their views or endorsement. These materials are for informational
                    purposes only. Bank of Kuanysh does not assume liability for any loss or damage resulting from anyone's reliance on
                    the information provided. Certain links may direct you away from Bank of Kuanysh to an unaffiliated site. Bank of Kuanysh
                    has not been involved in the preparation of the content supplied at the unaffiliated sites and does not guarantee or assume
                    any responsibility for its content. When you visit these sites, you are agreeing to all of their terms of use, including their
                    privacy and security policies. </p>
            </div>
        </div>
    </div>
</body>
</html>
