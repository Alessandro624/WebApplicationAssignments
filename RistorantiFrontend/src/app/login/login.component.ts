import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from './authentication.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  constructor(private _authenticationService: AuthenticationService, private _router: Router) {
  }

  onLogin() {
    this._authenticationService.onLogin(this.username, this.password).subscribe(
      () => {
        console.log("Logged user");
        this._router.navigate(['/']).then(error => console.log(error));
      },
      error => {
        console.log(error);
      }
    );
  }

  ngOnInit(): void {
    (globalThis as any).handleCredentialResponse = (response: any) => {
      this.handleGoogleLogin(response);
    };
  }

  handleGoogleLogin(response: any) {
    console.log('ID Token ricevuto: ', response.credential);
    // Decodifica l'ID Token (opzionale per visualizzare i dettagli lato client)
    const userInfo = this.decodeJwtResponse(response.credential);
    console.log('Informazioni utente: ', userInfo);
    // Invia il token al backend
    this.sendTokenToBackend(response.credential);
  }

  decodeJwtResponse(token: string) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
        .join('')
    );
    return JSON.parse(jsonPayload);
  }

  sendTokenToBackend(token: string) {
    console.log('ID Token ricevuto: ', token);
    this._authenticationService.onGoogleLogin(token).subscribe(
      () => {
        console.log('Google login completato.');
        this._router.navigate(['/']).then(() => console.log('Navigazione completata.'));
      },
      (error) => {
        console.error('Errore nel Google login:', error);
      }
    );
  }
}
