/*
 * NAME: com.sxit.cmpp.CMPP.java Company:SXIT
 */

package com.sxit.cmpp;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * cmppЭ��api,ͨ�����ô�apiʵ��cmppЭ��ĸ�����Ϣ <br>
 * �ڵ��ô���֮ǰ,�����ȵ���CMPPSocket���initialSock����,ʵ�ֺ�����socket���ӵĳ�ʼ��
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-29 8:59:06)
 */
public class CMPP {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(CMPP.class);

	/**
	 * cmpp��socket����
	 */
	private CMPPSocket socket;

	/**
	 * cmpp�������
	 */

	private CMPPRequestPacket packet;

	/**
	 * response��Ϣ����󷵻�ʱ�䣬��λΪ��
	 */
	private int delayTime;

	/**
	 * ���캯��
	 * 
	 * @param socket
	 *            ���ͺͽ���cmpp��Ϣ��ʱ��ʹ�õ�socket����
	 * @param delayTime
	 *            ����response��Ϣ����󷵻�ʱ��
	 */
	public CMPP(CMPPSocket socket, int delayTime) {
		this.socket = socket;
		this.packet = new CMPPRequestPacket();
		this.delayTime = delayTime;

	}

	/**
	 * ���캯��,Ĭ���ӳ�ʱ��Ϊ10��
	 * 
	 * @param socket
	 */
	public CMPP(CMPPSocket socket) {
		this.socket = socket;
		this.packet = new CMPPRequestPacket();
		this.delayTime = 10;// Ĭ��ʱ��Ϊ10��

	}

	/**
	 * ��cmpp���ؽ�������,ʵ��cmpp3Э���е�connect���� <br>
	 * ������connect�����,����ȡ��connectresp��Ϣ,���жϺ��������ӵ���ȷ��� ���5��֮��û�еõ�������Ϣ,����-1
	 * 
	 * @param spid
	 *            ��ҵ����
	 * @param password
	 *            ����
	 * @param version
	 *            �汾����,ʵ�ֵ���cmpp3.0Э��,��3
	 * @return int 0����ȷ <br>
	 *         1����Ϣ�ṹ�� <br>
	 *         2���Ƿ�Դ��ַ <br>
	 *         3����֤�� <br>
	 *         4���汾̫�� <br>
	 *         >5���������� <br>
	 *         -1������������ <br>
	 * @throws IOException
	 */
	public int cmppConnect(String spid, String password) throws IOException {
		packet.setCommandID(CommandID.CMPP_CONNECT);
		packet.setSequenceID(getSequence());
		packet.setRequestBody(new CMPPConnect(spid, password));
		try {
			// synchronized (socket) {
			socket.write(packet);
			// long beginTime = System.currentTimeMillis();
			CMPPConnectResp resp = new CMPPConnectResp();
			long begin = System.currentTimeMillis();
			// ѭ���ȴ�10��,����10��,��Ϊ�������ŷ���ʧ��
			while (true) {
				long now = System.currentTimeMillis();
				if (socket.getInputStream().available() > 0) {
					if (log.isDebugEnabled())
						log.debug("��connectresp��Ϣʱ����������Ϊ:" + socket.getInputStream().available());
					byte[] packetbytes = socket.read();
					if (packetbytes.length != resp.totalLength) {

						begin = System.currentTimeMillis();// ʱ������
						continue;
					}
					// socket.getInputStream().read();
					resp.parseResponseBody(packetbytes);
					return resp.status;
				}
				else if (now - begin > delayTime * 1000) {
					log.warn("��ȡconnectresp��Ϣʱ����,����-1");
					return -1;
				}
			}
			// }
		}
		catch (IOException e) {
			log.error("��ȡConnectResp��ϢIO�쳣:" + e.toString());
			// throw new CMPPException("connectresp io error:" + e.toString());
			throw e;
		}

	}

