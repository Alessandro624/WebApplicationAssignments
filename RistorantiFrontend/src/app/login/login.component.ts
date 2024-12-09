import {Component} from '@angular/core';
import {AuthenticationService} from './authentication.service';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private _authenticationService: AuthenticationService, private _router: Router) {
  }

  username: string = '';
  password: string = '';

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
}
