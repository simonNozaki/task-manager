import { async, ComponentFixture, TestBed, getTestBed,  } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatInputModule, MatIconModule, MatFormFieldModule, MatMenuModule  } from '@angular/material';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { SignupComponent } from './signup.component';
import { SigninComponent } from '../signin/signin.component';
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
import { SpecInitializingStubRouter } from '../../../stub/spec-initializing-stub-router';
import { StubUsersSignupResponse } from '../../../stub/stub-users-signup-response';
import { StubUsersSignupRequest } from '../../../stub/stub-users-signup-request';

/**
 * 利用者登録Componentのテストスイート
 */
describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  let httpMock: HttpTestingController;
  let signupService: SignupService;
  let commondeliveryService: CommonDeliveryService;
  let router: Router;
  let API_URL = "http://localhost:18080/api/v1/user/signup";

    /**
     * 実行前処理、モジュールのインジェクション・フィクスチャの初期化
     */
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [ SignupComponent,SigninComponent,TaskComponent,AppComponent ],
        imports : [ BrowserModule,HttpClientModule,WebModule,WebRoutingModule,FormsModule,ReactiveFormsModule,BrowserAnimationsModule,
          MatButtonModule,MatInputModule,MatIconModule,MatMenuModule,MatFormFieldModule,MatCheckboxModule,HttpClientTestingModule ],
        providers : [ TaskService,TaskLabelService,SignupService,SigninService,SignoutService,CommonDeliveryService,
          { provide : Router,useClass : SpecInitializingStubRouter }
        ],
        schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
      })
      .compileComponents().then(() => {
          fixture = TestBed.createComponent(SignupComponent);
          component = fixture.componentInstance;
          fixture.detectChanges();
          let injector = getTestBed();
          signupService = injector.get(SignupService);
          commondeliveryService = TestBed.get(CommonDeliveryService);
          router = TestBed.get(Router);
          httpMock = injector.get(HttpTestingController);
      });
    }));

    /**
     * 実行後共通処理
     */
    afterEach(() => {
        httpMock.verify();
    });

    /**
     * 正常系テストスイート
     */
    describe('正常系 : ', () => {
        /**
         * 画面表示チェック
         */
        it('should create', () => {
          component.ngOnInit();
          expect(component).toBeTruthy();
        });
  
        it('3-1, 有効なメールアドレス、パスワード、利用者名', () => {
            component.ngOnInit();
            signupService.signup(StubUsersSignupRequest[0]).subscribe(res => {
                expect(res).toEqual(StubUsersSignupResponse[0]);
            });
            let req = httpMock.expectOne(API_URL);
            expect(req.request.method).toBe("POST");
            req.flush(StubUsersSignupResponse[0]);
        });
    })
    
    describe('異常系 : ', () => {
        
        it("3-2, メールアドレス：入力なし", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[0].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[1].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[0].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-3, メールアドレス：メールアドレス形式でない文字列", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[2].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[2].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[2].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-4, メールアドレス：規定の文字数を超えた文字列", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[3].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[3].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[3].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-5, メールアドレス：半角英数字以外の文字を含む", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[4].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[4].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[4].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-6, パスワード：入力なし", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[0].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[0].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[1].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-7, パスワード：規定の文字数を超えた文字列", () => {
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[5].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[5].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[5].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-8, パスワード：半角英数字以外の文字を含む", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[6].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[6].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[6].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-9, 名前：入力なし", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[1].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[0].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[0].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
        it("3-10, 名前：規定の文字数を超えた文字列", () => {
            component.signupForm.controls['userNameControl'].setValue(StubUsersSignupRequest[7].email);
            component.signupForm.controls['emailControl'].setValue(StubUsersSignupRequest[7].email);
            component.signupForm.controls['passwordControl'].setValue(StubUsersSignupRequest[7].password);
            
            expect(component.signupForm.valid).toBeFalsy();
        });
    });        
});
