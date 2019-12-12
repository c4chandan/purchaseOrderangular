package com.project.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.User;
import com.project.service.UserService;

@Controller
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	private static final Logger logger = Logger.getLogger(UserController.class);

	@PostMapping("/user")
	public ResponseEntity<?> addUser(@RequestBody User uObj) {
		try {
			logger.info("i am in my UserController and method name is addUser");
			boolean b = userService.addUser(uObj);
			if (b) {
				return new ResponseEntity<String>("User Added Successfully..", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("problem  in Adding user..", HttpStatus.INTERNAL_SERVER_ERROR);

			}

		} catch (Exception e) {

			logger.error("error in addUser" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/userValidate", method = RequestMethod.POST)
	public ResponseEntity<?> validateUser(@RequestParam(name = "userEmail") String userEmail,
			@RequestParam(name = "userPass") String userPass, ModelMap map) {

		try {

			logger.info("i am in my UserController and method name is validateUser");
			User u = userService.validateUser(userEmail, userPass);

			if (u != null) {
				System.out.println(u);
				return new ResponseEntity<User>(u, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Not found", HttpStatus.INTERNAL_SERVER_ERROR);

			}

		} catch (Exception e) {

			logger.error("error in validateUser" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
