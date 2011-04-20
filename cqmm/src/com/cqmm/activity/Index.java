package com.cqmm.activity;

import com.cqmm.common.CurSession;
import com.cqmm.common.SysParams;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * 2��tab,һ����ϵͳά����һ���Ǳ��ؼ�¼
 * 
 * @author ����
 * 
 */
public class Index extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final TabHost tabHost = getTabHost();
		
		setTitle(SysParams.SYS_NAME + "(" + CurSession.username + ")ά��������");
	
		
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("�����豸")
				.setContent(new Intent(this, DeviceList.class)));

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("���ؼ�¼")
				.setContent(
						new Intent(this, LocalHistory.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
	}
}