package com.changpeng.nonlaw.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表还款记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-10</p>
 * @版本： V1.0
 * @修改：
 */

public class RepaylogListAction extends AbstractListAction  {
        private List repayloglist;
        private long nonlawid;
        private int oprflag;
        
		public long getNonlawid() {
			return nonlawid;
		}
		public void setNonlawid(long nonlawid) {
			this.nonlawid = nonlawid;
		}
		public RepaylogListAction() {
          rights="nlw5,1";
        }
        public String go() throws HibernateException {
                repayloglist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                if(oprflag==1)
                	return "float";
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TnlwRepaylog as repaylog where 1=1";
                if(nonlawid!=0)
                	queryName+=" and tnlwNonlaw.nonlawid="+nonlawid;
                
           	 basicDao.setSession(getSession());
                recordsize=basicDao.getCountOfQuery(queryName);
                
                queryName+=" order by repaylog.repaylogid desc";
                Query query = getSession().createQuery(queryName);
//                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getRepayloglist() {
          return repayloglist;
        }
		public int getOprflag() {
			return oprflag;
		}
		public void setOprflag(int oprflag) {
			this.oprflag = oprflag;
		}
}