	/**
	 * ����submit��Ϣ,������Ϻ�ȴ����submitresp,���жϷ��͵���ȷ���
	 * 
	 * @param sb
	 *            submit����
	 * @param sbresp
	 *            ���ص�submitresp����,ͨ�����õ�sequenceid�Լ�msgid
	 * @return int >0��ʾ�����Ѷ���submit��Ϣ,���ؽ��Ϊsubmiresp��result�ֶ�,
	 *         <0��ʾ���ͻ��߶�ȡ������,���5��֮��û�ж���submitresp��Ϣ,����-1 <br>
	 *         0����ȷ <br>
	 *         1����Ϣ�ṹ�� <br>
	 *         2�������ִ� <br>
	 *         3����Ϣ����ظ� <br>
	 *         4����Ϣ���ȴ� <br>
	 *         5���ʷѴ���� <br>
	 *         6�����������Ϣ�� <br>
	 *         7��ҵ������ <br>
	 *         8���������ƴ� <br>
	 *         9�������ز��������˼ƷѺ��� <br>
	 *         10��Src_Id���� <br>
	 *         11��Msg_src���� <br>
	 *         12��Fee_terminal_Id���� <br>
	 *         13��Dest_terminal_Id���� <br>
	 *         -1������������ <br>
	 * @throws IOException
	 */
	public int cmppSubmit(SubmitBody sb, CMPPSubmitResp sbresp) throws IOException {
		packet.setCommandID(CommandID.CMPP_SUBMIT);
		packet.setSequenceID(getSequence());
		packet.setRequestBody(new CMPPSubmit(sb));
		try {
			// synchronized (socket) {
			socket.write(packet);
			// long beginTime = System.currentTimeMillis();
			long begin = System.currentTimeMillis();
			// ѭ���ȴ�10��,����10��,��Ϊ�������ŷ���ʧ��
			while (true) {
				long now = System.currentTimeMillis();
				if (socket.getInputStream().available() > 0) {
					if (log.isDebugEnabled())
						log.debug("��submitrespʱ�������ɶ�����Ϊ:" + socket.getInputStream().available());
					byte[] packetbytes = socket.read();
					if (packetbytes.length != sbresp.totalLength) {
						begin = System.currentTimeMillis();// ʱ������
						continue;
					}
					sbresp.parseResponseBody(packetbytes);
					return sbresp.result;
				}
				else if (now - begin > delayTime * 1000) {
					log.warn("��ȡ������ʱ����,����-1");
					return -1;
				}
			}
			// }
		}

		catch (IOException e) {
			log.error("submit������ϢIO����:" + e.toString());
			// throw new CMPPException("submitresp io error:" + e.toString());
			// return -2;
			throw e;
		}

	}

	/**
	 * ������·����
	 * 
	 * @return int 0���ɹ� <br>
	 *         -1����÷��ذ��ӳ����� <br>
	 * @throws IOException
	 */
	public int cmppActiveTest() throws IOException {

		packet.setCommandID(CommandID.CMPP_ACTIVE_TEST);
		packet.setSequenceID(getSequence());
		packet.setRequestBody(new CMPPActive());
		try {
			// synchronized (socket) {
			socket.write(packet);
			// long beginTime = System.currentTimeMillis();
			CMPPActiveResp resp = new CMPPActiveResp();
			long begin = System.currentTimeMillis();
			// ѭ���ȴ�10��,����10��,��Ϊ�������ŷ���ʧ��
			while (true) {
				long now = System.currentTimeMillis();
				if (socket.getInputStream().available() > 0) {
					if (log.isDebugEnabled())
						log.debug("��activerespʱ�������ɶ�����Ϊ:" + socket.getInputStream().available());
					byte[] packetbytes = socket.read();
					if (packetbytes.length != resp.totalLength) {
						begin = System.currentTimeMillis();// ʱ������
						continue;
					}
					resp.parseResponseBody(packetbytes);
					return 0;
				}
				else if (now - begin > delayTime * 1000) {
					log.warn("��ȡ������ʱ����,����-1");
					return -1;
				}
			}
			// }
		}

		catch (IOException e) {
			log.error("active��ϢIO����:" + e.toString());
			throw e;
		}
	}

