<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.cmb.CMBDatas"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��������֧��</title>
</head>
<body>

<form name="form1" id="PaymentFormCMB" method="post" action="<%=com.cmb.CMBDatas.PAYURL %>"><br/>
<!-- �̳����ڷ��к� -->
<input type="hidden" name="BranchID" value="<%=com.cmb.CMBDatas.BRANCHID %>" />
<!-- �̳Ǳ��� -->
<input type="hidden" name="CoNo" value="<%=CMBDatas.CONO %>" />
<!-- �̳ǲ����Ķ����� -->
<input type="hidden" name="BillNo" value="<%=request.getAttribute("BillNo") %>" />
<!-- ��� -->
<input type="hidden" name="Amount" value="<%=request.getAttribute("Amount") %>" />
<!-- ���׵����� -->
<input type="hidden" name="Date" value="<%=request.getAttribute("Date") %>" /> 
<input type="hidden" name="MerchantUrl" value="<%=CMBDatas.NOTIFYURL %>" /> 
<input type="hidden" name="MerchantPara" value="<%=request.getAttribute("MerchantPara") %>" /> 
<input type="hidden" name="MerchantCode" value="<%=request.getAttribute("MerchantCode") %>" /> 
<!-- 
<input type="submit" value="֧��"/>
-->
</form>
<script type="text/javascript">
	document.form1.submit();
</script>
</body>
</html>
