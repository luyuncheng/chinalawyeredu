<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>�����ʻ���ѯ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String code,err,msg;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");//�ô���ֻ�����һ��
	if (ret != 0){  //��ʼ��ʧ��
		out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.queryAccountBalance(); //�����ʻ���ѯ
		if (rep == null){  
			err = client.getLastErr();
			out.print("���״�����Ϣ��"+err + "<br>");
		}
		else{
			code = rep.getRetCode(); //�õ����׷�����
			msg = rep.getErrorMessage();
			out.print("���׷����룺"+code + "<br>");
			out.print("���״�����Ϣ��"+msg + "<br>");
			if("0".equals(code)){//��ʾ���׳ɹ�
				out.print("<br>------------------------<br>");	
				OpResult opr = rep.getOpResult();
				String accountNo = opr.getValueByName("settAccount") ;		//�õ��˺�
				String accountName = opr.getValueByName("accountName"); 	//�õ��˺�����
				String currType = opr.getValueByName("currType") ;			//�õ�����
				String currBalance = opr.getValueByName("currBalance") ;	//�õ���ǰ���
				String validBalance = opr.getValueByName("validBalance") ;	//�õ��������
				out.print("�˺�:"+accountNo);
				out.print("<br>");
				out.print("�˺�����:"+accountName);
				out.print("<br>");
				out.print("����:"+currType);
				out.print("<br>");
				out.print("��ǰ���:"+currBalance);
				out.print("<br>");
				out.print("�������:"+validBalance);
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
