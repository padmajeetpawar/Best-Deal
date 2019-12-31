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

@WebServlet("/SalesReport")

public class SalesReport extends HttpServlet {
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
            sql = "SELECT orderName, orderPrice, quantitySold FROM customerorders";
            ResultSet rs = stmt.executeQuery(sql);

            //pw.print("</table></div></div></div>");	

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Table for Products Sold with Sales of Every Product</a>");
            pw.print("</h2><div class='entry'><table id='bestseller'>");

                pw.print("<tr>");
                pw.print("<td>");
                pw.print("<b>Product Name</b>");
                pw.print("</td>");
                pw.print("<td>");
                pw.print("<b>Product Price</b>");
                pw.print("</td>");
                pw.print("<td>");
                pw.print("<b>Quantity Sold</b>");
                pw.print("</td>");
                pw.print("</tr>");
                while(rs.next())
                {
                String productname  = rs.getString("orderName");
                double productprice  = rs.getDouble("orderPrice");
                int quantitysold  = rs.getInt("quantitySold");

                pw.print("<tr>");
                pw.print("<td>");
                pw.print(productname);
                pw.print("</td>");
                pw.print("<td>");
                pw.print(productprice);
                pw.print("</td>");
                pw.print("<td>");
                pw.print(quantitysold);
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
            sql1 = "SELECT date, noOfSales FROM orderDates";
            ResultSet rs1 = stmt1.executeQuery(sql1);

            pw.print("</table></div></div></div>");	

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Table for no of sales per day</a>");
            pw.print("</h2><div class='entry'><table id='bestseller'>");

                pw.print("<tr>");
                pw.print("<td>");
                pw.print("<b>Date</b>");
                pw.print("</td>");
                pw.print("<td>");
                pw.print("<b>Total Sale</b>");
                pw.print("</td>");
                pw.print("</tr>");
               
                while(rs1.next())
                {
                String date1  = rs1.getString("date");
                String noofsales  = rs1.getString("noOfsales");
                
               
                    pw.print("<tr>");
                    pw.print("<td>");
                    pw.print(date1);
                    pw.print("</td>");
                    pw.print("<td>");
                    pw.print(noofsales);
                    pw.print("</td>");
                    pw.print("</tr>"); 
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
    
                
                
                
                
        }
        
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}

}
