package com.cqmm.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cqmm.common.SysParams;

/**
 * checkbox�ķ�ʽ��ʾ8��checkbox�б�
 * 
 * @author ����
 * 
 */
public class SgsnList extends ListActivity {

	public SgsnList() {
		super();
	}

	private ArrayAdapter<CharSequence> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		// ȡ����title
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
//		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME + ")SGSN״̬��ѯ");

		adapter = ArrayAdapter.createFromResource(this,
				R.array.sgsns,
				android.R.layout.simple_list_item_multiple_choice);
		setListAdapter(adapter);
		
		//sgsn���Զ�ѡ
		ListView listView=getListView();
		listView.setItemsCanFocus(false);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}

	public void onItemSelected(AdapterView parent, View v, int position, long id) {
//		if (position >= 0) {
//			Cursor c = (Cursor) parent.getItemAtPosition(position);
//			mPhone.setText(c.getString(mPhoneColumnIndex));
//		}
	}

	//
	// public void onNothingSelected(AdapterView parent) {
	// mPhone.setText(R.string.list_7_nothing);
	//
	// }
@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		this.setTitle(adapter.getItem(position));
		Log.v(SysParams.LOG_TAG, "onListItemClick===>position=>value:"
				+ position + "=>" + adapter.getItem(position));
		
		if(position==1){
			Intent intent = new Intent();
			intent.setClass(this, LocalHistory.class);
			startActivity(intent);
		}
		
	}

}