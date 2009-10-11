<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${sysName }-事务所新增修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"time":new Date().getTime()}, function(json){
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
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"time":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}

</script>

</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
				事务所新增修改
				</td>
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>		
    <td>
    <s:form name="form1" action="theOfficeCreateEdit" method="post" validate="true">
     <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">
          	&nbsp;         
          </td>
        </tr>
      
     
      <tr>
          <td align="right" width="20%" class="tab_content1">
             所属律协:
          </td>
          <td class="tab_content1">
          <s:if test="!isedit">

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
             <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>
             
              <s:hidden name="datavisible.cityid"/>
            </s:else>
               
               </s:if>
               <s:else><!-- 不能修改所属律协 -->
                  <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.provinceid]"/>-
              <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>
            <s:hidden name="datavisible.provinceid"/>
              <s:hidden name="datavisible.cityid"/>
               </s:else>
            <s:hidden name="isedit"/>
          </td>
        </tr>
       
        <tr>
          <td align="right" class="tab_content">
       事务所名称：
          </td>
          <td class="tab_content">
            <s:textfield name="sysGroup.groupname" size="30" maxlength="50" cssClass="text1"/>
            <span class="hint">必须为有效字符并长度不超过25个汉字</span>
          </td>
        </tr>
       
          <tr>
          <td align="right" class="tab_content1">
             事务所执业证号:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.groupenname" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
       
           
     
        <tr>
          <td align="right" class="tab_content1">
             联系人:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.contacter" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             联系电话:
          </td>
          <td class="tab_content">
            <s:textfield name="sysGroup.phone" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
                <tr>
          <td align="right" class="tab_content1">
             传真:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.fax" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
          <tr>
          <td align="right" class="tab_content1">
             地址:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.address" size="35" maxlength="30" cssClass="text1"/>
          </td>
        </tr>
        <tr>
          <td align="right"   class="tab_content">
           备注信息:
          </td>
          <td class="tab_content">
             <s:textarea name="sysGroup.comments" cssClass="textarea1" cols="45" rows="4"/>
          
          </td>
        </tr>
        <tr>
          <td height="5" colspan="2">
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<input type="submit" value=" 保 存 " class="button">&nbsp;
           	<input type="reset" value=" 重 置 " class="button">&nbsp;
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