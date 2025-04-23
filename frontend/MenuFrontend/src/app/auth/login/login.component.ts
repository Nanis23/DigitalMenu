import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';
import { StorageService } from '../storage/storage.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  encapsulation: ViewEncapsulation.None,
})
export class LoginComponent implements OnInit {
  msg: string = '';
  msgType: 'success' | 'danger' | 'info' | 'warning' = 'success';
  loginForm!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }

    //Vetem per testim
    console.log(this.loginForm.value);

    this.authService.login(this.loginForm.value).subscribe({
      next: (res) => {
        console.log(res);

        if (res.userId != null) {
          const user = {
            username: res.username || '', // use actual username if available
            role: res.userRole,
            uId: res.userId,
          };

          StorageService.saveUser(user);
          StorageService.saveToken(res.jwtToken);

          const role = StorageService.getUserRole();

          if (role === 'ADMIN') {
            this.msg = 'Login successful! Welcome, admin!';
            this.msgType = 'success';
            setTimeout(() => {
              this.router.navigate(['/user']);
            }, 1500);
          } else if (role === 'USER') {
            this.msg = 'Login successful! Welcome, user!';
            this.msgType = 'success';
            setTimeout(() => {
              this.router.navigate(['/user']);
            }, 1500);
          } else {
            this.msg = 'Invalid role. Please contact support.';
            this.msgType = 'danger';
          }
        }
      },
      error: (err) => {
        console.error(err);
        this.msg = 'Login failed. Please try again.';
        this.msgType = 'danger';
      },
    });
  }

  toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
  }
}
