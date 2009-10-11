import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author 华锋
 * Oct 10, 2009 2:41:59 PM
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(new FileInputStream(new File("c:\\c.txt")),"utf-8"));
		String line = null;

	Map<String,Integer> map=new HashMap<String,Integer>();
		while((line=br.readLine())!=null){
			String date=line.substring(12);
			System.out.println(date);
			if(map.containsKey(date)){
				int now=map.get(date);
				map.remove(date);
				map.put(date, now+1);
			}else{
				map.put(date, 1);
			}
			
		}
		
		System.out.println(map);
		br.close();
		
		
		
	}

}
