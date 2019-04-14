import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServiceConst } from '../../const/service-const';
import { TaskComponent } from '../../component/task/task.component';
import { SignupComponent } from '../../component/user/signup/signup.component';
import { SigninComponent } from '../../component/user/signin/signin.component';
import { CommonModule } from '@angular/common';
import { TasklineComponent } from '../../component/taskline/taskline.component';
import { IntroductionComponent } from '../../introduction/introduction/introduction.component';

const routes: Routes = [ 
    { path: ServiceConst.URL_WEB_MAIN, component: IntroductionComponent },
    { path: ServiceConst.URL_WEB_TASK, component: TaskComponent },
    { path: ServiceConst.URL_WEB_USER_SIGNUP, component: SignupComponent},
    { path: ServiceConst.URL_WEB_USER_SIGNIN, component: SigninComponent},
    { path: ServiceConst.URL_WEB_TIMELINE, component: TasklineComponent }
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes), RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class WebRoutingModule { }
