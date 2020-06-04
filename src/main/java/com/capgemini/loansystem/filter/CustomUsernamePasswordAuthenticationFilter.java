package com.capgemini.loansystem.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.loansystem.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private User user;

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			user = null;
			try {
				User reg = getUser(request);
				return reg.getUser_id() + "";
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return super.obtainUsername(request);
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

			try {
				User reg = getUser(request);
				return reg.getPassword();
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		return super.obtainPassword(request);
	}

	private User getUser(HttpServletRequest request) throws IOException {

		if (user == null) {
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
			BufferedReader reader = request.getReader();
			while (reader.ready()) {
				json = json + reader.readLine();
			}
			user = mapper.readValue(json, User.class);
		}
		return user;
	}

}// End of class
 