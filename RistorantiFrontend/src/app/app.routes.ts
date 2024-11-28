import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {RistorantiHomeComponent} from './ristorante/ristoranti-home/ristoranti-home.component';
import {PiattiHomeComponent} from './piatto/piatti-home/piatti-home.component';
import {PiattoDetailsComponent} from './piatto/piatto-details/piatto-details.component';
import {AboutComponent} from './about/about.component';
import {LoginComponent} from './login/login.component';
import {RistoranteDetailsComponent} from './ristorante/ristorante-details/ristorante-details.component';
import {authenticationGuard} from './authentication.guard';

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'about', component: AboutComponent},
  {path: 'ristorante', component: RistorantiHomeComponent, canActivate: [authenticationGuard]},
  {path: 'piatto', component: PiattiHomeComponent, canActivate: [authenticationGuard]},
  {path: 'piatto/:nome', component: PiattoDetailsComponent, canActivate: [authenticationGuard]},
  {path: 'ristorante/:nome', component: RistoranteDetailsComponent, canActivate: [authenticationGuard]},
];
