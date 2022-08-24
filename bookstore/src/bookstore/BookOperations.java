package bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class BookOperations
{
	static ResultSet res = null;
	public static void addBook(String bName, String aName, String bGenre, double bPrice) 
	{
		try
		{
			String sql = "INSERT INTO book1 (bookName,authorName,bookGenre,bookPrice) VALUES (?,?,?,?)";
			PreparedStatement stmt = BookShop.con.prepareStatement(sql);
			//stmt.setInt(1, bId);
			stmt.setString(1, bName);
			stmt.setString(2, aName);
			stmt.setString(3, bGenre);
			stmt.setDouble(4, bPrice);
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
			
			String getTotalCount = "select count(*) from book1";
			res = BookShop.stmt.executeQuery(getTotalCount);
			res.next();
			int Totalcount = res.getInt(1);
			System.out.println("total number of the books present in the store is : "+Totalcount);
			System.out.println("");
			
			PreparedStatement myStmt = BookShop.con.prepareStatement("select count(*) from book1 where bookName = ?");
			myStmt.setString(1, bName);
			res = myStmt.executeQuery();
			res.next();
			int count = res.getInt(1);
			System.out.println("total number of "+bName+" books present in the store are : "+count);
			System.out.println();
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
			String getBooks = "select * from book1";
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
	
	public static void getBookByAuthor(String aName) 
	{
		try
		{
			PreparedStatement stmt = BookShop.con.prepareStatement("select * from book1 where authorName = ?");
			stmt.setString(1, aName);
			res = stmt.executeQuery();
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
	
	public static void getBookByGenre(String bGenre) 
	{
		try
		{
			PreparedStatement stmt = BookShop.con.prepareStatement("select * from book1 where bookGenre = ?");
			stmt.setString(1, bGenre);
			res = stmt.executeQuery();
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
	
	public static void sellBook(int bId)
	{
		try 
		{
			PreparedStatement stmt = BookShop.con.prepareStatement("delete from book1 where bookId = ?");
			stmt.setInt(1, bId);
			int rowsAffected = stmt.executeUpdate();
					
			System.out.println("Book has been sold successfully\ncurrent stock are : ");

			/*String q = "alter table book1 drop bookId";  
			BookShop.stmt.executeUpdate(q);
			
			String q1 = "alter table book1 add bookId int not null auto_increment primary key first";  
			BookShop.stmt.executeUpdate(q1);*/
			viewBooks();
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
