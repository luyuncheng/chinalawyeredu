<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.ccb.CCBDatas"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��������֧��</title>
</head>
<body>

<form name="form1" id="PaymentFormCCB" method="post" action="<%=com.ccb.CCBDatas.PAYURL %>">
<!-- �̻���̨���� -->
<input type="hidden" name="PUB32" value="<%=com.ccb.CCBDatas.KEY %>" />
<!-- �̻���̨���� -->
<input type="hidden" name="POSID" value="<%=com.ccb.CCBDatas.POSID %>" />
<!-- �̳����ڷ��к� -->
<input type="hidden" name="BRANCHID" value="<%=com.ccb.CCBDatas.BRANCHID %>" />
<!-- �̳Ǳ��� -->
<input type="hidden" name="MERCHANTID" value="<%=CCBDatas.MERCHANTID %>" />
<!-- �̳ǲ����Ķ����� -->
<input type="hidden" name="ORDERID" value="<%=request.getAttribute("BillNo") %>" />
<!-- ��� -->
<input type="hidden" name="PAYMENT" value="<%=request.getAttribute("Amount") %>" />
<!-- ����01Ϊ����� -->
<input type="hidden" name="CURCODE" value="<%=CCBDatas.CURCODE %>" />
<!-- ����������ֱ�Ӵ��������� -->
<input type="hidden" name="REMARK1" value="<%=request.getAttribute("REMARK1") %>"/> 
<input type="hidden" name="REMARK2" value="<%=request.getAttribute("REMARK2") %>" /> 
<input type="hidden" name="TXCODE" value="<%=CCBDatas.TXCODE %>" /> 
<input type="hidden" name="MAC" value="<%=request.getAttribute("md5") %>" /> 
<!--  
<input type="submit" value="֧��"/>
<input type="button" value="md5" onclick="make()"/>
 -->
</form>
<script language="JavaScript" src="md5.js"></script>
<script language="JavaScript"  src="pay.js"></script>
<script type="text/javascript">
  document.form1.submit();
</script>
</body>
</html>
