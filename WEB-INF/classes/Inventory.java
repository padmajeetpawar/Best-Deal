import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

@WebServlet("/Inventory")

public class Inventory extends HttpServlet {
        static Connection conn = null;
        static String message;
        public static String getConnection()
        {

            try
            {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");							
            message="Successfull";
            return message;
            }
            catch(SQLException e)
                {
                    message="unsuccessful";
                         return message;
                }
                catch(Exception e)
                {
                    message=e.getMessage();
                    return message;
                }
            }

            protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String name = "Inventory";


            Utilities utility = new Utilities(request, pw);
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            

            try
            {
            getConnection();
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT productName, productPrice, productQuantity FROM productdetails";
            ResultSet rs = stmt.executeQuery(sql);


            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Products with Available Quantity</a>");
            pw.print("</h2><div class='entry'><table id='bestseller'>");

                pw.print("<tr>");
                pw.print("<td>");
                pw.print("<b>Product Name</b>");
                pw.print("</td>");
                pw.print("<td>");
                pw.print("<b>Price</b>");
                pw.print("</td>");
                pw.print("<td>");
                pw.print("<b>Quantity Available</b>");
                pw.print("</td>");
                pw.print("</tr>");
                while(rs.next())
                {
                String productname  = rs.getString("productName");
                double productprice  = rs.getDouble("productPrice");
                int productquantity  = rs.getInt("productQuantity");

                pw.print("<tr>");
                pw.print("<td>");
                pw.print(productname);
                pw.print("</td>");
                pw.print("<td>");
                pw.print(productprice);
                pw.print("</td>");
                pw.print("<td>");
                pw.print("   "+productquantity);
                pw.print("</td>");
                pw.print("</tr>");
                }

             rs.close();
             stmt.close();
            } catch(SQLException se) {
             se.printStackTrace();
            } 
            catch(Exception e) 
            {
             e.printStackTrace();
            }
    
            try
            {
            getConnection();
            Statement stmt1 = conn.createStatement();
            String sql1;
            sql1 = "SELECT productName, productDiscount FROM productdetails";
            ResultSet rs1 = stmt1.executeQuery(sql1);

            pw.print("</table></div></div></div>");	

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>On Sale Products</a>");
            pw.print("</h2><div class='entry'><table id='bestseller'>");

                pw.print("<tr>");
                pw.print("<td>");
                pw.print("<b>Product Name</b>");
                pw.print("</td>");
                pw.print("<td>");
                pw.print("<b>Product Discount</b>");
                pw.print("</td>");
                pw.print("</tr>");
               
                while(rs1.next())
                {
                String productname1  = rs1.getString("productName");
                double productdiscount1  = rs1.getDouble("productDiscount");
                double temp1 = 0.0;
                
                if(productdiscount1 > temp1)
                {
                    pw.print("<tr>");
                    pw.print("<td>");
                    pw.print(productname1);
                    pw.print("</td>");
                    pw.print("<td>");
                    pw.print(productdiscount1);
                    pw.print("</td>");
                    pw.print("</tr>"); 
                }
               
                }
            

             rs1.close();
             stmt1.close();
            } catch(SQLException se) {
             se.printStackTrace();
            } 
            catch(Exception e) 
            {
             e.printStackTrace();
            }
    
                
                
                
            try
            {
            getConnection();
            Statement stmt2 = conn.createStatement();
            String sql2;
            sql2 = "SELECT productName, manufactureRebate FROM productdetails";
            ResultSet rs2 = stmt2.executeQuery(sql2);

            pw.print("</table></div></div></div>");	

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Products with Manufacture Rebate</a>");
            pw.print("</h2><div class='entry'><table id='bestseller'>");

                pw.print("<tr>");
                pw.print("<td>");
                pw.print("<b>Product Name</b>");
                pw.print("</td>");
                pw.print("<td>");
                pw.print("<b>Manufacturer Rebate</b>");
                pw.print("</td>");
                pw.print("</tr>");
               
                while(rs2.next())
                {
                String productname2  = rs2.getString("productName");
                String manrebate  = rs2.getString("manufactureRebate");
                String manrebate2 = "YES";
                
                if(manrebate.equals(manrebate2))
                {
                    pw.print("<tr>");
                    pw.print("<td>");
                    pw.print(productname2);
                    pw.print("</td>");
                    pw.print("<td>");
                    pw.print(manrebate);
                    pw.print("</td>");
                    pw.print("</tr>"); 
                }
               
                }
            

             rs2.close();
             stmt2.close();
            } catch(SQLException se) {
             se.printStackTrace();
            } 
            catch(Exception e) 
            {
             e.printStackTrace();
            }
                
                
                
                
        }
        
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}

}
