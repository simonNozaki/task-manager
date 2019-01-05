package com.tm.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.dao.repository.UserRepository;
import com.tm.dto.Users;
import com.tm.dto.UsersExample;
import com.tm.dto.bean.user.UserAuthenticationRequestDto;
import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.framework.BaseService;
import com.tm.service.user.UserAuthenticationService;
import com.tm.util.CryptoUtil;
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
        usersExample.createCriteria().andEmailEqualTo(req.getEmail());
        List<Users> target = userRepository.selectByExample(usersExample);

        // ハッシュにしたパスワードと平文のパスワードの照合を実施します
        List<Users> authenticatedUser = ObjectUtil.getStream(target)
                .filter((Users candidate) -> {
                    return CryptoUtil.match(req.getPassword(), candidate.getPassword());
                })
                .collect(Collectors.toList());

        // レスポンスチェック、ターゲット利用者不在
        if (ObjectUtil.isNullOrEmpty(authenticatedUser)) {
        	List<String> codes = new ArrayList<>();
            codes.add(TaskManagerErrorCode.ERR160001.getCode());
            throw new TaskManagerErrorRuntimeException(new Errors(codes));
        }

        // 正常結果を返却
        return doPipeServiceOut()
                  .setNormalResult(target.get(0))
                  .build();

    }

}