	/**
	 * ������·����������Ҫ������Ϣ
	 * 
	 * @return int 0���ɹ� <br>
	 * @throws IOException
	 */
	public int cmppActiveTestNoResp() throws IOException {

		packet.setCommandID(CommandID.CMPP_ACTIVE_TEST);
		packet.setSequenceID(getSequence());
		packet.setRequestBody(new CMPPActive());
		try {
			// synchronized (socket) {
			socket.write(packet);
			// }
			return 0;
		}

		catch (IOException e) {
			log.error("active��ϢIO����:" + e.toString());
			throw e;
		}
	}

	/**
	 * ���ͶϿ���������.����socket���ӵĹرգ������û��Լ�����CMPPSocket��closeSocket()����
	 * 
	 * @return int <br>
	 *         0:�ɹ��Ͽ����� <br>
	 *         -1:�����ӳ� <br>
	 * @throws IOException
	 */
	public int cmppTerminate() throws IOException {
		packet.setCommandID(CommandID.CMPP_TERMINATE);
		packet.setSequenceID(getSequence());
		packet.setRequestBody(new CMPPTerminate());
		try {
			// synchronized (socket) {
			socket.write(packet);
			// long beginTime = System.currentTimeMillis();
			CMPPTerminateResp resp = new CMPPTerminateResp();
			long begin = System.currentTimeMillis();
			// ѭ���ȴ�10��,����10��,��Ϊ�������ŷ���ʧ��
			while (true) {
				long now = System.currentTimeMillis();
				if (socket.getInputStream().available() > 0) {
					if (log.isDebugEnabled())
						log.debug("��submitrespʱ�������ɶ�����Ϊ:" + socket.getInputStream().available());
					byte[] packetbytes = socket.read();
					if (packetbytes.length != resp.totalLength) {
						begin = System.currentTimeMillis();// ʱ������
						continue;
					}
					resp.parseResponseBody(packetbytes);
					return 0;
				}
				else if (now - begin > delayTime * 1000) {
					log.warn("��ȡ������ʱ����,����-1");
					return -1;
				}
			}
			// }
		}

		catch (IOException e) {
			log.error("terminate��ϢIO����:" + e.toString());
			// return -2;
			throw e;
		}
	}

