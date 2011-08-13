<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 创建module</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-09-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<s:head/>
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
              <td width="97%"><span class="sort-title">模块管理&gt;&gt;新增模块</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="moduleCreate" validate="true" method="post">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
	 			 	<TR>
						<TD width="15%" class="listheadline">模块id:</TD>
						<TD width="35%" class="listline"><s:textfield name="module.moduleid"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">模块名:</TD>
						<TD width="35%" class="listline"><s:textfield name="module.modulename"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">描述:</TD>
						<TD width="35%" class="listline"><s:textfield name="module.description"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">排序:</TD>
						<TD width="35%" class="listline"><s:textfield name="module.moduleorder"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">权限类型:</TD>
						<TD width="35%" class="listline">
                      <SELECT name="module.powertype" class="input1">
                            <OPTION VALUE="1" >自定义</OPTION>
                            <OPTION VALUE="0" selected>系统默认</OPTION>
                      </SELECT>	
					</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
             </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
