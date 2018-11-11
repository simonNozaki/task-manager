package com.tm.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.LogCode;
import com.tm.dao.repository.UserRepository;
import com.tm.dto.Users;
import com.tm.dto.UsersExample;
import com.tm.dto.bean.user.UserRegistRequestDto;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.user.UserRegistService;
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
		if (!ObjectUtil.isNullOrEmpty(this.hasRegisteredEmail(user))) {
			return doPipeServiceOut()
					.setNormalResult(new Users())
					.setError(LogCode.TMURCM10015.getCode())
					.build();
		}

		// TODOこのへんでID発番、ID発番もクラスに切り出して作成

		// DBにオブジェクトを登録
		Users result = userRepository.registerUser(user);
		// レスポンスチェック
		if (result == null) {
		    return doPipeServiceOut()
                    .setNormalResult(new Users())
                    .setError(LogCode.TMURCM10015.getCode())
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
