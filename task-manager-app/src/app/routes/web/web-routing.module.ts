import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceConst } from '../../const/service-const';
import { TaskComponent } from '../../component/task/task.component';

const routes: Routes = [ 
  { path: ServiceConst.URL_WEB_TASK, component: TaskComponent } 
];

@NgModule({
  imports: [RouterModule.forChild(routes), RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class WebRoutingModule { }
