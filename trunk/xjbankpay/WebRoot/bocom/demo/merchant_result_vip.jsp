<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
    <head>
        <title>��ͨ�����̻����Խ��ҳ��</title>

        <meta http-equiv = "Content-Type" content = "text/html; charset=gb2312">
    </head>

    <%@ page language = "java" contentType = "text/html; charset=GBK"%>

    <body bgcolor = "#FFFFFF" text = "#000000">
        <%
               
        BOCOMB2CClient client = new BOCOMB2CClient();
        
				int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");//�ô���ֻ�����һ��
				
				if (ret != 0){  //��ʼ��ʧ��
				
					out.print("��ʼ��ʧ��,������Ϣ��"+client.getLastErr());
					
				}
				else{
        
        out.print("�̻����ҳ��");

        out.print("<br>");

        out.print("--------------------------");

        out.print("<br>");

        String                    notifyMsg = request.getParameter("notifyMsg");                    //��ȡ����֪ͨ���
																	notifyMsg =  java.net.URLDecoder.decode(notifyMsg,"GBK");					//����ʶ��ָ�������ʽ��
        int                       lastIndex = notifyMsg.lastIndexOf("|");

        String                    signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length()); //��ȡǩ����Ϣ

        String                    srcMsg = notifyMsg.substring(0, lastIndex + 1);

        int                       veriyCode = -1;

        com.bocom.netpay.b2cAPI.NetSignServer nss = new com.bocom.netpay.b2cAPI.NetSignServer();

        nss.NSDetachedVerify(signMsg.getBytes("GBK"), srcMsg.getBytes("GBK"));

        veriyCode = nss.getLastErrnum();

        if (veriyCode < 0) { //��ǩ����
            out.print("�̻�����֤ǩ��ʧ�ܣ�return code:" + veriyCode);

            return;
        }

        java.util.StringTokenizer stName = new java.util.StringTokenizer(srcMsg, "|"); //���֪ͨ�����Vector

        java.util.Vector          vc = new java.util.Vector();

        int                       i = 0;

        while (stName.hasMoreTokens()) {
            String value = (String)stName.nextElement();

            if (value.equals(""))
                value = "&nbsp;";

            vc.add(i++, value);
        }
        %>

        <table width = "75%" border = "0" cellspacing = "0" cellpadding = "0">
            <tr>
                <td width = "14%">
                    �̻��ͻ���
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(0));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    �������
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(1));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ���׽��
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(2));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ���ױ���
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(3));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ƽ̨���κ�
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(4));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    �̻����κ�
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(5));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ��������
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(6));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ����ʱ��
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(7));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ������ˮ��
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(8));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ���׽��
                </td>

                <td width = "86%">
                    <%
                    out.print(vc.get(9));
                    %>

                    &nbsp;[1:�ɹ�]
                </td>
            </tr>

            <tr>
                <td width = "14%">
                    �������ܶ�
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(10));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ���п�����
                </td>

                <td width = "86%">
                    <%
                    out.print(vc.get(11));
                    %>

                    &nbsp;[0:��ǿ� 1��׼���ǿ� 2:���ǿ�]
                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ���б�ע
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(12));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    ������Ϣ����
                </td>

                <td width = "86%">
                    <%
                    out.print(vc.get(13));
                    %>

                </td>
            </tr>
            <tr>
                <td width = "14%">
                    IP
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(14));
                    %>

                </td>
            </tr>
            <tr>
                <td width = "14%">
                    Referer
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(15));
                    %>

                </td>
            </tr>
        </table>
        
        <%
        }
        %>

        <p>
            &nbsp;
        </p>
    </body>
</html>
