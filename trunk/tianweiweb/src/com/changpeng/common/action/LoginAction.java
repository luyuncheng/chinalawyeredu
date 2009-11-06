/**
 * 
 */
package com.changpeng.common.action;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.global.model.GlobalLogLogin;

/**
 */
public class LoginAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(LoginAction.class);
	// 用户输入的验证码
	private String verifycode;
	// 用户输入的用户名
	private String loginname;
	// 用户输入的密码
	private String password;


	public LoginAction() {
		rightCode = "PUBLIC";
		this.moduleid = 10;
	}

	@Override
	protected String go() throws Exception {

		this.moduleid = 10;

		String result = "plainmsg";
		
		if (loginname == null || loginname.equals("")) {
			message = "请输入您的用户名";
		} else if (password == null || password.equals("")) {
			message = "请输入您的登录密码";
		}else if(verifycode!=null&&get("verifycode")!=null&&(!verifycode.equals(get("verifycode").toString()))){
			message="请输入正确的验证码";
		}else {
			UserService service = (UserService) this.getBean("userService");
	
			CoreUser user = service.getUserByLoginName(loginname);
			if (user != null&&user.getPwd().equals(password)) {
					String usersession = user.getId() + "," + user.getProvinceId()
							+ "," + user.getCityId() + "," + user.getDistrictId()
							+ "," + user.getUserRole() + "," + user.getUserName() + ","
							+ user.getUserType();
					set("USERSESSION", usersession);
					this.currentUserid = user.getId();
					this.currentRole = user.getUserRole();
					this.provinceid = user.getProvinceId();
					this.cityid = user.getCityId();
					this.districtid = user.getDistrictId();
					this.usertype = user.getUserType();
	
					GlobalLogLogin loglogin=new GlobalLogLogin();
					loglogin.setLoginIp(this.userip);
					loglogin.setLoginTime(new Timestamp(System.currentTimeMillis()));
					loglogin.setMobile(user.getMobile());
					loglogin.setUserid(user.getId());
					loglogin.setUserName(user.getUserName());
					loglogin.setUserRole(user.getUserRole());
					service.save(loglogin);
					message="ok";
			} else {
				message = "密码错误，请重新输入!";
			}
		}
		return result;
	}

	// 以下set方法页面调用
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
