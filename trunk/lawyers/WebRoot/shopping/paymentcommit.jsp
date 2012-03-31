<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="paybean" scope="request" class="com.changpeng.shopping.util.PaymentBean" />
<jsp:useBean id="pay_url" scope="request" class="java.lang.String" />
<html>
<head>

<title>${webpara.sysname}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>

<script language="javascript" type="text/javascript">
 
var issubmit=false;
function submitform (){	
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
  if(!issubmit){//���û���ύ,ֱ�ӹرմ��ڵĻ�
  
    $.getJSON("../commonajax/ajaxlogout.pl", {"now":new Date().getTime()}, function(resp){
      }
    ); 
  }
}

</script>
</head>
<body>

<div class="header">
  <form name="form1" method="post" action="common/logout.pl">	
  <div class="logo left"><img src="${resourcePath}${webpara.topbarpic}" width="234" height="51" /></div>
  <div class="denglu right">
  	${lawyer.lawyername}��ʦ ���ã���ӭ����¼��ѵƽ̨��&nbsp;&nbsp; 
  	<a href="../index/index.pl">��ҳ</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="common/passwdChange!input.pl">�޸�����</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">�˳�</a>
  </div>
  </form>
</div>
<div class="blank15px"></div>
<div class="nav2">
	<ul>
	  <li><a href="../index/index.pl"  >��ҳ</a></li>
	  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1">ѡ������</a></li>
	  <li><a href="../shopping/shoppaidList.pl">��ѡ���Ŀγ�</a></li>
	  <li><a href="../lessons/lessonsList.pl?lessonstyle=1">��ѵ֪ͨ</a></li>
	  <li><a href="../jifen/jifenQuery.pl" >�ҵ�ѧ��</a></li>
	  <li><a href="../shopping/shopfavoritesList.pl">�ղؼ�</a></li>
	  <li><a href="../shopping/shopcartList.pl" >���ﳵ</a></li>
	  <li><a href="../shopping/shopOrderList.pl" class="current">�ҵĶ���</a></li>
	  <li><a href="../lawyers/officeChangeApplyList.pl">ת������</a></li>
	  <li><a href="../lawyers/lawyersEditSelf!input.pl" >������Ϣ</a></li>
	  <li><a href="../articles/notifyList.pl?type=1">ϵͳ��Ϣ</a></li>
	</ul>
</div>
<div class="gml">��ǰλ�ã�<a href="index/index.pl">��ҳ</a>----<strong>�ҵĶ���</strong></div>
<div class="con">
	<div class="con_left3 left">
			<div class="con_left3_title">�ҵĶ���</div>  
     		
     		<div class="dan">
				<ul>
					<li><a href="../shopping/shopOrderList.pl?viewtype=0">ȫ��</a></li>
		       		<li><a href="../shopping/shopOrderList.pl?viewtype=2">�ѽ���</a></li>
		       		<li><a href="../shopping/shopOrderList.pl?viewtype=1">δ����</a></li>
		     	</ul>
     		</div>
  	</div>
  	<div class="con_right3 left">
    <div class="con_right2_title"><h2>֧����Ϣ</h2></div>
    <div class="star3">
	   <form name="payment" action="<%= pay_url %>"  method="post"  target="_blank">
<%
	String  MerId = paybean.getMerId();
	String  OrdId = paybean.getOrdId();
	String  TransDate = paybean.getTransDate();
	String  TransType = paybean.getTransType();
	String  TransAmt = paybean.getTransAmt();
	String  CuryId = paybean.getCuryId();
	String  GateId = paybean.getGateId();
	String  Version	= paybean.getVersion();
	String  ChkValue = paybean.getChkValue();
	String  BgRetUrl = paybean.getBgRetUrl();
	String  PageRetUrl = paybean.getPageRetUrl();
	String  Priv1 = paybean.getPriv1();
	
	//20100304����Ҫ����
	String  ClientIp = paybean.getClientIP();
	//20080515����Ҫ����
	String  CountryId = paybean.getCountryId();
	String  TimeZone = paybean.getTimeZone();
	String  TransTime = paybean.getTransTime();
	String  DSTFlag = paybean.getDSTFlag();
	String  ExtFlag = paybean.getExtFlag();
	String  Priv2 = paybean.getPriv2();
	
	
