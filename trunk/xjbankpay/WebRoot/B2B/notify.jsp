<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��������B2C֧��</title>
<style type="text/css">
td{
	font-size:10pt;	
}
</style>
</head>
<body>
<table>
<%
Enumeration params = request.getParameterNames();
String parameter = null ;
while( params.hasMoreElements()){
	parameter = (String) params.nextElement() ;
	String val=request.getParameter(parameter);
	out.println("<tr><td>"+parameter+"</td><td>"+val+"</td></tr>");
}
%>
</table>
</body>
</html>
