<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<%
/**
 * <p>���ܣ� �鿴Wait�б�</p>
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
<script language=javascript>
<!--
function getSearch(){
     document.form1.action = "searchUser.action";
     document.form1.submit();
}
function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=document.form1.selectAll.checked;
               }
          }else{
               document.form1.check.checked=document.form1.selectAll.checked;
          }
     }
}
function getDelete(){
     if(noChecked()){
          alert("��ѡ���¼��ɾ����Ҫѡ���¼��");
          return false;
     }
     if (confirm("��ȷ��Ҫ����ɾ��?")) {
          document.form1.action="deleteWaits.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
-->
</script>
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 1px;
	margin-right: 2px;
	margin-bottom: 1px;
}
-->
</style></HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13">
	      </td>
              <td width="97%"><span class="title-blank-b">��������&gt;&gt;�ļ��б�</span></td>
            </tr>
          </table>
      </TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="deleteWaits.action"   method="post">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD >ѡ��</TD>
                        <TD >����</TD>
                        <TD >�ύ��</TD>
                        <TD >״̬ </TD>
                        <TD >����</TD>
                        <TD >ʱ��</TD>                        
                        <TD>ɾ��</TD>
                      </TR>
<s:iterator value="Waitlist" status="status">
                      <TR class="listline">
                        <TD ><INPUT class="inputwhiteboder" type="checkbox" value='<s:property value="waitid"/>' name="check">
                        </TD>
                        <TD>
						<a href="<s:property value='url'/>&waitid=${waitid}&backurl=listWait3.action&pagenumber=<s:property value='pagenumber'/>">
						��<s:property value="subject"/>��
						</a></TD>
						<TD><s:property value="fromto"/></TD>
                        <TD>
                          <s:if test="docstatus==1"> <font color="#FF0000">����</font> </s:if>
                          <s:if test="docstatus==2"> <font color="#009900">���</font> </s:if>
                          <s:if test="docstatus==3"> <font color="#000000">�ļ�</font> </s:if>	
						</TD>
                        <TD><s:property value="flowstep"/></TD>
                        <TD><s:date name="createtime" format="yyyy-MM-dd"/></TD>
                        <TD>
                            <a href="deleteWait.action?waitid=<s:property value="waitid"/>">ɾ��</a>
                        </TD>
                  </TR>
</s:iterator>
<s:if test="waitlist!=null">                    <TR>
                      <TD colSpan=12 ><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">ȫѡ
                      </TD>
                    </TR>
</s:if>             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colSpan=12 align="middle">
<s:if test="recordsize>0">
  <div align="right" bgcolor="#FEF7E9">
  <span class="pt9-18">
   ��<font color=red><b><s:property value="recordsize"/></b></font>��¼
��<font color=red><b><s:property value="pagenumber+1"/></b></font>ҳ/
��<font color=red><b><s:property value="pagesize"/></b></font>ҳ
  </span>
  <s:if test="pagesize==1">
   <font color="#bbbbbb">
     ��ҳ ǰҳ ��ҳ ĩҳ
   </font>
  </s:if>
  <s:else>
  <s:if test="pagenumber+1==1">
   <font color="#bbbbbb">
    ��ҳ ǰҳ
   </font>
  </s:if>
  <s:else>
    <a href="javascript:page(0)">��ҳ</a>
    <a href="javascript:page(<s:property value="pagenumber-1"/>)">ǰҳ</a>
  </s:else>
  <s:if test="pagenumber+1==pagesize">
   <font color="#bbbbbb">
    ��ҳ ĩҳ
   </font>
  </s:if>
  <s:else>
    <a href="javascript:page(<s:property value="pagenumber+1"/>)">��ҳ</a>
    <a href="javascript:page(<s:property value="pagesize-1"/>)">ĩҳ</a>
  </s:else>
</s:else>
</div>
</s:if>
                      </TD>
                    </TR>
                    <TR align="center" bgcolor="#F9F9F7">
                      <TD colSpan=12 >

<input  class="botton" type=button onClick="return getDelete()" value="ɾ��">                      </TD>
                    </TR>
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="listWait3.action" method="POST">
	<s:hidden name="pagenumber" value="pagenumber"/>
</s:form>


