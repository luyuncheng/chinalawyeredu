/**
 * 
 */
package com.sxit.netquality.action;

import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 设置或者取消保障的号码
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class CellListAction extends AbstractListAction {

	private String cellid;
private String bscid;
	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		
		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		if (resultType.equals("list")) {
			focuslist = setservice.getFocusCellids();
			this.page = setservice.getCells(cellid, bscid,pageNo, pageSize);
			return SUCCESS;
		} else {
//			this.startIndex = (pageNo - 1) * pageSize;
//			this.page = new PaginationSupport(BasicSetService.ALL_CELL_LIST, BasicSetService.ALL_CELL_LIST.size(),
//					pageSize, startIndex);
			
			this.page = setservice.getCells(cellid, bscid,1, Integer.MAX_VALUE);
			return "excel";
		}
	}

	private String resultType = "list";

	private List focuslist;

	public List getFocuslist() {
		return this.focuslist;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the bscid
	 */
	public String getBscid() {
		return bscid;
	}

	/**
	 * @param bscid the bscid to set
	 */
	public void setBscid(String bscid) {
		this.bscid = bscid;
	}
}