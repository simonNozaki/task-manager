package com.tm.dao.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tm.dao.UsersMapper;
import com.tm.dto.Users;
import com.tm.dto.bean.user.UserRegistRequestDto;

/**
 * UsersモデルのカスタムDTOです.
 */
@Mapper
public interface UserRepository extends UsersMapper {

	/**
	 * 新規利用者を登録します。
	 * @param UserRegistRequestDto user
	 * @return Users
	 */
	public Users registerUser(@Param("user")UserRegistRequestDto user);
}
