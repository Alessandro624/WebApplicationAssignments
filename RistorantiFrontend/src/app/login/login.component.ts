import {Component, Inject, OnInit, PLATFORM_ID} from '@angular/core';
import {AuthenticationService} from './authentication.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {isPlatformBrowser} from '@angular/common';
import {environment} from '../../environments/environment';

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

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private _authenticationService: AuthenticationService, private _router: Router) {
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
    if (isPlatformBrowser(this.platformId)) {
      this.initGoogleButton();
      this.renderGoogleButton();
    }
  }

  initGoogleButton() {
    (globalThis as any).google.accounts.id.initialize({
      client_id: environment.googleClientId,
      callback: this.handleGoogleLogin.bind(this),
      ux_mode: "popup",
      auto_prompt: "false"
    });
  }

  renderGoogleButton() {
    (globalThis as any).google.accounts.id.renderButton(
      document.getElementById("google-signin-button"),
      {
        type: 'standard',
        theme: "outline",
        size: "large",
        text: "signin_with",
        shape: "pill",
        locale: "it",
        logo_alignment: "left",
        width: "200"
      }
    );
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
