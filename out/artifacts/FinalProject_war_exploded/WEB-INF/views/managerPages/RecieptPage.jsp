<%--
  Created by IntelliJ IDEA.
  User: kuanyshsalyk
  Date: 25.12.2018
  Time: 4:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="/resources/css.css" rel="stylesheet">

    <title>Title</title>
</head>
<body>
    <div id="invoice-POS">
        <div id="HTMLtoPDF">

        <center id="top">
            <div class="logo"></div>
            <div class="info">
                <h2>KuanyshBank</h2>
            </div><!--End Info-->
        </center><!--End InvoiceTop-->

        <div id="mid">
            <div class="info">
                <h2>Contact Info</h2>
                <p>
                    Manager : ${trHistory.manager_id.fullname} </br>
                </p>
                <p>
                    Email   : ${trHistory.manager_id.login}@gmail.com</br>
                </p>
                <p>
                    Phone   : 555-555-5555</br>
                </p>
            </div>
        </div><!--End Invoice Mid-->

        <div id="bot">

            <div id="table">
                <%--<table>--%>
                    <%--<tr class="tabletitle">--%>
                        <%--<td class="item"><h2>Item</h2></td>--%>
                        <%--<td class="Hours"><h2>Qty</h2></td>--%>
                        <%--<td class="Rate"><h2>Sub Total</h2></td>--%>
                    <%--</tr>--%>

                    <%--<tr class="service">--%>
                        <%--<td class="tableitem"><p class="itemtext">Communication</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">5</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">$375.00</p></td>--%>
                    <%--</tr>--%>

                    <%--<tr class="service">--%>
                        <%--<td class="tableitem"><p class="itemtext">Asset Gathering</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">3</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">$225.00</p></td>--%>
                    <%--</tr>--%>

                    <%--<tr class="service">--%>
                        <%--<td class="tableitem"><p class="itemtext">Design Development</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">5</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">$375.00</p></td>--%>
                    <%--</tr>--%>

                    <%--<tr class="service">--%>
                        <%--<td class="tableitem"><p class="itemtext">Animation</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">20</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">$1500.00</p></td>--%>
                    <%--</tr>--%>

                    <%--<tr class="service">--%>
                        <%--<td class="tableitem"><p class="itemtext">Animation Revisions</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">10</p></td>--%>
                        <%--<td class="tableitem"><p class="itemtext">$750.00</p></td>--%>
                    <%--</tr>--%>


                    <%--<tr class="tabletitle">--%>
                        <%--<td></td>--%>
                        <%--<td class="Rate"><h2>tax</h2></td>--%>
                        <%--<td class="payment"><h2>$419.25</h2></td>--%>
                    <%--</tr>--%>

                    <%--<tr class="tabletitle">--%>
                        <%--<td></td>--%>
                        <%--<td class="Rate"><h2>Total</h2></td>--%>
                        <%--<td class="payment"><h2>$3,644.25</h2></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>

                    <table>
                        <thead>
                        <tr class="tabletitle">
                            <td class="item">ID</td>
                            <td class="item">Manager</td>
                            <td class="Hours">Currency</td>
                            <td class="item">Receiver</td>
                            <td class="item">Sender</td>
                            <td class="item">Operation</td>
                            <td class="item">Date </td>
                        </tr>
                        </thead>
                        <tbody>
                        <%--change data here--%>
                        <tr class="service">

                            <td class="tableitem">
                                <p class="itemtext">
                                ${trHistory.id}
                                    </p>
                            </td>

                            <td class="tableitem">
                                <p class="itemtext">
                                ${trHistory.manager_id.fullname}
                                </p>
                            </td>

                            <td class="tableitem">
                                <p class="itemtext">
                                ${trHistory.currency_id.shortname} ${trHistory.amount}
                                </p>
                            </td>

                            <td class="tableitem">
                                <p class="itemtext">
                                ${trHistory.receiver_account_id.firstName}
                                </p>
                            </td>

                            <td class="tableitem">
                                <p class="itemtext">
                                ${trHistory.sender_account_id.firstName}
                                </p>
                            </td>

                            <td class="tableitem">
                                <p class="itemtext">
                                    ${trHistory.operation_id}
                                </p>
                            </td>
                            <td class="tableitem">
                                <p class="itemtext">
                                ${trHistory.operation_time.toString()}
                                </p>
                            </td>
                        </tr>
                        </tbody></table>
            </div><!--End Table-->
        </div>


        </div><!--End InvoiceBot-->
        <div id="legalcopy">
            <p class="legal"><strong>Thank you for your business!</strong>
            </p>
        </div>

        <a href="#" onclick="HTMLtoPDF()">Download PDF</a>

        <br>
        <button onclick="goBack()">Go Back</button>
    </div><!--End Invoice-->





</body>


<script>
    function goBack() {
        window.history.back();
    }
</script>
<script src="/resources/jspdf.js"></script>
<script src="/resources/jquery-2.1.3.js"></script>
<script src="/resources/pdfFromHTML.js"></script>
</html>
