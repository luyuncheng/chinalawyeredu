/**
 * LawyerlessonxfService.java
 */

package com.changpeng.jifen.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.dao.LawyerlessonxfDAO;
import com.changpeng.jifen.util.Jifenstatics;
import com.changpeng.jifen.util.LearnmodeStatics;
import com.changpeng.lawyers.dao.LawyersDAO;
import com.changpeng.models.Lawyerlessonxf;

/**
 * @author 华锋 2008-5-4 下午11:55:50
 * 
 */
public class LawyerlessonxfService extends BasicService {

	private LawyerlessonxfDAO lawyerlessonxfDAO;
	private LawyersDAO lawyersDAO;

	/**
	 * @param lawyersDAO
	 *            the lawyersDAO to set
	 */
	public void setLawyersDAO(LawyersDAO lawyersDAO) {
		this.lawyersDAO = lawyersDAO;
	}

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLawyerlessonxfDAO(LawyerlessonxfDAO lawyerlessonxfDAO) {
		this.lawyerlessonxfDAO = lawyerlessonxfDAO;
	}

	/**
	 * 根据lessonid,userid和learnmode得到学分情况
	 * 
	 * @param budengid
	 * @return
	 * @throws ServiceException
	 */
	public Lawyerlessonxf getXuefen(int lessonid, int userid, int learnmode) throws ServiceException {
		try {
			return lawyerlessonxfDAO.getXuefen(lessonid, userid, learnmode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PaginationSupport getJifentongji(Timestamp from, Timestamp end, String officename, String username,
			String lawerno, int pageNo, int pageSize, int isdabiao, Jifenstatics jifenstatics, String field,
			int fieldvalue) throws ServiceException {
		try {
			if (isdabiao == 0)
				return lawyerlessonxfDAO.getJifentongjiAll(from, end, officename, username, lawerno, pageNo, pageSize,
						jifenstatics.getAllusers(), field, fieldvalue);
			else if (isdabiao == 1)
				return lawyerlessonxfDAO.getJifentongjiDabiao(from, end, officename, username, lawerno, pageNo,
						pageSize, jifenstatics.getDabiaoshu(), field, fieldvalue);
			else if (isdabiao == 2)
				return lawyerlessonxfDAO.getJifentongjiNotDabiao(from, end, officename, username, lawerno, pageNo,
						pageSize, jifenstatics.getWeidabiao(), field, fieldvalue);
			else if (isdabiao == 3)
				return lawyerlessonxfDAO.getJifentongjiWeipeixun(from, end, officename, username, lawerno, pageNo,
						pageSize, jifenstatics.getWeipeixun(), field, fieldvalue);
			else
				return lawyerlessonxfDAO.getJifentongjiDabiao(from, end, officename, username, lawerno, pageNo,
						pageSize, jifenstatics.getDabiaoshu(), field, fieldvalue);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	public Jifenstatics getFiledDabiaoshu(Timestamp _from, Timestamp _end, float dabiaofen, String field, int fieldvalue)
			throws ServiceException {
		String sql = "";
		if (field != null && !field.equals("")) {

			sql = "select a.lawyerid,sum(a.pxxf) from lawyerlessonxf a where (UNIX_TIMESTAMP(a.lastupdate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") and a." + field + "=" + fieldvalue
					+ " group by a.lawyerid";
		} else {
			sql = "select a.lawyerid,sum(a.pxxf) from lawyerlessonxf a where (UNIX_TIMESTAMP(a.lastupdate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") group by a.lawyerid";
		}
		List list = lawyerlessonxfDAO.findBySqlQuery(sql);
		int length = list == null ? 0 : list.size();
		int dabiaoshu = 0;
		int weidabiaoshu = 0;
		for (int i = 0; i < length; i++) {
			Object[] obj = (Object[]) list.get(i);
			float xuefen = Float.parseFloat(obj[1].toString());
			// debug(obj[0] + "===" + obj[1]);
			if (xuefen >= dabiaofen) {
				dabiaoshu++;
			} else {
				weidabiaoshu++;
			}
		}
		String lawyerfield = "theoffice";
		if (field.equals("officeid"))
			lawyerfield = "theoffice";
		else if (field.equals("cityid"))
			lawyerfield = "directunion";
		else if (field.equals("provinceid"))
			lawyerfield = "provinceunion";

		int lawyercnt = lawyersDAO.getFieldLawyerCnt(lawyerfield, fieldvalue);
		Jifenstatics jifenstatics = new Jifenstatics();
		jifenstatics.setAllusers(lawyercnt);
		jifenstatics.setWeipeixun(lawyercnt - length);
		jifenstatics.setDabiaoshu(dabiaoshu);
		jifenstatics.setWeidabiao(weidabiaoshu);
		return jifenstatics;
	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public float getLawyerZongjifen(int lawyerid, Timestamp start, Timestamp end) {
		String sql = "select FORMAT(sum(pxxf),2) from lawyerlessonxf where (lastupdate between '" + df.format(start)
				+ "' and '" + df.format(end) + "') and lawyerid=" + lawyerid;
		List list = lawyersDAO.findBySqlQuery(sql);
		System.out.println(list);
		if (list != null && list.size() != 0){
		Object obj=list.get(0);
		if(obj==null)
			return 0;
			return Float.parseFloat(obj.toString());
		}
		return 0;
	}

	public LearnmodeStatics getFiledLearnmode(Timestamp _from, Timestamp _end, String field, int fieldvalue)
			throws ServiceException {

		String sql = "";

		if (field != null && !field.equals("")) {

			sql = "select learnmode,count(learnmode) from lawyerlessonxf where (UNIX_TIMESTAMP(lastupdate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") and " + field + "=" + fieldvalue
					+ " group by learnmode";

		} else {
			sql = "select learnmode,count(learnmode) from lawyerlessonxf where (UNIX_TIMESTAMP(lastupdate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") group by learnmode";
		}
		LearnmodeStatics statics = new LearnmodeStatics();

		List tongjilist = lawyerlessonxfDAO.findBySqlQuery(sql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		// 1现场培训2在线视频3文本课件4积分补登

		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			int learnmode = Integer.parseInt(obj[0].toString());
			if (learnmode == 1) {
				statics.setLocal(Integer.parseInt(obj[1].toString()));
			} else if (learnmode == 1) {
				statics.setVideo(Integer.parseInt(obj[1].toString()));
			} else if (learnmode == 1) {
				statics.setWenbenkejian(Integer.parseInt(obj[1].toString()));
			} else if (learnmode == 1) {
				statics.setBudeng(Integer.parseInt(obj[1].toString()));
			}
		}

		return statics;
	}

}