<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.cmb.CMBDatas"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>����֧��</title>
</head>
<body>
<form id="PaymentFormICBC" method="post" action="cmbpay"><br/>
<table>
<tr><td>������</td><td><input type="text" name="orderid"/></td></tr> 
<tr><td>�������</td><td><input type="text" name="amount" size="8"/>Ԫ </td></tr> 
<tr><td>֪ͨ��ַ</td><td><input type="text" name="notifyurl"/></td></tr> 
<tr><td colspan="2" align="center"><input type="submit" value="֧��"/></td></tr>
</table>
</form>
</body>
</html>
