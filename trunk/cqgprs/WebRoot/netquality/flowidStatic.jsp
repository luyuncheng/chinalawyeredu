<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-华为CHR之流程统计</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
function fanye(str){
 //document.form1.firstpage.value="no";
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
 //document.form1.firstpage.value="yes";
  document.form1.resultType.value="excel";
  document.form1.submit();
}
function queryit(){
// document.form1.firstpage.value="yes";
  document.form1.resultType.value="list";
  document.form1.submit();
}

$(document).ready(function(){
	var flag="${statflag}";
	showhidehour(flag);
});
function selecthour(obj){
	var flag=obj.value;
	showhidehour(flag);
}

function showhidehour(_flag){
	if(_flag==0){
		$("#hourselect").show();
	}else{
		$("#hourselect").hide();
	}
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>网络质量</a>＞<em>华为CHR日志</em>＞<em>流程ID统计</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="flowidStatic" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                  <s:hidden name="resultType"/>
                                   <s:hidden name="pageNo"/>
                                  
								   <td title="按时查询可以选择当天的日期">
								    <s:select name="statflag" list="#{'1':'按天','0':'按时'}" onchange="selecthour(this)" ></s:select>
								 <td>选择日期：<jscalendar:jscalendar name="statdate" cssClass="txt"/>
								 </td>
								 <td id="hourselect"><s:select name="stattime" list="@com.sxit.stat.service.StatService@ALL_HOUR_LIST"/>
								 </td>
								 <td><s:select name="sgsnid" list="#{'':'全部','SGSN7':'SGSN7','SGSN8':'SGSN8','SGSN9':'SGSN9'}"/>
								 </td>
								 <td>框号：<s:textfield name="kuang" cssClass="txt" size="10"/>&nbsp;
								 </td>
								 <td>槽号：<s:textfield name="cao" cssClass="txt" size="10"/>&nbsp;
								 </td>
								  <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							<!--  <td><input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/></td>
							-->
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>-->
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>

                          <th>流程ID</th>
                          <th>流程名称</th>
                        <!--    <s:if test="sgsnview">
                            <th>SGSNID</th>
                          </s:if>
                           <s:if test="caoview">
                             <th>槽号</th>
                          </s:if>
                           <s:if test="kuangview">
                             <th>框号</th>
                          </s:if>-->
                           <th>次数</th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                         <td title="点击查看该流程ID的错误情况"><a href="flowidErrorStatic.action?flowid=${flowid }&statflag=${statflag }&stattime=${stattime }&statdate=${statdate }">${flowid}</a></td>
                          <td><a href="flowidErrorStatic.action?flowid=${flowid }&statflag=${statflag }&stattime=${stattime }&statdate=${statdate }"><s:property value="@com.sxit.netquality.service.ChrQueryService@ALL_FLOWID[flowid]"/></a></td>
                        <!--  <s:if test="sgsnview">
                            <td>${sgsnid }</td>
                          </s:if>
                           <s:if test="caoview">
                             <td>${cao }</td>
                          </s:if>
                           <s:if test="kuangview">
                             <td>${kuang }</td>
                          </s:if>-->
                          <td>${flowcount }</td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			  
                    </table>
			  </div>

			 <div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>
				</div>
			</div>
		</div>
</s:form>
</body>

</html>

