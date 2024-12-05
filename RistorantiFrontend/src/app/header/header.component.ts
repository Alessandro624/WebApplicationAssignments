import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {AuthenticationService} from '../login/authentication.service';

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
export class HeaderComponent implements OnInit, AfterViewInit {
  user!: any;
  photos: string[] = [];

  constructor(private _authenticationService: AuthenticationService, private _router: Router) {
  }

  ngOnInit(): void {
    this._authenticationService.currentUser$.subscribe({
      next: data => {
        console.log(data);
        this.user = data;
      }, error: error => console.log(error)
    })
  }

  ngAfterViewInit(): void {
    for (let i = 0; i < 3; i++) {
      this.photos.push(getRandomPhoto());
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
}
