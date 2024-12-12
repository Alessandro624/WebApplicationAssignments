import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, catchError, of, switchMap} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  /*private isAuthenticatedSubject$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isAuthenticated$ = this.isAuthenticatedSubject$.asObservable();*/
  currentUserSubject = new BehaviorSubject<any | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();
  // private myAPIUrl = "api/personal";
  private APIUrl = "api";

  constructor(private http: HttpClient) {
    this.getUser().subscribe();
  }

  /*
    updateAuthentication() {
      this.isAuthenticated().subscribe(
        data => {
          this.isAuthenticatedSubject$.next(data);
        },
        error => {
          console.log(error);
          this.isAuthenticatedSubject$.next(false);
        }
      );
    }
  */

  onLogin(username: string, password: string) {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);
    return this.http.post<void>(`${this.APIUrl}/login`, body.toString(), {
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      withCredentials: true, // Per inviare il cookie di sessione
    }).pipe(
      switchMap(() => this.getUser()) // Aggiorna l'utente dopo il login
    );
    /*const encodedUsername = btoa(username);
    const encodedPassword = btoa(password);
    return this.http.get<string>(this.myAPIUrl + `/login?username=${encodedUsername}&password=${encodedPassword}`, {withCredentials: true});*/
  }

  onGoogleLogin(token: string) {
    return this.http.post<void>(`${this.APIUrl}/open/google-login`, {token}, {withCredentials: true}).pipe(
      switchMap(() => {
        console.log('Google login successful, updating user state.');
        return this.getUser(); // Aggiorna lo stato dell'utente
      }),
      catchError((error) => {
        console.error('Google login failed:', error);
        return of(null);
      })
    );
  }

  onLogout() {
    return this.http.post<void>(`${this.APIUrl}/logout`, {}, {withCredentials: true}).pipe(switchMap(() => {
      this.currentUserSubject.next(null); // Reset utente
      return of(null);
    }));
    // return this.http.get<string>(this.myAPIUrl + '/logout', {withCredentials: true});
  }

  /*isAuthenticated() {
    return this.http.get<boolean>(this.myAPIUrl + '/isAuthenticated', {withCredentials: true});
  }*/

  /**
   * Recupera l'utente corrente. Se già presente, lo restituisce.
   * Altrimenti, esegue una chiamata HTTP per verificare l'autenticazione.
   * @returns Observable dell'utente corrente o null se non autenticato
   */
  getUser() {
    if (this.currentUserSubject.value) {
      // Se l'utente è già presente, restituisci il valore
      return of(this.currentUserSubject.value);
    }
    // Altrimenti, effettua una chiamata HTTP per recuperare l'utente
    return this.http.get<any>(this.APIUrl + `/auth/v1/check-user`, {
      withCredentials: true,
    }).pipe(
      switchMap((user) => {
        // Se l'utente è autenticato, aggiorna il BehaviorSubject
        console.log(user);
        this.currentUserSubject.next(user);
        return of(user);
      }),
      catchError(() => {
        // In caso di errore (es: 401 Unauthorized), restituisci null
        this.currentUserSubject.next(null);
        return of(null);
      })
    );
  }
}
