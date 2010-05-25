﻿<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
	<head>
		<title>课程详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
.dtop{
	font-size: 10pt;
	font-weight: bold;
	padding-left: 15px;
	padding-top: 3px;
}
.commenttop{
	font-size: 8pt;
	padding-left: 30px;
}
.comment{
	font-size: 10pt;
	padding-left: 30px;
}
.dcontent{
	font-size: 9pt;
	padding-left: 15px;
	padding-top: 10px;
	padding-right: 10px;
}
-->
</style>


	</head>
	<body>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold"> 
					<img src="../imagesa/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
		<table width="99%" height="50" border="0" align="center" cellpadding="0" cellspacing="1">

			<tr>
				<td valign="top" width="100%">
					<div class="dtop">名称：${lesson.title}</div>
					<div class="dtop">主讲人：${lesson.teachers}</div>				
					<div class="dtop">类别：<s:property value="@com.changpeng.lessons.util.CommonDatas@LessonType[lesson.lessontype]"/>
							
					</div>
					<div class="dtop">学分：${lesson.xuefen}</div>
					<div class="dtop">时间：
					
					         <s:date name="lesson.lessondate" format="yyyy-MM-dd HH:mm"/>
					         
					         <s:if test="lesson.lessonend!=null">
					         -<s:date name="lesson.lessonend" format="yyyy-MM-dd HH:mm"/>
					         </s:if>
					         
				<!-- 	${lesson.lessondate}-${lesson.lessonend}
					 -->
					
					</div>
					<s:if test="lesson.lessonstyle==1||lesson.lessonstyle==3">
					<div class="dtop">地点：${lesson.lessonaddress}</div>	
					</s:if>	
				<s:if test="lesson.lessonstyle==2||lesson.lessonstyle==3">
				<div class="dtop">观看：<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lesson.lessonid}","在线培训","toolbar=no,location=no,width=600,height=550,menubar=no,scrollbars=no,resizable=no,status=no")>点击观看</a></div>
					</s:if>	
					<s:if test="hasattach">
					<div class="dtop">附件（右键点击下载）：<s:iterator value="filelist" status="stat"><a href="download.pl?filename=${filelist[stat.index]}">${filelist[stat.index]}</a>&nbsp;&nbsp;&nbsp;</s:iterator></div>
					</s:if>
				</td>
			
        	</tr>
        </table>
		<div class="dcontent" id="lessoncontent">
				${lesson.lessoncontent}
		</div>
		<div class="dtop">评论：</div>
		<hr width="95%" align="center"/>
		<s:iterator value="replylist" status="stat">	
		<div class="commenttop">评论人：${replyuser}&nbsp;&nbsp;&nbsp;评论日期:<s:date name="replytime" format="yyyy-MM-dd HH:mm"/></div>
		<hr width="95%" align="center"/>
		<div class="comment">${replycontent}</div>
		<hr width="95%" align="center"/>
		</s:iterator>
		<% 
	//	<s:form name="form1" method="post" action="replyCreate" >
	//	<input type="hidden" name="lessonid" value="${lesson.lessonid}">
	//	<div style="padding-left:15px">
	//	<FCK:editor id="replycontent" height="200" width="80%"
	//		skinPath="../editor/skins/default/"
	//		basePath="../" toolbarSet="Basic"
	//		>
	//	</FCK:editor>	
//		</div>
	//	<div style="padding-left:300px"><input type=submit value="发表评论" class="button"/></div>
	//	</s:form>	
		%>	
	</body>
</html>