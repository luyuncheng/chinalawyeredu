/*
 * Created on 2005-7-5 10:13:42
 * 
 */
package com.sxit.cmpp;

/**
 * ���ص���Ϣ����
 * 
 * @author HuaFeng
 * @version 1.0
 */
public abstract class CMPPResponsePacket {

	/**
	 * Logger for this class
	 */
	// private static final Log log =
	// LogFactory.getLog(CMPPResponsePacket.class);
	/**
	 * ������,������ͷ�Ͱ�����ܳ���
	 */
	protected int totalLength;

	/**
	 * ���Ͱ���������id
	 * 
	 * @link #commandID
	 */
	protected int commandID;

	/**
	 * �������кţ�����Ϊ1,�ﵽ���ֵ��ѭ��ʹ��
	 */
	protected int sequenceID;

	/**
	 * �õ��������������
	 * 
	 * @return
	 */
	public int getCommandID() {
		return this.commandID;
	}

	/**
	 * �õ�����������к�
	 * 
	 * @return
	 */
	public int getSequenceID() {
		return this.sequenceID;
	}

	/**
	 * �õ���������ܳ���
	 * 
	 * @return
	 */
	public int getTotalLength() {
		return this.totalLength;
	}

	/**
	 * �������ص����������ֽ���ʽ��
	 * 
	 * @param body
	 */
	public abstract void parseResponseBody(byte[] body);
}