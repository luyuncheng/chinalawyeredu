<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<%
/**
 * <p>���ܣ� ����Wait</p>
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
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
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
              <td width="97%"><span class="title-blank-b">�����ļ�&gt;&gt;��������</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<ww:form name="'form1'" action="'createWait'" validate="true" method="'post'">
<ww:hidden name="'flag'" value="'save'"/>
               <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center">����id:</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.waitid'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center">��������:</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.url'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center">����:</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.subject'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center"> :</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.fromto'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center"> :</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.userid'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center">״̬ 1 �Ѱ� 0 δ��:</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.status'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center">���̻���:</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.flowstep'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center">����״̬ 3 �ļ� 2 ��� 1 ����:</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.docstatus'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD bgcolor="#FFD275" class="pt9-18">
			<div align="center">�ύʱ��:</div>
		      </TD>
                      <TD bgcolor="#FFFFFF">
<ww:textfield  name="'wait.createtime'" template="'textfield.vm'" required="true"/>
		      </TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">״̬:</div></TD>
                      <TD  bgcolor="#ECECFF">
                      <SELECT name="wait.statusid" class="input1">
                            <OPTION VALUE="1" selected>����</OPTION>
                            <OPTION VALUE="0">����</OPTION>
                      </SELECT>	
                      </TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">�� ��:</div></TD>
                      <TD colspan=2 bgcolor="#ECECFF"><TEXTAREA name="wait.description" cols="60" rows="8" class="input1" id=""></TEXTAREA>
                    </TR>
                    <TR bgcolor="#FFFFFF">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="����">&nbsp;
			<INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="����">
		      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
        </ww:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>

