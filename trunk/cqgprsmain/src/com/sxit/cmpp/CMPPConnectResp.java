/*
 * NAME: com.sxit.cmpp.ConnectResp.java Company:SXIT
 */

package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cmpp.Common;

/**
 * cmppconnectresp��Ϣ
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-29 10:54:53)
 */
public class CMPPConnectResp extends CMPPResponsePacket {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPConnectResp.class);

    /**
     * ���ص�״̬,1λ
     */
    public int status;

    /**
     * ���ص���֤��
     */
    public String authenticaion;

    /**
     * ismg���ص�Э��汾
     */
    public int version;

    public CMPPConnectResp(){
    	this.commandID=CommandID.CMPP_CONNECT_REP;
    	this.totalLength=30;
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
            log.debug("���ذ������ֽ�����=" + commandID + "��ʵ��=" + CommandID.CMPP_CONNECT_REP);
        }

        byte[] seqid = new byte[4];
        System.arraycopy(packet, 8, seqid, 0, 4);
        this.sequenceID = Common.bytes4ToInt(seqid);
        if (log.isDebugEnabled()) {
            log.debug("���ذ����кŽ�����Ϊ:" + sequenceID);
        }

        //        byte stat[] = new byte[4];
        //        System.arraycopy(packet, 12, stat, 0, 4);
        //        status = Common.bytes4ToInt(stat);
        status = packet[12]; //cmpp3.0��4���ֽڣ�2.0ֻ��1���ֽ�

        byte[] authen = new byte[16];
        System.arraycopy(packet, 13, authen, 0, 16);
        this.authenticaion = new String(authen);

        version = packet[29];
        log.info("connectResp��Ϣ�����ɹ�,status=" + status + ",sequenceID=" + sequenceID);
    }
}