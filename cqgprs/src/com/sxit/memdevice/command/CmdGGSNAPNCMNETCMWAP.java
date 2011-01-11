package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Hibernate;

import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.common.Client;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;



/**
 * 
 * @author 孟凡强
 * Dec 9, 2010 5:05:14 PM  --------------未完成
 * 命令名称：CMNET流量查询
         CMWAP 流量查询
         其他APN流量查询
命令位置：在菜单GGSN—标准维护命令下

原始命令解析
show services ggsn  statistics apn  cmnet         
show services ggsn  statistics apn  cmwap
show services ggsn  statistics apn  (smjt.cq) 需要扩内输入  

原始输出结果举例如下：



解析要求：截取结果的部分字段
           APN :cmnet
         Actie PDP contexts:14334     
         IP-address pool statistics:
           AVAILABLE IP-addresses:115287
           IP-address in quarantine:1445
         Uplink traffic:
          Pacekets:234434
          BYTES:2200000
          DROPPED PACKETS:3324
         Downlink traffic
          Pacekets:2344342
          BYTES:22000002
          DROPPED PACKETS:33241
         Radius statistics:
          Failed Radius Authentication procedure :0
          Failed Radius Accounting procedure :0
展示结果：
           （时间戳）2010-11-18 10:00:02查询
               APN :cmnet
         Actie PDP contexts:14334     
         IP-address pool statistics:
           AVAILABLE IP-addresses:115287
           IP-address in quarantine:1445
         Uplink traffic:
          Pacekets:234434
          BYTES:2200000
          DROPPED PACKETS:3324
         Downlink traffic
          Pacekets:2344342
          BYTES:22000002
          DROPPED PACKETS:33241
         Radius statistics:
          Failed Radius Authentication procedure :0
          Failed Radius Accounting procedure :0

 */
public class CmdGGSNAPNCMNETCMWAP implements Command {
	
	public String getresult(String orgstr) {
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String result=nowstr+"查询 \r\n";
		boolean allok=true;
		String[] rs=orgstr.split("\r\n");
		for(String s:rs){
			if(!s.substring(s.lastIndexOf(":")).trim().equals("0")){
				result+=s+"\r\n";
			}
		}
		
		
		return result;
	}
	
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		String orgresult_cmnet=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),"show services ggsn  statistics apn  cmnet");
		String orgresult_cmwap=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),"show services ggsn  statistics apn  cmwap");
		String orgresult_apn=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),"show services ggsn  statistics apn  (smjt.cq)");
		String result=orgresult_cmnet+"\r\n"+orgresult_cmwap+"\r\n"+orgresult_apn;
		
		

		MemLog log=new MemLog();
		log.setCommandid(command.getCommandid());
		log.setCommandname(command.getCommananame());
		log.setCreatetime(new Timestamp(System.currentTimeMillis()));
		log.setDeviceid(device.getDeviceid());
		log.setDevicename(device.getDevicename());
		log.setResult(result);
		log.setOrgresult(Hibernate.createClob(result));
		log.setUserid(Integer.parseInt(userid));
		System.out.println(log.getCommandid()+","+log.getCommandname()+","+log.getCreatetime()+","+log.getDeviceid()+","+log.getDevicename()+","+log.getResult()+","+log.getUserid());
		try{
			memservice.save(log);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args){
		String ss="";
		String r=new CmdGGSNAPNCMNETCMWAP().getresult(ss);
		System.out.println("==============\r\n"+r);
	}
}
