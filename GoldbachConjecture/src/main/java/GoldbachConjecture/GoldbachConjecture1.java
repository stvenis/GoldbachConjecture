package GoldbachConjecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GoldbachConjecture1 {

	public static void main(String[] args) {
		GoldbachConjecture(500L);
		// System.out.println(isPrime(9223372036854775807L));
		// System.out.println(isPrime(101L));
	}

	/**
	 * 验证任一大于2的偶数都可写成两个质数之和
	 * 
	 * @param num
	 */

	public static void GoldbachConjecture(long num) {
		// 首先将num分解为两个奇数之和
		long num1, num2;
		// 得到连接
		try (Connection conn = DBUtils.getConn()) {

			String sql = "insert into  even_and_prime  (even,prime1,prime2)  values (?,?,?)";
			// 创建预编译的SQL执行对象
			PreparedStatement ps = conn.prepareStatement(sql);
			for (num1 = 1; num1 <= num / 2; num1++) {
				num2 = num - num1;
				// 分别判断组合的两个数字是否均为质数
				if (isPrime(num1) && isPrime(num2)) {
					System.out.println(num + "=" + num1 + "+" + num2);

					// 把？换成真正的变量
					ps.setLong(1, num);
					ps.setLong(2, num1);
					ps.setLong(3, num2);
					// 执行
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证是否是质数
	 * 
	 * @param n 要判断的数字
	 * @return true or false
	 */
	private static boolean isPrime(long n) {
//1和2单独判断，1已经不是素数，2是最小素数
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
