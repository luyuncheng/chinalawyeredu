package com.sxit.memdevice.common;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.ResultSet;

import org.apache.commons.net.telnet.TelnetClient;

public class Client {

	InputStream in;
	PrintStream out;
	String prompt = "";
	TelnetClient tc;
	boolean islogin=false;
	
	String username;
	String password;

	public Client(String username,String password) throws Exception {
		this.username=username;
		this.password=password;
		tc = new TelnetClient();
		tc.connect("10.10.0.72", 23);
		in = tc.getInputStream();
		out = new PrintStream(tc.getOutputStream());
		
		String loginresult=login();
		System.out.println("loginresult:"+loginresult);
		if(loginresult!=null&&loginresult.equals("login")){
			islogin=true;
		}else{
			islogin=false;
		}
		
	}

	public String login() {
		int step=0;
		try {
			byte[] buff = new byte[1024];
            int ret_read = 0;

            do
            {
                ret_read = in.read(buff);
                if(ret_read > 0)
                {
                	String bufstring=new String(buff, 0, ret_read);
                	
                    System.out.print("--"+bufstring+"E");
                    if(bufstring.endsWith("login: ")){
                    	write(username);
                    	step++;
                    }else if(bufstring.endsWith("Password: ")){
                    	write(password);
                    	islogin=true;
                    	step++;
                    }else if(bufstring.trim().endsWith("~ #")){
                    	return "login";
                    }
//                    else if(bufstring.indexOf("Login incorrect")!=-1){
//                    	return "wrong password";
//                    }
                }
            } while (ret_read >= 0);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String execute(String cmd){
		StringBuilder response=new StringBuilder();
		boolean isstart=false;
		boolean isend=false;
		
		byte[] buff = new byte[1024];
        int ret_read = 0;
        try{
        	write(cmd);
        	isstart=true;
			do{
				
	            ret_read = in.read(buff);
	            
	            if(ret_read > 0){
	            	String bufstring=new String(buff, 0, ret_read);
	                System.out.println("--"+bufstring+"E");
	                
					if(islogin&&!isstart&&bufstring.endsWith("~ # ")){
			        	
			        }else if(islogin&&isstart&&bufstring.endsWith("~ # ")){
			        	isend=true;
			        	response.append(bufstring);
			        	return response.toString();
			        }else if(isstart&&!isend){
			        	response.append(bufstring);
			        }
	            }
	        }while (ret_read >= 0);
        }catch(Exception e){
        	
        }
        return response.toString();
	}
	
	/**
	 * 写
	 * 
	 * @param value
	 */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 * 
	 */
	public void disconnect() {
		try {
			tc.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getres(String username,String password,String cmd) {
		String string="";
		try {
			Client telnet = new Client(username,password);
			if(telnet.islogin){
				string=telnet.execute(cmd);
				System.out.println("response:\r\n"+string);
			}else{
				System.out.println("not login");
			}
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}
	
//	public static String getresponse(String cmd){
//		String res="";
//		try {
//			Client telnet = new Client();
//			res=telnet.execute(cmd);
//			System.out.println("response:\r\n"+res);
//
//			telnet.disconnect();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return res;
//	}
}
