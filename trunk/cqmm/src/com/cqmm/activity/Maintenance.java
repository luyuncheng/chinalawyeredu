package com.cqmm.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cqmm.common.SysParams;

/**
 * ����һ��listview
 * 
 * @author ����
 * 
 */
public class Maintenance extends ListActivity {

	public Maintenance() {
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
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, list);

		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME + ")�豸ά��");

		adapter = ArrayAdapter.createFromResource(this,
				R.array.maintencesArray,
				android.R.layout.simple_expandable_list_item_1);
		setListAdapter(adapter);

		
		
		// listView.setAdapter(adapter);

		// setContentView(listView);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		Log.v(SysParams.LOG_TAG, "onListItemClick===>position=>value:"
				+ position + "=>" + adapter.getItem(position));

		
		if (position == 0) {// �豸״̬��ѯ
			Intent intent = new Intent();
			intent.setClass(this, EquipmentState.class);
			startActivity(intent);
		} else if (position == 1) {// �û�״̬��ѯ
			Intent intent = new Intent();
			intent.setClass(this, UserStateQuery.class);
			startActivity(intent);
		} else if (position == 2) {// �û�Ͷ�ߴ���
			// �����Ի���,��ʱ��δ���������
			showDialog(2);
		} else if (position == 3) {// �豸Ӧ�� ����
			showDialog(3);
		} else
			showDialog(4);
		
	
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		
		Log.v(SysParams.LOG_TAG, "onPrepareDialog");
	}

	@Override
	protected Dialog onCreateDialog(int i) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Maintenance.this);

//		builder.setTitle(SysParams.SYS_NAME);
		builder.setCancelable(false);
		if (i == 2)
			builder.setMessage("�û�Ͷ�ߴ�������δʵ��");
		else if (i == 3)
			builder.setMessage("�豸Ӧ�� ����������δʵ��");
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
//		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				Log.v(SysParams.LOG_TAG, "press the exit button");
//				dialog.dismiss();
//			}
//		});

		AlertDialog dialog = builder.create();

		return dialog;

	}

}