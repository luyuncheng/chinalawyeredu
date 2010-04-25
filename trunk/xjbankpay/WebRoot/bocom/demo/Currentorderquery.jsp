<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>����������ѯ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String begTime; 	//��ʼʱ��[yyyyMMddHHmmss]
	String endTime; 	//����ʱ��[yyyyMMddHHmmss]
	int flag = 0; 		//����״̬ 0 ���� 1 ��֧�� 2 �ѳ��� 3 �����˻� 4�˻������� 5 ȫ���˻� 
	int begIndex = 0; 	//��ʼ�����ţ���ΪNULL,Ĭ����0
	String begOrder; 	//��ʼ�����ţ���ΪNULL
	String endOrder; 	//���������ţ���ΪNULL
	int sortField = 1;	//�����ֶ�1�������ţ�2����3��ʱ��
	int sortOrder = 1; 	//1������2������
	String code; 
	String err ;
	String msg ;
	begTime = request.getParameter("begTime");
	endTime = request.getParameter("endTime");
	try{
		flag = Integer.parseInt(request.getParameter("flag"));
	}
	catch(Exception xcp){flag = 0;}
	try{
		begIndex = Integer.parseInt(request.getParameter("begIndex"));
	}
	catch(Exception xcp){begIndex = 0;}
	begOrder = request.getParameter("begOrder");
	endOrder = request.getParameter("endOrder");
	try{
		sortField = Integer.parseInt(request.getParameter("sortField"));
	}
	catch(Exception xcp){sortField = 1;}
	try{
		sortOrder = Integer.parseInt(request.getParameter("sortOrder"));
	}
	catch(Exception xcp){sortOrder = 1;}
	
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");//�ô���ֻ�����һ��
	if (ret != 0){  //��ʼ��ʧ��
		out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.queryCurOrder(begTime,endTime,flag,begIndex,begOrder,endOrder,sortField,sortOrder);
		if (rep == null){  
			err = client.getLastErr();
			out.print("���״�����Ϣ��"+err + "<br>");
		}else{
			code = rep.getRetCode() ;//�õ����׷�����
			msg = rep.getErrorMessage();
			out.print("���׷����룺"+code + "<br>");
			out.print("���״�����Ϣ��"+msg + "<br>");
			if("0".equals(code)){//��ʾ���׳ɹ�
				String num ;
				int iNum = 0;
				int index =0;
				OpResult opr = rep.getOpResult();
				String total = opr.getValueByName("totalNumber"); //�õ����ؼ�¼����
				OpResultSet oprSet = rep.getOpResultSet(); 
				iNum = oprSet.getOpresultNum();
				out.print("�ܽ��׼�¼����");
				out.print(total);
				out.print("<br>���η��ؼ�¼����");
				out.print(iNum);
				out.print("<br>------------------------<br>");
				for (index =0;index<=iNum-1;index++){
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
