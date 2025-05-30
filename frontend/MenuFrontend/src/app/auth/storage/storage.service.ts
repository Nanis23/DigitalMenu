import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor() {}

  ngOnInit() {
    if (typeof window !== 'undefined') {
      // Code accessing localStorage here
      const user = StorageService.getUser();
      // Other logic
    }
  }

  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string | null {
    if (typeof window !== 'undefined') {
      return localStorage.getItem(TOKEN);
    }
    return null;
  }

  static getUser(): any | null {
    const user = localStorage.getItem(USER);
    return user ? JSON.parse(user) : null;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) {
      return '';
    }

    return user.role;
  }

  static isAdminLogedIn() {
    if (this.getToken() == null) {
      return false;
    }

    const role: string = this.getUserRole();

    return role == 'ADMIN';
  }

  static isUserLogedIn() {
    if (this.getToken() == null) {
      return false;
    }

    const role: string = this.getUserRole();

    return role == 'USER';
  }

  static getUserId(): string {
    const user = this.getUser();

    if (user == null) {
      return '';
    }

    return user.uId;
  }

  static logout(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
