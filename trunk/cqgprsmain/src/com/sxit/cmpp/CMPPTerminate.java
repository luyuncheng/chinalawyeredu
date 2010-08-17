/*
 * NAME: com.sxit.cmpp.CMPPTerminate.java Company:SXIT
 */

package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ��ֹ���ӵ�terminate����,sp->ismg����ismg->sp
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-30 10:09:37)
 */
public class CMPPTerminate extends CMPPResponsePacket implements CMPPRequestBody {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPTerminate.class);

    public byte[] getRequestBody() {
        return new byte[0];
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
            log.debug("���ذ������ֽ�����=" + commandID + "��ʵ��=" + CommandID.CMPP_TERMINATE);
        }

        byte[] seqid = new byte[4];
        System.arraycopy(packet, 8, seqid, 0, 4);
        this.sequenceID = Common.bytes4ToInt(seqid);
        if (log.isDebugEnabled()) {
            log.debug("���ذ����кŽ�����Ϊ:" + sequenceID);
        }
        log.info("CMPPTerminate��Ϣ�����ɹ�,sequenceID=" + sequenceID);
    }
}