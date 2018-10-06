import { TaskComponent } from '../component/logic/task.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';

import { ServiceConst } from '../const/service-const';

const routes: Routes = [
  { path: ServiceConst.URL_WEB_TASK, component: TaskComponent }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ],
  declarations: []
})

/**
 * URLルーティングモジュールです.
 */
export class AppRouterModule { }
