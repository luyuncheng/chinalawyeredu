<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<%
/**
 * <p>功能： 创建employee</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-03-04</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">

<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown":""}${curuser.style==3?"green":""}.css" type="text/css"/>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%">
              <span class="sort-title">诉讼案件&gt;&gt;立案信息查看
              </span>
              </td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
			  <br>			  
             <table width="70%"  border=0 align=center cellpadding=3 cellspacing=1 >
              <tbody>
                <s:form name="form1" action="lianinfoView" validate="true" method="post">
			      
			        <TR class="listheadline">
	                    <TD colspan="4">立案信息
	                
	                      
	                    </TD>
	                </TR>
					<TR class="listheadline">
	                    <TD>起诉标的合计：</TD>
	                     <TD class="listline">${lianinfo.biaodiheji}
	                      <TD>起诉标的本金：</TD>
	                       <TD class="listline">${lianinfo.biaodibenjin}
	                </TR>
	                <TR class="listheadline">
	                    <TD>起诉标的利息：</TD>
	                     <TD class="listline">${lianinfo.biaodilixi}
	                      <TD>律师费：</TD>
	                       <TD class="listline">${lianinfo.lawyerfee}
	                </TR>
	            <TR class="listheadline">
	                    <TD>立案时间：</TD>
	                     <TD class="listline">${lianinfo.liandate}
	                      <TD>受理法院：</TD>
	                       <TD class="listline">${lianinfo.jigou}
	                </TR>
	                     <TR class="listheadline">
	                    <TD>联系法官：</TD>
	                     <TD class="listline">${lianinfo.faguan}
	                      <TD>诉讼案号：</TD>
	                       <TD class="listline">${lianinfo.susongno}
	                </TR>
	                     <TR class="listheadline">
	                    <TD>诉讼费：</TD>
	                     <TD class="listline">${lianinfo.susongfee}
	                      <TD>保全费：</TD>
	                       <TD class="listline">${lianinfo.baoquanfee}
	                </TR>
                 	
                <tr bgcolor="#CCCCCC">
                  <td align="center" colspan="4">
                  
                    <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                  </td>
                </tr>
                </s:form>
              </tbody>
            </table>
		    <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
