/*
 * NAME: com.sxit.cmpp.Test.java Company:SXIT
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cmpp.CMPP;
import com.sxit.cmpp.CMPPSocket;
import com.sxit.cmpp.CMPPSubmitResp;
import com.sxit.cmpp.Common;
import com.sxit.cmpp.SubmitBody;

/**
 * @author HuaFeng
 * @version 1.0 (2005-3-29 11:04:47)
 */
public class TestMT {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(TestMT.class);

    
    public static void main(String args[])throws Exception{
    	System.out.println("�ֻ���:"+args[0]);
    	main.cdralarm.SmsSend.send(args[0],"GGSN02/CG03��������");
    }
    
    public static void main1(String args[]) throws Exception {
//System.out.println(System.getProperty("file.encoding"));
        // ��ʼ��socket����
        CMPPSocket socket = new CMPPSocket("218.201.8.150", 7890);

        // ��socket����ע�ᵽCMPP api��,���response��Ϣ���ʱ��Ϊ10��
        CMPP cmpp = null;
        // ���������ص�connect����
        while (true) {
            try {
                socket.initialSock();
                // ��socket����ע�ᵽCMPP api��
//                cmpp = new CMPP(socket, 60);
                cmpp=new CMPP(socket);
                // ���������ص�connect����
                int status = cmpp.cmppConnect("922095", "922095");
                log.info("�����ؽ��������ӷ��ؽ��:" + status);

                if (status == 0) {
                    log.info("���ӽ����ɹ���\n");
                    break;
                }
                else {
                    log.warn("���ӽ������ɹ�,���Ϊ:" + status + "����������\n");
                    Thread.sleep(5000L);
                    socket.closeSock();
                }

            }
            catch (Exception e) {
                try {
                    socket.closeSock();
                    log.error("���������쳣,����5����ٴν�������!");
                    Thread.sleep(5000L);
                }
                catch (Exception ee) {
                    log.error("�ر������쳣:" + ee.toString());
                }

                log.error(e.toString());
            }
        }

        // submit��Ϣ,��װsubmit��,���ֶ����������doc�ĵ�
        SubmitBody submit = new SubmitBody(); // submit.msgID = 12;

        submit.ucPkTotal = 1;

        submit.ucPkNumber = 1;

        submit.ucRegister = 1;

        submit.ucMsgLevel = 1;

        submit.sServiceId = "10658477";

        submit.ucFeeUserType = 1;

        submit.sFeeTermId = "13635423870";

        submit.ucTpPid = 0;

        submit.ucTpUdhi = 0;

        submit.ucMsgFmt = 15;

        submit.sMsgSrc = "922095";

        submit.sFeeType = "01";

        submit.sFeeCode = "0";

        submit.sValidTime = "";

        submit.sAtTime = "";

        submit.sSrcTermId = "10658477";

        //����֧�ֶ������,cmpp2.0��ʱֻ֧��һ�����պ���
        submit.sDstTermId = "13635423870";
        //Ҳ�����趨ֻ��һ��
//        submit.sDstTermId = "13635423870";

        submit.ucMsgContent = "���ն��ŵ�MSISDN����,��Ӣ�Ķ���".getBytes("gb2312");

        submit.reserver = ""; // cmpp.cmppSubmit(submit); //
        // cmpp.cmppSubmit(submit); int i = 0;

        int i = 0;
        while (true) {
            long begin = System.currentTimeMillis();
            // ͬʱ���submitresp��Ϣ
            CMPPSubmitResp resp = new CMPPSubmitResp();
            cmpp.cmppSubmit(submit, resp);
            //			System.out.println(resp.msg_Id + "->messageid" + resp.sequenceID
            //					+ "->seqid" + resp.result + "->result");
            log.info("msgid=" + Common.bytes8ToLong(resp.msg_Id) + "--seqid=" + resp.getSequenceID() + "--result="
                    + resp.result);
            log.info("msgid=" + bytes2hex(resp.msg_Id));
            long now = System.currentTimeMillis();
            if (now - begin <= 100) {
                log.info("����������Ϣ����" + (now - begin) + "ʱ��,˯" + (100 - now + begin) + "��ô��\n\n");
                Thread.sleep(100 - now + begin);

            }
            if (i++ == 3) {
                break;
            }

        }
        log.info("--------------��Ϣ10��");
        //        Thread.sleep(10 * 1000);
        log.info("--------------������");
        // ������·����
        int iii = cmpp.cmppActiveTest();
        log.info("���ͼ����ɹ�=" + iii);
        //��סҪ�ر�����
        socket.closeSock();
        //        socket.closeSock();

    }

    private static String bytes2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            sb.append(Common.byte2hex(b[i]) + " ");
        return sb.toString();
    }
}