<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 16.12.2018
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<html>
<head>
    <title>Title</title>
    <link href="/resources/css.css" rel="stylesheet">

    <script>
        function goBack() {
            window.history.back();
        }
    </script>

</head>

<div class="container" style="margin-top: 15%">
    <div class="row">
        <div class="col-md-4">
        <form action="/changeExchange" method="get">
            <div class="container">

            <%--add changing--%>

            <input type="hidden" name="id" value="${currency.id}">
            <input type="hidden" name="id_cur" value="${currency.currency_id.id}">
                    <input type="hidden" name="name" value="${currency.currency_id.name}">
                    <input type="hidden" name="shortname" value="${currency.currency_id.shortname}">

                        <label>Name</label>
                            <jsp:text>${currency.currency_id.name}</jsp:text>
                        <br>
                        <label>Purchase</label>
                            <input type="text" class="form-control" name="purchase_value" value="${currency.purchase_value}">
                        <label>Sale</label>
                            <input type="text" class="form-control" name="sale_value" value="${currency.sale_value}">

            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        </div>
    </div>
</div>

<%--<div class="b-popup">--%>
    <%--<div class="b-popup-content">--%>
      <%----%>
    <%--</div>--%>
<%--</div>--%>
<br>
<br><br>
<button class="btn btn-primary" onclick="goBack()">Go Back</button>
</body>
</html>