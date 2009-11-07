/**
 * 
 */
package com.sxit.query.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractAction;
import com.sxit.query.model.SuccCdr;
import com.sxit.stat.util.StatUtil;

/**
 * @author 华锋 Nov 5, 2009-10:32:58 PM
 * 
 */
public class QueryService {
	private static Log _LOG = LogFactory.getLog(QueryService.class);

	private JdbcTemplate jdbcTemplate;


	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PaginationSupport queryCdr(Date date, String mobile, String cellid, String apnni, final int pageNo,
			final int pageSize) {
		String table = StatUtil.getCdrTable(date);
		int totalCount = 0;
		int startIndex = (pageNo - 1) * pageSize;
		String cntsql = "select count(*) as cnt from " + table + " where 1=1 ";
		String search = " where 1=1";
		if (mobile != null && !mobile.equals("")) {
			cntsql += " and msisdn='" + mobile + "'";
			search += " and msisdn='" + mobile + "'";
		}
		if (cellid != null && !cellid.equals("")) {
			cntsql += " and cellid='" + cellid + "'";
			search += " and cellid='" + cellid + "'";
		}
		if (apnni != null && !apnni.equals("")) {
			cntsql += " and apnni='" + apnni + "'";
			search += " and apnni='" + apnni + "'";
		}

		if (pageSize != Integer.MAX_VALUE) {
			totalCount = jdbcTemplate.queryForInt(cntsql);
		}

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from  " + table + search
				+ " ) A WHERE ROWNUM <= " + (startIndex + pageSize) + " )  WHERE RN >= " + startIndex;

		_LOG.debug("查询SQL:"+sql);
		
		// String sql = "select * from(select APNNI,STATTIME,USERCOUNT,ALLVOLUME
		// from STAT_APN where STATTIME="+_date+" and dayflag=1)";

		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				List list = new ArrayList();
				while (rs.next()) {
					SuccCdr model = new SuccCdr();
					model.setApnni(rs.getString("apnni"));
					model.setApnoi(rs.getString("apnoi"));
					model.setCellid(rs.getString("cellid"));
					model.setChangetime(rs.getLong("changetime"));
					model.setDownvolume(rs.getFloat("downvolume"));
					model.setDuration(rs.getInt("duration"));
					model.setImei(rs.getString("imei"));
					model.setImsi(rs.getString("imsi"));
					model.setLac(rs.getString("lac"));
					model.setMsisdn(rs.getString("msisdn"));
					model.setNettype(rs.getString("nettype"));
					model.setOpentime(rs.getLong("opentime"));
					model.setRecordtime(rs.getLong("recordtime"));
					model.setSgsnid(rs.getString("sgsnid"));
					model.setUpvolume(rs.getFloat("upvolume"));

					list.add(model);
				}
				return list;
			}
		});
		List list = (List) object;
		if (pageSize == Integer.MAX_VALUE) {
			totalCount = list.size();
		}
		// PaginationSupport ps=new PaginationSupport();
		PaginationSupport ps = new PaginationSupport(list, totalCount, pageSize, startIndex);
		return ps;
	}

}
