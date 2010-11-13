package com.cqmm.activity;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cqmm.bean.Device;
import com.cqmm.common.CurSession;
import com.cqmm.common.DataService;
import com.cqmm.common.SysParams;

/**
 * ����һ��listview
 * 
 * @author ����
 * 
 */
public class DeviceList extends ListActivity {

	public DeviceList() {
		super();
	}

	List<Device> devices;
	private ArrayAdapter<CharSequence> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		 ȡ����title
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
//		 ����ȫ��
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setTitle(SysParams.SYS_NAME + "(" + CurSession.username + ")�豸ά��");

		new Thread(){
			@Override
			public void run() {
				
				devices=DataService.getDevice();
				if(devices.size()>0){
					Message msg = new Message();
		            Bundle b = new Bundle();// �������
		            b.putString("result", "true");
		            msg.setData(b);

		            DeviceList.this.handler.sendMessage(msg); // ��Handler������Ϣ,����UI
				}
				
			}
		}.start();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

//		Log.v(SysParams.LOG_TAG, "onListItemClick===>position=>value:"
//				+ position + "=>" + adapter.getItem(position));

		Bundle bundle=new Bundle();
		bundle.putInt("deviceid", devices.get(position).getId());
		bundle.putString("devicename", devices.get(position).getDevicename());
		
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(this, CommandList.class);
		startActivity(intent);
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		
		Log.v(SysParams.LOG_TAG, "onPrepareDialog");
	}

	@Override
	protected Dialog onCreateDialog(int i) {
		AlertDialog.Builder builder = new AlertDialog.Builder(DeviceList.this);

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
		

		AlertDialog dialog = builder.create();

		return dialog;

	}
	
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle b = msg.getData();
			if(b.getString("result").equals("true")){
				String[] mStrings=new String[devices.size()];
				Log.i("devices.size();",""+devices.size());
				for(int i=0;i<devices.size();i++){
					mStrings[i]=devices.get(i).getDevicename();
				}
				setListAdapter(new ArrayAdapter<String>(DeviceList.this,
		                android.R.layout.simple_list_item_1, mStrings));
				
			}
		}
	};

}