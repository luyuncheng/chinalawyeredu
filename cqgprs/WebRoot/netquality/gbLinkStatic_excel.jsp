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
    <td  colspan="7" align="center" bgcolor="#FFFF00"><b>
    <s:if test="bscid!=null&&!bscid.equals(\"\")">
    ${date }之GB流量统计
    </s:if>
    <s:else>
    ${date }之归属BSC/RNC：${bscid }之GB流量统计
    </s:else>
    </b></td>
  </tr>
      <tr>
   
  
                          <th>NSVCID</th>
                          <th>NSVC查询索引</th>
                          <th>所属BSC/RNC</th>
                          <th>所属SGSN</th>
                          <th>总流量（M）</th>
      </tr>
      <tbody>
         <s:iterator value="page.items" status="status">
      <tr>
          <td class="listline2">${nsvcid}</td>
                          <td class="listline2">${nsvc.nsvcindex}</td>
                          <td class="listline2">${bscid }</td>
                          <td class="listline2">${bsc.sgsnid}</td>
                          <td class="listline2">${totalStreamStr}</td>
   
      </tr>
   
    </s:iterator>
      </tbody>
    </table>

</body>
</html>