import {Component, Inject, OnInit, PLATFORM_ID} from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthenticationService} from '../login/authentication.service';
import {UserRole} from '../models/user/user-role.enum';
import {isPlatformBrowser} from '@angular/common';

declare function getRandomPhoto(): string;

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
  user!: any;
  photos: string[] = [];

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private _authenticationService: AuthenticationService, private _router: Router) {
    this._authenticationService.currentUser$.subscribe({
      next: data => {
        console.log("User logged: " + data);
        this.user = data;
      }, error: error => console.log(error)
    });
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      for (let i = 0; i < 3; i++) {
        this.photos.push(getRandomPhoto());
      }
    }
  }

  logout() {
    this._authenticationService.onLogout().subscribe(
      () => {
        alert(`Logout successfully `);
        this._router.navigate(['/']).then(() => console.log("Logout successfully" + this.user));
      }
    );
  }

  protected readonly UserRole = UserRole;
}
