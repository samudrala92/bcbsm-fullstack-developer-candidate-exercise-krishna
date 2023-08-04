import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  login(username: string, password: string): boolean {
    // In a real-world application, you'd make an HTTP request to your backend here.
    // For this example, we'll just accept any non-empty strings as valid credentials.

    if (username && password) {
      localStorage.setItem('username', username);
      return true;
    } else {
      return false;
    }
  }

  logout(): void {
    // remove user from local storage to log user out
    localStorage.removeItem('username');
  }

  public get loggedIn(): boolean {
    return (localStorage.getItem('username') !== null);
  }
}
