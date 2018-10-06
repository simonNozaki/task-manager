import { AppRouterModule } from './app-router/app-router.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { TaskComponent } from './component/logic/task.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

/**
 * モジュール基底クラス
 */
export class AppModule { }
