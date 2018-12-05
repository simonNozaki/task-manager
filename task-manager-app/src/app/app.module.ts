import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TaskComponent } from './component/task/task.component';
import { HttpClientModule } from '@angular/common/http';
import { WebModule } from './routes/web/web.module';
import { TaskService } from './service/task.service';
import { UserComponent } from './component/user/user.component';
import { WebRoutingModule } from './routes/web/web-routing.module';
import { SignupComponent } from './component/user/signup/signup.component';
import { SignupService } from './service/signup.service';

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent,
    UserComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    WebModule,
    WebRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ TaskService, SignupService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
