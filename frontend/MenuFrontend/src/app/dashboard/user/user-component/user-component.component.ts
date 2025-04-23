import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddUserComponent } from '../add-user/add-user.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-component',
  imports: [
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    ReactiveFormsModule,
    RouterModule,
  ],
  templateUrl: './user-component.component.html',
  styleUrl: './user-component.component.css',
})
export class UserComponentComponent {}
