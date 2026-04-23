import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './auth.service';
import { UserService } from './user.service';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  title = 'ShopKarts';

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService 
  ) {}


  get isUserLoggedIn(): boolean {
    return this.userService.isLoggedIn();
  }

  ngOnInit() {
    this.authService.checkAuth().subscribe({
      next: (res) => {
        this.userService.setUser(res);
      },
      error: () => {
        this.userService.clearUser();
      }
    });
  }

  logout() {
  this.authService.logout().subscribe({
    next: () => {

      this.userService.clearUser();

      this.toastr.success("Logged out successfully");

      this.router.navigate(['/login']);
    },
    error: () => {
      this.toastr.error("Logout failed");
    }
  });
}
  get username(): string | null {
  return this.userService.getUsername();
}
}