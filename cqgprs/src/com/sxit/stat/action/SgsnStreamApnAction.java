/**
 * 
 */
package com.sxit.stat.action;

import java.util.List;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.LineChart;

import com.sxit.stat.models.SgsnStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * <pre>
 * SGSN分apn统计
 * SGSN流量分析 中增加到GGSN的分CMWAP和CMNET的流量统计(话单中有GGSN address字段）
 *  比如
 *  SGSN号                      总流量（KEY)     总用户数
 *  SGSN01  GGSN02    CMWAP
 *                    CMNET
 *                    其他
 *          GGSN03    CMWAP
 *                    CMNET
 *                    其他
 *          GGSN04    CMWAP
 *                    CMNET
 *                    其他
 *          GGSN05    CMWAP
 *                    CMNET
 *                    其他
 *          GGSN其他
 * </pre>
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class SgsnStreamApnAction extends StatAction {

	private String sgsnid;
	
	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/*
	 * 得到某一天的各个sgsn的流量 显示柱状图和线图
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		if (startDate == null) {
			startDate = getPrevDate();
			this.start = df.format(startDate);
		}
		pageNo=1;
		pageSize=Integer.MAX_VALUE;
		
		if (resultType.equals("list")){
			this.page = statservice.getDaySgsnStreamGgsnApn(startDate,sgsnid,getOrderby(),pageNo,pageSize);
			this.sgsnlist=page.getItems();
			return SUCCESS;
		}

		else if (resultType.equals("excel")){
			this.page = statservice.getDaySgsnStreamGgsnApn(startDate,sgsnid,getOrderby(),pageNo,pageSize);
			this.sgsnlist=page.getItems();
			return "excel";
		}
		else if (resultType.equals("flash")) {
			sgsnlist = statservice.getDaySgsnStreamApn(startDate);
			if (flashType.equals("bar")) { // 产生柱状图
				this.flashChart = barChart();
			} else { // 产生线图
				this.flashChart = lineChart();
			}
			return "ofc";
		} else {
			this.message = "返回数据类型错误";
			return "message";
		}
	}

	/**
	 * 显示图片时候的网络类型
	 */
	private String apnni = "cmwap";

	private List sgsnlist;

	public List getSgsnlist() {
		return this.sgsnlist;
	}

	/**
	 * @return the apnni
	 */
	public String getNettype() {
		return apnni;
	}

	/**
	 * @param apnni
	 *            the apnni to set
	 */
	public void setNettype(String apnni) {
		this.apnni = apnni;
	}

	private Chart lineChart() {
		double min = 0d;
		double max = 0d;
		LineChart c2 = new LineChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = sgsnlist.size();
		for (int i = 0; i < len; i++) {
			SgsnStatModel stat = (SgsnStatModel) sgsnlist.get(i);

			if (stat.getApnni().equalsIgnoreCase(apnni)) {
				// 总流量
				float value = 0;
				// 总流量
				if (flashby.equals("total"))
					value = (float) stat.getTotalStream();
				else if (flashby.equals("average"))
					value = stat.getAverageStream();
				else if (flashby.equals("user"))
					value = stat.getTotalUser();
				if (value < min)
					min = value;
				if (value > max)
					max = value;
				c2.addValues(value);
				jofc2.model.axis.Label label = new Label();
				label.setText(stat.getSgsnid());
				label.setRotation(ration);
				xlables.addLabels(label);
			}
		}

		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表

		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setXAxisLabels(xlables); // 显示横坐标
		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(min - steps <= 0 ? 0 : (min - steps)); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		flashChart.setYAxis(yaxis);
		Text title = new Text();
		title.setStyle("{font-size:14px;}");

		if (apnni.equalsIgnoreCase("cmwap")) {
			if (flashby.equals("total"))
				title.setText(start + "各SGSN总流量分析(CMWAP)");
			else if (flashby.equals("average")) {
				title.setText(start + "各SGSN平均流量分析(CMWAP)");
			} else if (flashby.equals("user")) {
				title.setText(start + "各SGSN总用户数分析(CMWAP)");
			}
		} else if(apnni.equalsIgnoreCase("cmnet")){
			if (flashby.equals("total"))
				title.setText(start + "各SGSN总流量分析(CMNET)");
			else if (flashby.equals("average")) {
				title.setText(start + "各SGSN平均流量分析(CMNET)");
			} else if (flashby.equals("user")) {
				title.setText(start + "各SGSN总用户数分析(CMNET)");
			}
		}else{
			if (flashby.equals("total"))
				title.setText(start + "各SGSN总流量分析(其他)");
			else if (flashby.equals("average")) {
				title.setText(start + "各SGSN平均流量分析(其他)");
			} else if (flashby.equals("user")) {
				title.setText(start + "各SGSN总用户数分析(其他)");
			}
		}
		flashChart.setTitle(title);

		return flashChart;
	}

	private Chart barChart() {
		double min = 0d;
		double max = 0d;
		BarChart c2 = new BarChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = sgsnlist.size();
		for (int i = 0; i < len; i++) {
			SgsnStatModel stat = (SgsnStatModel) sgsnlist.get(i);
			if (stat.getApnni().equalsIgnoreCase(apnni)) {
				// 总流量
				float value = 0;
				// 总流量
				if (flashby.equals("total"))
					value = (float) stat.getTotalStream();
				else if (flashby.equals("average"))
					value = stat.getAverageStream();
				else if (flashby.equals("user"))
					value = stat.getTotalUser();
				if (value < min)
					min = value;
				if (value > max)
					max = value;
				c2.addValues(value);
				jofc2.model.axis.Label label = new Label();
				label.setText(stat.getSgsnid());
				label.setRotation(ration);
				xlables.addLabels(label);
			}
		}

		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表

		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setXAxisLabels(xlables); // 显示横坐标
		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(min - steps <= 0 ? 0 : (min - steps)); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		flashChart.setYAxis(yaxis);
		Text title = new Text();
		title.setStyle("{font-size:14px;}");
		if (apnni.equalsIgnoreCase("cmwap")) {
			if (flashby.equals("total"))
				title.setText(start + "各SGSN总流量分析(CMWAP)");
			else if (flashby.equals("average")) {
				title.setText(start + "各SGSN平均流量分析(CMWAP)");
			} else if (flashby.equals("user")) {
				title.setText(start + "各SGSN总用户数分析(CMWAP)");
			}
		} else if(apnni.equalsIgnoreCase("cmnet")){
			if (flashby.equals("total"))
				title.setText(start + "各SGSN总流量分析(CMNET)");
			else if (flashby.equals("average")) {
				title.setText(start + "各SGSN平均流量分析(CMNET)");
			} else if (flashby.equals("user")) {
				title.setText(start + "各SGSN总用户数分析(CMNET)");
			}
		}else{
			if (flashby.equals("total"))
				title.setText(start + "各SGSN总流量分析(其他)");
			else if (flashby.equals("average")) {
				title.setText(start + "各SGSN平均流量分析(其他)");
			} else if (flashby.equals("user")) {
				title.setText(start + "各SGSN总用户数分析(其他)");
			}
		}
		flashChart.setTitle(title);
		return flashChart;
	}
}
