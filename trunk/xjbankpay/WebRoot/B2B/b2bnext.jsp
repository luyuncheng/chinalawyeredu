<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@ page import="cn.com.infosec.icbc.ReturnValue,java.io.*"%>
<%  
String APIName="B2B";
String APIVersion="001.001.001.001";
String TranTime=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
String Shop_code="3002EC13358181";
String MerchantURL="http://localhost:8080/ICBC/B2B/notify.jsp";
String ContractNo=TranTime;
String ContractAmt=request.getParameter("ContractAmt");
String Account_cur="001";
String JoinFlag="2";
String SendType="0";
String Shop_acc_num=request.getParameter("Shop_acc_num");
String PayeeAcct=request.getParameter("PayeeAcct");
String transdate="APIName="+APIName+"&APIVersion="+APIVersion+"&Shop_code="+Shop_code+"&MerchantURL="+MerchantURL+"&ContractNo="+ContractNo+"&ContractAmt="+ContractAmt+"&Account_cur="+Account_cur+"&JoinFlag="+JoinFlag+"&SendType="+SendType+"&TranTime="+TranTime+"&Shop_acc_num="+Shop_acc_num+"&PayeeAcct="+PayeeAcct;

String password = "12345678"; //�̻�˽Կ��������

byte[] byteSrc = transdate.getBytes();

char[] keyPass = password.toCharArray();

FileInputStream in = new FileInputStream("/usr/software/apache-tomcat-6.0.18/webapps/icbc/WEB-INF/b2buser.key");
byte[] bkey = new byte[in.available()];
in.read(bkey);
in.close();
byte[] sign=ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass); //��������ǩ��
String Mer_Icbc20_signstr=new String(ReturnValue.base64enc(sign)); //����ǩ������base64����


in = new FileInputStream("/usr/software/apache-tomcat-6.0.18/webapps/icbc/WEB-INF/b2buser.crt");
byte[] bcert = new byte[in.available()];
in.read(bcert);
in.close();
String Cert=new String(ReturnValue.base64enc(bcert)); //��Կbase64����
%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>��������B2B֧��</title>
<style type="text/css">
td{
	font-size:10pt;	
}
</style>
</head>
<body>
<form id="PaymentFormICBC" method="post" action="https://corporbank.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet"><br/>
<!-- �ӿ����� -->
<input type="hidden" name="APIName" value="<%=APIName%>" />
<!-- �ӿڰ汾�� -->
<input type="hidden" name="APIVersion" value="<%=APIVersion%>" />
<!-- �̻����� -->
<input type="hidden" name="Shop_code" value="<%=Shop_code%>" />
<!-- ֧�������Ϣ֪ͨ�����ַ -->
<input type="hidden" name="MerchantURL" value="<%=MerchantURL%>" />
<!-- ������ -->
<input type="hidden" name="ContractNo" value="<%=ContractNo%>" />
<!-- ������� -->
<input type="hidden" name="ContractAmt" value="<%=ContractAmt%>" />
<!-- ֧������ -->
<input type="hidden" name="Account_cur" value="<%=Account_cur%>" />
<!-- ����������־ -->
<input type="hidden" name="JoinFlag" value="<%=JoinFlag%>" />
<!-- ����ǩ������ -->
<input type="hidden" name="Mer_Icbc20_signstr" value="<%=Mer_Icbc20_signstr%>" />
<!-- �̳�֤������ -->
<input type="hidden" name="Cert" value="<%=Cert%>" />
<!-- ����������� -->
<input type="hidden" name="SendType" value="<%=SendType%>" />
<!-- ��������ʱ�� -->
<input type="hidden" name="TranTime" value="<%=TranTime%>" />
<!-- �̳��˺� -->
<input type="hidden" name="Shop_acc_num" value="<%=Shop_acc_num%>" />
<!-- �տλ�˺� -->
<input type="hidden" name="PayeeAcct" value="<%=PayeeAcct%>" />
<table>
<tr><td>������</td><td><%=ContractNo%> </td></tr> 
<tr><td>�������</td><td><%=ContractAmt%></td></tr> 
<tr><td>�̳��˺�</td><td><%=Shop_acc_num%></td></tr> 
<tr><td>�տλ�˺�</td><td><%=PayeeAcct%> </td></tr> 
<tr><td colspan="2" align="center"><input type="button" onclick="history.go(-1)" value="��һ��"/>&nbsp;&nbsp;<input type="submit" value="ȷ��"/></td></tr>
</table>
</form>
</body>
</html>
