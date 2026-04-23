import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {

  data = {
    username: '',
    password: ''
  };

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  login() {
    this.authService.login(this.data).subscribe({

      next: () => {

        this.authService.checkAuth().subscribe({
          next: (res) => {
            this.userService.setUser(res);

            this.toastr.success("Login successful");

            this.router.navigate(['/products']);
          }
        });

      },

      error: (err) => {
        this.toastr.error(
          err.error?.message || 'Login failed',
        );
      }

    });
  }
}