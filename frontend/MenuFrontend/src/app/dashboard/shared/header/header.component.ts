import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';


import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
 

  dropdownOpen = false;  // Variabli për të menaxhuar hapjen e dropdown-it

  // Funksioni për të hapur dhe mbyllur dropdown-in
  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  // Funksioni për shfaqjen e profilit
  viewProfile(event: MouseEvent) {
    event.stopPropagation(); // Ndalon ngjarjen të shkojë më tutje
    console.log('Shiko Profilin');
    this.dropdownOpen = false;  // Mbyll dropdown-in pas klikimit
  }

  // Funksioni për logout
  logout(event: MouseEvent) {
    event.stopPropagation(); // Ndalon ngjarjen të shkojë më tutje
    console.log('Logout clicked');
    this.dropdownOpen = false;  // Mbyll dropdown-in pas klikimit
  }
}
