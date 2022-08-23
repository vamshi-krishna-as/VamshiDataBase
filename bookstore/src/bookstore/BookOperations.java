package bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class BookOperations
{
	static ResultSet res = null;
	public static void addBook(int bId,String bName, String aName, String bGenre, double bPrice) 
	{
		try
		{
			String sql = "INSERT INTO book (bookId,bookName,authorName,bookGenre,bookPrice) VALUES (?,?,?,?,?)";
			PreparedStatement stmt = BookShop.con.prepareStatement(sql);
			stmt.setInt(1, bId);
			stmt.setString(2, bName);
			stmt.setString(3, aName);
			stmt.setString(4, bGenre);
			stmt.setDouble(5, bPrice);
			int rowsSelected = stmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
	
	public static void getCount(String bName) 
	{
		try 
		{
			String getTotalCount = "select count(*) from book";
			res = BookShop.stmt.executeQuery(getTotalCount);
			res.next();
			int Totalcount = res.getInt(1);
			System.out.println("total number of the books present in the store is : "+Totalcount);
			
			PreparedStatement myStmt = BookShop.con.prepareStatement("select count(*) from book where bookName = ?");
			myStmt.setString(1, bName);
			res = myStmt.executeQuery();
			res.next();
			int count = res.getInt(1);
			System.out.println("total number of "+bName+" books present in the store are : "+count);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void viewBooks() 
	{
		try
		{
			String getBooks = "select * from book";
			res = BookShop.stmt.executeQuery(getBooks);
			while(res.next()) 
			{
				System.out.println("bookId : "+res.getString("bookId")+"\nbookName : "+res.getString("bookName")+"\nauthorkName : "+res.getString("authorName")+"\nbookGenre : "
										+res.getString("bookGenre")+"\nbookPrice : "+res.getString("bookPrice"));
				System.out.println("-------------------------------------------------");
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
}
