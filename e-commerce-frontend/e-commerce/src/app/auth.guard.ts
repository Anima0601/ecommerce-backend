import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { UserService } from './user.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const userService = inject(UserService);
  console.log('Guard triggered for:', state.url);

  
    return authService.checkAuth().pipe(
      map((res) => {
      userService.setUser(res); 
      return true;
    }),
    catchError((err) => {
      console.log('AUTH FAILED:', err);
      router.navigate(['/login']);
      return of(false);
    })
  );
};