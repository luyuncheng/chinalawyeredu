<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>֧��</title>
<style type="text/css">
td{
	font-size:10pt;	
}
</style>
</head>
<body>
<form id="PaymentFormICBC" method="post" action="http://211.90.75.36/icbc/B2C/b2c.jsp"><br/>
<table>
<tr><td>������</td><td><input type="text" name="orderid"/></td></tr> 
<tr><td>�������</td><td><input type="text" name="amount" size="8"/>�� </td></tr> 
<tr><td>֪ͨ��ַ</td><td><input type="text" name="notifyurl"/></td></tr> 
<tr><td colspan="2" align="center"><input type="submit" value="��һ��"/></td></tr>
</table>
</form>
</body>
</html>
