<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>�˿���ϸ��ѯ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String begDate = request.getParameter("begDate");
	String endDate = request.getParameter("endDate");
	int refundtype = 99;
	try{
		refundtype = Integer.parseInt(request.getParameter("refundtype"));
	}
	catch(Exception xcp){refundtype = 0;}
	String order = request.getParameter("order");
	int flag = 0;
	try{
		flag = Integer.parseInt(request.getParameter("flag"));
	}
	catch(Exception xcp){flag = 0;}
	int begIndex = 0;
	try{
		begIndex = Integer.parseInt(request.getParameter("begIndex"));
	}
	catch(Exception xcp){begIndex = 0;}
	String code ;
	String err ;
	String msg ;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
	if (ret != 0){  //��ʼ��ʧ��
		out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
	}
	else {
		BOCOMB2COPReply rep = client.queryRefund( begDate, endDate, refundtype,order,flag,begIndex) ;//�˿���ϸ��ѯ
		if (rep == null){  
			err = client.getLastErr();
			out.print("���״�����Ϣ��"+err + "<br>");
		}
		else{
			code = rep.getRetCode(); 		//�õ����׷�����
			msg = rep.getErrorMessage();	//�õ����׷�����Ϣ
			out.print("���׷����룺"+code + "<br>");
			out.print("���״�����Ϣ��"+msg + "<br>");
			if("0".equals(code)){//��ʾ���׳ɹ�
				int num ;
				String index ;
				OpResult opr = rep.getOpResult();
				String total = opr.getValueByName("totalNumber"); 	//�õ����ؼ�¼����
				OpResultSet oprSet = rep.getOpResultSet();
				int iNum = oprSet.getOpresultNum();
				int iIndex =  0;
				int iTotal =  Integer.parseInt(total);
				out.print("�ܽ��׼�¼����");
				out.print(total);
				out.print("<br>���η��ؼ�¼����");
				out.print(iNum);
				out.print("<br>------------------------<br>");
				for (iIndex=0;iIndex<=iNum-1;iIndex++) {
					String order1 			= oprSet.getResultValueByName (iIndex,"order");			//������
					String orderDate 		= oprSet.getResultValueByName (iIndex,"orderDate");		//��������
					String curType 			= oprSet.getResultValueByName (iIndex,"curType"); 		//����
					String amount 			= oprSet.getResultValueByName (iIndex,"amount"); 		//���
					String refundType 		= oprSet.getResultValueByName (iIndex,"refundType"); 	//�˿�����
					String state  			= oprSet.getResultValueByName (iIndex,"tranState"); 	//�˿��״̬
					String fee 				= oprSet.getResultValueByName (iIndex,"fee"); 			//������
					String merchantComment 	= oprSet.getResultValueByName(iIndex, "merchantComment");//�̻���ע
					String bankComment 		= oprSet.getResultValueByName(iIndex, "bankComment"); 	//���б�ע
				
					out.print("�����ţ�"+order1);
					out.print("<br>");
					out.print("�������ڣ�"+orderDate);
					out.print("<br>");
					out.print("���֣�"+curType);
					out.print("<br>");
					out.print("�˿��"+amount);
					out.print("<br>");
					out.print("�˿����ͣ�"+refundType);
					out.print("<br>");
					out.print("�˿��״̬:"+state);
					out.print("<br>");
					out.print("������:"+fee);
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
