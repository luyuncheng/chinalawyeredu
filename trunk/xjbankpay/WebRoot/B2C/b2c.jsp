<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.icbc.common.B2CUtil;"%>
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
<%  
B2CUtil b2c=new B2CUtil();
b2c.b2cFormData(request);
if(!b2c.isError()){
%>  
<form name="form1" id="PaymentFormICBC" method="post" action="https://B2C.icbc.com.cn/servlet/ICBCINBSEBusinessServlet"><br/>
<!-- �ӿ����� -->
<input type="hidden" name="interfaceName" value="ICBC_PERBANK_B2C" />
<!-- �ӿڰ汾�� -->
<input type="hidden" name="interfaceVersion" value="1.0.0.7" />
<!-- �������� -->
<input type="hidden" name="tranData" value="<%=b2c.getB2cTrandata()%>" />
<!-- ����ǩ������ -->
<input type="hidden" name="merSignMsg" value="<%=b2c.getMerSignMsg()%>" />
<!-- �̳�֤�鹫Կ -->
<input type="hidden" name="merCert" value="<%=B2CUtil.merCert%>" /> 
<script type="text/javascript">
	document.form1.submit();
</script>
<%}else	
	out.println(b2c.getMessage());
%>
</form>
</body>
</html>
