/*
 * NAME: com.sxit.cmpp.CMPPDeliver.java Company:SXIT
 */

package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * delive��Ϣ
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-30 13:13:34)
 */
public class CMPPDeliver extends CMPPResponsePacket {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPDeliver.class);

    /**
     * 8λ,��Ϣ��ʶ �����㷨���£� ����64λ��8�ֽڣ��������� ��1�� ʱ�䣨��ʽΪMMDDHHMMSS��������ʱ���룩��bit64~bit39������ bit64~bit61���·ݵĶ����Ʊ�ʾ��
     * bit60~bit56���յĶ����Ʊ�ʾ�� bit55~bit51��Сʱ�Ķ����Ʊ�ʾ�� bit50~bit45���ֵĶ����Ʊ�ʾ�� bit44~bit39����Ķ����Ʊ�ʾ�� ��2��
     * �������ش��룺bit38~bit17���Ѷ������صĴ���ת��Ϊ������д�����ֶ��С� ��3�� ���кţ�bit16~bit1��˳�����ӣ�����Ϊ1��ѭ��ʹ�á� �������粻�����������㣬�Ҷ��롣
     */
    public byte msg_Id[]=new byte[8];

    // public long msgID;

    /**
     * 21λ, Ŀ�ĺ��� SP�ķ�����룬һ��4--6λ��������ǰ׺Ϊ�������ĳ����룻�ú������ֻ��û�����Ϣ�ı��к��롣
     */
    public String dest_Id;

    /**
     * 10λ, ҵ�����ͣ������֡���ĸ�ͷ��ŵ���ϡ�
     */
    public String service_Id;

    /**
     * 1λ, GSMЭ�����͡���ϸ������ο�GSM03.40�е�9.2.3.9
     */
    public byte tp_pid;

    /**
     * 1λ, GSMЭ�����͡���ϸ������ο�GSM03.40�е�9.2.3.23����ʹ��1λ���Ҷ���
     */
    public byte tp_udhi;

    /**
     * 1λ, ��Ϣ��ʽ 0��ASCII�� 3������д������ 4����������Ϣ 8��UCS2���� 15����GB����
     */
    public byte msg_Fmt;

    /**
     * 21λ, Դ�ն�MSISDN���루״̬����ʱ��ΪCMPP_SUBMIT��Ϣ��Ŀ���ն˺��룩
     */
    public String src_terminal_Id;

    /**
     * 1 λ, �Ƿ�Ϊ״̬���� 0����״̬���� 1��״̬����
     */
    public byte registered_Delivery;

    /**
     * 1λ, ��Ϣ����
     */
    public int msg_Length;

    /**
     * Msg_length λ, ��Ϣ�����ֽ�����ʽ�������deliver��Ϣ���û����Լ�����ת���ִ�
     */
    public byte[] msg_Content;

    /**
     * ״̬�������
     */
    public CMPPReport report;

    /**
     * 8λ, ������,����cmpp2.0�ж����
     */
    public String reserved;

    //    /**
    //     * 20λ���㲥ҵ��ʹ�õ�LinkID���ǵ㲥��ҵ���MT���̲�ʹ�ø��ֶΡ�
    //     */
    //    public String linkID;

    public CMPPDeliver() {
        msg_Id = new byte[8];
    }

    /**
     * �������������õ��İ����ֽ���
     */
    public void parseResponseBody(byte[] packet) {
        byte[] length = new byte[4];
        System.arraycopy(packet, 0, length, 0, 4);
        this.totalLength = Common.bytes4ToInt(length);
        if (log.isDebugEnabled()) {
            log.debug("���ذ����Ƚ�����Ϊ:" + totalLength);
        }

        byte[] commandid = new byte[4];
        System.arraycopy(packet, 4, commandid, 0, 4);
        this.commandID = Common.bytes4ToInt(commandid);
        if (log.isDebugEnabled()) {
            log.debug("���������ֽ�����=" + commandID + "��ʵ��=" + CommandID.CMPP_DELIVER);
        }

        byte[] seqid = new byte[4];
        System.arraycopy(packet, 8, seqid, 0, 4);
        this.sequenceID = Common.bytes4ToInt(seqid);
        if (log.isDebugEnabled()) {
            log.debug("���ذ����кŽ�����Ϊ:" + sequenceID);
        }

        System.arraycopy(packet, 12, msg_Id, 0, 8);

        byte[] dest = new byte[21];
        System.arraycopy(packet, 20, dest, 0, 21);
        this.dest_Id = new String(dest);

        byte[] service = new byte[10];
        System.arraycopy(packet, 41, service, 0, 10);
        this.service_Id = new String(service);

        this.tp_pid = packet[51];
        this.tp_udhi = packet[52];
        this.msg_Fmt = packet[53];

        //        byte[] src = new byte[32];
        //        System.arraycopy(packet, 42 + 12, src, 0, 32);
        //        this.src_terminal_Id = new String(src);
        byte[] src = new byte[21];
        System.arraycopy(packet, 54, src, 0, 21);
        this.src_terminal_Id = new String(src);

        this.registered_Delivery = packet[75];
        this.msg_Length = packet[76];
        if (msg_Length < 0)
            msg_Length = 256 + msg_Length;

        byte[] content = new byte[this.msg_Length];
        if (log.isDebugEnabled())
            log.debug("deliver�����ݳ���Ϊ:" + content.length);
        System.arraycopy(packet, 77, content, 0, content.length);

        if (this.registered_Delivery == 1) {

            report = new CMPPReport();
            System.arraycopy(content, 0, report.msg_Id, 0, 8);
            log.info("���ص���״̬����,��Ӧmsgid=" + Common.bytes8ToLong(report.msg_Id));

            byte[] state = new byte[7];
            System.arraycopy(content, 8, state, 0, 7);
            report.stat = new String(state);

            byte[] subtime = new byte[10];
            System.arraycopy(content, 15, subtime, 0, 10);
            report.submit_time = new String(subtime);

            byte[] donetime = new byte[10];
            System.arraycopy(content, 25, donetime, 0, 10);
            report.done_time = new String(donetime);

            //            byte[] destre = new byte[32];
            //            System.arraycopy(content, 35, destre, 0, 32);
            //            report.dest_terminal_Id = new String(destre);
            byte[] destre = new byte[21];
            System.arraycopy(content, 35, destre, 0, 21);
            report.dest_terminal_Id = new String(destre);

            byte[] seqre = new byte[4];
            System.arraycopy(content, 56, destre, 0, 4);
            report.smsc_sequence = Common.bytes4ToInt(seqre);
        }
        else {
            //            try {
            //                this.msg_Content = new String(content, "gb2312");
            //            }
            //            catch (UnsupportedEncodingException e) {
            //                log.error("ϵͳ��֧��gb2312�ַ���,����ϵͳĬ���ַ���");
            //                this.msg_Content = new String(content);
            //            }
            this.msg_Content = content;
        }

        byte[] reserver = new byte[8];
        //        System.arraycopy(packet, 78 + content.length , linkid, 0, 20);
        //        linkID = new String(linkid);
        System.arraycopy(packet, 77 + content.length, reserver, 0, 8);
        reserved = new String(reserver);

        log.info("deliver��Ϣ�����ɹ�,msgid=" + bytes2hex(msg_Id));
    }

    private static String bytes2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            sb.append(Common.byte2hex(b[i])).append(" ");
        return sb.toString();
    }
}