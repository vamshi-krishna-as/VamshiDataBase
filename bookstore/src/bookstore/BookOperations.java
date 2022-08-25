package bookstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class BookOperations
{
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
			BookShop.res = BookShop.stmt.executeQuery(getTotalCount);
			BookShop.res.next();
			int Totalcount = BookShop.res.getInt(1);
			System.out.println("total number of the books present in the store is : "+Totalcount);
			System.out.println("");
			
			PreparedStatement myStmt = BookShop.con.prepareStatement("select count(*) from book1 where bookName = ?");
			myStmt.setString(1, bName);
			BookShop.res = myStmt.executeQuery();
			BookShop.res.next();
			int count = BookShop.res.getInt(1);
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
			BookShop.res = BookShop.stmt.executeQuery(getBooks);
			if(BookShop.res.next()  == true)
			{
				do
				{
					System.out.println("bookId : " + BookShop.res.getString("bookId") + "\nbookName : "
							+ BookShop.res.getString("bookName") + "\nauthorkName : "
							+ BookShop.res.getString("authorName") + "\nbookGenre : "
							+ BookShop.res.getString("bookGenre") + "\nbookPrice : "
							+ BookShop.res.getString("bookPrice"));
					System.out.println("-------------------------------------------------");
				}while(BookShop.res.next()) ;
			}
			else
			{
				System.out.println("Book Store is empty\n");
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
			BookShop.res = stmt.executeQuery();
			if(BookShop.res.next() == true)
			{
				do
				{
					System.out.println("bookId : " + BookShop.res.getString("bookId") + "\nbookName : "
							+ BookShop.res.getString("bookName") + "\nauthorkName : "
							+ BookShop.res.getString("authorName") + "\nbookGenre : "
							+ BookShop.res.getString("bookGenre") + "\nbookPrice : "
							+ BookShop.res.getString("bookPrice"));
					System.out.println("-------------------------------------------------");
				}while(BookShop.res.next());

			}
			else
			{
				System.out.println(aName+" books are not present in the book store\n");
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
			BookShop.res = stmt.executeQuery();
			if(BookShop.res.next() == true)
			{
				do
				{
					System.out.println("bookId : " + BookShop.res.getString("bookId") + "\nbookName : "
							+ BookShop.res.getString("bookName") + "\nauthorkName : "
							+ BookShop.res.getString("authorName") + "\nbookGenre : "
							+ BookShop.res.getString("bookGenre") + "\nbookPrice : "
							+ BookShop.res.getString("bookPrice"));
					System.out.println("-------------------------------------------------");
				}while(BookShop.res.next());

			}
			else 
			{
				System.out.println(bGenre+" books are not present in the store\n");
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
			
			if(rowsAffected==1) 
			{
				System.out.println("Book has been sold successfully\ncurrent stock are : ");
				viewBooks();

			}
			else
			{
				System.out.println("Id "+bId+" is not present in the book store\n");
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
