import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		

		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
		utility.storeProduct(name, type, maker, access);
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<tr>");
				pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("</tr>");
				total = total +oi.getPrice();
				i++;
			}
			pw.print("<input type='hidden' name='orderTotal' value='"+total+"'>");	
			pw.print("<tr><th></th><th>Total</th><th>"+total+"</th>");
			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			pw.print("</table></form>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			pw.print(carousel.carouselfeature(utility));
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}




/*
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		

		
		
		utility.storeProduct(name, type, maker, access);
		displayCart(request, response);
	}
	


	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<tr>");
				pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("</tr>");
				total = total +oi.getPrice();
				i++;
			}
			pw.print("<input type='hidden' name='orderTotal' value='"+total+"'>");	
			pw.print("<tr><th></th><th>Total</th><th>"+total+"</th>");
			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			pw.print("</table></form>");
          
          
			pw.print("<div id='content'><div class='post'><h2 class='title meta'><a style='font-size: 24px;'>User who bough above product also bough this one :</a></h2><div id='myCarousel' class='carousel slide' data-ride='carousel'><ol class='carousel-indicators'><li data-target='#myCarousel' data-slide-to='0' class='active'></li><li data-target='#myCarousel' data-slide-to='1'></li><li data-target='#myCarousel' data-slide-to='2'></li></ol><div class='carousel-inner'><div class='item active'><div class='col-md-6' style='background-color: white;border :1px solid #cfd1d3'><div id='shop_item'><h3>Microsoft Wireless Keyboard and Mouse</h3><strong>30.99$</strong><ul><li id='item'><img src='images/accessories/mouse.jpg' alt=''></li><li><form method='post' action='Cart'><input type='hidden' name='name' value='macbook_rs'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' class='btnbuy' value='Buy Now'></form></li><li><form method='post' action='WriteReview'><input type='hidden' name='name' value='macbook_rs'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' value='WriteReview' class='btnreview'></form></li><li><form method='post' action='ViewReview'><input type='hidden' name='name' value='macbook rs'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' value='ViewReview' class='btnreview'></form></li></ul></div></div></div><div class='item'><div class='col-md-6' style='background-color: white;border :1px solid #cfd1d3'><div id='shop_item'><h3>Apple Lightning-to-3.5mm Headphone Adapter</h3><strong>10.99$</strong><ul><li id='item'><img src='images/accessories/adapter.jpg' alt=''></li><li><form method='post' action='Cart'><input type='hidden' name='name' value='macbook_wc'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' class='btnbuy' value='Buy Now'></form></li><li><form method='post' action='WriteReview'><input type='hidden' name='name' value='macbook_wc'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' value='WriteReview' class='btnreview'></form></li><li><form method='post' action='ViewReview'><input type='hidden' name='name' value='macbook_wc'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' value='ViewReview' class='btnreview'></form></li></ul></div></div></div><div class='item'><div class='col-md-6' style='background-color: white;border :1px solid #cfd1d3'><div id='shop_item'><h3>Pendrive</h3><strong>89.99$</strong><ul><li id='item'><img src='images/accessories/pendrive.jpg' alt=''></li><li><form method='post' action='Cart'><input type='hidden' name='name' value='macbook_kg'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' class='btnbuy' value='Buy Now'></form></li><li><form method='post' action='WriteReview'><input type='hidden' name='name' value='macbook_kg'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' value='WriteReview' class='btnreview'></form></li><li><form method='post' action='ViewReview'><input type='hidden' name='name' value='macbook_kg'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='laptop'><input type='hidden' name='access' value='macbook'><input type='submit' value='ViewReview' class='btnreview'></form></li></ul></div></div></div></div><a class='left carousel-control' href='#myCarousel' data-slide='prev'><span class='glyphicon glyphicon-chevron-left'></span><span class='sr-only'>Previous</span></a><a class='right carousel-control' href='#myCarousel' data-slide='next'><span class='glyphicon glyphicon-chevron-right'></span><span class='sr-only'>Next</span></a></div></div></div>");
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
} */
