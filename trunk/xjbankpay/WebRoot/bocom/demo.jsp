<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��ͨ����֧��</title>
</head>
<body>
<form id="PaymentFormBOCOM" method="post" action="bocompay"><br/>
<table>
<tr><td>������:</td><td><input type="text" name="orderid"/></td></tr> 
<tr><td>�������:</td><td><input type="text" name="amount" size="8"/>�� </td></tr> 
<tr><td>֪ͨ��ַ:</td><td><input type="text" name="notifyurl" size="25"/></td></tr> 
<tr><td>��������˵��:</td><td><input type="text" name="orderContent" size="25"/>��������50�����֣�</td></tr> 
<tr><td>�̼ұ�ע��Ϣ:</td><td><input type="text" name="orderMono" size="25"/>��������50�����֣�</td></tr> 

<tr><td colspan="2" align="center"><input type="submit" value="֧��"/></td></tr>
</table>
</form>
</body>
</html>
