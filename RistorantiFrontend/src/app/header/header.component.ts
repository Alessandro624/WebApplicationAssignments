import {Component, OnInit} from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthenticationService} from '../login/authentication.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  isAuthenticated!: boolean;

  constructor(private _authenticationService: AuthenticationService, private _router: Router) {
  }

  ngOnInit(): void {
    this._authenticationService.updateAuthentication();
    this._authenticationService.isAuthenticated$.subscribe(value => this.isAuthenticated = value);
  }

  logout() {
    this._authenticationService.onLogout().subscribe(
      data => {
        console.log(data);
        if (data == "Logout successful") {
          console.log("Logout successful");
          this._router.navigate(['/']);
        }
        this._authenticationService.updateAuthentication();
      },
      error => {
        console.log(error);
        this._authenticationService.updateAuthentication();
      }
    );
  }
}
