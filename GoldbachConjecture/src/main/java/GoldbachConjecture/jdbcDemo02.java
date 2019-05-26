package GoldbachConjecture;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * 测试连接池等待问题
 * @author Administrator
 *
 */
public class jdbcDemo02 {
     public static void main(String[] args) throws SQLException {
		/*
    	 Connection  c1=DBUtils.getConn();
		System.out.println("获取连接1："+c1);
		
		Connection  c2=DBUtils.getConn();
		System.out.println("获取连接2："+c2);
		Connection  c3=DBUtils.getConn();
		System.out.println("获取连接3："+c3);
		//使用连接池后close并非关闭连接而是归还到连接池
		c1.close();
		Connection  c4=DBUtils.getConn();
		System.out.println("获取连接4："+c4);
		*/
    	 DemoThread  d1=new  DemoThread();
    	 d1.start();
    	 DemoThread  d2=new  DemoThread();
    	 d2.start();
    	 DemoThread  d3=new  DemoThread();
    	 d3.start();
    	 DemoThread  d4=new  DemoThread();
    	 d4.start();
	}
}
class DemoThread  extends Thread {
    @Override
    public  void  run() {
    	//得到连接
    	try {
			Connection  conn=DBUtils.getConn();
			System.out.println("得到连接："+conn);
			//模拟耗时
			Thread.sleep(3000);
			//归还连接
			conn.close();
			System.out.println("归还连接");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	
    	
    }
}
