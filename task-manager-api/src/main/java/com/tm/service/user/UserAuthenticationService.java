package com.tm.service.user;

import org.springframework.stereotype.Service;

import com.tm.dto.Users;
import com.tm.dto.bean.user.UserAuthenticationRequestDto;
import com.tm.dto.common.ServiceOut;

/**
 * 利用者認証Serviceクラス。
 */
@Service
public interface UserAuthenticationService {

    /**
     * 利用者の認証を実施します。
     * @param UserAuthenticationRequestDto 利用者認証のリクエストデータ
     * @return UserAuthenticationResponseDto 利用者認証の結果データ
     */
    public ServiceOut<Users> execute(UserAuthenticationRequestDto req);

}
