package user.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UsersUserDetailService userUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//시나리오 적용. HttpSecurity객체에 설정해줌.
		http.authorizeHttpRequests().antMatchers("/").permitAll() //인증만이라면
		//얘한테 인증가 인가를 적용. 슬래시요청이 들어오면, 무조건 허용-permitAll메서드. 
									.antMatchers("/member/**").authenticated() //인가만이라면
									.antMatchers("/manager/**").hasRole("MANAGER") //권한
									.antMatchers("/admin/**").hasRole("admin"); //권한
		//(/**)은 그 하위경로 uri 다 포함 
		// http.authorizeHttpRequests() 이 부분 여러개라면 생략 가능(대신에 세미나콜론 비연결)
		
		http.csrf().disable();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess",true);
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		http.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		//세션에 로그인 정보저장. 세션에 있는 정보 제거해야 함. 델리트쿠키로 쿠키삭제도 가능함. 로그아웃 성공시 다시 로그인페이지로
		
		http.userDetailsService(userUserDetailService);
	
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
//인증에 필요한 사용자 정보를 메모리에 저장
/*
 * @Autowired public void authenticate(AuthenticationManagerBuilder auth) throws
 * Exception{ auth.inMemoryAuthentication().withUser("manager")
 * .password("{noop}manager123") //{noop}은 비밀번호를 암호화처리하지 않겠다는 의미다.
 * .roles("MANAGER");
 * 
 * auth.inMemoryAuthentication().withUser("admin") .password("{noop}admin123")
 * .roles("ADMIN"); }
 * 
 * }
 */
