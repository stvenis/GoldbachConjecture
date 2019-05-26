package GoldbachConjecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 * ���°ͺշֲ������Ĵ�ż���㷨
 * 
 * @author wec
 */
public class GoldbachConjecture4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (Connection conn = DBUtils.getConn()) {
			String sql = "truncate  even_and_prime";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate(sql);
			
            /*
			if (!ps.execute()) {
				System.out.println("���ݱ�δ��գ������״̬");
			} else {
				System.out.println("���ݱ������");
			}
            */
			
			while (true) {

				System.out.println("���������2��ż����Χ��2�˳�����");
				long n = sc.nextLong();
				if (n < 2) {
					System.out.println("���������2��ż��");
				} else if (n == 2) {
					System.out.println("�������");
					break;
				} else if (n > 2 && n % 2 != 0) {
					System.out.println("���������2��ż��");
				} else {

					for (long i = n; i > 2; i = i - 2) {
						GoldbachConjecture(i);
					}
				}

			}
			/**
			 * select even,prime1,prime2 from even_and_prime where even in (select even from
			 * (select even,count(1) as cnt from even_and_prime group by even order by
			 * count(1) desc limit 0,1) as even_temp)
			 */
			//String sql1 = "select even,count(1) from even_and_prime  group by even  order  by count(1)  desc  limit  0,5";
			String sql1 ="select even,count(1)  from even_and_prime where even in (select even from  (select even,count(*) as cnt from even_and_prime group by even order by  count(1) desc limit 0,1) as even_temp)";
			String sql2 ="select even,count(1)  from even_and_prime where even in (select even from  (select even,count(*) as cnt from even_and_prime group by even order by  count(1)  limit 0,1) as even_temp)";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet  rs1 = ps1.executeQuery();
			ResultSet  rs2 = ps2.executeQuery();
			System.out.println("������ѯ�ĸ�°ͺշֲ�������ż�������ǵĶ����Ľ��������ʾ��");
			while (rs1.next()) {
				String even = rs1.getString("even");
				String count1 = rs1.getString("count(1)");
				System.out.println(even + "==========>" + count1);
				
				

			}
			System.out.println("-------------------------------------------------");
			System.out.println("������ѯ�ĸ�°ͺշֲ������ٵ�ż�������ǵĶ����Ľ��������ʾ��");
			while (rs2.next()) {
				String even = rs2.getString("even");
				String count1 = rs2.getString("count(1)");
				System.out.println(even + "==========>" + count1);
				
				

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
					System.out.println(num + "=" + num1 + "+" + num2);
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
