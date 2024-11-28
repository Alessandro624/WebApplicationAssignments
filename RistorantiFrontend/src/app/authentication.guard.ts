import {CanActivateFn, Router} from '@angular/router';
import {AuthenticationService} from './login/authentication.service';
import {inject} from '@angular/core';

export const authenticationGuard: CanActivateFn = async () => {
  const _authService = inject(AuthenticationService);
  const _router = inject(Router);
  const isAuthenticated = await _authService.isAuthenticated().toPromise();
  if (!isAuthenticated) {
    _router.navigate(['/login']).then(
      error => console.log(error)
    );
    return false;
  }
  return true;
};
