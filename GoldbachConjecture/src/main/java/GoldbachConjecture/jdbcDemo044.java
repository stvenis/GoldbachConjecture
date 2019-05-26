package GoldbachConjecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcDemo044 {
	/*
	create table jdbcuser(id int primary key auto_increment,
	username varchar(10),password varchar(10));
	*/
	public static void main(String[] args) {
		Scanner  sc=new Scanner(System.in);
		System.out.println("ÇëÊäÈëÓÃ»§Ãû£º");
		String username=sc.nextLine();
		System.out.println("ÇëÊäÈëÃÜÂë£º");
		String  password=sc.nextLine();
		try(Connection  conn=DBUtils.getConn()){
			String  sql="select  count(*) from  jdbcuser  where  username=?  and  password=? ";
			PreparedStatement  ps=conn.prepareStatement(sql);
			ps.setString(1,username );
			ps.setString(2,password);
			ResultSet  rs=ps.executeQuery();
			while(rs.next()) {
				int  count=rs.getInt(1);
				if(count>0) {
					System.out.println("µÇÂ¼³É¹¦£¡");
				}else {
					System.err.println("µÇÂ¼Ê§°Ü!");
				}
			}
		}catch (SQLException  e) {
			e.printStackTrace();
		}

	}

}
