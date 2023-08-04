import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username: string = 'username';
  password: string = 'password';
  loginError: boolean = false;

  constructor(private authService: AuthService,  private router: Router) { }

  login() {
    if (this.authService.login(this.username, this.password)) {
      this.router.navigate(['/home']);
    } else {
      this.loginError = true;
    }

    this.username = '';
    this.password = '';
  }
}
