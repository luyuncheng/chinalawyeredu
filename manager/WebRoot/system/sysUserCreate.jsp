﻿<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title>管理员新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<jscalendar:head/>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
  }
}
function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}
function checkLoginname(loginname){	

	if((loginname == null) || (loginname.length == 0)){
	    $("#checkloginname").html("不为空且长度不超过15个字符");
		return;
	}
	var now=new Date().getTime();
	var url="../systemajax/checkLoginname.pl";
   $.getJSON(url, { "loginname": loginname,"now":now}, function(json){

     if(json.isrepeat == true){
   		$("#checkloginname").html("<font color='red'>对不起，您输入的帐号【"+json.loginname+"】已经被他人使用，请选择其他名字后再试。</font>");
   		$("#save").attr("disabled",true);
   }else{
	    $("#save").attr("disabled",false);
   }
});
}

function openGroupTree(){
	var popwin=window.open("sysGroupTree.pl","部门列表","toolbar=no,location=no,width=300,height=305,menubar=no,scrollbars=yes,resizable=no,status=no");
	popwin.moveTo((screen.width+200)/2,(screen.height-400)/2);
}

</script>
<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>

</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
					管理员新增
				</td>
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>
    <td>
    <s:form name="form1" action="sysUserCreate" method="post" validate="true">
         <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;   
          <s:actionmessage/>
          <s:actionerror/>
          <s:fielderror/>     
         </td>
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             所属部门:
          </td>
          <td class="tab_content1">
            <s:if test="datavisible.provinceview">
             <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             </s:if>
            <s:else>
              <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.provinceid]"/>-
             
              <s:hidden name="datavisible.provinceid"/>
            </s:else>
                  <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getOffices(this.value)"/>
            </s:if>
           <s:else>
             <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>-
             
              <s:hidden name="datavisible.cityid"/>
            </s:else>
                 <s:if test="datavisible.officeview">
             <s:select name="datavisible.officeid" id="office" list="datavisible.officelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择"/>
              </s:if>
            <s:else>
              <s:hidden name="datavisible.officeid"/>
                <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.officeid]"/>
              
            </s:else>
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             管理员姓名:
          </td>
          <td class="tab_content">
            <s:textfield name="sysUser.username" size="15" maxlength="25" cssClass="text1" required="true"/>
              <span class="hint">不为空且长度不超过7个汉字</span> </td>
        </tr>
        <tr>
            <td align="right" class="tab_content1"> 登录名称: </td>
          <td class="tab_content1">
            <s:textfield name="sysUser.loginname" size="15" maxlength="25" cssClass="text1" onblur="checkLoginname(this.value)" required="true"/>
              <span class="hint" id="checkloginname">不为空且长度不超过15个字符</span> 
              </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 登录密码: </td>
          <td class="tab_content">
            <s:password name="sysUser.password" size="20" maxlength="25" cssClass="text1" required="true"/>
            <span class="hint">不为空且长度不超过20个字符</span>
          </td>
        </tr>
		<tr>
            <td align="right" class="tab_content1"> 重复输入登录密码: </td>
          <td class="tab_content1">
            <s:password name="passagain" size="20" maxlength="25" cssClass="text1"/>
            
          </td>
        </tr>
        		<tr>
            <td align="right" class="tab_content1"> 选择角色: </td>
          <td class="tab_content1">
           <s:radio name="sysUser.sysRole.roleid" list="allroles" listKey="roleid" listValue="rolename"/>
          </td>
        </tr>
        
        
		<tr>
            <td align="right" class="tab_content"> 电话号码: </td>
          <td class="tab_content">
            <s:textfield name="sysUser.officephone" size="25" maxlength="25" cssClass="text1"/>
            </td>
        </tr>
		<tr>
            <td align="right" class="tab_content1"> 手机号码: </td>
          <td class="tab_content1">
            <s:textfield name="sysUser.mobile" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
		  <tr> 
            <td align="right" class="tab_content">EMAIL: </td>
          <td class="tab_content">
            <s:textfield name="sysUser.email" size="40" maxlength="40" cssClass="text1"/>
            </td>
        </tr>
<tr> 
            <td align="right" class="tab_content1">性别: </td>
          <td class="tab_content1">
           <s:select name="sysUser.gender" list="#{'G':'男','M':'女'}"/>
            </td>
        </tr>
        <!-- 
        <tr> 
            <td align="right" class="tab_content">生日: </td>
          <td class="tab_content">
           
            <jscalendar:jscalendar name="sysUser.birthday" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr> -->
        <tr> 
            <td align="right" class="tab_content1">备注信息: </td>
          <td class="tab_content1">
            <s:textarea name="sysUser.comments" rows="4" cols="50" cssClass="textarea1"/>
            </td>
        </tr>
		
        <tr>
          <td height="2" colspan="2">
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:submit value=" 保 存 " cssClass="button" id="save" />&nbsp;
           	&nbsp;
          	<input type="button" value=" 返 回 " onClick="javascript:history.back(-1)" class="button">
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>