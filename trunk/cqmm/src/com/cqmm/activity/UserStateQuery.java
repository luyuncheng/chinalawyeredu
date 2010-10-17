package com.cqmm.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.cqmm.common.SysParams;
import com.cqmm.util.ActivityFunction;
import com.cqmm.util.MobileVerify;

/**
 * �豸״̬��ѯ
 * 
 * @author ����
 * 
 */
public class UserStateQuery extends Activity {

	public UserStateQuery() {
		super();
	}

	private ArrayList<String> selectedSgsns = new ArrayList<String>();
	private EditText userMobileText;
	private ArrayAdapter<CharSequence> adapter;

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

		this.setContentView(R.layout.user_state_query);

		adapter = ArrayAdapter.createFromResource(this, R.array.sgsns,
				android.R.layout.simple_list_item_multiple_choice);
		ListView listView = (ListView) findViewById(R.id.sgsnList);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setItemsCanFocus(false);
			
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(android.widget.AdapterView adapterView,
					android.view.View view, int position, long id) {
			
				
				CheckedTextView ctv=(CheckedTextView)view;
				boolean isChecked=!ctv.isChecked();
			
			
				String sgsnid = adapter.getItem(position).toString();
				if (!selectedSgsns.contains(sgsnid)&&isChecked)
					selectedSgsns.add(sgsnid);
				if(selectedSgsns.contains(sgsnid)&&!isChecked)
					selectedSgsns.remove(sgsnid);
				Log.v(SysParams.LOG_TAG, "setOnItemClickListener=="
						+ adapterView + "," + view + "," + position + "===>"
						+ adapter.getItem(position) + ",," + id+","+isChecked+","+selectedSgsns);
			}

		});

		
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.user_state_query, null);

	
		// ��������
		ActivityFunction.inputMethodHandle(layout, this);

		listView.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log.v(SysParams.LOG_TAG, "onItemSelected==" + parent + ","
						+ view + "," + position + "===>"
						+ adapter.getItem(position) + ",," + id);
			}

			/**
			 * 
			 * @param parent
			 *            The AdapterView that now contains no selected item.
			 */
			public void onNothingSelected(AdapterView<?> parent) {
				Log.v(SysParams.LOG_TAG, "onItemSelected==" + parent);
			}
		});

		listView.setAdapter(adapter);

		Button queryBtn = (Button) findViewById(R.id.userStateQueryBtn);
		userMobileText = (EditText) findViewById(R.id.userMobile);

		// ���ѯ��Ķ���
		queryBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String mobile = userMobileText.getText().toString();
				// TODO Auto-generated method stub
				if (mobile == null || mobile.equals("")
						|| !MobileVerify.isMobile(mobile)) {// ��ʾҪ�����ֻ�����
					Toast.makeText(UserStateQuery.this, "��������ȷ���й��ƶ��ֻ���",
							Toast.LENGTH_SHORT).show();
				} 
				else if(selectedSgsns.isEmpty()){
					Toast.makeText(UserStateQuery.this, "��ѡ���Ӧ��SGSNID",
							Toast.LENGTH_SHORT).show();
				}
				else {
					// ��ת��һ����ҳ��,��ʾһ���ȴ��ĶԻ���,֮���̵߳ķ�ʽ��ʾ�����Ϣ
					Intent intent=new Intent();
					intent.setClass(UserStateQuery.this, UserStateQueryResult.class);
					intent.putStringArrayListExtra("selectedSgsns", selectedSgsns);
					intent.putExtra("userMobile", mobile);
					startActivity(intent);
//					intent.putExtra("selectedSgsns", selectedSgsns.toString());
					
				}
			}
		});
		
	}

	public void onItemSelected(AdapterView parent, View v, int position, long id) {
		// if (position >= 0) {
		// Cursor c = (Cursor) parent.getItemAtPosition(position);
		// mPhone.setText(c.getString(mPhoneColumnIndex));
		// }
	}

}