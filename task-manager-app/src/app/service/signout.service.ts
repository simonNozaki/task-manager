import { Injectable } from '@angular/core';
import { CommonDeliveryService } from './common-delivery.service';
import { ObjectUtil } from '../util/object.util';

/**
 * 利用者サインアウトサービスクラス。
 */
@Injectable()
export class SignoutService {

    constructor(private commonDeliveryService: CommonDeliveryService) { }

    /**
     * サインイン状態フラグ
     */
    private signedinFlag: boolean;

    /**
     * サインアウトを実施します。
     */
    public signout(): void {
        if(this.isSignedin) {
            this.commonDeliveryService.initialize();
        };
    };

    /**
     * 認証状態をチェックします。
     * @returns signedinFlag: boolean 認証フラグの状態
     */
    private isSignedin(): boolean {
        this.commonDeliveryService.observableUserId.subscribe((userId: string) => {
            if (ObjectUtil.isNullOrUndefined(userId)) {
                this.signedinFlag = false;
            } else {
                this.signedinFlag = true;
            }
        });
        return this.signedinFlag;
    };

}
