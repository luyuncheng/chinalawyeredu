/**
 * 
 */
package main.stat;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.util.DBUtils;
import main.util.MainStatUtil;
import main.util.SelfLog;

/**
 * 
 * 按天统计出错情况
 * 
 * @author 华锋 Nov 5, 2009-10:02:00 AM
 * 
 */
public class StatApnError {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHH");


	private Connection con;

	public StatApnError(Connection con) {
		this.con = con;
	}
	
	public  void stathour() {

		
		int prehourend = MainStatUtil.getDateHourTime(new Date());
		int prehourstart = prehourend - 60 * 60;
		
		String hour=df.format(prehourstart*1000L);
		
//		System.out.println(hour);
		
		String errcodesql="insert into stat_apn_error_code (errcode,errcount,usercount,stattime,dayflag) select errcode,count(errcode),count(distinct(imsi)) ,to_char(sysdate,'yyyyMMddHH'),0 from cdr_mistake where opentime between "+prehourstart+" and "+prehourend+" group by errcode";

		String sql="insert into stat_apn_error(apnni,errorcount,usercount,stattime,dayflag,errcode) select reqapnni,count(reqapnni),count(distinct(imsi)),"+hour+",0,errcode from cdr_mistake where reqapnni is not null and (opentime between "+prehourstart+" and "+prehourend+") group by reqapnni,errcode";
		String nullsql="insert into stat_apn_error(apnni,errorcount,usercount,stattime,dayflag,errcode) select '',count(*),count(distinct(imsi)),"+hour+",0,errcode from cdr_mistake where (reqapnni is null or reqapnni='') and (opentime between "+prehourstart+" and "+prehourend+") group by errcode";
		LOG.info("sql::"+sql);
		LOG.info("nullsql::"+nullsql);
		LOG.info("errcodesql::"+errcodesql);
		List<String> sqls = new ArrayList<String>();
		sqls.add(errcodesql);
		sqls.add(sql);
		sqls.add(nullsql);
		try {
			main.util.MainStatUtil.executeSql(con, sqls);
			LOG.info("APN错误代码小时统计入库成功");
		} catch (Exception e) {
			LOG.error("APN错误代码小时统计入库失败", e);
		}

	}

	/**
	 * 
	 */
	public void statday() throws Exception {
		long yetime=MainStatUtil.getYestardayTime();
		long yeendtime=MainStatUtil.getOneDayAfter(yetime);
		
		//明细记录情况
		String sql="insert into stat_apn_error(apnni,errorcount,usercount,stattime,dayflag,errcode) select reqapnni,count(reqapnni),count(distinct(imsi)),to_char(sysdate-1,'yyyyMMdd'),1,errcode from cdr_mistake where reqapnni is not null and (opentime between "+yetime/1000+" and "+yeendtime/1000+") group by reqapnni,errcode";
		String nullsql="insert into stat_apn_error(apnni,errorcount,usercount,stattime,dayflag,errcode) select '',count(*),count(distinct(imsi)),to_char(sysdate-1,'yyyyMMdd'),1,errcode from cdr_mistake where (reqapnni is null or reqapnni='') and (opentime between "+yetime/1000+" and "+yeendtime/1000+") group by errcode";
	
		String errcodesql="insert into stat_apn_error_code (errcode,errcount,usercount,stattime,dayflag) select errcode,count(errcode),count(distinct(imsi)) ,to_char(sysdate-1,'yyyyMMdd'),1 from cdr_mistake where opentime between "+yetime/1000+" and "+yeendtime/1000+" group by errcode";
		
		LOG.info("sql::"+sql);
		LOG.info("nullsql::"+nullsql);
		LOG.info("errcodesql::"+errcodesql);
		List<String> sqls=new ArrayList<String>();
		sqls.add(sql);
		sqls.add(nullsql);
		sqls.add(errcodesql);
		MainStatUtil.executeSql(con, sqls);
	}
	
	public static void main(String args[]) throws Exception {
		SelfLog.LOGDIR = "c:/";
		LOG = SelfLog.getInstance();
		LOG.info("开始统计STAT_APN_ERROR数据");
		Connection con = DBUtils.getOracleCon();
		LOG.info("建立数据库连接成功");
		StatApnError s = new StatApnError(con);
		s.stathour();
		con.close();
	}
//	private static SelfLog LOG =null;
	private static SelfLog LOG = SelfLog.getInstance();
}