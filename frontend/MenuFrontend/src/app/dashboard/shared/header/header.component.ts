import { Component, OnInit } from '@angular/core';
import { StorageService } from '../../../auth/storage/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  // username: string = '';
  // constructor(private router: Router) {}
  // ngOnInit(): void {
  //   const user = StorageService.getUser();
  //   this.username = user ? user.username : '';
  // }
  // logout(): void {
  //   StorageService.logout();
  //   this.router.navigateByUrl('/login');
  // }
}
