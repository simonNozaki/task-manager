package com.tm.service.user.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.log.LogCode;
import com.tm.dao.repository.UserRepository;
import com.tm.dto.Users;
import com.tm.dto.UsersExample;
import com.tm.dto.bean.user.UserRegistRequestDto;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.user.UserRegistService;
import com.tm.util.IdCounter;
import com.tm.util.ObjectUtil;

/**
 * 利用者登録Serviceの実装クラス。
 */
@Service
public class UserRegistServiceImpl extends BaseService implements UserRegistService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ServiceOut<Users> execute(UserRegistRequestDto user) throws Exception{
		// 入力チェック。同一メールアドレスですでに登録がある場合はエラーを返却
		if (!ObjectUtil.isNullOrEmpty(hasRegisteredEmail(user))) {
			return doPipeServiceOut()
					.setNormalResult(new Users())
					.setError(LogCode.TMURCM10016.getCode())
					.build();
		}

		// DB登録用にBeanの詰替およびID発番
		Users newUser = new Users();
		BeanUtils.copyProperties(user, newUser);
		newUser.setUserId(IdCounter.assignIdForUser(8));

		// DBにオブジェクトを登録
		Users result = userRepository.registerUser(newUser);

		// レスポンスチェック
		if (result == null) {
		    return doPipeServiceOut()
                      .setNormalResult(new Users())
                      .setError(LogCode.TMURCM90001.getCode())
                      .build();
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
