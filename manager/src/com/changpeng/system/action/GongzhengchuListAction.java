/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * 
 * 公证处列表，显示所有
 * 
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class GongzhengchuListAction extends AbstractListAction {

	private String groupname;

	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}

	/**
	 * @param groupname
	 *            the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	private String groupenname;

	// private SysGroupService groupservice = (SysGroupService)
	// this.getBean("sysGroupService");

	public GongzhengchuListAction() {
		this.datavisible = new DataVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 * 
	 * 根据parentid得到下面一层的所有group;
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		// 也显示全国律协的以及系统层级的
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		SysGroup mygroup = this.getLoginUser().getSysGroup();
		if (mygroup != null && mygroup.getGrouptype() == 1) {
			this.message = "您没有查看公证处列表的权利,请返回";
			this.nextPage = "javascript:history.go(-1)";
			return "message";
		}

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysGroup.class).add(
				Restrictions.like("groupname", "公证处", MatchMode.END)).add(Restrictions.eq("grouptype", 1)).add(
				Restrictions.eq("delflag", false));
		;

		if (groupname != null && !"".equals(groupname)) {
			detachedCriteria.add(Restrictions.like("groupname", groupname.trim(), MatchMode.ANYWHERE));
		}
		if (groupenname != null && !"".equals(groupenname)) {
			detachedCriteria.add(Restrictions.like("groupenname", groupenname.trim(), MatchMode.ANYWHERE));
		}

		if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("directgroup", datavisible.getProvinceid()));
		}
		if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("parentid", datavisible.getCityid()));
		}

		detachedCriteria.addOrder(Order.desc("groupid"));

		BasicService service = (BasicService) this.getBean("basicService");
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		SysUser user = this.getLoginUser();
		if (user.getLoginname().equals("admin") || user.getRightList().contains("sysGroupExcludeRight")) {
			hasright = true;
		}

		return SUCCESS;
	}

	/**
	 * @return the groupenname
	 */
	public String getGroupenname() {
		return groupenname;
	}

	/**
	 * @param groupenname
	 *            the groupenname to set
	 */
	public void setGroupenname(String groupenname) {
		this.groupenname = groupenname;
	}

	private boolean hasright;

	public boolean getHasright() {
		return this.hasright;
	}

	private boolean candel = true;
	private boolean canupd = true;
	private boolean canins = true;

	/**
	 * @return the candel
	 */
	public boolean getCandel() {
		return candel;
	}

	/**
	 * @return the canupd
	 */
	public boolean getCanupd() {
		return canupd;
	}

	/**
	 * @return the canins
	 */
	public boolean getCanins() {
		return canins;
	}

}