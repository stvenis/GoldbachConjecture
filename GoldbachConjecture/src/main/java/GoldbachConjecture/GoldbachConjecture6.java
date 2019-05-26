package GoldbachConjecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 
 * ���°ͺշֲ����������ٵĴ�ż������400-500֮�䣩�㷨
 * 
 */
public class GoldbachConjecture6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (Connection conn = DBUtils.getConn()) {
			String sql = "truncate  even_and_prime";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate(sql);

			while (true) {

				System.out.println("���������2��Сż����2�˳�����");
				long n1 = sc.nextLong();
				System.out.println("���������2�Ĵ�ż����2�˳�����");
				long n2 = sc.nextLong();
				if (n1 < 2 || n2 < 2) {
					System.out.println("���������2��ż��");
				} else if (n1 == 2 || n2 == 2) {
					System.out.println("�������");
					break;
				} else if ((n1 > 2 && n1 % 2 != 0) || (n2 > 2 && n2 % 2 != 0)) {
					System.out.println("������������з�ż����");
				} else {
					long temp = 0;
					if (n1 > n2) {
						temp = n1;
						n1 = n2;
						n2 = temp;
					}
					for (long i = n2; i >= n1; i = i - 2) {
						GoldbachConjecture(i);

					}
					break;
				}

			}

			/*
			 * String sql1 ="select even,count(1) from \r\n" + "even_and_prime \r\n" +
			 * "group by \r\n" + "even \r\n" + "having  count(1)=(select count(1) from \r\n"
			 * + "even_and_prime \r\n" + "where even in \r\n" + "(select even from\r\n" +
			 * "(select even,count(1)  \r\n" + "from \r\n" + "even_and_prime \r\n" +
			 * "group by \r\n" + "even \r\n" + "order by count(1) desc\r\n" +
			 * "limit 0,1) as even_temp))";
			 */
			/*
			 * String sql5 ="select even,count(1) from \r\n" + "even_and_prime \r\n" +
			 * "group by \r\n" + "even \r\n" + "having  count(1)=(select count(1) from \r\n"
			 * + "even_and_prime \r\n" + "where even in \r\n" + "(select even from\r\n" +
			 * "(select even,count(1)  \r\n" + "from \r\n" + "even_and_prime \r\n" +
			 * "group by \r\n" + "even \r\n" + "order by count(1) \r\n" +
			 * "limit 0,1) as even_temp))";
			 */
			int sql1_even = 0;
			int sql1_count1 = 0;
			int sql2_even = 0;
			int sql2_count1 = 0;
			int sql3_even = 0;
			int sql3_count1 = 0;
			int sql4_even = 0;
			int sql4_count1 = 0;
			int  sql5_even=0;
			int  sql5_prime1=0;
			int  sql5_prime2=0;
			int  sql5_delta=0;
			int  sql6_even=0;
			int  sql6_prime1=0;
			int  sql6_prime2=0;
			int  sql6_delta=0;
			String sql1 = "select even,count(1)  from  even_and_prime  group by  even  order by count(1) asc  limit  0,1";

