import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

/**
 * 共通データ授受サービス。ルートコンポーネントと機能コンポーネント間のデータ受け渡しをサポートします。
 */
@Injectable()
export class CommonDeliveryService {

    constructor() { }

    /**
     * コンポーネント共通利用者ID
     */
    private userId = new Subject<string>();

    /**
     * 利用者IDObservableオブジェクト
     */
    public observableUserId: Observable<string> = this.userId.asObservable();

    /**
     * 利用者IDの変更を随時反映します
     */
    public emitUserIdChange(userId: string): void {
        this.userId.next(userId);
    }

}
