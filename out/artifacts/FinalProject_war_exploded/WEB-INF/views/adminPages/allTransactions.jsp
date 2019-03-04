<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 13.12.2018
  Time: 3:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <title>dropDowm</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="/main"> <img src="/resources/123.jpg"  width="120" height="54" ></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">ADMIN PAGE<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/addManagerPage">ADD MANAGER</a>
            </li>
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                <div class="dropdown-menu" aria-labelledby="dropdown10">
                    <a class="dropdown-item" href="/allTransactionPage">All transactions</a>
                    <a class="dropdown-item" href="/exchangePage">Exchange values</a>
                    <a class="dropdown-item" href="/BankBalancePage">Bank total balance</a>
                </div>
            </li>
            <li class="nav-item right">
                <form action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="submit" class="btn btn-danger" value="Logout">
                </form>
            </li>
        </ul>
    </div>
</nav>


<div class="container" style="margin-top: 10px">
    <div class="row">

        <div class="col-lg-5">
            <label><h3>All Transactions</h3></label>
        </div>

        <c:if test="${allTransactions!=null}">
            hello
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
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allTransactions}" var="transaction">
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>



<br>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<div class="container">
<button onclick="goBack()">Go Back</button>
</div>
</body>
</html>
