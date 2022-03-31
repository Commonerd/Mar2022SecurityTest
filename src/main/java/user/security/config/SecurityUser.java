package user.security.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import user.security.dto.Users;

public class SecurityUser extends User {

	private static final long serialVersionUID = 1L;
	
	public SecurityUser(Users users) {
		super(users.getId(), users.getPassword(),
				AuthorityUtils.createAuthorityList(users.getRole().toString()));
	}
}


/*//암호화하고 싶지 않다면 아래와 같이 {noop}
 * super(users.getId(),"{noop}"+users.getPassword(),
 * AuthorityUtils.createAuthorityList(users.getRole().toString())); }
 */