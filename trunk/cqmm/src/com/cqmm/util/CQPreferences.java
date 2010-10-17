package com.cqmm.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.cqmm.common.SysParams;

public class CQPreferences {

	private static SharedPreferences mSharedPreferences;

	public CQPreferences(Context context) {
		mSharedPreferences = context.getSharedPreferences(
				SysParams.PREFERENCES, Context.MODE_PRIVATE);
	}

	/**
	 * ������Ҳ���������
	 * 
	 * @return
	 */
	public String getLatestUserName() {
		String s = mSharedPreferences.getString("logigNames", null);
		if (s == null || s.equals(""))
			return s;
		String[] usernames = s.split(",");
		return usernames[usernames.length - 1];
	}
	/**
	 * 
	 * @param savedLoginName
	 * @return
	 */
	public String getSavedPwd(String savedLoginName){
		return mSharedPreferences.getString(savedLoginName, null);
	}

	/**
	 * �õ����еĵ�¼�ʺ�
	 * 
	 * @return
	 */
	public String[] getAllUserNames() {
		String s = mSharedPreferences.getString("logigNames", null);
		if (s == null || s.equals(""))
			return new String[0];
		return s.split(",");
	}

	/**
	 * ����һ��userName
	 * 
	 * @param userName
	 */
	public void addUserName(String userName) {
		String s = mSharedPreferences.getString("logigNames", null);
	
		if(s==null||s.equals(""))
		{
			s=userName;
		}
		else if (!s.contains(userName)) {
			s = s + "," + userName;
		} else {
			s = s.replace(userName, userName).replace(",,", ",");
			if (s.endsWith(","))
				s = s + userName;
			else
				s = s + "," + userName;
		}
		mSharedPreferences.edit().putString("logigNames", s).commit();
	}

	/**
	 * ����userName�Ͷ�Ӧ������
	 * 
	 * @param userName
	 * @param pwd
	 */
	public void addPwd(String loginName, String pwd) {
		// String s=mSharedPreferences.getString(loginName, null);
		// if(s==null||s.equals(""))
		mSharedPreferences.edit().putString(loginName, pwd).commit();
	}

	/**
	 * ������е�
	 */
	public void clear() {
		mSharedPreferences.edit().clear().commit();
	}
}
