/**
 * AbstractManager.java
 */

package com.sxit.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;
import org.springframework.transaction.annotation.Transactional;

import com.sxit.common.exception.ServiceException;

/**
 * @author 华锋 2009-1-5  下午04:57:11
 * 
 */
public class BasicService {

	protected BasicDAO basicDAO;

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}
@Transactional
	public void save(final Object entity) throws ServiceException {
		try {
//			System.out.println("basicDAO===="+basicDAO);
			basicDAO.save(entity);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	


@Transactional
public void saveOrupdate(final Object entity) throws ServiceException {
    try {
        basicDAO.saveOrupdate(entity);
    } catch (Exception e) {
        throw new ServiceException(e);
    }
}
	
@Transactional
	public void update(final Object entity) throws ServiceException {
		try {
			basicDAO.update(entity);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
@Transactional
	public void delete(final String table,final String keycolumn,final Object keyvalue)throws ServiceException {
		
		try {
		
			basicDAO.delete(table,keycolumn,keyvalue);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
@Transactional
	public void delete(final Class entity,final Serializable id) throws ServiceException {
		try {
			Object obj=basicDAO.get(entity, id);
			basicDAO.delete(obj);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
@Transactional
	public void delete(final Object obj)throws ServiceException {
		try {
		
			basicDAO.delete(obj);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public Object load(final Class entity, final Serializable id) throws ServiceException {
		try {
			return basicDAO.load(entity, id);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public Object get(final Class entity, final Serializable id) throws ServiceException {
		try {
			return basicDAO.get(entity, id);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List findAll(final Class entity) throws ServiceException {
		try {
			return basicDAO.findAll(entity);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List find(final String query) throws ServiceException {
		try {
			return basicDAO.find(query);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public List findBySqlQuery(final String sql)throws ServiceException{
		try {
			return basicDAO.findBySqlQuery(sql);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List find(final String query, final Object parameter) throws ServiceException {
		try {
			return basicDAO.find(query, parameter);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 执行一个hql语句
	 * 
	 * @param hql
	 */
	@Transactional
	public void execute(String hql) throws ServiceException {

		try {
			basicDAO.execute(hql);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	public void executeSql(String sql) throws ServiceException {

		try {
			basicDAO.executeSql(sql);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	public int execute(final String hql, final Object value)  throws ServiceException {
		return execute(hql, new Object[] { value });
	}
	@Transactional
	public int execute(final String hql, final Object[] values) throws ServiceException  {
		try {
			return basicDAO.execute(hql, values);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria) throws ServiceException  {
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, 0);
	}

	public PaginationSupport findPageByCriteriaDistinct(final DetachedCriteria detachedCriteria) throws ServiceException  {
		return findPageByCriteriaDistinct(detachedCriteria, PaginationSupport.PAGESIZE, 0);
	}
	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int startIndex) throws ServiceException  {
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, startIndex);
	}

	public PaginationSupport findPageByCriteriaDistinct(final DetachedCriteria detachedCriteria, final int startIndex) throws ServiceException  {
		return findPageByCriteriaDistinct(detachedCriteria, PaginationSupport.PAGESIZE, startIndex);
	}
	/**
	 * 
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 *            查询的最大大小
	 * @param startIndex
	 *            查询的起始位置
	 * @return
	 */
	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) throws ServiceException  {
		try {
			return basicDAO.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PaginationSupport findPageByCriteriaDistinct(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) throws ServiceException  {
		try {
			return basicDAO.findPageByCriteriaDistinct(detachedCriteria, pageSize, pageNo);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List findAllByCriteria(final DetachedCriteria detachedCriteria)  throws ServiceException {
		try {
			return basicDAO.findAllByCriteria(detachedCriteria);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	//@Transactional
	public int getCountByCriteria(final DetachedCriteria detachedCriteria)  throws ServiceException {
		try {
			return basicDAO.getCountByCriteria(detachedCriteria);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public void setCriteriaSpecification(ResultTransformer resultTransformer) {
		basicDAO.setCriteriaSpecification(resultTransformer);
	}
}