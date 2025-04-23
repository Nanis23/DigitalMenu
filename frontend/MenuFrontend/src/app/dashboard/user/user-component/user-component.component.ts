import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { FooterComponent } from '../../shared/footer/footer.component';
<<<<<<< HEAD
import { ReactiveFormsModule } from '@angular/forms';
import { AddUserComponent } from '../addUser/add-user/add-user.component';

@Component({
  selector: 'app-user-component',
  imports: [HeaderComponent,SidebarComponent,FooterComponent,AddUserComponent,ReactiveFormsModule],
=======
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-user-component',
  imports: [HeaderComponent, SidebarComponent, FooterComponent],
>>>>>>> 471f9b5636e57632ba780e45cc13f71678532a3f
  templateUrl: './user-component.component.html',
  styleUrl: './user-component.component.css',
})
export class UserComponentComponent {}
