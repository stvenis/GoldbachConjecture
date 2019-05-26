package GoldbachConjecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcDemo033 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入偶数：");
		Long even = sc.nextLong();
		System.out.println("请输入哥德巴赫分拆数1：");
		Long prime1 = sc.nextLong();
		System.out.println("请输入哥德巴赫分拆数2：");
		Long prime2 = sc.nextLong();
		// 得到连接
		try (Connection conn = DBUtils.getConn()) {
			String sql = "insert into  even_and_prime  (even,prime1,prime2)  values (?,?,?)";
			// 创建预编译的SQL执行对象
			PreparedStatement ps = conn.prepareStatement(sql);
			// 把？换成真正的变量
			ps.setLong(1, even);
			ps.setLong(2, prime1);
			ps.setLong(3, prime2);
			// 执行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
