import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TaskComponent } from './component/task/task.component';
import { HttpClientModule } from '@angular/common/http';
import { WebModule } from './routes/web/web.module';
import { TaskService } from './service/task.service';
import { UserComponent } from './component/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    WebModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ TaskService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
