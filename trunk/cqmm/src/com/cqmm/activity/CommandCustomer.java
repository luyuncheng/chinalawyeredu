package com.cqmm.activity;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cqmm.bean.CmdLog;
import com.cqmm.bean.Command;
import com.cqmm.bean.Device;
import com.cqmm.common.CurSession;
import com.cqmm.common.DataService;
import com.cqmm.common.Requests;
import com.cqmm.common.SysParams;

public class CommandCustomer extends Activity {

	public CommandCustomer() {
		super();
	}

	List<Command> commands;
	private ArrayAdapter<CharSequence> adapter;
	ListView lv;
	TextView tv;
	int[] commandid;
	int curcmdid;
	String curcmdname;
	String cmdresult;
	
	ProgressDialog pdialog;
	
	Spinner sp_device_ex;
	
	String[] devicelist_name;
	
	EditText et_input_cmd;
	Button btn_exe_cmd;
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
		setContentView(R.layout.commandlist);
		et_input_cmd=(EditText)findViewById(R.id.et_input_cmd);
		btn_exe_cmd=(Button)findViewById(R.id.btn_exe_cmd);
		et_input_cmd.setVisibility(View.VISIBLE);
		btn_exe_cmd.setVisibility(View.VISIBLE);
		
		lv=(ListView)findViewById(R.id.command_list);
		lv.setVisibility(View.GONE);
		
		tv=(TextView)findViewById(R.id.command_result);
		sp_device_ex=(Spinner)findViewById(R.id.sp_device_ex);
		
		sp_device_ex.setVisibility(View.VISIBLE);
		findViewById(R.id.sp_device_title).setVisibility(View.VISIBLE);
		//��ʼ�� ִ�н��������
		String curResult=DataService.cmd_reslut_map.get(CommandList.deviceid);
		if(curResult==null){
			curResult="";
		}
		tv.setText(curResult);
		
//		new Thread(){
//			@Override
//			public void run() {
//				 commands=DataService.getCommand(CommandList.deviceid,Command.TYPE_COMMON);
//				 if(commands.size()>0){
//					Message msg = new Message();
//		            Bundle b = new Bundle();// �������
//		            b.putString("result", "cmdlist");
//		            msg.setData(b);
//		            
//		            CommandCustomer.this.handler.sendMessage(msg); // ��Handler������Ϣ,����UI
//				}
//				
//			}
//		}.start();
		
		
		
		//��ʼ�������豸�б�
		devicelist_name=new String[DataService.open_devices.size()];
		int i=0;
		for(Device device:DataService.open_devices){
			devicelist_name[i++]=device.getDevicename();
		}
		ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,devicelist_name);
		sp_device_ex.setAdapter(adapter);
		sp_device_ex.setVisibility(View.VISIBLE);
        
		//���������豸��ѡ���¼�
		sp_device_ex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
//				updatecmdlist();
				Device curDevice=DataService.open_devices.get(position);
				if(curDevice.getId()!=CommandList.deviceid){
					Bundle bundle=new Bundle();
	        		bundle.putInt("deviceid", curDevice.getId());
	        		bundle.putString("devicename", curDevice.getDevicename());
	        		bundle.putInt("curTab", 0);
	        		
	        		Intent intent = new Intent();
	        		intent.putExtras(bundle);
	        		intent.setClass(CommandCustomer.this, CommandList.class);
	        		startActivity(intent);
	        		finish();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//�Զ�������ִ��
		this.btn_exe_cmd.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				pdialog = new ProgressDialog(CommandCustomer.this);   
//				pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pdialog.setMessage("����ִ��");
				pdialog.show();
				
				lv.setClickable(false);
				
				curcmdname=et_input_cmd.getText().toString();
				new Thread(){
					@Override
					public void run() {
						 cmdresult=Requests.execmd(CommandList.deviceid, curcmdname);
//						 if(commands.size()>0){
							Message msg = new Message();
				            Bundle b = new Bundle();// �������
				            b.putString("result", "cmdresult");
				            msg.setData(b);
				            
				            CommandCustomer.this.handler.sendMessage(msg); // ��Handler������Ϣ,����UI
//						}
				            pdialog.cancel();
						
					}
				}.start();
			}
			
		});
	}

	
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle b = msg.getData();
			if(b.getString("result").equals("cmdlist")){
				String[] mStrings=new String[commands.size()];
				Log.i("commands.size();",""+commands.size());
				for(int i=0;i<commands.size();i++){
					mStrings[i]=commands.get(i).getCommandname();
				}
				lv.setAdapter(new ArrayAdapter<String>(CommandCustomer.this,
		                android.R.layout.simple_list_item_1, mStrings));
				lv.setVisibility(View.VISIBLE);
				lv.setOnItemClickListener(new ListView.OnItemClickListener() {
				
					//���Ҫִ�е�����
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						pdialog = new ProgressDialog(CommandCustomer.this);   
//						pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						pdialog.setMessage("����ִ��");
						pdialog.show();
						
						lv.setClickable(false);
						curcmdid=commands.get(position).getId();
						curcmdname=commands.get(position).getCommandname();
						new Thread(){
							@Override
							public void run() {
								 cmdresult=Requests.execmd(CommandList.deviceid, curcmdid);
//								 if(commands.size()>0){
									Message msg = new Message();
						            Bundle b = new Bundle();// �������
						            b.putString("result", "cmdresult");
						            msg.setData(b);
						            
						            CommandCustomer.this.handler.sendMessage(msg); // ��Handler������Ϣ,����UI
//								}
						            pdialog.cancel();
								
							}
						}.start();
					}
				
				});
			}else if(b.getString("result").equals("cmdresult")){
				
				//��������������ִ�н��
				lv.setClickable(true);
				
				String curResult=DataService.cmd_reslut_map.get(CommandList.deviceid);
				if(curResult==null){
					curResult=cmdresult;
				}else{
					curResult=curResult+"\r\n"+cmdresult;
				}
				DataService.cmd_reslut_map.put(CommandList.deviceid, curResult);
				tv.setText(curResult);
				
				CmdLog log=new CmdLog();
				log.setDevicename(CommandList.devicename);
				log.setOptname(curcmdname);
				log.setResult(cmdresult);
				log.setUserid(CurSession.userid);
				DataService.insertLog(CommandCustomer.this, log);
			}
		}
	};

}