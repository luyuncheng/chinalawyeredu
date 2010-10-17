package com.cqmm.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
 * �豸״̬��ѯ
 * 
 * @author ����
 * 
 */
public class EquipmentState extends ListActivity {

	public EquipmentState() {
		super();
	}

	private ArrayAdapter<CharSequence> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȡ����title
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME + ")�豸״̬��ѯ");

		adapter = ArrayAdapter.createFromResource(this, R.array.equipStates,
				android.R.layout.simple_expandable_list_item_1);
		setListAdapter(adapter);

		// listView.setAdapter(adapter);

		// setContentView(listView);
	}

	public void onItemSelected(AdapterView parent, View v, int position, long id) {
		// if (position >= 0) {
		// Cursor c = (Cursor) parent.getItemAtPosition(position);
		// mPhone.setText(c.getString(mPhoneColumnIndex));
		// }
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
	
		Log.v(SysParams.LOG_TAG, "onListItemClick===>position=>value:"
				+ position + "=>" + adapter.getItem(position));

		if (position == 0) {// sgsn״̬ ��ѯ
			Intent intent = new Intent();
			intent.setClass(this, SgsnList.class);
			startActivity(intent);
		} else if (position == 1) {// ggsn״̬��ѯ
			showDialog(1);
		} else if (position == 2) {// �û�Ͷ�ߴ���
			// �����Ի���,��ʱ��δ���������
			showDialog(2);
		}else
			showDialog(4);

	}

	@Override
	protected Dialog onCreateDialog(int i) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				EquipmentState.this);

		builder.setTitle(SysParams.SYS_NAME);
		if (i == 1)
			builder.setMessage("GGSN״̬��ѯ������δʵ��");
		else if (i == 2)
			builder.setMessage("CG״̬��ѯ������δʵ��");
		else
			builder.setMessage("�����˴����ѡ��");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.v(SysParams.LOG_TAG, "press the confirm button");
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.v(SysParams.LOG_TAG, "press the exit button");
				dialog.dismiss();
			}
		});

		AlertDialog dialog = builder.create();

		return dialog;

	}
}