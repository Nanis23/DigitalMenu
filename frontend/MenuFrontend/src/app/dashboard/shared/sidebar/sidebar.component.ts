import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar',
  imports: [CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  activeMenus: { [key: string]: boolean } = {};

  toggleSubMenu(menu: string) {
    this.activeMenus[menu] = !this.activeMenus[menu];
  }

}
