<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.ccb.CCBDatas"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��ͨ����֧��</title>
</head>
<body>

<form name="form1" id="PaymentFormBOCOM" method="post" action="<%=request.getAttribute("payurl")%>">
<!-- �汾��-->
 			<input type = "hidden" name = "interfaceVersion" value = "<%=request.getAttribute("interfaceVersion")%>">
<!-- �̻��ͻ��ţ��̻���ţ�  -->
            <input type = "hidden" name = "merID" value = "<%=request.getAttribute("merID")%>">
<!-- ������ -->
            <input type = "hidden" name = "orderid" value = "<%=request.getAttribute("orderid")%>">
<!--�̻��������� -->
            <input type = "hidden" name = "orderDate" value = "<%=request.getAttribute("orderDate")%>">
<!-- �̻�����ʱ�� -->
            <input type = "hidden" name = "orderTime" value = "<%=request.getAttribute("orderTime")%>">
<!-- �������  -->
            <input type = "hidden" name = "tranType" value = "<%=request.getAttribute("tranType")%>">
<!-- �������  -->
            <input type = "hidden" name = "amount" value = "<%=request.getAttribute("amount")%>">
<!-- ��������  -->
            <input type = "hidden" name = "curType" value = "<%=request.getAttribute("curType")%>">
<!-- ��������  -->
            <input type = "hidden" name = "orderContent" value = "<%=request.getAttribute("orderContent")%>">
<!-- �̼ұ�ע -->
            <input type = "hidden" name = "orderMono" value = "<%=request.getAttribute("orderMono")%>">
<!-- �������ͱ�־  -->
            <input type = "hidden" name = "phdFlag" value = "<%=request.getAttribute("phdFlag")%>">
<!-- ֪ͨ��ʽ  -->
            <input type = "hidden" name = "notifyType" value = "<%=request.getAttribute("notifyType")%>">
<!--����֪ͨURL -->
            <input type = "hidden" name = "merURL" value = "<%=request.getAttribute("merURL")%>">
<!-- ȡ��URL  -->
            <input type = "hidden" name = "goodsURL" value = "<%=request.getAttribute("goodsURL")%>">
<!-- �Զ���תʱ�� -->
            <input type = "hidden" name = "jumpSeconds" value = "<%=request.getAttribute("jumpSeconds")%>">
<!-- �̻����κ� -->
            <input type = "hidden" name = "payBatchNo" value = "<%=request.getAttribute("payBatchNo")%>">
<!-- �����̼�����  -->
            <input type = "hidden" name = "proxyMerName" value = "<%=request.getAttribute("proxyMerName")%>">
<!-- �����̼�֤������ -->
            <input type = "hidden" name = "proxyMerType" value = "<%=request.getAttribute("proxyMerType")%>">
<!-- �����̼�֤������ -->
            <input type = "hidden" name = "proxyMerCredentials" value = "<%=request.getAttribute("proxyMerCredentials")%>">
<!-- �������  -->
            <input type = "hidden" name = "netType" value = "<%=request.getAttribute("netType")%>">
<!-- �̼�ǩ�� -->
            <input type = "hidden" name = "merSignMsg" value = "<%=request.getAttribute("signMsg")%>">
    
<input type="submit" value="֧��"/>

</form>
<script type="text/javascript">
	
	//document.form1.submit();
</script>
</body>
</html>
