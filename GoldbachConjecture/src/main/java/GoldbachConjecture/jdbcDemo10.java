package GoldbachConjecture;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
/**
 * 获取元数据
 * @author Administrator
 *
 */
public class jdbcDemo10 {

	public static void main(String[] args) {
		try {
			Connection  conn=DBUtils.getConn();
			DatabaseMetaData  dbmd=conn.getMetaData();
			System.out.println("驱动版本："+dbmd.getDriverVersion());
			System.out.println("用户名："+dbmd.getUserName());
			System.out.println("数据库厂商："+dbmd.getDatabaseProductName());
			//获取表的元数据
			String  sql="select  *  from  even_and_prime";
			Statement  stat=conn.createStatement();
			ResultSet  rs=stat.executeQuery(sql);
			ResultSetMetaData  rsmd=rs.getMetaData();
			//获取表字段数量
			int  count=rsmd.getColumnCount();
			//获取每个字段的名称
			for (int i = 0; i < count; i++) {
				String  name=rsmd.getColumnName(i+1);
				String  type=rsmd.getColumnTypeName(i+1);
				System.out.println(name+":"+type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
