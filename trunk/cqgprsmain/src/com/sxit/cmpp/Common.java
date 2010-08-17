/*
 * NAME: com.sxit.cmpp.Common.java Company:SXIT
 */

package com.sxit.cmpp;

/**
 * ������
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-28 23:00:06)
 */
public final class Common {
    private static final char chars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };

    public static String byte2hex(byte b) {
        char hex[] = new char[2];
        hex[0] = chars[(new Byte(b).intValue() & 0xf0) >> 4];
        hex[1] = chars[new Byte(b).intValue() & 0xf];
        return new String(hex);
    }
    public static String bytes2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byte2hex(b[i]));
            sb.append(" ");
        }
        return sb.toString();
    }
    /**
     * ���ַ���ת�����ض����ȵ�byte[],���value�ĳ���С��idx,���Ҳ��㡣���� getText(5,"1"),���Ϊ{49,0,0,0,0}; ���value�ĳ��ȴ���idx,���ȡ��һ���֡�����
     * getText(2,"11111"),���Ϊ{49,49};
     * 
     * @param idx
     *            ת����byte[]�ĳ���
     * @param value
     *            Ҫת�����ַ���
     * @return byte[]
     */
    public static byte[] getText(int idx, String value) {
        byte[] b1 = new byte[idx];
        int i = 0;
        if (value != null || !value.equals("")) {
            byte[] b2 = value.getBytes();
            while (i < b2.length && i < idx) {
                b1[i] = b2[i];
                i++;
            }
        }
        while (i < b1.length) {
            b1[i] = 0;
            i++;
        }
        return b1;
    }

    /**
     * 8λ��byte[]����ת����long��
     * 
     * @param mybytes
     * @return long
     */
    public static long bytes8ToLong(byte mybytes[]) {
        long tmp = (0xff & mybytes[0]) << 56 | (0xff & mybytes[1]) << 48 | (0xff & mybytes[2]) << 40
                | (0xff & mybytes[3]) << 32 | (0xff & mybytes[4]) << 24 | (0xff & mybytes[5]) << 16
                | (0xff & mybytes[6]) << 8 | 0xff & mybytes[7];
        return tmp;
    }

    /**
     * long����ת����8���ֽ�
     * 
     * @param i
     *            Ҫת���ĳ�����
     * @return byte[]
     */
    public static byte[] longToBytes8(long i) {
        byte mybytes[] = new byte[8];
        mybytes[7] = (byte) (int) ((long) 255 & i);
        mybytes[6] = (byte) (int) (((long) 65280 & i) >> 8);
        mybytes[5] = (byte) (int) (((long) 0xff0000 & i) >> 16);
        mybytes[4] = (byte) (int) (((long) 0xff000000 & i) >> 24);
        int high = (int) (i >> 32);
        mybytes[3] = (byte) (0xff & high);
        mybytes[2] = (byte) ((0xff00 & high) >> 8);
        mybytes[1] = (byte) ((0xff0000 & high) >> 16);
        mybytes[0] = (byte) ((0xff000000 & high) >> 24);
        return mybytes;
    }

    /**
     * intת����4���ֽڵ�����
     * 
     * @param i
     *            Ҫת�������α���
     * @return byte[]
     */

    public static byte[] intToBytes4(int i) {
        byte mybytes[] = new byte[4];
        mybytes[3] = (byte) (0xff & i);
        mybytes[2] = (byte) ((0xff00 & i) >> 8);
        mybytes[1] = (byte) ((0xff0000 & i) >> 16);
        mybytes[0] = (byte) ((0xff000000 & i) >> 24);
        return mybytes;
    }

    /**
     * byte����ת����int����
     * 
     * @param mybytes
     *            Ҫת����
     * @return int
     */
    public static int bytes4ToInt(byte mybytes[]) {
        int tmp = (0xff & mybytes[0]) << 24 | (0xff & mybytes[1]) << 16 | (0xff & mybytes[2]) << 8 | 0xff & mybytes[3];
        return tmp;
    }
}