/*
 * Created on 2005-7-5 10:15:19
 * 
 */
package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * cmpp�����
 * 
 * @author HuaFeng
 * @version 1.0
 */
public class CMPPRequestPacket {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(CMPPRequestPacket.class);

	/**
	 * ������,������ͷ�Ͱ�����ܳ���
	 */
	private int totalLength;

	/**
	 * ���Ͱ���������id
	 * 
	 * @link #commandID
	 */
	private int commandID;

	/**
	 * �������кţ�����Ϊ1,�ﵽ���ֵ��ѭ��ʹ��
	 */
	private int sequenceID;

	/**
	 * CMPP����
	 */
	private CMPPRequestBody body;

	/**
	 * ����������
	 * 
	 * @param commandID
	 */
	public void setCommandID(int commandID) {
		this.commandID = commandID;
	}

	/**
	 * �������к�
	 * 
	 * @param sequenceID
	 */
	public void setSequenceID(int sequenceID) {
		this.sequenceID = sequenceID;
	}

	/**
	 * ���ð���
	 * 
	 * @param body
	 */
	public void setRequestBody(CMPPRequestBody body) {
		this.body = body;
	}

	/**
	 * ȡ�ð���
	 * 
	 * @return
	 */
	public CMPPRequestBody getRequestBody() {
		return this.body;
	}

	/**
	 * ȡ������cmpp��������ֽ���ʽ
	 * 
	 * @return
	 */
	public byte[] getRequestPacket() {
		log.info(body.getClass().getName() + " ��Ϣ����,sequenceID=" + sequenceID);
		byte[] bodybytes = body.getRequestBody();
		this.totalLength = 12 + bodybytes.length;

		byte[] requestPacket = new byte[totalLength];
		System.arraycopy(Common.intToBytes4(totalLength), 0, requestPacket, 0, 4);
		System.arraycopy(Common.intToBytes4(commandID), 0, requestPacket, 4, 4);
		System.arraycopy(Common.intToBytes4(sequenceID), 0, requestPacket, 8, 4);
		System.arraycopy(bodybytes, 0, requestPacket, 12, bodybytes.length);

		return requestPacket;
	}
}