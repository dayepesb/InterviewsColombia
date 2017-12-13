package com.poligran.edu.Entrevista_Sergio.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poligran.edu.Entrevista_Sergio.Entity.UserEntity;
import com.poligran.edu.Entrevista_Sergio.Service.Impl.UserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl us;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public UserEntity addUser(@RequestParam("name") String name, @RequestParam("lastname") String lastname,
			@RequestParam("address") String address, @RequestParam("zipcode") String zipcode,
			@RequestParam("phone") String phone, @RequestParam("color") String color) {
		return us.saveUser(address, color.toLowerCase(), name, lastname, phone, zipcode);
	}

	@RequestMapping(value = "/list/one", method = RequestMethod.GET)
	public String listUserOne() {
		List<UserEntity> list = us.allUsers();
		HashMap<String, Integer> map = new HashMap<>();
		for (UserEntity ue : list) {
			String color = ue.getColor().toLowerCase();
			if (!map.containsKey(color)) {
				map.put(color, 0);
			}
			map.put(ue.getColor(), map.get(color) + 1);
		}
		int i = 0;
		StringBuilder sb = new StringBuilder("{\"status\":\"sucesful\",\"users\":[");
		for (Entry<String, Integer> cout : map.entrySet()) {
			if (i > 0)
				sb.append(",");
			sb.append("{\"color\":\"" + cout.getKey() + "\",\"count\":" + cout.getValue() + "}");
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	@RequestMapping(value = "/list/two", method = RequestMethod.GET)
	public String listUserTwo() {
		List<UserEntity> list = us.allUsers();
		HashMap<String, Integer> count = new HashMap<>();
		HashMap<String, String> color = new HashMap<>();
		for (UserEntity ue : list) {
			if (!count.containsKey(ue.getColor().toLowerCase())) {
				count.put(ue.getColor().toLowerCase(), 0);
				color.put(ue.getColor().toLowerCase(), "");
			}
			if (count.get(ue.getColor().toLowerCase()) != 0)
				color.put(ue.getColor().toLowerCase(), color.get(ue.getColor().toLowerCase()) + ",");
			color.put(ue.getColor().toLowerCase(), color.get(ue.getColor().toLowerCase()) + "\"" + ue.getName() + " " + ue.getLastname() + "\"");
			count.put(ue.getColor().toLowerCase(), count.get(ue.getColor().toLowerCase()) + 1);
		}
		int i = 0;
		StringBuilder sb = new StringBuilder("{\"status\":\"sucesful\",\"users\":[");
		for (Entry<String, Integer> cout : count.entrySet()) {
			if (i > 0)
				sb.append(",");
			sb.append("{\"color\":\"" + cout.getKey() + "\",\"count\":" + cout.getValue() + ",\"names\":["
					+ color.get(cout.getKey()) + "]}");
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	@RequestMapping(value = "/csv")
	public String saveCsv(@RequestParam("line") String line) {
		String name, lastname, phone, color, zip, address;
		int caseNumber = getCase(line);
		StringTokenizer st = new StringTokenizer(line, ",");
		switch (caseNumber) {
		case 1:
			name = (st.nextToken().trim());
			lastname = (st.nextToken().trim());
			phone = (st.nextToken().trim());
			color = (st.nextToken().trim());
			zip = (st.nextToken().trim());
			us.saveUser("", color, name, lastname, phone, zip);
			return "{\"status\":\"sucesful\"}";
		case 2:
			StringTokenizer st2 = new StringTokenizer(st.nextToken(), ",");
			name = (st2.nextToken().trim());
			lastname = (st2.nextToken().trim());
			color = (st.nextToken().trim());
			zip = (st.nextToken().trim());
			phone = (st.nextToken().trim());
			us.saveUser("", color, name, lastname, phone, zip);
			return "{\"status\":\"sucesful\"}";
		case 3:
			name = (st.nextToken().trim());
			lastname = (st.nextToken().trim());
			zip = (st.nextToken().trim());
			phone = (st.nextToken().trim());
			color = (st.nextToken().trim());
			us.saveUser("", color, name, lastname, phone, zip);
			return "{\"status\":\"sucesful\"}";
		case 4:
			st2 = new StringTokenizer(st.nextToken(), ",");
			name = (st2.nextToken().trim());
			lastname = (st2.nextToken().trim());
			address = (st.nextToken().trim());
			zip = (st.nextToken().trim());
			phone = (st.nextToken().trim());
			color = (st.nextToken().trim());
			us.saveUser(address, color, name, lastname, phone, zip);
			return "{\"status\":\"sucesful\"}";
		default:
			break;
		}
		return null;
	}

	private int getCase(String line) {
		StringTokenizer st = new StringTokenizer(line, ","), st2;
		if (st.countTokens() == 1)
			st = new StringTokenizer(line, ";");
		switch (st.countTokens()) {
		case 4:
			try {
				st2 = new StringTokenizer(st.nextToken());
				String name = st2.nextToken().trim();
				String lastname = st2.nextToken().trim();
				String color = st.nextToken().trim();
				Integer zipcode = Integer.parseInt(st.nextToken().trim());
				String phone = st.nextToken().trim();
				return 2;
			} catch (Exception e) {
				return 10;
			}
		case 5:
			try {
				st2 = new StringTokenizer(st.nextToken());
				if (st2.countTokens() == 2) {
					return 4;
				} else if (st2.countTokens() == 1) {
					String name = st2.nextToken().trim();
					String lastname = st.nextToken().trim();
					String number = st.nextToken().trim();
					String color = st.nextToken().trim().toLowerCase();
					if (constainsColor(color))
						return 1;
					return 3;
				} else
					return 10;

			} catch (Exception e) {
				return 10;
			}
		default:
			return 10;
		}
	}

	private boolean constainsColor(String color) {
		try {
			BufferedReader in = new BufferedReader(
					new FileReader(new File("src/main/java/com/poligran/edu/Entrevista_Sergio/Controller/Colors.txt")));
			for (String line; (line = in.readLine()) != null;) {
				if (line.trim().equals(color.trim()))
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
