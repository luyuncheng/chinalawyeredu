/*
 * NAME: com.sxit.cmpp.TestMO.java Company:SXIT
 */

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cmpp.CMPP;
import com.sxit.cmpp.CMPPActive;
import com.sxit.cmpp.CMPPDeliver;
import com.sxit.cmpp.CMPPReport;
import com.sxit.cmpp.CMPPSocket;
import com.sxit.cmpp.CommandID;
import com.sxit.cmpp.Common;

/**
 * ��������ط�������
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-31 13:48:40)
 */
public class TestMO {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(TestMO.class);

	/**
	 * socket����
	 */
	private static CMPPSocket socket;

	/**
	 * cmpp����
	 */
	private static CMPP cmpp;

	private static String host = "211.139.59.3";

	private static int port = 7910;

	private static String spid = "922059";

	private static String password = "922059";

	private static int delayTime = 30;

	// ���ϲ���Ҳ���Դ������ļ���ȡ��

	public static void main(String args[]) throws Exception {

		socket = new CMPPSocket(host, port);
		// Thread thread = null;
		byte[] resppacket = null;
		int status = -1;
		// ��ʼ��socket����
		while (true) {
			try {
				socket.initialSock();
				// ��socket����ע�ᵽCMPP api��
				cmpp = new CMPP(socket, delayTime);
				// ���������ص�connect����
				status = cmpp.cmppConnect(spid, password);
				log.info("the resoponse of connect to ismg:" + status);
				if (status == 0) {
					log.info("connect successfully!\n");
					break;
				}
				else {
					log.warn("connect failer,result is:" + status + "��reconnect!\n");

				}

			}
			catch (Exception e) {
				try {
					socket.closeSock();
					log.error("connect exception!sleep 5 seconds");
					Thread.sleep(5000L);
				}
				catch (Exception ee) {
					log.error("connect close exception:" + ee.toString());
				}

				log.error(e.toString());
			}
		}

		// ��ʽ�������ط��͹�����
		int count = 0;
		long beginTime = System.currentTimeMillis();
		int commandID = CommandID.CMPP_ACTIVE_TEST;
		while (true) {
//			synchronized (socket) {// ͬ��socket����

				// �����ݾͶ�������û�����ݵĻ����ȴ�50����
				long now = System.currentTimeMillis();
				try {
					int available = socket.getInputStream().available();

					if (available > 0) {
						beginTime = System.currentTimeMillis();
						System.out.println("\n");
						log.info("the" + (++count) + "th message");
						resppacket = socket.read(); // �õ��������ֽ���ʽ
						if (log.isDebugEnabled()) {
							log.debug("the response body length is��" + resppacket.length);
						}
						byte[] commandid = new byte[4];
						System.arraycopy(resppacket, 4, commandid, 0, 4);
						commandID = Common.bytes4ToInt(commandid);
						switch (commandID) {
						case CommandID.CMPP_DELIVER:
							log.info("deliver message");
							deliver(resppacket);
							break;
						case CommandID.CMPP_ACTIVE_TEST:
							log.info("cmppactive message");
							active(resppacket);
							break;
						case CommandID.CMPP_ACTIVE_TEST_REP:
							log.info("cmppactive resp message");
							break;
						case CommandID.CMPP_DELIVER_REP:
							log.info("unresonable message");
							break;
						case CommandID.CMPP_SUBMIT_REP:
							log.info("submitresp");
							break;
						default:
							log.error("wrong commandid:" + commandID);
							break;
						}
					}

					//���1����֮��û���������������active��
					else if (commandID != CommandID.CMPP_ACTIVE_TEST && now - beginTime >= 10 * 1000) {
						beginTime = now;
						log.info("send the active packet!");
						int active = cmpp.cmppActiveTestNoResp();
						log.info("the result of sending active packet"
								+ (active == 0 ? "success" : "failer,active=" + active));
					}
					else {
						// ����50�룬�ȴ���һ��deliver�ĵ���
						try {
							Thread.sleep(50);
						}
						catch (InterruptedException e) {
							log.error(e.toString());
						}
					}
				}
				// ����쳣������ԭ�������ӹص��˵ȣ������½���������
				catch (IOException e) {
					log.error(e.toString());
					try {

						socket.closeSock();// �ر����Ӻ��ڽ�������
						socket.initialSock();
					}
					catch (IOException ee) {
						log.error(ee.toString());
					}

				}
//			}
		}

	}

	private static void deliver(byte[] resppacket) throws IOException {

		// �����ڴ˶���һprivate�ķ������÷�������Ϊһ������߳�
		// �ԵĽ��д���
		CMPPDeliver deliver = new CMPPDeliver();
		deliver.parseResponseBody(resppacket);

		// ����deliverresp

		log.info("sending deliverresp");
		cmpp.cmppDeliverResp(deliver);
		log.info("deliverresp sending successfully");

		System.out.println("            ID:" + com.sxit.cmpp.Common.bytes8ToLong(deliver.msg_Id));
		System.out.println("          dest:" + deliver.dest_Id);
		System.out.println("           msg:" + bytes2str(deliver.msg_Content));
		System.out.println("        format:" + deliver.msg_Fmt);
		System.out.println("deliver length:" + deliver.msg_Length);
		System.out.println("      isreport:" + deliver.registered_Delivery);
		System.out.println("      sequence:" + deliver.getSequenceID());
		System.out.println("        report:" + deliver.report);
		System.out.println("            ID:" + bytes2hex(deliver.msg_Id));
		if (deliver.registered_Delivery == 1) {
			CMPPReport report = deliver.report;
			System.out.println("dest_terminal_Id=" + report.dest_terminal_Id);
			System.out.println("          msg_Id=" + report.msg_Id);
			System.out.println("            stat=" + report.stat);
			System.out.println("     submit_time=" + report.submit_time);
			System.out.println("       done_time=" + report.done_time);
			System.out.println("   smsc_sequence=" + report.smsc_sequence);

		}

	}

	private static String bytes2str(byte b[]) {
		if (b == null || b.length == 0)
			return "";
		String str = "";
		for (int i = 0; i < b.length; i++)
			str += b[i] + " ";
		return str;

	}

	private static void active(byte[] resppacket) throws IOException {
		CMPPActive active = new CMPPActive();
		active.parseResponseBody(resppacket);

		log.info("send activeresp");
		cmpp.cmppActiveResp(active);
		log.info("activeresp send successfully");

	}

	private static String bytes2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			sb.append(Common.byte2hex(b[i]) + " ");
		return sb.toString();
	}

}