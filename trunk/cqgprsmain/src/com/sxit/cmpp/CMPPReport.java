/*
 * NAME: com.sxit.cmpp.CMPPReport.java Company:SXIT
 */
package com.sxit.cmpp;

/**
 * ��������״̬�������
 * @author HuaFeng
 * @version 1.0 (2005-3-30 13:17:21)
 */
public class CMPPReport {
    /**
     * 8λ,��Ϣ��ʶ,SP�ύ���ţ�CMPP_SUBMIT������ʱ����SP������ISMG������Msg_Id��
     */
    public byte[] msg_Id= new byte[8];
    
    //public long msgID;
    /**
     * 7λ,���Ͷ��ŵ�Ӧ������������SMPPЭ��Ҫ����stat�ֶζ�����ͬ�����cmpp3.0Э���ĵ���SP���ݸ��ֶ�ȷ��CMPP_SUBMIT��Ϣ�Ĵ���״̬��
     */
    public String stat;

    /**
     * 10λ,���ŷ���ʱ��,��ʽΪYYMMDDHHMM��YYΪ��ĺ���λ00-99��MM��01-12��DD��01-31��HH��00-23��MM��00-59��
     */
    public String submit_time;

    /**
     * 10λ,���Ŵ���ʱ��,��ʽΪYYMMDDHHMM������ͬ�ϣ�
     */
    public String done_time;

    /**
     * 32λ,Ŀ���ն�MSISDN����(SP����CMPP_SUBMIT��Ϣ��Ŀ���ն�)
     */
    public String dest_terminal_Id;

    /**
     * 4λ,ȡ��SMSC����״̬�������Ϣ���е���Ϣ��ʶ��
     *  
     */
    public int smsc_sequence;
}