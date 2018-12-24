import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

/**
 * 共通データ授受サービス。ルートコンポーネントと機能コンポーネント間のデータ受け渡しをサポートします。
 */
@Injectable()
export class CommonDeliveryService {

    constructor() { }

    /**
     * コンポーネント共通利用者ID
     */
    private userId: BehaviorSubject<string> = new BehaviorSubject<string>(null);

    /**
     * 利用者IDObservableオブジェクト
     */
    public observableUserId: Observable<string> = this.userId.asObservable();

    /**
     * 利用者IDの変更を随時反映します。
     */
    public emitUserIdChange(userId: string): void {
        this.userId.next(userId);
    }

    /**
     * 利用者IDをnullで初期化します。
     */
    public finalize(): void {
        this.userId.next(null);
    }

}
