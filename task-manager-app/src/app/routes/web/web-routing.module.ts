import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceConst } from '../../const/service-const';
import { TaskComponent } from '../../component/task/task.component';
import { SignupComponent } from '../../component/user/signup/signup.component';
import { SigninComponent } from '../../component/user/signin/signin.component';

const routes: Routes = [ 
    { path: ServiceConst.URL_WEB_TASK, component: TaskComponent },
    { path: ServiceConst.URL_WEB_USER_SIGNUP, component: SignupComponent},
    { path: ServiceConst.URL_WEB_USER_SIGNIN, component: SigninComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes), RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class WebRoutingModule { }
