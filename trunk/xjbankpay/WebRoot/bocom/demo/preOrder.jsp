<%@ page import = "com.bocom.netpay.b2cAPI.*"%>

<%@ page language = "java" contentType = "text/html; charset=GBK"%>

<html>
    <head>
        <title>Ԥ�������ɲ���</title>

        <meta http-equiv = "Content-Type" content = "text/html; charset=gb2312">
    </head>

    <body bgcolor = "#FFFFFF" text = "#000000">
        <%
        String         code, err, msg;

        BOCOMB2CClient client = new BOCOMB2CClient();

        int            ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml"); //�ô���ֻ�����һ��

        if (ret != 0) {                                                                 //��ʼ��ʧ��
            out.print("��ʼ��ʧ��,������Ϣ��" + client.getLastErr());
        }

        else {
 			String interfaceVersion;

        String merID;

        String orderid;

        String orderDate;

        String orderTime;

        String tranType;

        String amount;

        String curType;

        String orderContent;

        String orderMono;
        
        String phone;
        
        String period;

        String phdFlag;

        String notifyType;

        String merURL;

        String goodsURL;

        String jumpSeconds;

        String payBatchNo;

        String proxyMerName;

        String proxyMerType;

        String proxyMerCredentials;

        String netType;

        String sourceMsg;

        interfaceVersion = request.getParameter("interfaceVersion");

        merID = BOCOMSetting.MerchantID;

        orderid = request.getParameter("orderid");

        orderDate = request.getParameter("orderDate");

        orderTime = request.getParameter("orderTime");

        tranType = request.getParameter("tranType");

        amount = request.getParameter("amount");

        curType = request.getParameter("curType");
        
        orderContent = request.getParameter("orderContent");
        
        orderMono = new String(request.getParameter("orderMono").getBytes(),"GB2312");
        
        phone = request.getParameter("phone");

        period = request.getParameter("period");

        phdFlag = request.getParameter("phdFlag");

        notifyType = request.getParameter("notifyType");

        merURL = request.getParameter("merURL");

        goodsURL = request.getParameter("goodsURL");

        jumpSeconds = request.getParameter("jumpSeconds");

        payBatchNo = request.getParameter("payBatchNo");

        proxyMerName = request.getParameter("proxyMerName");

        proxyMerType = request.getParameter("proxyMerType");

        proxyMerCredentials = request.getParameter("proxyMerCredentials");

        netType = request.getParameter("netType");       
        PreOrder tran = new PreOrder(orderid,orderDate,orderTime,amount,curType,orderContent, orderMono,phone,period, phdFlag,notifyType,merURL,goodsURL,jumpSeconds,payBatchNo,proxyMerName,proxyMerType,proxyMerCredentials,netType);
        	
            BOCOMB2COPReply rep = client.createPreOrder(tran); //�����ʻ���ѯ

            if (rep == null) {
                err = client.getLastErr();

                out.print("���״�����Ϣ��" + err + "<br>");
            }

            else {
                code = rep.getRetCode(); //�õ����׷�����

                msg = rep.getErrorMessage();

                out.print("���׷����룺" + code + "<br>");

                out.print("���״�����Ϣ��" + msg + "<br>");

                if ("0".equals(code)) { //��ʾ���׳ɹ�
                    out.print("<br>------------------------<br>");

                }
            }
        }
        %>

        <p>
            <a href = "Index.htm">������ҳ</a>
        </p>

        <p>
            &nbsp;
        </p>
    </body>
</html>
