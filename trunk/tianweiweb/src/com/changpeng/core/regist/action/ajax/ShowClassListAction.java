/**
 * 
 */
package com.changpeng.core.regist.action.ajax;



import com.changpeng.common.action.AbstractAction;



/**
 * 显示班级
 * @author 肖云亮
 * 2009-5-15 下午02:04:58
 */
public class ShowClassListAction extends AbstractAction {
	/**
	 * 
	 */
//	Logger log = Logger.getLogger(ShowClassListAction.class);
	private static final long serialVersionUID = 1L;
	public ShowClassListAction() {
		this.rightCode = "PUBLIC";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		return SUCCESS;
	}

	@Override
	protected String getin() {
		// TODO Auto-generated method stub
		return INPUT;
	}
	
}
