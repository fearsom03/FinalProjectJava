<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 13.12.2018
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Title</title>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>

</head>
<body>
<div class="container" style="margin-top: 15%">
    <div class="row">
        <div class="col-md-4">
        <form action="/changeUser" method="get">
    <%--add changing--%>
    <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label>Login</label>
            <input type="text" name="login"  class="form-control"value="${user.login}">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="text" class="form-control" name="pass" >
        </div>
        <div class="form-group">
            <label>FullName</label>
            <input type="text" class="form-control" name="fullname" value="${user.fullname}">
        </div>
        <br>
    <button type="submit" class="btn btn-primary"> Change</button>
</form>
    </div>
    </div>
</div>

<br>
<br><br>
<button onclick="goBack()">Go Back</button>
</body>
</html>