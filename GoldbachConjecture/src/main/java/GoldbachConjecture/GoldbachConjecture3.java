package GoldbachConjecture;

import java.util.Scanner;
/**
 * 
 * ���°ͺշֲ������Ĵ�ż���㷨
 * @author wec 
 */
public class GoldbachConjecture3 {
    static  int  count=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        
		while (true) {
			long  max=0,max_n=0;
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
				for (long  i = n; i >2; i=i-2) {
					
					count=GoldbachConjecture(i);
					if (count>max) {
						max=count;
						max_n=i;
					}
				}
				
				
                System.out.println("������Ĵ���2��ż��"+n+"���ڵ����ĸ�°ͺշֲ����Ķ�����"+max+"������"+max_n);
			}
			
		}
		sc.close();
		
		// GoldbachConjecture(n);

		// System.out.println(isPrime(10L));
	}
	/**
	 * ��֤��һ����2��ż������д����������֮��
	 * 
	 * @param num
	 */
	private  static  int  GoldbachConjecture(long num) {
		int  countGBC=0;
		// ���Ƚ�num�ֽ�Ϊ��������֮��
		long num1, num2;
		for (num1 = 1; num1 <= num / 2; num1++) {
			num2 = num - num1;
			// �ֱ��ж���ϵ����������Ƿ��Ϊ����
			if (isPrime(num1) && isPrime(num2)) {
				System.out.println(num + "=" + num1 + "+" + num2);
				countGBC++;
			}
		}
		return  countGBC;
	}
	/**
	 * ��֤�Ƿ�������
	 * 
	 * @param n Ҫ�жϵ�����
	 * @return true or false
	 */
	private  static  boolean isPrime(long n) {
		//1��2�����жϣ�1�Ѿ�����������2����С����
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
