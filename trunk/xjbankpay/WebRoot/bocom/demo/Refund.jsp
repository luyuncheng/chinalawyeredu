<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>�˿�¼�����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
String operator = request.getParameter("operator");
String order = request.getParameter("order");
String orderdate = request.getParameter("date");
String amount = request.getParameter("amount");
String comment = request.getParameter("comment");
String code ;
String err ;
String msg ;
BOCOMB2CClient client = new BOCOMB2CClient();
int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
if (ret != 0){  //��ʼ��ʧ��
		out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
}else {
	BOCOMB2COPReply rep = client.Refund( operator,order,orderdate,amount,comment); //�˿�¼��
	if (rep == null){  
		err = client.getLastErr();
		out.print("���״�����Ϣ��"+err + "<br>");
	}else{
		code = rep.getRetCode(); 	//�õ����׷�����
		err = rep.getLastErr();
		msg = rep.getErrorMessage();
		out.print("���׷����룺"+code + "<br>");
		out.print("���״�����Ϣ��"+msg + "<br>");
		if("0".equals(code)){//��ʾ���׳ɹ�
			out.print("<br>------------------------<br>");
			OpResult opr = rep.getOpResult();
			String serial = opr.getValueByName ("serial"); 		//�˿���ˮ��
			String account = opr.getValueByName ("account"); 	//�˿��˺�
	
			out.print("�˿���ˮ��"+serial);
			out.print("<br>");
			out.print("�˿��˺�"+account);
			out.print("<br>");
			out.print("<p></p>");
		}
	}
}
%>
<p><a href="Index.htm">������ҳ</a></p>
<p>&nbsp;</p>
</body>
</html>
