package user.security.dao;

import org.apache.ibatis.annotations.Mapper;

import user.security.dto.Users;

@Mapper
public interface UsersDao {

	Users findById(String id);

	int insertUser(Users users);
}
