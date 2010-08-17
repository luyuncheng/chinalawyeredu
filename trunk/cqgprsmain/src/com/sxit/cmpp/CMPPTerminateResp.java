/*
 * NAME: com.sxit.cmpp.CMPPTerminateResp.java Company:SXIT
 */

package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����terminate��Ϣ��,���terminateresp��Ϣ
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-30 10:34:12)
 */
public class CMPPTerminateResp extends CMPPResponsePacket implements CMPPRequestBody {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPTerminateResp.class);

    public byte[] getRequestBody() {
        return new byte[0];
    }

    
    public CMPPTerminateResp(){
    	this.commandID=CommandID.CMPP_TERMINATE_REP;
    	this.totalLength=12;
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
            log.debug("���ذ������ֽ�����=" + commandID + "��ʵ��=" + CommandID.CMPP_TERMINATE_REP);
        }

        byte[] seqid = new byte[4];
        System.arraycopy(packet, 8, seqid, 0, 4);
        this.sequenceID = Common.bytes4ToInt(seqid);
        if (log.isDebugEnabled()) {
            log.debug("���ذ����кŽ�����Ϊ:" + sequenceID);
        }
        log.info("CMPPTerminateResp��Ϣ�����ɹ�,sequenceID=" + sequenceID);
    }
}