			String sql2 = "select  even,count(1)  from  even_and_prime  group  by  even  having  count(1)=?";
			String sql3 = "select  even,count(1)  from  even_and_prime  group by  even  order  by  count(1)  desc  limit  0,1";
			// String sql4 = "select even,count(1) from even_and_prime group by even having
			// count(1)=?";
			String sql5 = "select even,prime1,prime2,prime2-prime1  as  delta  from  even_and_prime  where prime2-prime1=(select  max(prime2-prime1) as max_delta from  even_and_prime)";
			String sql6 = "select even,prime1,prime2,prime2-prime1  as  delta  from  even_and_prime  where prime2-prime1=(select  min(prime2-prime1) as max_delta from  even_and_prime)";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			System.out.println("������ѯ�ĸ�°ͺշֲ������ٵ�ż�������ǵĶ����Ľ��������ʾ��");
			while (rs1.next()) {
				sql1_even = rs1.getInt(1);
				sql1_count1 = rs1.getInt(2);
				System.out.println("������ѯ�ĸ�°ͺշֲ����Ķ������ٵ��ǣ�" + sql1_count1);
			}

			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, sql1_count1);

			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				sql2_even = rs2.getInt(1);
				sql2_count1 = rs2.getInt(2);
				System.out.println(sql2_even + "==========>" + sql2_count1);
			}
			System.out.println("-------------------------------------------------");
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ResultSet rs3 = ps3.executeQuery();
			System.out.println("������ѯ�ĸ�°ͺշֲ�������ż�������ǵĶ����Ľ��������ʾ��");
			while (rs3.next()) {
				sql3_even = rs3.getInt(1);
				sql3_count1 = rs3.getInt(2);
				System.out.println("������ѯ�ĸ�°ͺշֲ����Ķ��������ǣ�" + sql3_count1);
			}

			PreparedStatement ps4 = conn.prepareStatement(sql2);
			ps4.setInt(1, sql3_count1);
			ResultSet rs4 = ps4.executeQuery();
			while (rs4.next()) {
				sql4_even = rs4.getInt(1);
				sql4_count1 = rs4.getInt(2);
				System.out.println(sql4_even + "==========>" + sql4_count1);
			}
			
            System.out.println("------------------------------------------");
			PreparedStatement  ps5=conn.prepareStatement(sql5);
			ResultSet  rs5=ps5.executeQuery();
			while (rs5.next()) {
				sql5_even=rs5.getInt(1);
				sql5_prime1=rs5.getInt(2);
				sql5_prime2=rs5.getInt(3);
			    sql5_delta=rs5.getInt(4);
				System.out.println("������ѯ�ĸ�°ͺշֲ�����������ֵ���Ϊ��"+sql5_even+ "==========>" + sql5_prime1+ "==========>" + sql5_prime2+ "==========>" +  sql5_delta);
			}
			
			 System.out.println("------------------------------------------");
				PreparedStatement  ps6=conn.prepareStatement(sql6);
				ResultSet  rs6=ps6.executeQuery();
				while (rs6.next()) {
					sql6_even=rs6.getInt(1);
					sql6_prime1=rs6.getInt(2);
					sql6_prime2=rs6.getInt(3);
				    sql6_delta=rs6.getInt(4);
					System.out.println("������ѯ�ĸ�°ͺշֲ����������Сֵ���Ϊ��"+sql6_even+ "==========>" + sql6_prime1+ "==========>" + sql6_prime2+ "==========>" +  sql6_delta);
				}
				
		} catch (SQLException e) {

			e.printStackTrace();
		}
		sc.close();
	}

	// GoldbachConjecture(n);

	// System.out.println(isPrime(10L));

	/**
	 * ��֤��һ����2��ż������д����������֮��
	 * 
	 * @param num
	 */
	private static void GoldbachConjecture(long num) {
		// ���Ƚ�num�ֽ�Ϊ��������֮��
		long num1, num2;
		// �õ�����
		try (Connection conn = DBUtils.getConn()) {

			String sql = "insert into  even_and_prime  (even,prime1,prime2)  values (?,?,?)";
			// ����Ԥ�����SQLִ�ж���
			PreparedStatement ps = conn.prepareStatement(sql);
			for (num1 = 1; num1 <= num / 2; num1++) {
				num2 = num - num1;
				// �ֱ��ж���ϵ����������Ƿ��Ϊ����
				if (isPrime(num1) && isPrime(num2)) {
					// System.out.println(num + "=" + num1 + "+" + num2);
					// �ѣ����������ı���
					ps.setLong(1, num);
					ps.setLong(2, num1);
					ps.setLong(3, num2);
					// ִ��
					ps.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ��֤�Ƿ�������
	 * 
	 * @param n Ҫ�жϵ�����
	 * @return true or false
	 */
	private static boolean isPrime(long n) {
		// 1��2�����жϣ�1�Ѿ�����������2����С����
		if (n == 1) {
			return false;
		} else if (n == 2) {
			return true;
		} else {
			for (long i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0) {
					return false;
				}

			}
			return true;
		}

	}
}
