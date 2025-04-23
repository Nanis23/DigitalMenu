import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-add-user',
  imports: [HeaderComponent,SidebarComponent,FooterComponent, ReactiveFormsModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  userForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      profilePic: ['']
    });
  }

  onSubmit() {
    if (this.userForm.valid) {
      this.http.post('http://localhost:8080/api/user/registerUser', this.userForm.value).subscribe({
        next: res => {
          alert('User registered successfully!');
          this.userForm.reset();
        },
        error: err => {
          alert(err.error || 'Something went wrong!');
        }
      });
    }
  }
}
