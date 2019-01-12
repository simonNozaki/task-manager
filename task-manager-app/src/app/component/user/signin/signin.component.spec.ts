import { async, ComponentFixture, TestBed, getTestBed, inject, fakeAsync, tick } from '@angular/core/testing';

import { BrowserModule, By } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, DebugElement } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatInputModule, MatIconModule, MatFormFieldModule, MatMenuModule  } from '@angular/material';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MockBackend, MockConnection } from '@angular/http/testing';

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
import { Location, APP_BASE_HREF } from "@angular/common";
import { SpecInitializingStubRouter } from '../../../stub/spec-initializing-stub-router';
import { StubUsersSigninResponse } from '../../../stub/stub-users-signin-response';
import { StubUsersSigninRequest } from '../../../stub/stub-users-signin-request';
import { SigninServiceStub } from '../../../stub/user/signin-service-stub';
import { ConnectionBackend } from '@angular/http';
import { of } from 'rxjs/observable/of';

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
  let injector = getTestBed();
  let debugElement: DebugElement;
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
        declarations: [ SigninComponent, SignupComponent,TaskComponent,AppComponent ],
        imports : [ BrowserModule,HttpClientModule,WebModule,WebRoutingModule,FormsModule,ReactiveFormsModule,BrowserAnimationsModule,
            MatButtonModule,MatInputModule,MatIconModule,MatMenuModule,MatFormFieldModule,MatCheckboxModule,HttpClientTestingModule ],
        providers : [ TaskService,TaskLabelService,SignupService,SigninService,SignoutService,CommonDeliveryService,
            { provide : Router, useClass : SpecInitializingStubRouter },
            { provide: APP_BASE_HREF, useValue : '/' },
            { provide : SigninServiceStub, useClass : SigninServiceStub },
            { provide: ConnectionBackend, useClass: MockBackend },
        ],
        schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
    })
    .compileComponents().then(() => {
          fixture = TestBed.createComponent(SigninComponent);
          component = fixture.componentInstance;
          fixture.detectChanges();
          location = TestBed.get(Location);
          router = TestBed.get(Router);
          signinService = injector.get(SigninService);
          commondeliveryService = TestBed.get(CommonDeliveryService);
          httpMock = injector.get(HttpTestingController);
          debugElement = fixture.debugElement;
      });
  }));

  beforeEach(() => {
      let signinServiceSpy = jasmine.createSpyObj("SigninServiceSpy", ["signin"]);
      let signinSpy = signinServiceSpy.signin.and.returnValue(of(StubUsersSigninResponse[0]));
  });

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
      it("2-1", fakeAsync(() => {
          component.ngOnInit();
          // 入力フォームのHTML要素を取得します
          component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[0].email);
          component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[0].password);
          
          // ボタンを押して、クリックイベントを実行
          fixture.debugElement.query(By.css('button'))
              .triggerEventHandler('click', null);

          tick();
          // 更新を検知(これがないとバリデーションチェックでエラーになる)
          fixture.detectChanges();
    
          // アサーション
          expect(component.signin).toHaveBeenCalled();
          expect(component.signinForm.valid).toBeTruthy();
          expect(location.path()).toBe('/task');
      }));
  });

  describe("異常系 : ", ()=>{
      it("2-2, メールアドレス入力なし", fakeAsync(() => {
          component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[1].email);
          component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[0].password);

          expect(component.signinForm.valid).toBeFalsy();
      }));
      it("2-3, メールアドレス形式でない", fakeAsync(() => {
          component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[2].password);
          component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[2].password);

          expect(component.signinForm.valid).toBeFalsy();
      }));
      it("2-4, メールアドレス：規定の文字数を超えた文字列", fakeAsync(() => {
        component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[4].email);
        component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[4].password);

        expect(component.signinForm.valid).toBeFalsy();
      }));
      it("2-5, メールアドレス：半角英数字以外の文字を含む", fakeAsync(() => {
        component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[3].email);
        component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[3].password);

        expect(component.signinForm.valid).toBeFalsy();
      }));
      it("2-6, パスワード:入力なし", fakeAsync(() => {
        component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[1].email);
        component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[1].password);

        expect(component.signinForm.valid).toBeFalsy();
      }));
      it("2-7, パスワード：規定の文字数を超えた文字列", fakeAsync(() => {
        component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[1].email);
        component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[1].password);

        expect(component.signinForm.valid).toBeFalsy();
      }));
      it("2-8, パスワード：半角英数字以外の文字を含む", fakeAsync(() => {
        component.signinForm.controls['emailControl'].setValue(StubUsersSigninRequest[6].email);
        component.signinForm.controls['passwordControl'].setValue(StubUsersSigninRequest[6].password);

        expect(component.signinForm.valid).toBeFalsy();
      }));
  });

});
