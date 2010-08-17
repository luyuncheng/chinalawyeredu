/*
 * NAME: com.sxit.cmpp.SubmitBody.java Company:SXIT
 */

package com.sxit.cmpp;

/**
 * cmpp submit��Ϣ���� <br>
 * �����ֶ�Ĭ��ֵ���£� <br>
 * ucPkTotal = 1 <br>
 * ucPkNumber = 1 <br>
 * ucRegister = 0 <br>
 * ucMsgLevel = 1 <br>
 * ucTpPid = 0 <br>
 * ucTpUdhi = 0 <br>
 * ucMsgFmt = 15 <br>
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-29 16:34:57)
 */
public class SubmitBody {

    public SubmitBody() {

        usMsgId = new byte[8];
//        for (int i = 0; i < 8; i++)
//            usMsgId[i] = 0;

    }

    /**
     * ��Ϣ��ʶ����SP����Ķ������ر��������������ա�
     */
    byte usMsgId[];

    /**
     * ��ͬMsg_Id����Ϣ����������1��ʼ
     */
    public byte ucPkTotal = 1;

    /**
     * ��ͬMsg_Id����Ϣ��ţ���1��ʼ
     */
    public byte ucPkNumber = 1;

    /**
     * �Ƿ�Ҫ�󷵻�״̬ȷ�ϱ��棺 <br>
     * 0������Ҫ <br>
     * 1����Ҫ <br>
     * 2������SMC���� �������Ͷ��Ž������ؼƷ�ʹ�ã������͸�Ŀ���ն�)
     */
    public byte ucRegister;

    /**
     * ��Ϣ����
     */
    public byte ucMsgLevel = 1;

    /**
     * ҵ�����ͣ������֡���ĸ�ͷ��ŵ����
     */
    public String sServiceId;

    /**
     * �Ʒ��û������ֶ� <br>
     * 0����Ŀ���ն�MSISDN�Ʒ� <br>
     * 1����Դ�ն�MSISDN�Ʒ� <br>
     * 2����SP�Ʒ� <br>
     * 3����ʾ���ֶ���Ч����˭�ƷѲμ�Fee_terminal_Id�ֶΡ�
     */
    public byte ucFeeUserType;

    /**
     * ���Ʒ��û��ĺ��루�籾�ֽ���գ����ʾ���ֶ���Ч����˭�ƷѲμ�Fee_UserType�ֶΣ����ֶ���Fee_UserType�ֶλ��⣩
     */
    public String sFeeTermId;

    //    /**
    //     * ���Ʒ��û��ĺ������ͣ�0����ʵ���룻1��α�롣
    //     */
    //    public byte fee_terminal_type;

    /**
     * GSMЭ������ ��ϸ�ǽ�����ο�GSM03.40�е�9.2.3.9
     */
    public byte ucTpPid;

    /**
     * GSMЭ������ ��ϸ�ǽ�����ο�GSM03.40�е�9.2.3.23,��ʹ��1λ���Ҷ���
     */
    public byte ucTpUdhi;

    /**
     * ��Ϣ��ʽ <br>
     * 0��ASCII�� <br>
     * 3������д������ <br>
     * 4����������Ϣ <br>
     * 8��UCS2���� <br>
     * 15����GB����
     */
    public byte ucMsgFmt = 15;

    /**
     * ��Ϣ������Դ(SP_Id)
     */
    public String sMsgSrc = "";

    /**
     * �ʷ���� <br>
     * 01���ԡ��Ʒ��û����롱��� <br>
     * 02���ԡ��Ʒ��û����롱��������Ϣ�� <br>
     * 03���ԡ��Ʒ��û����롱��������ȡ��Ϣ�� <br>
     * 04���ԡ��Ʒ��û����롱����Ϣ�ѷⶥ <br>
     * 05���ԡ��Ʒ��û����롱���շ�����SPʵ��
     */
    public String sFeeType = "";

    /**
     * �ʷѴ��루�Է�Ϊ��λ��
     */
    public String sFeeCode = "0";

    /**
     * �����Ч�ڣ���ʽ��ѭSMPP3.3Э��
     */
    public String sValidTime = "";

    /**
     * ��ʱ����ʱ�䣬��ʽ��ѭSMPP3.3Э��
     */
    public String sAtTime = "";

    /**
     * Դ���� SP�ķ�������ǰ׺Ϊ�������ĳ�����, ���ؽ��ú����������SMPPЭ��Submit_SM��Ϣ��Ӧ��source_addr�ֶΣ��ú����������û��ֻ�����ʾΪ����Ϣ�����к���
     */
    public String sSrcTermId = "";

    /**
     * ������Ϣ���û�����(С��100���û�)
     */
    byte ucDestUsrTl;

    /**
     * ���ն��ŵ�MSISDN����,��Ӣ�Ķ���","�ָ��� <br>
     */
    public String sDstTermId;

    //    /**
    //     * ���ն��ŵ��û��ĺ������ͣ�0����ʵ���룻1��α�롣
    //     */
    //    public byte dest_terminal_type;

    /**
     * ��Ϣ����,��usMsgContentȷ��(Msg_FmtֵΪ0ʱ�� <160���ֽڣ����� <=140���ֽ�)
     */
    byte ucMsgLen;

    /**
     * ��Ϣ���ݵ��ֽ�����ʽ���û����Լ�����getBytes()�Ƚ����ݻ�������ͼƬ��ת�����ֽ���
     */
    //    public String usMsgContent;
    public byte[] ucMsgContent;

    /**
     * ����
     */
    public String reserver = "";
    //    /**
    //     * �㲥ҵ��ʹ�õ�LinkID���ǵ㲥��ҵ���MT���̲�ʹ�ø��ֶΡ�
    //     */
    //    public String linkID = "";
}