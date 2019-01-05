package com.tm.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.dao.repository.UserRepository;
import com.tm.dto.Users;
import com.tm.dto.UsersExample;
import com.tm.dto.bean.user.UserRegistRequestDto;
import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.framework.BaseService;
import com.tm.service.user.UserSignupService;
import com.tm.util.CryptoUtil;
import com.tm.util.IdCounter;
import com.tm.util.ObjectUtil;

/**
 * 利用者登録Serviceの実装クラス。
 */
@Service
public class UseSignupServiceImpl extends BaseService implements UserSignupService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ServiceOut<Users> execute(UserRegistRequestDto user) throws Exception{
		// 入力チェック。同一メールアドレスですでに登録がある場合はエラーを返却
		if (!ObjectUtil.isNullOrEmpty(hasRegisteredEmail(user))) {
            List<String> codes = new ArrayList<>();
            codes.add(TaskManagerErrorCode.ERR130004.getCode());
            throw new TaskManagerErrorRuntimeException(new Errors(codes));
		}

		// DB登録用にBeanの詰替およびID発番
		Users newUser = new Users();
		BeanUtils.copyProperties(user, newUser);
		newUser.setUserId(IdCounter.assignIdForUser(8));
		newUser.setPassword(CryptoUtil.encode(user.getPassword()));

		// DBにオブジェクトを登録
		Users result = userRepository.registerUser(newUser);

		// レスポンスチェック
		if (ObjectUtil.isNullOrEmpty(result)) {
			List<String> codes = new ArrayList<>();
            codes.add(TaskManagerErrorCode.ERR999999.getCode());
            throw new TaskManagerErrorRuntimeException(new Errors(codes));
		}

		// 正常結果を返却
		return doPipeServiceOut()
				  .setNormalResult(result)
				  .build();
	}

	/**
	 * 同一メールアドレスのユーザがいれば取得して返却します。
	 * @param UserRegistRequestDto user
	 * @return List<Users>
	 */
	private List<Users> hasRegisteredEmail(UserRegistRequestDto user) {
		UsersExample usersExample = new UsersExample();
		usersExample.createCriteria().andEmailEqualTo(user.getEmail());
		return userRepository.selectByExample(usersExample);
	}

}
