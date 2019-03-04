<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 17.12.2018
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
<form action="/changeAcc" method="get">
    <%--add changing--%>
    <input type="hidden" name="id" value="${accaunt.id}">
        <label>Login</label><input type="text" class="form-control" name="name" value="${accaunt.name}">
        <label>Full Name</label><input type="text" class="form-control" name="fullName" value="${accaunt.firstName}">
        <label>Birth Date</label><input type="date" class="form-control" name="birthdate" value="${accaunt.birth_date}">
        <label>UIN</label><input type="text" name="UIN" class="form-control" minlength="12" maxlength="12" value="${accaunt.uniqueIdentificationNumber}">
        <label>Blocked</label><input type="text" class="form-control" name="blocked" value="${accaunt.is_blocked}">
        <br>
    <button type="submit"> Change</button>
</form>
        </div></div></div>
<br>
<br><br>
<button onclick="goBack()">Go Back</button>
</body>
</html>