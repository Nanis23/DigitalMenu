import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { AppComponent } from './app.component';
import { UserComponentComponent } from './dashboard/user/user-component/user-component.component';
import { HeaderComponent } from './dashboard/shared/header/header.component';
import { SidebarComponent } from './dashboard/shared/sidebar/sidebar.component';
import { FooterComponent } from './dashboard/shared/footer/footer.component';
import { AddUserComponent } from './dashboard/user/add-user/add-user.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

export const routes: Routes = [
  {
    path: '',
    component: AppComponent, // show login button here
  },
  { path: 'login', component: LoginComponent },
  {
    path: 'admin',
    loadChildren: () =>
      import('./dashboard/admin/admin.module').then((m) => m.AdminModule),
  },
  {
    path: 'user',
    component: UserComponentComponent,
    children: [{ path: 'add-user', component: AddUserComponent }],
  },
  { path: 'header', component: HeaderComponent },
  { path: 'sidebar', component: SidebarComponent },
  { path: 'footer', component: FooterComponent },
  { path: 'add-user', component: AddUserComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    ReactiveFormsModule,
    HttpClientModule,
  ],

  exports: [RouterModule],
})
export class AppRoutes {}
