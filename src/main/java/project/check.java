package project;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class check {

	private static String accno;  
    private static String name;  
    private static String acc_type;  
    private static long balance; 
    static String amt;
    Scanner sc = new Scanner(System.in);  
    //method to open new account  
    public void openAccount() {  
        System.out.print("Enter Account No: ");  
        accno = sc.next();  
        System.out.print("Enter Account type: ");  
        acc_type = sc.next();  
        System.out.print("Enter Name: ");  
        name = sc.next();  
        System.out.print("Enter Balance: ");  
        balance = sc.nextLong(); 
        
    }  
    //method to display account details  
    public void checkbalance() { 
    	 
    	System.out.println("enter account no of balance checking");
    	 accno= sc.next();
        }
    public void deposit() {  
         
        System.out.println("Enter the amount you want to deposit: and account no ");  
        amt = sc.next(); 
        accno=sc.next();
   
    }  
    //method to withdraw money  
   
    
public  void connectinsert()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "scott";
		String password = "tiger";
		
		Connection con = DriverManager.getConnection(url, userName, password);
		
		Statement stmt = con.createStatement();
		
		 String q1 = "insert into bankdetail values('" +accno+ "', '" +name+ "', '" +acc_type+ "', " +balance+ ")";
    int x = stmt.executeUpdate(q1);


con.close();
}
	catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}


}
public  void connectdeposit()
{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "scott";
		String password = "tiger";
		
		Connection con = DriverManager.getConnection(url, userName, password);
		
		Statement stmt = con.createStatement();
		 if (amt ==null || accno ==null) {
	            System.out.println("All Field Required!");
	            return ;
	        }
		
   
	String 	 sql = "select * from bankdetail where accountno="
                 + accno;
     
       String  sqli = "update bankdetail set balance=balance+"
               + amt + " where accountno=" + accno;
         stmt.executeUpdate(sqli);
        con.close();
	}
	catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} 
	catch (SQLException e) {
		e.printStackTrace();
	
	}
	}
public void connectwidraw()
{
	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			
			
			Statement stmt = con.createStatement();
			
	           
	            ResultSet rs = stmt.executeQuery ( "select * from bankdetail where accountno="
		                  + accno);
	 
	            if (rs.next()) {
	          int  a=  rs.getInt("balance") ;
	          int t= Integer.parseInt(amt);
	                		if(a < t) {
	                    System.out.println(
	                        "Insufficient Balance!");
	                    return ;
	                }
	            }
	 
	 
			 if (amt ==null || accno ==null) {
		            System.out.println("All Field Required!");
	            return ;
		        }
		
	     
	       String  sqli = "update bankdetail set balance=balance- "
	               +amt + " where accountno=" + accno;
	         stmt.executeUpdate(sqli);
	         if(sqli!=null)
	         {
	         System.out.println(", balance: " + rs.getInt("balance"));
	         }
			
		         
	con.close();
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		
		}
		}


	public  void connectcheck()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from bankdetail where accountno = " +accno  );
			 
			 while(rs.next()){
		            //Display values
		            System.out.print("acc_no: " + rs.getString("accountno"));
		            System.out.print(",name: " + rs.getString("acc_name"));
		            System.out.print(",type: " + rs.getString("acc_type"));
		            System.out.println(", balance: " + rs.getInt("balance"));
		         }
	 

	con.close();
	}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}


}


public static void main(String arg[]) {  
    Scanner sc = new Scanner(System.in);  
    //create initial accounts  
    System.out.print("How many number of customers do you want to input? ");  
    int n = sc.nextInt();  
   check C[] = new  check [n];  
    for (int i = 0; i < C.length; i++) {  
        C[i] = new   check();  
        
    }  
    // loop runs until number 5 is not pressed to exit  
    int ch;  
    do {  
        System.out.println("\n ***Banking System Application***");  
        System.out.println("1.open the account \n 2. check balance\n 3. Deposit the amount \n 4. Withdraw the amount \n 5.Exit ");  
        System.out.println("Enter your choice: ");  
        ch = sc.nextInt();  
            switch (ch) {  
            case 1:
            {
          	  System.out.println("\n ***welcome to our bank***"); 
          	  for (int i = 0; i < C.length; i++) {  
                    C[i].openAccount();  
                    C[i].connectinsert();
                } 
            }
                break;  
             
            
            case 2:  
            {   
            	for (int i = 0; i < C.length; i++) {  
            		C[i].checkbalance();
                        C[i].connectcheck();
                    }
            }
                    break;  
                 
                case 3: 
              	  
                {
                    for (int i = 0; i < C.length; i++) {  
                         
                        
             C[i].deposit();  
  C[i].connectdeposit() ;                 
            }
            }
                    break;  
                case 4: 
                {

                    for (int i = 0; i < C.length; i++) {  
                         
                        
             C[i].deposit();  
  C[i].connectwidraw() ;                 
            }
             
                    }  
                    break;  
                case 5:  
                    System.out.println("See you soon...");  
                    break;  
            }  
        }  
        while (ch != 5);  
    }  
} 
