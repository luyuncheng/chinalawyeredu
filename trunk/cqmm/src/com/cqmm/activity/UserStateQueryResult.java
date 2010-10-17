package com.cqmm.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.cqmm.common.SysParams;

/**
 * �豸״̬��ѯ
 * 
 * @author ����
 * 
 */
public class UserStateQueryResult extends Activity {

	public UserStateQueryResult() {
		super();
	}

	private TextView textView;
	private WebView queryResult;
	private Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				removeDialog(1); // ȡ����dialog����ʾ��ȡ������
				String data="���·��Ͷ���������˵��<a href=\"http://www.sina.com.cn\">����������������</a>";
//				queryResult.loadData(data, "text/html", "gb2312");
				queryResult.loadDataWithBaseURL(null, data, "text/html", "gb2312", null);
				break;
			}
			super.handleMessage(msg);
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȡ����title
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME
				+ ")�û�״̬��ѯ");

		this.setContentView(R.layout.user_state_query_result);

		textView=(TextView)super.findViewById(R.id.queryResultView);
		queryResult=(WebView)super.findViewById(R.id.userStateQueryWebview);
		
		
		
		ArrayList<String> selectedSgsns = getIntent().getStringArrayListExtra(
				"selectedSgsns");
		String mobile = getIntent().getStringExtra("userMobile");
		Log.v(SysParams.LOG_TAG, "ѡ�е�sgsns::" + selectedSgsns);

		textView.setText(mobile+"��"+selectedSgsns.toString().replace("[", "").replace("]", "")+"�Ĳ�ѯ���");
		
		showDialog(1);

		new Thread() {
			public void run() {
				int i = 0;
				while (i++ < 5) {
					try {
						Thread.sleep(500L);
					} catch (Exception e) {
						setResult(Activity.RESULT_CANCELED);
						Log.v(SysParams.LOG_TAG, "��¼����");
					}
				}
				Message message = new Message();
				message.what = 1;	
				UserStateQueryResult.this.myHandler.sendMessage(message);
			}
		}.start();
	}

	@Override
	public Dialog onCreateDialog(int dialogId) {

		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("��ȴ�");
		dialog.setMessage("���ڲ�ѯ��");
		return dialog;
	}

}