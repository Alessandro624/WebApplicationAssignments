import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private isAuthenticatedSubject$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isAuthenticated$ = this.isAuthenticatedSubject$.asObservable();

  constructor(private http: HttpClient) {
  }

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

  onLogin(username: string, password: string) {
    const encodedUsername = btoa(username);
    const encodedPassword = btoa(password);
    return this.http.get<string>(`api/login?username=${encodedUsername}&password=${encodedPassword}`, {withCredentials: true});
  }

  onLogout() {
    return this.http.get<string>('api/logout', {withCredentials: true});
  }

  isAuthenticated() {
    return this.http.get<boolean>('api/isAuthenticated', {withCredentials: true});
  }
}
