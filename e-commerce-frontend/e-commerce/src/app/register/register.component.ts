import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  data = {
    name: '',
    phoneNo: '',
    username: '',
    password: ''
  };

  constructor(
    private authService: AuthService,
    private toastr: ToastrService
  ) {}

  register() {
    this.authService.register(this.data).subscribe({
      next: (res) => {
        this.toastr.success(res.message, 'Success');
      },
      error: (err) => {
        this.toastr.error(
          err.error?.message || 'Registration failed',
          'Error'
        );
      }
    });
  }
}