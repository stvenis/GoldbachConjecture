package GoldbachConjecture;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * �������ӳصȴ�����
 * @author Administrator
 *
 */
public class jdbcDemo02 {
     public static void main(String[] args) throws SQLException {
		/*
    	 Connection  c1=DBUtils.getConn();
		System.out.println("��ȡ����1��"+c1);
		
		Connection  c2=DBUtils.getConn();
		System.out.println("��ȡ����2��"+c2);
		Connection  c3=DBUtils.getConn();
		System.out.println("��ȡ����3��"+c3);
		//ʹ�����ӳغ�close���ǹر����Ӷ��ǹ黹�����ӳ�
		c1.close();
		Connection  c4=DBUtils.getConn();
		System.out.println("��ȡ����4��"+c4);
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
    	//�õ�����
    	try {
			Connection  conn=DBUtils.getConn();
			System.out.println("�õ����ӣ�"+conn);
			//ģ���ʱ
			Thread.sleep(3000);
			//�黹����
			conn.close();
			System.out.println("�黹����");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	
    	
    }
}
