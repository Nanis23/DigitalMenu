import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddUserComponent } from '../addUser/add-user/add-user.component';

@Component({
  selector: 'app-user-component',
  imports: [HeaderComponent,SidebarComponent,FooterComponent,AddUserComponent,ReactiveFormsModule],
  templateUrl: './user-component.component.html',
  styleUrl: './user-component.component.css',
})
export class UserComponentComponent {}
