package zappos;

import java.util.List;

public class Data {

	private String currentResultCount;
   	private String limit;
   	private String originalTerm;
   	private List results;
   	private String statusCode;
   	private String term;
   	private String totalResultCount;
   	
   	

 	public String getCurrentResultCount(){
		return this.currentResultCount;
	}
	public void setCurrentResultCount(String currentResultCount){
		this.currentResultCount = currentResultCount;
	}
 	public String getLimit(){
		return this.limit;
	}
	public void setLimit(String limit){
		this.limit = limit;
	}
 	public String getOriginalTerm(){
		return this.originalTerm;
	}
	public void setOriginalTerm(String originalTerm){
		this.originalTerm = originalTerm;
	}
 	public List getResults(){
		return this.results;
	}
	public void setResults(List results){
		this.results = results;
	}
 	public String getStatusCode(){
		return this.statusCode;
	}
	public void setStatusCode(String statusCode){
		this.statusCode = statusCode;
	}
 	public String getTerm(){
		return this.term;
	}
	public void setTerm(String term){
		this.term = term;
	}
 	public String getTotalResultCount(){
		return this.totalResultCount;
	}
	public void setTotalResultCount(String totalResultCount){
		this.totalResultCount = totalResultCount;
	}
}
