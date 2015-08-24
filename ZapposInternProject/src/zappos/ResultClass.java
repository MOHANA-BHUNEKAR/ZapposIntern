package zappos;
import java.util.Comparator;
public class ResultClass implements Comparable<ResultClass>{
	
	String ProductId;
	Float Price;
	
	ResultClass(Float Price1,String Product1)
	{
		Price=Price1;
		ProductId=Product1;
	}
	
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String ProductId ) {
		this.ProductId = ProductId;
	}

	public Float getPrice() {
		return Price;
	}
	public void setPrice(Float Price ) {
		this.Price = Price;
	}
	@Override
	public int compareTo(ResultClass comparePrice) {
		// TODO Auto-generated method stub
		
		Float comparePrice1 = ((ResultClass) comparePrice).getPrice(); 
		return ((int)(this.Price -comparePrice1));
	}

	

	
	}
