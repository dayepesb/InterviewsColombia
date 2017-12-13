package co.edu.poligran.Example.Example.Rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestNumber {
	private String number;
	private String fibonacci;
	private String factorial;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFibonacci() {
		return fibonacci;
	}
	public void setFibonacci(String fibonacci) {
		this.fibonacci = fibonacci;
	}
	public String getFactorial() {
		return factorial;
	}
	public void setFactorial(String factorial) {
		this.factorial = factorial;
	}
	
}