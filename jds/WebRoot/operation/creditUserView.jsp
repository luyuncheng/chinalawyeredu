<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
/**
 * <p>功能： 查看信用卡用户信息</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<style type="text/css">
	
		body,td,th {
			font-size: 12px;
			line-height:16px;
			color: #000000;
		}
		.tr{border-left:1px solid #000000; }
		.tr2{border-left:1px solid #000000; border-top:1px solid #000000;}
		.td{ border-right:1px solid #000000; border-bottom:1px solid #000000;}
		-->
		#layer1 
		{
			position: absolute;
			left:380px;
			top:150px;
			width:250px;
			background-color:#f0f5FF;
			border: 1px solid #000;
			z-index: 50;
		}
		#layer1_handle 
		{
			background-color:#5588bb;
			padding:2px;
			text-align:center;
			font-weight:bold;
			color: #FFFFFF;
			vertical-align:middle;
		}
		#layer1_content 
		{
			padding:5px;
		}
		#close
		{
			float:right;
			text-decoration:none;
			color:#FFFFFF;
		}
		h1, h2
		{
			font-size:20px;
		}
.jqDrag {
  width: 100%;
  cursor: move;
}
.jqDnR {	
    z-index: 3;
    position: relative;   
    width: 400px;
    font-size: 0.77em;
    color: #618d5e;
    margin: 5px 10px 10px 10px;
    background-color: #EEE;
    border: 1px solid #CCC;
}
	</style>
<link rel="stylesheet" href="../js/thickbox.css" type="text/css" media="screen" />
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script> 
<script type="text/javascript" src="../js/thickbox-compressed.js"></script> 
<script type="text/javascript" src="../js/jqDnR.js"></script> 
<script type="text/javascript" src="../js/callCenter.js"></script> 
<script language="javascript">

$(document).ready(function(){
	var call=false;
	$("#layer1").hide();
	$("#showmsg").hide();	
	$("#jqdrag").hide();
	$('#savelog').click(function(){
		$("#jqdrag").show();
		$("#logFrame").get(0).src="creditlogCreate2!input.action?bankid=${creditcard.bankid}&credittaskid=${credittaskid}&addressid="+$("#addressidid").val();
	});
	$('#closelog').click(function(){
		$("#jqdrag").hide();
	});		
	$('#call').click(function()
	{	
		$("#showmsg").html("");
		$("#layer1").show();
		//alert($('input[@name=phoneradio][@checked]').val());
	});
	
	$('#close').click(function()
	{	if(call)
			Release('${curuser.userno}','${curuser.bqqno}');	
		$("#layer1").hide();
		call=false;
	});
	
	$('#closeButton').click(function()
	{	
		if(call)
			Release('${curuser.userno}','${curuser.bqqno}');		
		$("#layer1").hide();
		call=false;
	});
	
	$('#callPhone').click(function()
	{	  		
		  
		if(!isTEL($("#phone").val())){
			alert("请输入正确的呼叫号码");
		}else{
			//呼叫前发起挂断(暂停)操作
			Wrap('${curuser.userno}','${curuser.bqqno}');	
			call=true;
			//等待600毫秒后再发起呼叫操作
			window.setTimeout("Dail($('#phone').val(),'${curuser.userno}','${curuser.bqqno}')",600);
		}
	});
	$('#jqdrag').jqDrag();
});
function setPhone(val,addressid){
	$("#phone").val(val);
	$("#addressidid").val(addressid);
}
function phoneDelete(phoneid){
	if(confirm("是否确认删除？")){
		$.ajax({
	           type:"GET",
	           url:'../addressAjax/addressDelete.action',
	           dataType:"json",
	           data:"addressid="+phoneid,
	           success:function(data){
			    if(data.result){
			      	alert("号码删除成功。");
			      	location.reload();
			      }
	           }
	    });  
    }
}
function logDelete(logid){
	if(confirm("是否确认删除？")){
		$.ajax({
           type:"GET",
           url:'../operationAjax/creditlogAjaxDelete.action',
           dataType:"json",
           data:"logid="+logid,
           success:function(data){
		    if(data.result){
		      	alert("日志删除成功。");
		      	location.reload();
		      }
           }
   		 });  
	}
}
function logEdit(logid){
	$("#jqdrag").show();
	//$("#logFrame").get(0).src="creditlogEdit2!input.action?logid="+logid;
	//document.getElementById("logFrame").location.reload();
	logFrame.location.href="creditlogEdit2!input.action?logid="+logid;
}
function canlink(val){
	if(confirm("是否修改?")){
		$.ajax({
           type:"GET",
           url:'../operationAjax/taskcanLink.action',
           dataType:"json",
           data:"credittaskid=${credittask.credittaskid}&canlink="+val+"&"+new Date().getTime(),
           success:function(data){
		    if(data.result){
		      	alert("修改成功。");
		      	//location.reload();
		      }
           }
   		});  
	}
}
function editComments(){
	$("#comments").hide();
	$("#cedit").show();
}
function cancelComments(){
	$("#comments").show();
	$("#cedit").hide();
}
function saveComments(){
	$.ajax({
         type:"post",
         url:'../operationAjax/editComments.action',
         dataType:"json",
         data:"creditcardid=${creditcard.creditcardid}&comments="+$("#bcomments").val(),
         success:function(data){
	    if(data.result){
	      	$("#comments").html($("#bcomments").val());
			$("#comments").show();
			$("#cedit").hide();
	      }
        }
   	});  
	
}

</script>

</HEAD>
<BODY >
<fieldset style="text-align:center;width:620px;">
	<legend>
		<span style="font:bold">联系电话【已还<font color='red'>${creditcard.refee}<s:if test="repaytime!=null">,最后还款时间:${repaytime}</s:if></font>】</span>
	</legend>
	<table align="center" width="80%"  border=0 align=center style="FONT-SIZE:12px;">
		<TR class="listheadline"><td>&nbsp;</td><td>号码</td><td>姓名</td><td>说明</td><td>编辑</td><td>删除</td></tr>
		<s:iterator value="addressList" status="stat">
			<TR class=listline>
				<td><input type="radio" name="phoneradio" value="${phone}" onclick="setPhone(this.value,'${addressid }')"></td>
				<td><div id="phone${stat.index}">${phone}</div></td>
				<td>${username}</td>
				<td>${comments}</td>
				<td>
					<a href="../address/addressEdit!input.action?addressid=${addressid}&keepThis=true&TB_iframe=true&height=250&width=400" title="号码编辑" class="thickbox" >编辑</a></td>
				<td><a href="#" onclick="phoneDelete(${addressid})">删除</a></td>
			</tr>
		</s:iterator>	
		
		<TR class="listline"><td colspan="6">是否能联系上:<s:select name="credittask.canlink" list="#{'y':'是','n':'否','yy':'联系亲友','nn':'无法接通'}" onchange="canlink(this.value)"/></td></tr>
	</table>
	<div align="center" style="padding-top:10px"> 
		<a href="../address/addressCreate!input.action?oprflag=1&oprid=${creditcard.creditcardid}&keepThis=true&TB_iframe=true&height=250&width=400" title="号码新增" class="thickbox" >
			<input type=button class="mask" value="新增" id="addPhone">&nbsp;&nbsp;
		</a>
		<input type=button class="mask" value="呼叫" id="call"> &nbsp;&nbsp;
		<!-- 
		<input type=button class="mask" value="挂断"> &nbsp;&nbsp;
		
		<a href="creditlogCreate2!input.action?credittaskid=${credittaskid}&keepThis=true&TB_iframe=true&height=190&width=420" title="记录日志" class="thickbox" >
			<input type=button class="mask" value="记录日志">
		</a> -->
		
		 <input type=button class="mask" value="记录日志" id="savelog">
		 <input type=hidden value="0" id="addressidid">
		 
		&nbsp;&nbsp;
		<a href="repaylogList.action?oprflag=1&creditcardid=${creditcard.creditcardid}&keepThis=true&TB_iframe=true&height=200&width=500" title="还款记录" class="thickbox" >
			<input type=button class="mask" value="还款记录">
		</a> 
		&nbsp;&nbsp;
		<a href="../address/addressList.action?keepThis=true&TB_iframe=true&height=200&width=500" title="号码查询" class="thickbox" >
			<input type=button class="mask" value="号码查询">
		</a>  
		&nbsp;&nbsp;
		<a href="../sms/smsSend!input.action?keepThis=true&TB_iframe=true&height=200&width=500" title="发送短信" class="thickbox" >
			<input type=button class="mask" value="发送短信">
		</a>        
	</div>       
</fieldset>
<table width="80%">
	<tr>
		<td>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="20" align="center" >
    	<a style="font:bold;color:red" href="exportLOG.action?creditcardid=${creditcard.creditcardid}" title="导出催收记录">信用卡迟缴催收记录</a>
    	
			约定还款:
			<a href="setPaydate!input.action?credittaskid=${credittask.credittaskid}&keepThis=true&TB_iframe=true&height=250&width=400" title="约定还款日期设置" class="thickbox" >		
			<font color="red">
			<s:if test="credittask.paydate==null">设置</s:if>
			<s:else>${credittask.paydate}</s:else>
			</font>
			</a>
    </td>
  </tr>
</table>

<table width="620px" border="1" align="center" cellpadding="0" cellspacing="0" >

  <tr>
    <td width="5%" rowspan="5" align="left" bgcolor="#FFFFFF" class="td">持卡人资料</td>
    <td width="31%" align="left" bgcolor="#FFFFFF" class="td">姓名：${creditcard.username}</td>
    <td width="31%" align="left" bgcolor="#FFFFFF" class="td">
    	透支金额：
    	<s:if test="creditcard.curcnfee!=null">${creditcard.curcnfee}</s:if><s:else>0</s:else>（人民币）<br/>
    	<s:if test="creditcard.curusafee!=null">${creditcard.curusafee}</s:if><s:else>0</s:else>（美元）<br/>
    	<s:if test="creditcard.curhkfee!=null">${creditcard.curhkfee}</s:if><s:else>0</s:else>（港元）<br/>
    	<s:if test="creditcard.cureurfee!=null">${creditcard.cureurfee}</s:if><s:else>0</s:else>（欧元）<br/>
    </td>
    <td width="31%" align="left" colspan="2" bgcolor="#FFFFFF" class="td">信用额度：${creditcard.maxfee}</td>
  </tr>
  <tr>
    <td align="left" bgcolor="#FFFFFF" class="td">卡号：${creditcard.creditcard}</td>
    <td align="left" bgcolor="#FFFFFF" class="td">身份证号：${creditcard.idcard}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">透支天数：<s:property value="@com.changpeng.operation.util.OperationUtil@consignflagMap[creditcard.consignflag]"/></td>
  </tr>
  <tr>
    <td colspan="2" align="left" bgcolor="#FFFFFF" class="td">住址：${creditcard.homeaddr}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">宅电：${creditcard.homephoneold}</td>
  </tr>
  <tr>
    <td colspan="2" align="left" bgcolor="#FFFFFF" class="td">单位名称：${creditcard.company}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">单电：${creditcard.workphoneold}</td>
  </tr>
  <tr>
    <td colspan="2" align="left" bgcolor="#FFFFFF" class="td">单位地址：${creditcard.compaddr}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">手机:${creditcard.mobileold}</td>
  </tr>

 	<tr>
 	  <td width="10%" rowspan="2" align="left" bgcolor="#FFFFFF" class="td">其他卡号资料</td>
 	  <td width="30%" align="left" bgcolor="#FFFFFF" class="td">卡号：</td>
 	  <td width="30%" align="left" bgcolor="#FFFFFF" class="td">欠款金额：</td>
 	  <td width="30%" align="left" colspan="2" bgcolor="#FFFFFF" class="td">其他电话：</td>
  </tr>
 	<tr>
    <td colspan="4" align="left" bgcolor="#FFFFFF" class="td">EMAIL:${creditcard.email}</td>
  </tr>

     <tr>
       <td width="10%" align="left" bgcolor="#FFFFFF" class="td">担保人/联系人</td>
       <td width="30%" align="left" bgcolor="#FFFFFF" class="td">姓名：${creditcard.cautioner}</td>
       <td width="30%" align="left" bgcolor="#FFFFFF" class="td">身份证号：</td>
       <td width="30%" align="left" bgcolor="#FFFFFF" colspan="2" class="td">联系电话：${creditcard.cauworkphone}&nbsp;${creditcard.cauhomephone}&nbsp;${creditcard.caumobile}</td>
     </tr>
     <tr>
       <td width="10%" align="left" bgcolor="#FFFFFF" class="td">备注</td>
       <td width="78%" align="left" bgcolor="#FFFFFF" class="td" colspan="3">
	   	<div  id="comments" style="float:left;color:red">${creditcard.comments}</div>
	   	<div  id="cedit" style="float:left;display:none">
	   		<input type="text" value="${creditcard.comments}" id="bcomments" style="height:14pt;" size="45"/>
	   		<input type="button" value="保存" style="height:14pt" onclick="saveComments()">
	   		<input type="button" value="取消" style="height:14pt" onclick="cancelComments()">
	   	</div>
	   </td>
      <td width="12%" align="left" bgcolor="#FFFFFF" class="td">
	   	<a href="#" onclick="editComments()"><font color="red">记录</font></a>
	   </td>
     </tr>
   <tr>
    <td width="10%" align="left" bgcolor="#FFFFFF" class="td">银行资料</td>
    <td width="21%" align="left" bgcolor="#FFFFFF" class="td">经办网点：</td>
    <td width="23%" align="left" bgcolor="#FFFFFF" class="td">经办人：</td>
    <td width="23%" align="left" bgcolor="#FFFFFF" class="td">电话：</td>
    <td width="23%" align="left" bgcolor="#FFFFFF" class="td">代码:${creditcard.guiyuanno}</td>
  </tr>

     <tr>
       <td width="10%" rowspan="<s:property value="logList.size+1"/>" align="left" bgcolor="#FFFFFF" class="td">催收记录</td>
       <td align="center" bgcolor="#FFFFFF" class="td">日期</td>
       <td align="center" bgcolor="#FFFFFF" class="td" colspan="4">催收情况</td>
     </tr>
     <s:iterator value="logList" status="stat">
      <tr>
        <td width="18%" align="center" bgcolor="#FFFFFF" class="td">${logtime}</td>
       	<td width="72%" align="left" bgcolor="#FFFFFF" colspan="4" class="td">
       	&nbsp;${comments}
       	&nbsp;<a href="#" onclick="logEdit(${logid})"><font color='red'>编辑</font></a>
       	&nbsp;<a href="#" onclick="logDelete(${logid})"><font color='red'>删除</font></a>
       	</td>
  	  </tr>
     </s:iterator>

   <tr>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">是否结清</td>
    <td width="20%" align="left" bgcolor="#FFFFFF" class="td">是□    否□</td>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">损失原因</td>
    <td width="50%" align="left" bgcolor="#FFFFFF" colspan="3" class="td">冒用、伪造证件□   恶意透支□   无法联系□   经济困难□</td>
  </tr>
  <tr>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">催收建议</td>
    <td width="85%" align="left" bgcolor="#FFFFFF" class="td" colspan="5">继续开卡□              停用卡□               额度降半□</td>
  </tr>
  <tr>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">户籍：</td>
    <td width="85%" align="left" bgcolor="#FFFFFF" class="td" colspan="5">上门情况：</td>
  </tr>
</table>
		
 <div align="center" style="padding-top:10px"> 
		
	</div>          
 <div id="layer1">
	<div id="layer1_handle">			
		<a href="#" id="close">[ x ]</a>
		呼叫中心
	</div>
	<div id="layer1_content">
		
			请选择呼叫号码:<br>
			<input type='text' name='phone' id='phone'>
			<div id="showmsg" style="color:red"></div>
			<div align="center" style="padding-top:8px"><input type="button" value="确定" id="callPhone"/>&nbsp;&nbsp;<input type="button" id="closeButton" value="挂断" /></div>
	
	</div>
</div>           
<div id="jqdrag" class="jqDnR">
  <div style="height:20px;width:400px;text-align:right"><a href="#" id="closelog" style="float:right;FONT-SIZE:12px;color:blue">关闭</a></div>
  <iframe src="#" id="logFrame" name="logFrame" height="250px" width="598px"/>
</div>             
</BODY>
</HTML>
