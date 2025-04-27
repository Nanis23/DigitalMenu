import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../../auth/storage/storage.service';

@Component({
  selector: 'app-add-user',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css',
})
export class AddUserComponent {
  userForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      profilePic: [''],
    });
  }

  isDarkMode: boolean = false;

  toggleDarkMode() {
    this.isDarkMode = !this.isDarkMode;
  }

  onSubmit() {
    if (this.userForm.valid) {
      // Retrieve JWT token from StorageService
      const token = StorageService.getToken();

      if (!token) {
        alert('No JWT token found! Please log in again.');
        return;
      }

      // Set the Authorization header with the JWT token
      const headers = {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
      };

      this.http
        .post(
          'http://localhost:8080/api/user/registerUser',
          this.userForm.value,
          { headers }
        )
        .subscribe({
          next: (res) => {
            alert('User registered successfully!');
            this.userForm.reset();
          },
          error: (err) => {
            alert(err.error || 'Something went wrong!');
          },
        });
    } else {
      console.log('Form is invalid');
    }
  }
}
