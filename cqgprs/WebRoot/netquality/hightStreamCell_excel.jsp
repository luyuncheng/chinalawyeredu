﻿<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}</title>
<style type="text/css">
<!--
.listheadline {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #919191}
th
{FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #919191}

.listline {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #EFEFEF
}
.listline1 {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #CCCCFF}
.listline2 {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #FFCC99}
.listline3 {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #9999CC}
-->
</style>
<%
String filename="export.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename);
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
</head>
<body>


    
   <table id="data" width="100%"  border=1 align=center cellpadding=3 cellspacing=1 bgcolor="#F9F9F7">
     <tr>
    <td  colspan="6" align="center" bgcolor="#FFFF00"><b>
    <s:if test="flag.equals(\"1\")">
    ${date}之高流量小区
    </s:if>
    <s:else>
      ${date}之${hour }时段高流量小区
    </s:else>
    <s:if test="standard.equals(\"1\")">
    （流量大于${condition} K）
    </s:if>
    <s:else>
    （流量排名前${condition} 位）
    </s:else>
    </b></td>
  </tr>
      <tr>
     <!-- 
                          <th class="listheadline">SGSN号</th>
                          -->
                          <th>小区编码</th>
                          <th>归属BSC/RNC</th>
                          <th>归属SGSN</th>
                          <th>历史总流量</th>
                          <th>当前流量</th>
      </tr>
      <tbody>
      <s:iterator value="topcelllist" status="stat">
      <tr>
                          <td class="listline2">${cellkey}</td>
                          <td class="listline2">${bscrncid}</td>
                          <td class="listline2">${sgsnid }</td>
                          <td class="listline2">${totalStream}</td>
                          <td class="listline2">${currentStream}</td>
      </tr>
    </s:iterator>
      </tbody>
    </table>

</body>
</html>