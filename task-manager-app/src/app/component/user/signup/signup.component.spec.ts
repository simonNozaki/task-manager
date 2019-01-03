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
  
        /**
        * 正常系、有効なメールアドレス、パスワードで利用者データが作成されること
        * 3-1
        */
        it('3-1', () => {
            component.ngOnInit();
            signupService.signup(StubUsersSignupRequest[0]).subscribe(res => {
                expect(res).toEqual(StubUsersSignupResponse[0]);
            });
            let req = httpMock.expectOne(API_URL);
            expect(req.request.method).toBe("POST");
            req.flush(StubUsersSignupResponse[0]);
        });

        /**
         * 異常系、 
         */
        it("3-2", () => {
            component.ngOnInit();
        });
         
    })
    
});
