<%@ page contentType="text/xml;charset=gb2312" import="com.changpeng.common.*,java.sql.*,org.dom4j.DocumentHelper,org.dom4j.Element" %><%
String phone=request.getParameter("AniNum");
Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs=null;
String sql="";
out.println("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
Element res = DocumentHelper.createElement("NewDataSet");
try{
	
	con=Globals.getInstance().getCon();
	sql="select a.*,b.bqqno  from (select * from tusr_address a where  phone='"+phone+"') a left  join tsys_user b on a.userid=b.userid";
	System.out.println(sql);
	pstmt=con.prepareStatement(sql);
	rs=pstmt.executeQuery();
	while(rs.next()){
		Element tab1 = DocumentHelper.createElement("Table1");
		int oprflag=rs.getInt("oprflag"); //1���ÿ�����  2 ���ߴ��� 3����ҵ��
		String cusflag="";
		if(oprflag==1) cusflag="���ÿ����տͻ�";
		else if(oprflag==1) cusflag="���ߴ��տͻ�";
		else if(oprflag==1) cusflag="���Ͽͻ�";
		String bqqno=nullToSpace(rs.getString("bqqno"));
		String username=nullToSpace(rs.getString("username"));
		String comments=nullToSpace(rs.getString("comments"));
		String homeaddr=nullToSpace(rs.getString("homeaddr"));
		String company=nullToSpace(rs.getString("company"));
		String email=nullToSpace(rs.getString("email"));

		tab1.addElement("�ͻ�����").addText(username);
		tab1.addElement("��ͥסַ").addText(homeaddr);
		tab1.addElement("��˾����").addText(company);
		tab1.addElement("EMAIL").addText(email);
		tab1.addElement("��ע").addText(comments);
		tab1.addElement("�ͻ����").addText(cusflag);
		tab1.addElement("AniType").addText(bqqno);
		res.add(tab1);
	}
}finally{
	if(rs!=null) rs.close();
	if(pstmt!=null) pstmt.close();
	if(con!=null) con.close();
}
out.println(res.asXML());
%>
<%!
public static String nullToSpace(String str){
	if(str==null) str="";
	return str;
}
%>