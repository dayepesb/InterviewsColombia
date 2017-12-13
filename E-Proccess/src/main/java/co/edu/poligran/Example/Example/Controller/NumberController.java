package co.edu.poligran.Example.Example.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poligran.Example.Example.Entity.NumberEntity;
import co.edu.poligran.Example.Example.service.Impl.NumberServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/number")
public class NumberController {

	@Autowired
	private NumberServiceImpl nsi;

	@RequestMapping(value = "/findby/id", method = RequestMethod.GET)
	public String findById(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
		NumberEntity ne = nsi.findById(id);
		;
		if (ne == null) {
			return "{\"number\":\"null\"}";
		} else {
			return "{\"number\":\"" + ne.getNumber() + "\",\"factorial\":\"" + ne.getFactorial() + "\",\"fibonacci\":\""
					+ ne.getFibonacci() + "\"}";
		}
	}

	@RequestMapping(value = "/findby/number", method = RequestMethod.GET)
	public String findByNumber(@RequestParam("number") String number, HttpServletResponse response) throws IOException {
		NumberEntity ne = nsi.findByNumber(number);
		if (ne == null) {
			return "{\"number\":\"null\"}";
		} else {
			return "{\"number\":\"" + ne.getNumber() + "\",\"factorial\":\"" + ne.getFactorial() + "\",\"fibonacci\":\""
					+ ne.getFibonacci() + "\"}";
		}
	}

	@RequestMapping(value = "/save/number", method = RequestMethod.GET)
	public NumberEntity findByUsername(@RequestParam("number") String number,
			@RequestParam("fibonacci") String fibonacci, @RequestParam("factorial") String factorial,
			HttpServletResponse response) throws IOException {

		NumberEntity ne = new NumberEntity();
		ne.setNumber(number);
		ne.setFactorial(factorial);
		ne.setFibonacci(fibonacci);
		return nsi.saveNumber(ne);
	}

}
