<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<!--
 * FCKeditor - The text editor for internet
 * Copyright (C) 2003-2005 Frederico Caldeira Knabben
 * 
 * Licensed under the terms of the GNU Lesser General Public License:
 * 		http://www.opensource.org/licenses/lgpl-license.php
 * 
 * For further information visit:
 * 		http://www.fckeditor.net/
 * 
 * File Name: sample04.jsp
 * 	FCKeditor sample file 4.
 * 
 * Version:  2.3
 * Modified: 2005-07-19 13:57:00
 * 
 * File Authors:
 * 		Simone Chiaretta (simo@users.sourceforge.net)
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>FCKeditor - JSP Sample</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="robots" content="noindex, nofollow">
		<link href="sample.css" rel="stylesheet" type="text/css">
			<script type="text/javascript" src="fckeditor.js"></script>
			<script type="text/javascript">

function FCKeditor_OnComplete( editorInstance )
{
	var oCombo = document.getElementById( 'cmbToolbars' ) ;
	oCombo.value = editorInstance.ToolbarSet.Name ;
	oCombo.style.visibility = '' ;
}

function ChangeLanguage( languageCode )
{
	window.location.href = window.location.pathname + "?toolbar=" + languageCode ;
}

			</script>
	</head>
<%

String toolBarSetStr=request.getParameter("toolbar");
if(toolBarSetStr==null) {
	toolBarSetStr="";
}
String path=request.getContextPath();
%>	
	<body>
		<h1>FCKeditor - JSP - Sample</h1>
		This sample shows how to change the editor toolbar.
		<hr>
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>
					Select the toolbar to load:&nbsp;
				</td>
				<td>
					<select id="cmbToolbars" onchange="ChangeLanguage(this.value);" style="VISIBILITY: hidden">
						<option value="Default" selected>Default</option>
						<option value="Basic">Basic</option>
						<option value="custom">自定义</option>
					</select>
				</td>
			</tr>
		</table>
		<br>
		<form action="sampleposteddata.jsp" method="get" target="_blank">
		<%System.out.println(request.getContextPath()); %>
			<FCK:editor id="EditorDefault" height="300"  
				width="70%"
				skinPath="../editor/skins/default/"
				basePath="../"
				toolbarSet="<%=toolBarSetStr%>"
				imageBrowserURL="/jxqmanage/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
				imageUploadURL=" /jxqmanage/editor/filemanager/upload/simpleuploader?Type=Image"
>
				
			</FCK:editor>
			<br>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>