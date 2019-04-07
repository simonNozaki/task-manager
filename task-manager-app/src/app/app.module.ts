import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatInputModule, MatIconModule, MatFormFieldModule, MatMenuModule  } from '@angular/material';
import { CookieService } from 'ngx-cookie-service';

import { AppComponent } from './app.component';
import { TaskComponent } from './component/task/task.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { WebModule } from './routes/web/web.module';
import { TaskService } from './service/task.service';
import { WebRoutingModule } from './routes/web/web-routing.module';
import { SignupComponent } from './component/user/signup/signup.component';
import { SignupService } from './service/signup.service';
import { CommonDeliveryService } from './service/common-delivery.service';
import { SigninComponent } from './component/user/signin/signin.component';
import { SigninService } from './service/signin.service';
import { TaskLabelService } from './service/task-label.service';
import { SignoutService } from './service/signout.service';
import { TasklabelComponent } from './component/tasklabel/tasklabel.component';
import { TasklineComponent } from './component/taskline/taskline.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent,
    SignupComponent,
    SigninComponent,
    TasklabelComponent,
    TasklineComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    WebModule,
    WebRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatMenuModule,
    MatFormFieldModule,
    MatCheckboxModule
  ],
  providers: [ 
    TaskService,
    TaskLabelService,
    SignupService,
    SigninService,
    SignoutService,
    CommonDeliveryService,
    CookieService
    // ,
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: XhrGlobalInterceptor, 
    //   multi: true
    // }
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
