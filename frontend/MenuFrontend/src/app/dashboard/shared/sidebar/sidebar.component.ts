import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { StorageService } from '../../../auth/storage/storage.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent implements OnInit {
  activeMenus: { [key: string]: boolean } = {};
  role: string = '';

  constructor() {}

  ngOnInit(): void {
    const user = StorageService.getUser();
    this.role = user?.role ?? '';
  }

  toggleSubMenu(menu: string) {
    this.activeMenus[menu] = !this.activeMenus[menu];
  }
}
