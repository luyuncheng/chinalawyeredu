<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<%
/**
 * <p>���ܣ� �༭Wait</p>
 * <p>���ߣ� zrb</p>
 * <p>��˾�� ��ѵ�ſ�</p>
 * <p>���ڣ� 2005-04-29</p>
 * @�汾�� V1.0
 * @�޸ģ�
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 2px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=15 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="title-blank-b">�����ļ�&gt;&gt;�༭����</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<s:form name="'form1'" action="'editWait'" validate="true" method="'post'">
<s:hidden name="'wait.waitid'" value="wait.waitid"/>
<s:hidden name="'flag'" value="'save'"/>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center">����id:</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.waitid'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center">��������:</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.url'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center">����:</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.subject'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center"> :</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.fromto'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center"> :</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.userid'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center">״̬ 1 �Ѱ� 0 δ��:</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.status'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center">���̻���:</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.flowstep'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center">����״̬ 3 �ļ� 2 ��� 1 ����:</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.docstatus'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                   <TR>
                    <TD bgcolor="#FFD275" class="pt9-18">
		        <div align="center">�ύʱ��:</div>
		       </TD>
                    <TD bgcolor="#ECECFF">
	  	        <s:textfield  name="'wait.createtime'" template="'textfield.vm'" required="true"/>
                    </TD>
                   </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">״̬:</div></TD>
                      <TD  bgcolor="#ECECFF">
                      <SELECT name="wait.statusid" class="input1">
<s:if test="wait.statusid==1">
                         <OPTION VALUE="1" selected>����</OPTION>
                         <OPTION VALUE="0">����</OPTION>
</s:if>
<s:if test="wait.statusid==0">
                         <OPTION VALUE="1">����</OPTION>
                         <OPTION VALUE="0" selected>����</OPTION>
</s:if>
                      </SELECT>
                      </TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">�� ��:</div></TD>
                      <TD colspan=2 bgcolor="#ECECFF">
                        <TEXTAREA name="wait.description" cols="60" rows="8" class="input1" id=""><s:property value="wait.description"/></TEXTAREA>
                      </TD>
                    </TR>
                    <TR bgcolor="#ECECFF">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="����">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="����">
		      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>

