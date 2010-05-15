<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<html>
<head>
<title>${sysName }-补登积分</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<script language="javascript">
	function isupload(val){
		if(val==true){
			document.getElementById("upload").style.display="";
			document.getElementById("show").style.display="";
			document.getElementById("input").style.display="none";
		}else{
			document.getElementById("upload").style.display="none";
			document.getElementById("show").style.display="none";
			document.getElementById("input").style.display="";
		}
	}
	function alertError(field, error){
  		alert(error);
  		field.focus();
	}
	function isFloat(val){ 
	  var parten = /^\d+(\.\d+)?$/; 
	  if(parten.exec(val)){ 
	    return true;
	  }else{ 
	    return false;
	  } 
	}
    function validateForm_jifenbudeng() {
        form = document.getElementById("jifenbudeng");
		var errors = false;
        if (form.elements['budeng.title']) {
            field = form.elements['budeng.title'];
            var error = "请输入内容标题!";
            if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
                alertError(field, error);
            errors = true;
            return !errors;
            }
        }
        // field name: ware.warename
        // validator name: stringlength
        if (form.elements['budeng.title']) {
            field = form.elements['budeng.title'];
            var error = " 内容标题长度必须小于22个汉字或45个字母!";
            if (field.value != null) {
                var value = field.value;
                    //trim field value
                    while (value.substring(0,1) == ' ')
                        value = value.substring(1, value.length);
                    while (value.substring(value.length-1, value.length) == ' ')
                        value = value.substring(0, value.length-1);
                if ((-1 > -1 && value.length < -1) ||
                    (100 > -1 && value.length > 45)) {
                    alertError(field, error);
		            errors = true;
		            return !errors;
                }
            } 
        }
        
        if (form.elements['budeng.budengdate']) {
            field = form.elements['budeng.budengdate'];
            var error = "请选择积分日期!";
            if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
                alertError(field, error);
            errors = true;
            return !errors;
            }
        }
        
        // field name: budeng.xuefen
        // validator name: double
        if (form.elements['budeng.xuefen']) {
            field = form.elements['budeng.xuefen'];
            var error = "学分必须为数字!";
            if (!isFloat(field.value)) {
	             alertError(field, error);
			     errors = true;
			     return !errors;
            }
        }
       
     //  if (form.elements['beupload']) {
       /*     field = form.elements['beupload'];
            
            if(field.checked){
            	var upload = form.elements['uploadfile'];
            	filename=upload.value.toLowerCase();
            
            	index=filename.lastIndexOf(".");
            	endfix="";
            	if(index>0)
					endfix=filename.substring(index+1);
				var error = "上传文件必须为文本文件!";	
				
				if(endfix!="txt"&&endfix!="text"){
					alertError(field, error);
				    errors = true;
				    return !errors;
				}
            }else{*/
            	field = form.elements['budeng.lawerno'];
	            var error = "请输入律师执业证号!";
	            if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
	                alertError(field, error);
	            errors = true;
	            return !errors;
	            }
        //    }
        //}
       return !errors;
      // return false;
    }
</script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
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
</script>
<script language="javascript"> 

