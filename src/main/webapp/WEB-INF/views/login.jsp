<%--
  Created by IntelliJ IDEA.
  User: ansar
  Date: 12/6/18
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>login</title>
</head>
<body>
    <div class="container" style="margin-top: 15%">
        <div class="row">
            <div class="col-md-4">
                <form action="/login" method="post">
                    <label> Kuanysh</label>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" id="email" class="form-control" name="email_parameter">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" id="pwd" class="form-control" name="password_parameter">
                    </div>
                    <input type="submit" class="btn btn-primary" value="SIGN IN">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </div>
        </div>
    </div>

    <br>
    <form action="/teachersPage" method="get">
        <input type="submit" value="Teacher"></form>
    </form>
    <form action="/studentFirstPage
" method="get">
        <input type="submit" value="Student"></form>
    </form>
</body>
</html>
