package user.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import user.security.dto.Users;
import user.security.service.UsersService;

@Controller
public class SecurityController {
	
	@Autowired
	UsersService uservice;
	
	@GetMapping("/")
	public String index() {
	//디스패처 서블릿이 불러오니까 당연히 퍼블릭으로 시작
		System.out.println("index 요청입니다.");
		return "index";	
	}
	
	@GetMapping("/member") //요청과 뷰네임이 같으면 생략가능하다 JSP파일과 요청네임이 같음.
	public void forMember() {
		System.out.println("Member 요청입니다.");
	}
	@GetMapping("/manager")
	public void forManager() {
		System.out.println("Manager 요청입니다.");
	}
	@GetMapping("/admin")
	public void forAdmin() {
		System.out.println("Admin 요청입니다.");
	}
	@GetMapping("/login") //login.jsp페이지 찾아서 출력
	public void login() {
	}

	@GetMapping("/loginSuccess") //loginSuccess요청. 
	public void loginSuccess() {
	}
	
	@GetMapping("/accessDenied") //accessDenied요청.
	public void acessDenied() {
	}
	@GetMapping("/insert")
	public String insert(Users users) {
		uservice.insertUser(users);
		return "redirect:/";
	}
}
