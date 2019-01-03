import { async, ComponentFixture, TestBed, getTestBed } from '@angular/core/testing';

import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatInputModule, MatIconModule, MatFormFieldModule, MatMenuModule  } from '@angular/material';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { SigninComponent } from './signin.component';
import { SignupComponent } from '../signup/signup.component';
import { AppComponent } from '../../../app.component';
import { HttpClientModule } from '@angular/common/http';
import { WebModule } from '../../../routes/web/web.module';
import { WebRoutingModule } from '../../../routes/web/web-routing.module';
import { TaskComponent } from '../../task/task.component';
import { TaskService } from '../../../service/task.service';
import { TaskLabelService } from '../../../service/task-label.service';
import { SignupService } from '../../../service/signup.service';
import { SigninService } from '../../../service/signin.service';
import { SignoutService } from '../../../service/signout.service';
import { CommonDeliveryService } from '../../../service/common-delivery.service';
import { Router } from '@angular/router';
import { Location } from "@angular/common";
import { SpecInitializingStubRouter } from '../../../stub/spec-initializing-stub-router';
import { StubUsersSigninResponse } from '../../../stub/stub-users-signin-response';
import { StubUsersSigninRequest } from '../../../stub/stub-users-signin-request';

/**
 * 利用者認証コンポーネントのテストスイート
 */
describe('SigninComponent', () => {
  let component: SigninComponent;
  let fixture: ComponentFixture<SigninComponent>;
  let httpMock: HttpTestingController;
  let signinService: SigninService;
  let commondeliveryService: CommonDeliveryService;
  let router: Router;
  let location: Location;
  let API_URL = "http://localhost:18080/api/v1/user/signin";
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
        declarations: [ SigninComponent, SignupComponent,TaskComponent,AppComponent ],
        imports : [ BrowserModule,HttpClientModule,WebModule,WebRoutingModule,FormsModule,ReactiveFormsModule,BrowserAnimationsModule,
            MatButtonModule,MatInputModule,MatIconModule,MatMenuModule,MatFormFieldModule,MatCheckboxModule,HttpClientTestingModule ],
        providers : [ TaskService,TaskLabelService,SignupService,SigninService,SignoutService,CommonDeliveryService,
            { provide : Router,useClass : SpecInitializingStubRouter }
        ],
        schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents().then(() => {
          let injector = getTestBed();
          fixture = TestBed.createComponent(SigninComponent);
          component = fixture.componentInstance;
          fixture.detectChanges();
          location = TestBed.get(Location);
          router = TestBed.get(Router);
          // ルーティングスペックのデフォルトページを初期化します
          router.initialNavigation();
          signinService = injector.get(SigninService);
          commondeliveryService = TestBed.get(CommonDeliveryService);
          httpMock = injector.get(HttpTestingController);
      });
  }));

  afterEach(() => {
      httpMock.verify();
  });

  /**
   * 正常系スペック
   */
  describe('正常系 : ', () => {
      it('should create', () => {
          component.ngOnInit();
          expect(component).toBeTruthy();
      });

      /**
       * 有効なメールアドレスとパスワードで認証できることを確認する
       * 2-1
       */
      it("2-1", () => {
          component.ngOnInit();
          // 入力フォームのHTML要素を取得します
          component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[0].email);
          component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[0].password);
          // バリデーションを通せることを確認します
          expect(component.signinForm.valid).toBeTruthy();

          // DTOにデータをバインド、清浄結果を取得できることを確認します
          signinService.signin(StubUsersSigninRequest[0]).subscribe(res => {
              expect(res).toEqual(StubUsersSigninResponse[0]);
          });

          // 認証の実行
          component.signin();

          // タスク一覧画面に遷移することを確認します。
          expect(location.path()).toBe('/task');

      });

  });

});