%>	

    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  				<tr>
    				<td>
    				<span id="right_lblist">
    					<TABLE class='tab_list' width=100% cellSpacing=1 cellPadding=1 align=center bgColor=#b5b5b5 border=0>
    						<TR bgColor=#649fbc nowrap></tr>
    						<tr bgcolor=#f3f3f3>
    							
    							<td align=center height=30 nowrap="nowrap">
    								�γ�����
    							</td>
    							<td align=center>������</td>
    							<td align=center >ѧ��</td>
    							<td align=center>����ʱ��</td>
    							<td align=center width="65px;">�۸�(��)</td>
    							
    						</tr>
    						<s:set name="allprice" value="0"/>
    						<s:iterator value="page.items" status="stat">
    						<tr bgcolor=#f3f3f3>
    							
    							<td align=left height=30 nowrap="nowrap">&nbsp;&nbsp;${title}</td>
    							<td align=center>${teachers}</td>
    							<td align=center >${xuefen}</td>
    							<td align=center><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
    							<td align=right>${price}&nbsp;&nbsp;&nbsp;&nbsp;</td> 
    							
    							<s:set name="allprice" value="#allprice+price"/>  							
    						</tr>
    						</s:iterator>
    					</TABLE>   
    					</span> 			
   					</td>
  				</tr>
			</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">		
			
			<tr>
				<td width="100px;" height="30px;" align="right" >
					�̻���:
				</td>

				<td width="200px;">
                     <%= MerId %>
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					������:
				</td>

				<td>
                     <%= OrdId %> 
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					��������:
				</td>

				<td>
                     <%= TransDate %>
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					��������:
				</td>

				<td>
                     ����֧��
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					���ױ���:
				</td>

				<td>
                     �����
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					֧�����:
				</td>

				<td>
                     <%= TransAmt %> &nbsp;<font color=gray>(12λ���֣�����Ĭ�Ͻ��Ϊ1��)</font>
                </td>
			</tr>
			<tr>
				

				<td colspan="2" align="right">
                    <input type='button' name='v_action' value='ȷ��֧��' onClick='document.payment.submit()' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
			</tr>
			
			

		</table>	

<%
	
%>
	<hr/>
<input type=hidden name="MerId" value="<%=MerId%>" />
<input type=hidden name="OrdId" value="<%=OrdId%>"/>
<input type=hidden name="TransAmt" value="<%=TransAmt%>" />
<input type=hidden name="CuryId" value="156" />
<input type=hidden name="TransDate" value="<%=TransDate%>" />
<input type=hidden name="TransType" value="<%=TransType%>" />
<input type=hidden name="Version" value="<%=Version%>" />
<input type=hidden name="BgRetUrl" value="<%=BgRetUrl%>" />
<input type=hidden name="PageRetUrl" value="<%=PageRetUrl%>" />
<input type=hidden name="Priv1" value="<%=Priv1%>" />
<input type=hidden name="ChkValue" value="<%=ChkValue%>" />
<input type=hidden name="GateId" value="<%=GateId%>" />
<input type=hidden name="ClientIp" value="<%=ClientIp%>" />

<input type=hidden name="CountryId" value="<%=CountryId%>" />
<input type=hidden name="TransTime" value="<%=TransTime%>" />
<input type=hidden name="TimeZone" value="<%=TimeZone%>" />
<input type=hidden name="DSTFlag" value="<%=DSTFlag%>" />
<input type=hidden name="ExtFlag" value="<%=ExtFlag%>"/>
<input type=hidden name="Priv2" value="<%=Priv2%>"/>

		
</form>






    
    </div>
    
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  �й���ʦ��ѵ��  ��Ȩ����    ���������<a href="http://www.cpsoft.cn/" target="_blank">�������</a></br>
������ţ���ICP��05082150��
</div>
</body>
</html>





<title>ChinaPay ��������֧��</title>
</head>
<br />
<p class="menu">
	
</p>



<body>

</body>
</html>