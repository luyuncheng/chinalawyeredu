<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>���ʵ����ز���</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String settleDate = request.getParameter("settleDate");
	String code,err,msg;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
	if (ret != 0){  //��ʼ��ʧ��
		out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.downLoadSettlement(settleDate) ;//���ʵ�����
		if (rep == null){  
			err = client.getLastErr();
			out.print("���״�����Ϣ��"+err + "<br>");
		}else{
			code = rep.getRetCode(); //�õ����׷�����
			err = rep.getLastErr();
			msg = rep.getErrorMessage();
			out.print("���׷����룺"+code + "<br>");
			out.print("���״�����Ϣ��"+msg + "<br>");
			if("0".equals(code)){//��ʾ���׳ɹ�
				out.print("<br>------------------------<br>");
				OpResult opr = rep.getOpResult();
				String totalSum = opr.getValueByName("totalSum"); //�ܽ��
				String totalNumber = opr.getValueByName("totalNumber"); //�ܱ���
				String totalFee = opr.getValueByName("totalFee"); //��������
	
				out.print("�ܽ�"+totalSum);
				out.print("<br>");
				out.print("�ܱ�����"+totalNumber);
				out.print("<br>");
				out.print("�������ѣ�"+totalFee);
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