package com.tm.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.log.LogCode;
import com.tm.dao.repository.UserRepository;
import com.tm.dto.Users;
import com.tm.dto.UsersExample;
import com.tm.dto.bean.user.UserAuthenticationRequestDto;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.user.UserAuthenticationService;
import com.tm.util.ObjectUtil;

/**
 * 利用者認証Service実装クラス。
 */
@Service
public class UserAuthenticationServiceImpl extends BaseService implements UserAuthenticationService {

    @Autowired
    UserRepository userRepository;

    /**
     * 利用者の認証を実施します。
     * @param UserAuthenticationRequestDto 利用者認証のリクエストデータ
     * @return UserAuthenticationResponseDto 利用者認証の結果データ
     */
    @Override
    public ServiceOut<Users> execute(UserAuthenticationRequestDto req) {

        // メールアドレス、パスワードで一致するユーザをDBから検索
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andEmailEqualTo(req.getEmail()).andPasswordEqualTo(req.getPassword());
        List<Users> target = userRepository.selectByExample(usersExample);

        // レスポンスチェック、ターゲット利用者不在
        if (ObjectUtil.isNullOrEmpty(target)) {
            return doPipeServiceOut()
                    .setNormalResult(new Users())
                    .setError(LogCode.TMURCM90001.getCode())  // TODO コード値あとで採番し直す
                    .build();
        }

        // レスポンスが1件異常、つまり複数該当してしまう場合は不能として業務エラーとする
        if (target.size() > 1) {
            return doPipeServiceOut()
                    .setNormalResult(new Users())
                    .setError(LogCode.TMURCM90001.getCode())  // TODO コード値あとで採番し直す
                    .build();
        }

        // 正常結果を返却
        return doPipeServiceOut()
                  .setNormalResult(target.get(0))
                  .build();

    }

}
