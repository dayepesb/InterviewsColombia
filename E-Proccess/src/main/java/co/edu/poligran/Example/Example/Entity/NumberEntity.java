package co.edu.poligran.Example.Example.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "numbers")
public class NumberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idnumber;

	private String number;

	private String fibonacci;

	private String factorial;

	public Integer getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(Integer idnumber) {
		this.idnumber = idnumber;
	}

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
