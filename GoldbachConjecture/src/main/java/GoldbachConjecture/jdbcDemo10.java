package GoldbachConjecture;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
/**
 * ��ȡԪ����
 * @author Administrator
 *
 */
public class jdbcDemo10 {

	public static void main(String[] args) {
		try {
			Connection  conn=DBUtils.getConn();
			DatabaseMetaData  dbmd=conn.getMetaData();
			System.out.println("�����汾��"+dbmd.getDriverVersion());
			System.out.println("�û�����"+dbmd.getUserName());
			System.out.println("���ݿ⳧�̣�"+dbmd.getDatabaseProductName());
			//��ȡ���Ԫ����
			String  sql="select  *  from  even_and_prime";
			Statement  stat=conn.createStatement();
			ResultSet  rs=stat.executeQuery(sql);
			ResultSetMetaData  rsmd=rs.getMetaData();
			//��ȡ���ֶ�����
			int  count=rsmd.getColumnCount();
			//��ȡÿ���ֶε�����
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
