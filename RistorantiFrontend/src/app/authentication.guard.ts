import {CanActivateFn, Router} from '@angular/router';
import {AuthenticationService} from './login/authentication.service';
import {inject} from '@angular/core';
import {firstValueFrom} from 'rxjs';
import {UserRole} from './models/user/user-role.enum';

export const authenticationGuard: CanActivateFn = async (route) => {
  const _authService = inject(AuthenticationService);
  const _router = inject(Router);
  const expectedRole: UserRole[] = route.data['requiredRoles']
  return firstValueFrom(_authService.getUser())
    .then(user => {
      if (user) {
        if (!expectedRole) {
          return true; // L'utente esiste e non è necessario un ruolo specifico, consenti l'accesso
        }
        // check role
        if (expectedRole == user.authorities[0].authority) {
          return true;
        }
        _router.navigate(['403']);
        return false;
      }
      // if not logged go to login page
      _router.navigate(["/login"]);
      // never used
      return false;
    })
    .catch(() => _router.navigate(["/login"])
    ); // Blocca l'accesso in caso di error
  /*
  const isAuthenticated = await _authService.isAuthenticated().toPromise();
  if (!isAuthenticated) {
    _router.navigate(['/login']).then(
      error => console.log(error)
    );
    return false;
  }
  return true;
  */
};
