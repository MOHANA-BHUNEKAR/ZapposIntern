package zappos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



/**
 * Servlet implementation class Servvlet
 */
@WebServlet("/Servvlet")
public class Servvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String quantity3=request.getParameter("quantity");
		String price3=request.getParameter("price");
		
		int quantity=Integer.parseInt(quantity3);
		float price=Float.parseFloat(price3);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("You have entered quantity: "+quantity+"<br>"+" and price: "+price+"<br>");
		
		
		
		 
		   String iniRequest="http://api.zappos.com/Search?term=&limit=100&excludes=[%22styleId%22,%22colorId%22,%22brandName%22,%22productUrl%22,%22thumbnailImageUrl%22,%22originalPrice%22,%22percentOff%22]&page=1&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73";
           String Jjsonn=HttpGet(iniRequest);
		    int totalCount=jsonToJava(Jjsonn);
		    
		    String strUrl[]=new String[totalCount];
			 String Jjson[]=new String[totalCount];
			 ArrayList<ResultClass> price_product = new ArrayList<ResultClass>();
			 ArrayList<ResultClass> price_product1 = new ArrayList<ResultClass>();
			 
            totalCount=totalCount/500;
            
            
            
		   	for ( int i = 1 ; i <= totalCount; i++)
		   	{
		   	strUrl[i]="http://api.zappos.com/Search?term=&limit=100&excludes=[%22styleId%22,%22colorId%22,%22brandName%22,%22productUrl%22,%22thumbnailImageUrl%22,%22originalPrice%22,%22percentOff%22]&page="+i+"&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73";
		   	
		   
		    Jjson[i]=HttpGet(strUrl[i]);
		    // HttpGet("http://developer.zappos.com/");			  

		price_product=jsonToJava(Jjson[i],price);
		price_product1.addAll(price_product);
	
		   	}
		   	
		   	Collections.sort(price_product1);

	
		
		float price_upperLimit=(float) (price*1.03);
		float price_lowerlimit=(float) (price*0.97);
		out.print("<br>"+"UpperLimit: "+price_upperLimit+"<br>"+"price_lowerlimit: "+price_lowerlimit+"<br>");
		out.print("<br>");
		float sum=price;
		int i1,j1,k1,l1,searchresults=0;
		float productSum;
		for( i1=0;i1<price_product1.size();i1++)
		{
	
			sum=0;
			int quan=price_product1.size();
			for(j1=i1;j1<=quantity+i1-2&& j1<quan;j1++)
			{

				sum+=price_product1.get(j1).Price;
				
			}
			
			for(l1=j1;l1<price_product1.size()-1;l1++)
			{
				productSum=0;
			if(sum+price_product1.get(l1).Price<price_upperLimit && sum+price_product1.get(l1).Price> price_lowerlimit)
			{

				for(k1=i1;k1<j1;k1++)
				{
             productSum=productSum+price_product1.get(k1).Price;
					out.println("ProductId:--> "+price_product1.get(k1).ProductId+"---"+ "Price:  "+price_product1.get(k1).Price+"<br>");
				}
				
				out.println("ProductId:--> "+price_product1.get(l1).ProductId+"---"+ "Price:  "+price_product1.get(l1).Price+"<br>");
				searchresults++;
				float finsum=productSum+price_product1.get(l1).Price;
				out.print("  sum:---> "+finsum+"<br><br><br>");
				break;

			}

		}
	}
		
		System.out.println("Total Search results: "+searchresults);

		   	}
	
		

	private int jsonToJava(String jjsonn) {
		// TODO Auto-generated method stub
		   Gson gson = new Gson();  
		   Data student = gson.fromJson(jjsonn,Data.class);  
		   
		int x=Integer.parseInt(student.getTotalResultCount());
		System.out.println("Total Result count:------------------------> "+x);
		return x;
	}

	private ArrayList<ResultClass> jsonToJava(String str,float price2) {
		// TODO Auto-generated method stub
		
				 // obtained Gson object  
			
				   Gson gson = new Gson();  
//				 
				   // passed  class reference to convert converted result as Student object  
				   Data student = gson.fromJson(str,Data.class);  
				   int j=0;  
				   String s[]=new String[student.getResults().size()];
				   String price[]=new String[student.getResults().size()];
				   String productId[]=new String[student.getResults().size()];
				   ArrayList<String> price_product= new ArrayList<String>();
				   
			 ArrayList<ResultClass> results = new ArrayList<ResultClass>();
            
		   for(int i=0;i<=student.getResults().size()-1;i++){  
				    s[i]= student.getResults().get(i).toString();
				    price[i]=s[i].substring(s[i].indexOf('$')+1, s[i].indexOf(','));
				 //   int sindex=s[i].indexOf('$');
				 //   int eindex=s[i].indexOf(',');
				    Float pri=Float.parseFloat(price[i]);
				   if(pri<price2)
				    		{
					   j++;
					
				    productId[i]=s[i].substring(s[i].lastIndexOf('=')+1,s[i].indexOf('}'));
				
				   String pri_pro=price[i]+" "+productId[i];
				   price_product.add(pri_pro);
				   
				
				    ResultClass result=new ResultClass(Float.parseFloat(price[i]),productId[i]);
				  results.add(result);
				  
				   }}
		
		   return results;
		
	}

	public String HttpGet(String strUrl) throws IOException {
		// TODO Auto-generated method stub
		 URL url = new URL(strUrl);
		  HttpURLConnection conn =
		      (HttpURLConnection) url.openConnection();

		  if (conn.getResponseCode() != 200) {
			  
			 // System.out.println("Response code: "+ conn.getResponseCode());
		    throw new IOException(conn.getResponseMessage());
		  }

		  // Buffer the result into a string
		  BufferedReader rd = new BufferedReader(
		      new InputStreamReader(conn.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  rd.close();

		  conn.disconnect();
		  return sb.toString();
	}

	}


