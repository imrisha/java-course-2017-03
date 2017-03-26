package com.example;

import com.jcourse.kladov.StackCalc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Map;

@Controller
@SpringBootApplication
public class Task2aApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task2aApplication.class, args);
	}

	@RequestMapping(value = "/evaluate", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> evaluate(@RequestParam("data") String data) {
		try {
			if (data == null || data.equals("")) {
				return Ajax.successResponse(new String("Nothing to evaluate"));
			}

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			StackCalc calc = new StackCalc(new BufferedReader(new StringReader(data)), new PrintStream(byteArrayOutputStream));
			calc.evaluate();
			String result = String.format("Evaluate '%s' expression, result is: '%s'", data, byteArrayOutputStream.toString());

			return Ajax.successResponse(result);
		} catch (Exception e) {
			return Ajax.errorResponse(e.toString());
		}
	}
}
