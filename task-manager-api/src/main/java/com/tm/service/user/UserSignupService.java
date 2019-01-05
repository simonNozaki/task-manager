package com.tm.service.user;

import org.springframework.stereotype.Service;

import com.tm.dto.Users;
import com.tm.dto.bean.user.UserRegistRequestDto;
import com.tm.dto.common.ServiceOut;

/**
 * 利用者登録Service。
 */
@Service
public interface UserSignupService {

	/**
	 * 実行メソッド。
	 * @param UserRegistRequestDto user
	 * @return Users
	 * @throws Exception
	 */
	public ServiceOut<Users> execute(UserRegistRequestDto user) throws Exception;
}
