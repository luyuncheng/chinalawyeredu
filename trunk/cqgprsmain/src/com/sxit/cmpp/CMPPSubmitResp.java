/*
 * NAME: com.sxit.cmpp.CMPPSubmitResp.java Company:SXIT
 */

package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * submit������Ϣ��
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-30 13:27:03)
 */
public class CMPPSubmitResp extends CMPPResponsePacket {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPSubmitResp.class);

    /**
     * 8λ,��Ϣ��ʶ�������㷨���£�����64λ��8�ֽڣ��������� <br>
     * ��1�� ʱ�䣨��ʽΪMMDDHHMMSS��������ʱ���룩��bit64~bit39������ bit64~bit61���·ݵĶ����Ʊ�ʾ�� bit60~bit56���յĶ����Ʊ�ʾ�� bit55~bit51��Сʱ�Ķ����Ʊ�ʾ��
     * bit50~bit45���ֵĶ����Ʊ�ʾ�� bit44~bit39����Ķ����Ʊ�ʾ�� <br>
     * ��2�� �������ش��룺bit38~bit17���Ѷ������صĴ���ת��Ϊ������д�����ֶ��С� <br>
     * ��3�� ���кţ�bit16~bit1��˳�����ӣ�����Ϊ1��ѭ��ʹ�á� �������粻�����������㣬�Ҷ��롣 ��SP���������Ӧ����Ϣ��Sequence_Idһ���ԾͿɵõ�CMPP_Submit��Ϣ��Msg_Id��
     */
    public byte[] msg_Id;

    /**
     * Unsigned Integer ��� <br>
     * 0����ȷ <br>
     * 1����Ϣ�ṹ�� <br>
     * 2�������ִ� <br>
     * 3����Ϣ����ظ� <br>
     * 4����Ϣ���ȴ� <br>
     * 5���ʷѴ���� <br>
     * 6�����������Ϣ�� <br>
     * 7��ҵ������ <br>
     * 8���������ƴ� <br>
     * 9�������ز��������˼ƷѺ��� <br>
     * 10�� Src_Id���� <br>
     * 11��Msg_src���� <br>
     * 12��Fee_terminal_Id���� <br>
     * 13��Dest_terminal_Id���� ����
     */
    public int result;

    public CMPPSubmitResp() {
        msg_Id = new byte[8];
        this.commandID=CommandID.CMPP_SUBMIT_REP;
        this.totalLength=21;
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
            log.debug("���ذ������ֽ�����=" + commandID + "��ʵ��=" + CommandID.CMPP_SUBMIT_REP);
        }

        byte[] seqid = new byte[4];
        System.arraycopy(packet, 8, seqid, 0, 4);
        this.sequenceID = Common.bytes4ToInt(seqid);

        System.arraycopy(packet, 12, msg_Id, 0, 8);

        //        byte[] results = new byte[4];
        //        System.arraycopy(packet, 20, results, 0, 4);
        //        result = Common.bytes4ToInt(results);

        result = packet[20];//cmpp3.0��4���ֽڣ�2.0��1���ֽ�
        log.info("submitResp��Ϣ�����ɹ�,result=" + result + ",sequenceID=" + sequenceID);
    }
}