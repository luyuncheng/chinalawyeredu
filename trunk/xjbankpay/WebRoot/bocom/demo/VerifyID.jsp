<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>�ֿ��������֤(VIP�̻�</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String card = request.getParameter("card");
	String custName = request.getParameter("custName");
	String certType = request.getParameter("certType");
	String certNo = request.getParameter("certNo");
	String code;
	String err;
	String msg;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
	if (ret != 0){  //��ʼ��ʧ��
		out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.verifyCustID( card, custName,certType,certNo) ;
		if (rep == null){  
			err = client.getLastErr();
			out.print("���״�����Ϣ��"+err + "<br>");
		}else{
			code = rep.getRetCode(); //�õ����׷�����
			err = rep.getLastErr();
			msg = rep.getErrorMessage();
			out.print("���׷����룺"+code + "<br>");
			out.print("���״�����Ϣ��"+msg + "<br>");
		}
	}
%>
<p><a href="Index.htm">������ҳ</a></p>
<p>&nbsp;</p>
</body>
</html>
