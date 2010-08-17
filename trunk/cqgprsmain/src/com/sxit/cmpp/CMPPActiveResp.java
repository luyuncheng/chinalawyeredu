/*
 * NAME: com.sxit.cmpp.CMPPActiveResp.java Company:SXIT
 */
package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ��·����������Ϣ
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-31 14:38:43)
 */
public class CMPPActiveResp extends CMPPResponsePacket implements CMPPRequestBody {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPActiveResp.class);

    public CMPPActiveResp(){
    	this.commandID=CommandID.CMPP_ACTIVE_TEST_REP;
    	this.totalLength=12;
    }
    
    /**
     * ������
     */
    byte reserve;

    public byte[] getRequestBody() {
        return new byte[] { reserve };
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
            log.debug("���ذ������ֽ�����=" + commandID + "��ʵ��=" + CommandID.CMPP_ACTIVE_TEST_REP);
        }

        byte[] seqid = new byte[4];
        System.arraycopy(packet, 8, seqid, 0, 4);
        this.sequenceID = Common.bytes4ToInt(seqid);
        if (log.isDebugEnabled()) {
            log.debug("���ذ����кŽ�����Ϊ:" + sequenceID);
        }
        reserve = packet[12];
        log.info("CMPPActiveResp��Ϣ�����ɹ�,sequenceID=" + sequenceID);
    }
}