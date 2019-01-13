import { async, ComponentFixture, TestBed, getTestBed,  } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatInputModule, MatIconModule, MatFormFieldModule, MatMenuModule  } from '@angular/material';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TaskComponent } from './task.component';
import { SignupService } from '../../service/signup.service';
import { CommonDeliveryService } from '../../service/common-delivery.service';
import { SignupComponent } from '../user/signup/signup.component';
import { SigninComponent } from '../user/signin/signin.component';
import { AppComponent } from '../../app.component';
import { HttpClientModule } from '@angular/common/http';
import { WebModule } from '../../routes/web/web.module';
import { WebRoutingModule } from '../../routes/web/web-routing.module';
import { TaskService } from '../../service/task.service';
import { TaskLabelService } from '../../service/task-label.service';
import { SigninService } from '../../service/signin.service';
import { SignoutService } from '../../service/signout.service';
import { Router } from '@angular/router';
import { SpecInitializingStubRouter } from '../../stub/spec-initializing-stub-router';

describe('TaskComponent', () => {
  let component: TaskComponent;
  let fixture: ComponentFixture<TaskComponent>;
  let httpMock: HttpTestingController;
  let signupService: SignupService;
  let commondeliveryService: CommonDeliveryService;
  let taskService: TaskService;
  let taskLabelService: TaskLabelService;
  let router: Router;
  let API_URL = "http://localhost:18080/api/v1/user/signup";

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
      fixture = TestBed.createComponent(TaskComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe("正常系 : ", () => {
    it("4-1, 有効なタスク情報", () => {

    });
  });

  describe("異常系 : ", () => {
    it("4-5, タイトル：入力なし", () => {

    });
  });

});
