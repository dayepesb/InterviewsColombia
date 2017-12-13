package co.edu.poligran.Example.Example.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

import org.springframework.web.client.RestTemplate;

import co.edu.poligran.Example.Example.Rest.RestNumber;

public class MainApp {
	private static RestNumber rn;
	private static String url = "http://localhost:8080/number";

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		for (String line; !(line = in.readLine().trim()).equalsIgnoreCase("END");) {
			double a = 3.23606797749979;
			double b = 1.23606797749979;

			boolean bool = true;
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (!Character.isDigit(c)) {
					out.println("No Es Un Numero");
					out.flush();
					bool = false;
					break;
				}
			}
			if (bool) {
				RestTemplate rt = new RestTemplate();
				rn = rt.getForObject(url + "/findby/number?number=" + line, RestNumber.class);
				if (rn.getNumber().equals("null")) {
					// calcule
					BigInteger bi = new BigInteger(line);
					a = Math.pow(a, Integer.parseInt(bi.toString()));
					b = Math.pow(b, Integer.parseInt(bi.toString()));
					double div = (double) (2 * Integer.parseInt(line) * Math.sqrt(5));
					long fibonacci = (long) ((a) - (b) / (div));

					long factorial = factorial(Long.parseLong(line));

					rt = new RestTemplate();
					rn = rt.getForObject(
							url + "/save/number?number=" + line + "&fibonacci=" + fibonacci + "&factorial=" + factorial,
							RestNumber.class);

					out.printf("Fibonacci : %s%nFactorial : %s%n", rn.getFibonacci(), rn.getFactorial());
					out.flush();
				} else {
					out.printf("Fibonacci : %s%nFactorial : %s%n", rn.getFibonacci(), rn.getFactorial());
					out.flush();
				}
			}
		}

		out.close();
		in.close();
	}

	static long factorial(long numero) {
		if (numero <= 1) {
			return 1;
		} else {
			RestTemplate rt = new RestTemplate();
			rn = rt.getForObject(url + "/findby/number?number=" + (numero - 1), RestNumber.class);
			if (rt.equals("null")) {
				return numero * factorial(numero - 1);
			} else {
				return numero * Long.parseLong(rn.getFactorial());
			}

		}
	}
}
