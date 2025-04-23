import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { StorageService } from './auth/storage/storage.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserComponentComponent } from './dashboard/user/user-component/user-component.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'MenuFrontend';

  isCustomerLogedIn: boolean = false;
  isAdminLogedIn: boolean = false;

  constructor(private router: Router) {}

  isLoginPage: boolean = false;

  ngOnInit() {
    this.updateLoginStatus();

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateLoginStatus();

        // update page check
        this.isLoginPage =
          this.router.url === '/login' || this.router.url === '/';
      }
    });
  }

  updateLoginStatus() {
    this.isAdminLogedIn = StorageService.isAdminLogedIn();
    this.isCustomerLogedIn = StorageService.isUserLogedIn();
  }

  logout() {
    StorageService.logout();
    this.router.navigateByUrl('/login');
  }
}
