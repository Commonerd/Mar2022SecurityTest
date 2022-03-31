package user.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import user.security.dao.UsersDao;
import user.security.dto.Role;
import user.security.dto.Users;

@Service
public class UsersService {
		@Autowired
		UsersDao dao;
		
		@Autowired
		PasswordEncoder encoder;
		
		public int insertUser(Users users) {
			
			users.setId("tester");
			users.setPassword(encoder.encode("tester123"));
			users.setName("테스터");
			users.setRole(Role.ROLE_MANAGER);
			
			return dao.insertUser(users);
		}
}
