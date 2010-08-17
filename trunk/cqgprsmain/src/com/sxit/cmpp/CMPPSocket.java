/*
 * NAME: com.sxit.cmpp.CMPPSocket.java Company:SXIT
 */
package com.sxit.cmpp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���������ص�socket����
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-31 22:52:58)
 */
public class CMPPSocket {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPSocket.class);

    /**
     * ����������ַ
     */
    private String host;

    /**
     * �����˿�
     */
    private int port;

    /**
     * �������socket��
     */
    private Socket socket;

    /**
     * �����
     */
    private OutputStream os;

    /**
     * ������
     */
    private DataInputStream is;

    /**
     * ���캯��
     * 
     * @param host
     *            ����������ַ
     * @param port
     *            �����˿�
     */
    public CMPPSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * �õ�������
     * 
     * @return DataInputStream
     */
    public DataInputStream getInputStream() {
        return this.is;

    }

    /**
     * ��ʼ��socket����,�趨��ʱʱ��Ϊ5�� <br>
     * ʹ��cmppЭ�������֮ǰ,������ô˷���
     * 
     * @throws CMPPException
     *             ��װ����ʱ�׳���UnknownHostException�Լ�IOException
     */
    public void initialSock() throws IOException {
        try {
            if (socket == null) {
                socket = new Socket(host, port);
            
            }
            socket.setSoTimeout(30 * 1000);
            os = socket.getOutputStream();
            is = new DataInputStream(socket.getInputStream());
            log.info("�ɹ�����������ص�socket����");
        }
        catch (UnknownHostException e) {
            log.error("��ַ\"" + host + "\"δ֪" + e.toString());
            throw e;
        }
        catch (IOException e) {
            log.error("����socket IO�쳣:" + e.toString());

            throw e;
        }

    }

    /**
     * �ر�socket����
     * 
     * @throws CMPPException
     *             ��װ�ر�����ʱ�׳���IOException
     */
    public void closeSock() throws IOException {

        try {
            if (socket != null) {
                socket.close();
                if (os != null)
                    os.close();
                if (is != null)
                    is.close();
            }
            socket = null;
            os = null;
            is = null;
            log.info("socket���ӹرճɹ�");
        }
        catch (IOException e) {
            log.error("socket�ر��쳣:" + e.toString());
            throw e;
        }

    }

    //    /**
    //     * �ж������Ƿ�ر�
    //     *
    //     * @return boolean
    //     */
    //    public boolean isClosed() {
    //        return socket.isClosed();
    //
    //    }
    //
    //    /**
    //     * �ж������Ƿ���ȷ�����ɹ�
    //     *
    //     * @return boolean
    //     */
    //    public boolean isConnected() {
    //        return socket.isConnected();
    //    }
    //
    //    public boolean isInputShutdown() {
    //        return socket.isInputShutdown();
    //    }
    //
    //    public boolean isOutputShutdown() {
    //        return socket.isOutputShutdown();
    //    }

    /**
     * socket�����Ϸ��������
     * 
     * @param packet
     * @throws IOException
     */
    void write(CMPPRequestPacket cmpppacket) throws IOException {

        byte[] packets = cmpppacket.getRequestPacket();

        os.write(packets);
        os.flush();

        log.info(cmpppacket.getRequestBody().getClass().getName() + " ���Ͱ���ɹ�");
    }

    /**
     * socket�����϶�ȡ������
     * 
     * @return ���������ֽ���ʽ
     * @throws IOException
     */
    public byte[] read() throws CMPPException, IOException {
        //��ͷ
        byte[] head = new byte[12];
        byte[] packet = null;
        int reads = -1;
        // Ӧ��������,�ȶ�12���ֽ�,Ȼ�������õ�����,�ٶ������ĳ���
        //        try {
        reads = is.read(head, 0, 12);
        // û�ж����Ļ�
        if (reads == -1) {
            throw new CMPPException("����ͷʱ�������ѿ�,û�ж�������!");
        }
        // �õ����������ܳ��ȣ�Ӧ�ú�is.available��һ��
        byte[] length = new byte[4];
        System.arraycopy(head, 0, length, 0, 4);
        int packetlen = Common.bytes4ToInt(length);

        //�������������ֽ���ʽ
        packet = new byte[packetlen];
        //����ͷ�����������������ֽ���ʽ��
        System.arraycopy(head, 0, packet, 0, 12);
        //������������а���
        if (packetlen - 12 != 0) {
            //            reads = is.read(packet, 12, packetlen - 12);
            is.readFully(packet, 12, packetlen - 12);
            //            if (reads == -1) {
            //                throw new CMPPException("������ʱ�������ѿ�,û�ж�������!");
            //            }
        }
        log.info("������������ȡ���,totalLength=" + packetlen);
        return packet;
        //        }
        //        catch (IOException o) {
        //            log.error(o.getMessage());
        //            throw new CMPPException(o.getMessage());
        //        }
    }
}