	/**
	 * ���Ͳ�ѯ��Ϣ���õ���ѯ���
	 * 
	 * @param date
	 *            ��ѯʱ�䣬��ȷ����
	 * @param queryType
	 *            ��ѯ���,0��������ѯ,1����ҵ�����Ͳ�ѯ
	 * @param queryCode
	 *            ��ѯ��.��Query_TypeΪ0ʱ��������Ч(�˴���null)����Query_TypeΪ1ʱ��������дҵ������Service_Id.
	 * @param reserve
	 *            ����
	 * @return CMPPQueryResp ���ز�ѯ���������ӳٻ����׳��쳣������null
	 * @throws IOException
	 */
	// public CMPPQueryResp cmppQuery(Date date, int queryType, String
	// queryCode, String reserve) throws IOException {
	// packet.setCommandID(CommandID.CMPP_QUERY);
	// packet.setSequenceID(getSequence());
	// packet.setRequestBody(new CMPPQuery(date, queryType, queryCode,
	// reserve));
	// try {
	// // synchronized (socket) {
	// socket.write(packet);
	// // long beginTime = System.currentTimeMillis();
	// CMPPQueryResp resp = new CMPPQueryResp();
	// long begin = System.currentTimeMillis();
	// // ѭ���ȴ�10��,����10��,��Ϊ�������ŷ���ʧ��
	// while (true) {
	// long now = System.currentTimeMillis();
	// if (socket.getInputStream().available() > 0) {
	// if (log.isDebugEnabled())
	// log.debug("��cmppqueryRespʱ�������ɶ�����Ϊ:" +
	// socket.getInputStream().available());
	// byte[] packetbytes = socket.read();
	//
	// resp.parseResponseBody(packetbytes);
	// return resp;
	// }
	// else if (now - begin > delayTime * 1000) {
	// log.warn("��ȡ������ʱ����,����-1");
	// return null;
	// }
	// }
	// // }
	// }
	//
	// catch (IOException e) {
	// log.error("terminate��ϢIO����:" + e.toString());
	// throw e;
	// }
	// }
	/**
	 * ɾ��msg_Id�����ص���Ϣ
	 * 
	 * @param msg_Id
	 *            submitresp����������Ϣid
	 * @return 0:ɾ���ɹ� <br>
	 *         -1:��response��Ϣ�ӳ� <br>
	 * @throws IOException
	 */
	// public int cmppCancel(byte[] msg_Id) throws IOException {
	//
	// packet.setCommandID(CommandID.CMPP_QUERY);
	// packet.setSequenceID(getSequence());
	// packet.setRequestBody(new CMPPCancel(msg_Id));
	// try {
	// // synchronized (socket) {
	// socket.write(packet);
	// // long beginTime = System.currentTimeMillis();
	//
	// long begin = System.currentTimeMillis();
	// // ѭ���ȴ�10��,����10��,��Ϊ�������ŷ���ʧ��
	// while (true) {
	// long now = System.currentTimeMillis();
	// if (socket.getInputStream().available() > 0) {
	// if (log.isDebugEnabled())
	// log.debug("��cmppqueryRespʱ�������ɶ�����Ϊ:" +
	// socket.getInputStream().available());
	// byte[] packetbytes = socket.read();
	//
	// return packetbytes[12];
	// }
	// else if (now - begin > delayTime * 1000) {
	// log.warn("��ȡ������ʱ����,����-1");
	// return -1;
	// }
	// }
	// // }
	// }
	//
	// catch (IOException e) {
	// log.error("terminate��ϢIO����:" + e.toString());
	// // return -2;
	// throw e;
	// }
	//
	// }
	/**
	 * ����deliverresp��Ϣ
	 * 
	 * @param deliver
	 * @param mysocket
	 */
	public void cmppDeliverResp(CMPPDeliver deliver) throws IOException {
		packet.setCommandID(CommandID.CMPP_DELIVER_REP);
		packet.setSequenceID(deliver.getSequenceID());
		packet.setRequestBody(new CMPPDeliverResp(deliver.msg_Id, 0));
		try {
			// synchronized (socket) {
			socket.write(packet);
			// long beginTime = System.currentTimeMillis();
			// }
		}
		catch (IOException e) {
			log.error("deliverResp��ϢIO����:" + e.toString());
			throw e;
		}
	}

	/**
	 * ����activeresp��Ϣ
	 * 
	 * @param active
	 */
	public void cmppActiveResp(CMPPActive active) throws IOException {
		packet.setCommandID(CommandID.CMPP_ACTIVE_TEST_REP);
		packet.setSequenceID(active.getSequenceID());
		packet.setRequestBody(new CMPPActiveResp());
		try {
			// synchronized (socket) {
			socket.write(packet);
			// long beginTime = System.currentTimeMillis();
			// }
		}
		catch (IOException e) {
			log.error("cmppActiveResp��ϢIO����:" + e.toString());
			throw e;
		}
	}

	/**
	 * ����terminateresp��Ϣ
	 * 
	 * @param terminate
	 */
	public void cmppTerminateResp(CMPPTerminate terminate) throws IOException {
		packet.setCommandID(CommandID.CMPP_TERMINATE_REP);
		packet.setSequenceID(terminate.getSequenceID());
		packet.setRequestBody(new CMPPTerminateResp());
		try {
			// synchronized (socket) {
			socket.write(packet);
			// long beginTime = System.currentTimeMillis();
			// }
		}
		catch (IOException e) {
			log.error("terminate��ϢIO����:" + e.toString());
			throw e;
		}
	}

	private int sequence = 0;

	/**
	 * ȡ��ÿ�β��������к�,����Ϊ1,�ظ�ʹ��
	 * 
	 * @return int
	 */
	private int getSequence() {
		sequence++;
		if (sequence > 0x7fffffff)
			sequence = 2;
		return sequence;
	}
}