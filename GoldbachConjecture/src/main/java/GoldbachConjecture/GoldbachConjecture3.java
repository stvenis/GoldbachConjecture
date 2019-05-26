package GoldbachConjecture;

import java.util.Scanner;
/**
 * 
 * 求哥德巴赫分拆数最多的大偶数算法
 * @author wec 
 */
public class GoldbachConjecture3 {
    static  int  count=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        
		while (true) {
			long  max=0,max_n=0;
			System.out.println("请输入大于2的偶数范围（2退出）：");
			long n = sc.nextLong();
			if (n < 2) {
				System.out.println("请输入大于2的偶数");
			} else if (n == 2) {
				System.out.println("程序结束");
                break;
			} else if (n > 2 && n % 2 != 0) {
				System.out.println("请输入大于2的偶数");
			} else {
				for (long  i = n; i >2; i=i-2) {
					
					count=GoldbachConjecture(i);
					if (count>max) {
						max=count;
						max_n=i;
					}
				}
				
				
                System.out.println("您输入的大于2的偶数"+n+"以内的最大的哥德巴赫分拆数的对数是"+max+"出现在"+max_n);
			}
			
		}
		sc.close();
		
		// GoldbachConjecture(n);

		// System.out.println(isPrime(10L));
	}
	/**
	 * 验证任一大于2的偶数都可写成两个质数之和
	 * 
	 * @param num
	 */
	private  static  int  GoldbachConjecture(long num) {
		int  countGBC=0;
		// 首先将num分解为两个奇数之和
		long num1, num2;
		for (num1 = 1; num1 <= num / 2; num1++) {
			num2 = num - num1;
			// 分别判断组合的两个数字是否均为质数
			if (isPrime(num1) && isPrime(num2)) {
				System.out.println(num + "=" + num1 + "+" + num2);
				countGBC++;
			}
		}
		return  countGBC;
	}
	/**
	 * 验证是否是质数
	 * 
	 * @param n 要判断的数字
	 * @return true or false
	 */
	private  static  boolean isPrime(long n) {
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
