<%@ page import="com.bocom.netpay.b2cAPI.*"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
<head>
<title>����������ѯ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String orders = request.getParameter("orders");
	String detail = request.getParameter("detail");
	int showdetail = 0;
	String code,err,msg;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");//�ô���ֻ�����һ��
	if (ret != 0){  //��ʼ��ʧ��
		out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
	}else {
		try{
			showdetail = Integer.parseInt(detail);
		}
		catch(Exception xcp){showdetail = 0;}
		BOCOMB2COPReply rep = client.queryOrder(orders); //����������ѯ
		if (rep == null) {
			err = client.getLastErr();
			out.print("���״�����Ϣ��" + err + "<br>");
		} else {
			code = rep.getRetCode(); //�õ����׷�����
			err = rep.getLastErr();
			msg = rep.getErrorMessage();
			out.print("���׷����룺" + code + "<br>");
			out.print("���״�����Ϣ��" + msg + "<br>");
			if("0".equals(code)){//��ʾ���׳ɹ�
				String num;
				int index;
				OpResultSet oprSet = rep.getOpResultSet(); 
				int iNum  = oprSet.getOpresultNum();
				out.print("�ܽ��׼�¼����");
				out.print(iNum);
				out.print("<br>------------------------<br>");
				for (index = 0; index <= iNum - 1; index++) {
					String order 			= oprSet.getResultValueByName(index,"order");			//������
					String orderDate 		= oprSet.getResultValueByName(index, "orderDate");		//��������
					String orderTime 		= oprSet.getResultValueByName(index, "orderTime");		//����ʱ��
					String curType 			= oprSet.getResultValueByName(index,"curType");			//����
					String amount 			= oprSet.getResultValueByName(index,"amount"); 			//���
					String tranDate 		= oprSet.getResultValueByName(index,"tranDate"); 		//��������
					String tranTime 		= oprSet.getResultValueByName(index,"tranTime"); 		//����ʱ��
					String tranState 		= oprSet.getResultValueByName(index,"tranState"); 		//֧������״̬
					String orderState 		= oprSet.getResultValueByName(index, "orderState"); 	//����״̬
					String fee 				= oprSet.getResultValueByName(index, "fee"); 			//������
					String bankSerialNo 	= oprSet.getResultValueByName(index, "bankSerialNo"); 	//������ˮ��
					String bankBatNo 		= oprSet.getResultValueByName(index, "bankBatNo"); 		//�������κ�
					String cardType 		= oprSet.getResultValueByName(index, "cardType"); 		//���׿�����0:��ǿ� 1��׼���ǿ� 2:���ǿ�
					String merchantBatNo	= oprSet.getResultValueByName(index, "merchantBatNo"); 	//�̻����κ�
					String merchantComment 	= oprSet.getResultValueByName(index, "merchantComment");//�̻���ע
					String bankComment 		= oprSet.getResultValueByName(index, "bankComment"); 	//���б�ע
					out.print("�����ţ�" + order);
					out.print("<br>");
					out.print("�������ڣ�" + orderDate);
					out.print("<br>");
					out.print("����ʱ�䣺" + orderTime);
					out.print("<br>");
					out.print("���֣�" + curType);
					out.print("<br>");
					out.print("��" + amount);
					out.print("<br>");
					out.print("�������ڣ�" + tranDate);
					out.print("<br>");
					out.print("����ʱ�䣺" + tranTime);
					out.print("<br>");
					out.print("֧������״̬[1:�ɹ�]��" + tranState);
					out.print("<br>");
					out.print("����״̬[0 ���� 1 ��֧�� 2 �ѳ��� 3 �����˻� 4�˻������� 5 ȫ���˻�]��" + orderState);
					out.print("<br>");
					out.print("�����ѣ�" + fee);
					out.print("<br>");
					out.print("������ˮ�ţ�" + bankSerialNo);
					out.print("<br>");
					out.print("�������κţ�" + bankBatNo);
					out.print("<br>");
					out.print("���׿�����[0:��ǿ� 1��׼���ǿ� 2:���ǿ�]��" + cardType);
					out.print("<br>");
					out.print("�̻����κţ�" + merchantBatNo);
					out.print("<br>");
					out.print("�̻���ע��" + merchantComment);
					out.print("<br>");
					out.print("���б�ע��" + bankComment);
					out.print("<p></p>");
				}
			}
		}
	}
%>
<p><a href="Index.htm">������ҳ</a></p>
<p>&nbsp;</p>
</body>
</html>
