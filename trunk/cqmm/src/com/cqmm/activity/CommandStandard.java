package com.cqmm.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cqmm.bean.CmdLog;
import com.cqmm.bean.Command;
import com.cqmm.bean.Device;
import com.cqmm.common.CurSession;
import com.cqmm.common.DataService;
import com.cqmm.common.HttpComm;
import com.cqmm.common.Requests;
import com.cqmm.common.SysParams;

public class CommandStandard extends Activity {

	public CommandStandard() {
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
	
	EditText et_loginname;
	EditText et_password;
	Device curDevice;
	
	
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
		lv=(ListView)findViewById(R.id.command_list);
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
		
		new Thread(){
			@Override
			public void run() {
				
				 commands=DataService.getCommand(CommandList.deviceid,Command.TYPE_COMMON);
				 if(commands.size()>0){
					Message msg = new Message();
		            Bundle b = new Bundle();// �������
		            b.putString("result", "cmdlist");
		            msg.setData(b);
		            
		            CommandStandard.this.handler.sendMessage(msg); // ��Handler������Ϣ,����UI
				}
				
			}
		}.start();
		
		
		//��ʼ�������豸�б�
		devicelist_name=new String[DataService.getDevice().size()];
		int i=0;

//		�����б�
//		for(Device device:DataService.open_devices){
//			devicelist_name[i++]=device.getDevicename();
//		}
		
		//�����豸
		for(Device device:DataService.getDevice()){
			devicelist_name[i++]=device.getDevicename();
		}
		ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,devicelist_name);
		sp_device_ex.setAdapter(adapter);
		sp_device_ex.setVisibility(View.VISIBLE);
		for(int j=0;j<DataService.getDevice().size();j++){
			if(DataService.getDevice().get(j).getId()==CommandList.deviceid){
				sp_device_ex.setSelection(j);
			}
		}
		
        
		//���������豸��ѡ���¼�
		sp_device_ex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
//				updatecmdlist();
				curDevice=DataService.getDevice().get(position);
				if(curDevice.getId()!=CommandList.deviceid){
					if(DataService.isopen(curDevice)){
						Bundle bundle=new Bundle();
		        		bundle.putInt("deviceid", curDevice.getId());
		        		bundle.putString("devicename", curDevice.getDevicename());
		        		bundle.putInt("curTab", 0);
		        		
		        		Intent intent = new Intent();
		        		intent.putExtras(bundle);
		        		intent.setClass(CommandStandard.this, CommandList.class);
		        		startActivity(intent);
		        		finish();
					}else{
						CommandList.isnew=false;
						LayoutInflater factory = LayoutInflater.from(CommandStandard.this);
						final View textEntryView = factory.inflate(R.layout.device_login_dialog, null);
						AlertDialog dlg = new AlertDialog.Builder(CommandStandard.this)
				        .setTitle("��½�豸")
				        .setView(textEntryView)
				        .setPositiveButton("��½", new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int whichButton) {
					        		Log.v("cqmm", "device_loginname:"+et_loginname.getText().toString());
					        		Log.v("cqmm", "et_password:"+et_password.getText().toString());
					            	String loginresult=HttpComm.login_device(curDevice.getId(),et_loginname.getText().toString(), et_password.getText().toString());
				
					            	if(loginresult.equals("OK")){
					            		DataService.add_open_device(curDevice);
						        		Bundle bundle=new Bundle();
						        		bundle.putInt("deviceid", curDevice.getId());
						        		bundle.putInt("curTab", 0);
						        		bundle.putString("devicename", curDevice.getDevicename());
						        		
						        		Intent intent = new Intent();
						        		intent.putExtras(bundle);
						        		intent.setClass(CommandStandard.this, CommandList.class);
						        		startActivity(intent);
					            	}else{
					            		Toast.makeText(CommandStandard.this, "��½ʧ��", Toast.LENGTH_SHORT).show();
					            	}
				            	
				            }
				        }).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int whichButton) {
		
				            }
				        }).create();
						dlg.show();
						et_loginname=(EditText)dlg.findViewById(R.id.loginName1);
				 		et_password=(EditText)dlg.findViewById(R.id.pwdName1);
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
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
				lv.setAdapter(new ArrayAdapter<String>(CommandStandard.this,
		                android.R.layout.simple_list_item_1, mStrings));
				lv.setVisibility(View.VISIBLE);
				lv.setOnItemClickListener(new ListView.OnItemClickListener() {
				
					//���Ҫִ�е�����
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						pdialog = new ProgressDialog(CommandStandard.this);   
//						pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						pdialog.setMessage("����ִ��");
						pdialog.show();
						
						lv.setClickable(false);
						curcmdid=commands.get(position).getId();
						curcmdname=commands.get(position).getCommandname();
						new Thread(){
							@Override
							public void run() {
								 cmdresult=HttpComm.execmd(CommandList.deviceid, curcmdid);
//								 if(commands.size()>0){
									Message msg = new Message();
						            Bundle b = new Bundle();// �������
						            b.putString("result", "cmdresult");
						            msg.setData(b);
						            
						            CommandStandard.this.handler.sendMessage(msg); // ��Handler������Ϣ,����UI
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
					curResult=curResult+cmdresult;
				}
				DataService.cmd_reslut_map.put(CommandList.deviceid, curResult);
				tv.setText(curResult);
				
				CmdLog log=new CmdLog();
				log.setDevicename(CommandList.devicename);
				log.setOptname(curcmdname);
				log.setResult(cmdresult);
				log.setUserid(CurSession.userid);
				DataService.insertLog(CommandStandard.this, log);
			}
		}
	};

}