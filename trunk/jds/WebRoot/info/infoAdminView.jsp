<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看info</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function delattach(attachid){
	if(confirm("您确定要删除附件?")){
	  document.location.href="attachDelete.action?attachid="+attachid+"&nextpage=infoAdminView.action?infoid="+${info.infoid};
	  return true;
	}else {
          return false;
    }
}
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;查看信息</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        <TR>
				<TD  bgColor=#F9F9F7>
                 <TABLE width="100%"  border=0 align=left cellPadding=3 cellSpacing=0 bgcolor="#F9F9F7">
                   <TR>
                     <TD width="5" >&nbsp;</TD>
                     <TD align="left" >
					 
                       <input  name="edit2" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                       <input  name="delete2" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
					  <input  name="upload" type=submit class="mask" onClick="document.uploadForm.submit()" value="上传附件">
                      
                     <input  name="back2" type=button class="mask" onClick="document.backForm.submit()" value="返回"></TD>
                     <TD > </TD>
                     <TD align="right" >  </TD>
                     <TD width="5" >&nbsp;</TD>
                   </TR>
                 </TABLE>
            </TD>
          </TR>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=0 bgcolor="#F9F9F7">
                  <TR >
                    <TD colSpan=12 align=center>&nbsp;</TD>
                  </TR>
                  <TR bgColor='#EEEEEE'>
                    <TD class="pt9-18">类别：${info.tinfType.name}</TD>
                    <TD class="pt9-18">文件编号：${info.filenumber}</TD>
                    <TD class="pt9-18">状态：<s:property escape="false" value="@com.sxit.info.util.CommonDatas@StatusWeb[${info.statusid}]"/></TD>
                  </TR>
                  <TR bgcolor="#F9F9F7" class="pt9-18">
                    <TD colSpan=12 height="30" align="center">
                    </TD>
                  </TR>
                </TABLE>                
                <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
                    <TR >
                      <TD align="center" class="posttitle">${info.infoname}</TD>
                    </TR>
                    <TR >
                      <TD align="center">
					  <div class="postinfo">作者:${info.createuser.username} 时间:<s:date name="info.createtime" format="yyyy-MM-dd"/>  部门:${info.department.departmentname}  
					  </div>
					  </TD>
                    </TR>
                    <TR valign="top">
                      <TD height="60" class="post"><br>${info.content}</TD>
                    </TR>
                  </TBODY>
                </TABLE>                
                <br>
                <TABLE width="580"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <s:if test="infoAttachList.size>0">
                  <TR>
                    <TD colspan="4">
					  <div class="postinfo">附件:</div>
					</TD>
                  </TR>
                  <TR>
                    <TD colspan="3" class="post" align="left"> 
					<table width="60%" border="0" cellspacing="0" cellpadding="0">
					<s:iterator value="infoAttachList" status="status">
					<table class="pt9-18" style="display:inline" border="0" cellspacing="0" cellpadding="0">
					  <tr >
						<td align="center" class="pt9-18" onMouseOver="style.backgroundColor='#EEEEEE'"
                                  onmouseout="style.backgroundColor='#FFFFFF'">
                    		<a title="点击下载附件" href="download.action?attachid=${attachid }"><img src="images2/doc.gif" width="40" height="40" border="0"><br>${filename}</a>
                    	</td>
					  </tr>
					  <tr >
						<td  height="24"  align="center">
						<span title="删除附件" align="center" class="mask" onClick="delattach(${attachid })">删除</span>
						</td>
					  </tr>
					</table>&nbsp;
					</s:iterator>	
					</table>
                    </TD>
                  </TR>
                  </s:if>
                </TABLE>                
                <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>

   <s:form name="editForm" action="infoAdminEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="infoAdminDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="infoList.action" method="POST">
   <s:hidden name="type" value="${type}"/>
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="uploadForm" action="attachCreate!input.action" method="POST">
   <s:hidden name="info.statusid" value="0"/>
   <s:hidden name="nextpage" value="infoAdminView.action?infoid=${info.infoid}" />
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
