import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent,
    SignupComponent,
    SigninComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    WebModule,
    WebRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ 
    TaskService,
    SignupService,
    CommonDeliveryService,
    SigninService
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
