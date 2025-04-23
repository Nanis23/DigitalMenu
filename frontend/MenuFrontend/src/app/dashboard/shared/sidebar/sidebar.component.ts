import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent {
  activeMenus: { [key: string]: boolean } = {};

  toggleSubMenu(menu: string) {
    this.activeMenus[menu] = !this.activeMenus[menu];
  }
}
