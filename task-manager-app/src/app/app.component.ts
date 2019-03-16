import { Component } from '@angular/core';
import { SignoutService } from './service/signout.service';
import { Router } from '@angular/router';
import { ServiceConst } from './const/service-const';

/**
 * アプリケーションルートコンポーネントクラス。
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

    /**
     * デフォルトコンストラクタ。
     */
    constructor(private signoutService: SignoutService, private router: Router) {}

    public isSignedin(): boolean {
        return this.signoutService.isSignedin();
    }

    /**
     * ログアウトを実施して、トップページにリダイレクトします。
     */
    public signout(): void {
        this.signoutService.signout();
        this.router.navigateByUrl(ServiceConst.BASE_SLASH);
    }
}
