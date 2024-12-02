import {CanActivateFn, Router} from '@angular/router';
import {AuthenticationService} from './login/authentication.service';
import {inject} from '@angular/core';
import {firstValueFrom} from 'rxjs';

export const authenticationGuard: CanActivateFn = async () => {
  const _authService = inject(AuthenticationService);
  const _router = inject(Router);
  return firstValueFrom(_authService.getUser())
    .then(user => {
      if (user) {
        return true; // L'utente esiste, consenti l'accesso
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
