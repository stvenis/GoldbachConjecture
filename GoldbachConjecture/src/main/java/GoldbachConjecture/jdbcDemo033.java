package GoldbachConjecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcDemo033 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("������ż����");
		Long even = sc.nextLong();
		System.out.println("�������°ͺշֲ���1��");
		Long prime1 = sc.nextLong();
		System.out.println("�������°ͺշֲ���2��");
		Long prime2 = sc.nextLong();
		// �õ�����
		try (Connection conn = DBUtils.getConn()) {
			String sql = "insert into  even_and_prime  (even,prime1,prime2)  values (?,?,?)";
			// ����Ԥ�����SQLִ�ж���
			PreparedStatement ps = conn.prepareStatement(sql);
			// �ѣ����������ı���
			ps.setLong(1, even);
			ps.setLong(2, prime1);
			ps.setLong(3, prime2);
			// ִ��
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
