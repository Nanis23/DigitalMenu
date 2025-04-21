import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { AppComponent } from './app.component';
import { UserComponentComponent } from './dashboard/user/user-component/user-component.component';
import { HeaderComponent } from './dashboard/shared/header/header.component';

export const routes: Routes = [
  {
    path: '',
    component: AppComponent, // show login button here
  },
  // { path: 'login', component: LoginComponent },
  // {
  //   path: 'admin',
  //   loadChildren: () =>
  //     import('./dashboard/admin/admin.module').then((m) => m.AdminModule),
  // },
  { path: 'user', component: UserComponentComponent },
  { path: 'header', component: HeaderComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

  exports: [RouterModule],
})
export class AppRoutes {}