function islocalclick(obj){
  if(obj.checked){
    $("#locallisttr").show();
    $("#titleidtr").hide();
       $("#budengdateid").attr("readonly",true);
       $("#applytitleid").attr("readonly",true);
       $("#xuefenid").attr("readonly",true);
       var cityid=$("#city").val();
      $("#budenglessonid")[0].length=0;
  var _o=new Option('请选择',0);
  $("#budenglessonid")[0].options.add(_o);  
  if(cityid!=0){
     $.getJSON("../lessonajax/getLocalLessonsByCityId.pl", { "cityid": cityid,"now":new Date().getTime()}, function(json){
     for(var k in json.locallessons)  
     {     
        var _o=new Option(json.locallessons[k.toString()],k);
		$("#budenglessonid")[0].options.add(_o);  
     }
}); 
  }   
       
  }
  else{
      $("#locallisttr").hide();
      $("#titleidtr").show();
       $("#budengdateid").attr("readonly",false);
       $("#applytitleid").attr("readonly",false);
       $("#xuefenid").attr("readonly",false);
  }
}
function selectlocal(lessonid){
  if(lessonid==0){
    alert("请选择您需要补登的对应现场课程");
  }else{
     if(lessonid!=0){
       $.getJSON("../lessonajax/getLessonsById.pl", { "lessonid": lessonid,"now":new Date().getTime()}, function(json){
          var exist=json.exist;
       
          if(exist==1){
             $("#budengdateid").attr("value",json.lessondatestr);
              $("#applytitleid").attr("value",json.title);
               $("#xuefenid").attr("value",json.xuefen);
          }
       }); 
       
    }
  
  }
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font">
    <img src="../imagesa/b_02.gif" width="4" height="7"> 当前位置：积分补登 &gt; 新增补登积分 </td>
  </tr>
</table>
<s:form action="jifenbudeng" method="post" name="form1" onsubmit="return validateForm_jifenbudeng();" enctype="multipart/form-data">
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold">
        </td>
        </tr>
          <tr>
        <td width="37%" class="tab_content" align="right">对应市(省直属)律协选择：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <s:if test="datavisible.provinceview">
          <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" label="省级律协" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
        </s:if>
        <s:else>
              <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.provinceid]"/>
              <s:hidden name="datavisible.provinceid"/>
        </s:else>
              <!-- 具体到某个律协就行了 -->
        <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname"/>
        </s:if>
        <s:else>
            <s:hidden name="datavisible.cityid" id="city"/>
            <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>
        </s:else>
        </td>
        </tr>
    
      <tr id="input">
        <td width="37%" class="tab_content" align="right">律师执业证号：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="budeng.lawyerno" size="20"/></td>
        </tr>
            <tr>
        <td width="37%" class="tab_content" align="right">是否计为现场培训：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <s:checkbox name="budeng.islocal" onclick="islocalclick(this)"/>
       	&nbsp;&nbsp;&nbsp;<font color='red'>如果计为现场培训,则补登的积分,在积分统计处,培训方式显示为"现场培训"</font>
        </td>
        </tr>
        
         <tr id="locallisttr" style="display:none">
        <td width="37%" class="tab_content" align="right">选择现场课程：</td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <select name="budeng.lessonid" id="budenglessonid"  onchange="selectlocal(this.value)"/>
        </td>
        </tr>
        
      <tr id="titleidtr">
        <td width="37%" class="tab_content" align="right">内容标题：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <s:textfield name="budeng.title" size="40" id="applytitleid"/></td>
        </tr>
   <tr>
        <td width="37%" class="tab_content" align="right">计分日期：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <jscalendar:jscalendar name="budeng.budengdate" format="%Y-%m-%d" showstime="false" id="budengdateid"/>
         	
        </td>
        </tr>
         <tr>
        <td width="37%" class="tab_content" align="right">学分：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <s:textfield name="budeng.xuefen" size="5" id="xuefenid"/> 
        	</td>
        </tr>
         <tr>
        <td width="37%" class="tab_content" align="right">补登积分年度：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
       
       <s:select name="budeng.theyear" list="jifentime.years"/> 
       	&nbsp;&nbsp;&nbsp;<font color='red'>补登的积分,将会计算到所选择的补登年度</font>
        </td>
        </tr>
     
         
   
      <tr>
       	
        <td class="tab_content1"></td>
        <td colspan="2" class="tab_content1">　
         
          <input type="submit" name="Submit" value=" 保存 "/>
          　 
          <input type="reset" name="Submit2" value=" 重置 "/>
          　
          <input type="button" name="Submit3" value=" 返回 " onClick="javascript:history.go(-1)">
          </td>
        </tr>
         <tr style="display:none" id="show">
         	<td class="tab_content1" colspan="3">
         			<span style="color:red;padding-left: 25px">注意，上传文件格式如下：</span><br>
         			<div style="color:blue;padding-left: 50px">
         			19020311007390<br>
         			19020311007391<br>
         			19020311007392<br>
         			</div>
         	</td>
         </tr>
    </table>
    </s:form>
    </td>
  </tr>

</table>
</body>
</html>