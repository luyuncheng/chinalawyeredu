/**
 * GetLessonRequest.java
 */

package com.changpeng.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class GetClassRegRequest extends ElearningRequests {

	private static final Log LOG = LogFactory.getLog(GetClassRegRequest.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private long lastyear() {
		Calendar c = Calendar.getInstance();

		c.add(Calendar.MONTH, -3);
		return c.getTimeInMillis();
	}

	public String requestService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		StringBuilder temp = new StringBuilder();
		StringBuilder lawyertemp = new StringBuilder();

		try {
			BasicService basicService = (BasicService) globals.getBean("basicService");
			List peixunlist = new ArrayList();

			// 状态为培训课程,并且是3个月内新增的课程
			DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(Lessons.class).add(
					Restrictions.eq("lessonstate", (byte) 1));
			detachedCriteria1.add(Restrictions.ge("createtime", new Timestamp(lastyear())));

			List _list = basicService.findAllByCriteria(detachedCriteria1);
			int lessonsize = _list == null ? 0 : _list.size();
			for (int i = 0; i < lessonsize; i++) {
				Lessons lesson = (Lessons) _list.get(i);
				peixunlist.add(lesson.getLessonid());
			}

			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
			detachedCriteria.createAlias("lawer", "lawer").add(Restrictions.eq("lawer.roleid", 1));
			detachedCriteria.add(Restrictions.in("lessonid", peixunlist));
			detachedCriteria.add(Restrictions.eq("learnmode", "现场培训"));
			detachedCriteria.add(Restrictions.ge("lastupdate", new Timestamp(lastyear())));

			// 培训方式只是现场培训

			List list = basicService.findAllByCriteria(detachedCriteria);
			int xflen = list == null ? 0 : list.size();

			temp.append("<respcode>").append(xflen).append("</respcode>");
			temp.append("<respmsg>").append("此课程有" + xflen + "已参加了培训").append("</respmsg>");

			lawyertemp.append("<classregs>");
			for (int i = 0; i < xflen; i++) {
				Lawyerlessonxf xf = (Lawyerlessonxf) list.get(i);
				lawyertemp.append("<classreg>");
				lawyertemp.append("<dotime>").append(df.format(xf.getLastupdate())).append("</dotime>");
				lawyertemp.append("<lawyerno>").append(xf.getLawer().getLawerno()).append("</lawyerno>");
				lawyertemp.append("<lessonid>").append(xf.getLessonid()).append("</lessonid>");
				lawyertemp.append("<regtype>").append(xf.getLearnmode()).append("</regtype>");
				lawyertemp.append("</classreg>");
			}
			lawyertemp.append("</classregs>");

		} catch (Exception e) {
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>获取该课程已培训信息异常:").append(e.getMessage()).append("</respmsg>");
		}
		result.append(temp);
		result.append(lawyertemp);
		result.append("</response>");
		return result.toString();
	}
}
