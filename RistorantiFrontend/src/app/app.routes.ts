import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {RistorantiHomeComponent} from './ristorante/ristoranti-home/ristoranti-home.component';
import {PiattiHomeComponent} from './piatto/piatti-home/piatti-home.component';
import {PiattoDetailsComponent} from './piatto/piatto-details/piatto-details.component';
import {AboutComponent} from './about/about.component';
import {LoginComponent} from './login/login.component';

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'ristorante', component: RistorantiHomeComponent},
  {path: 'piatto', component: PiattiHomeComponent},
  {path: 'about', component: AboutComponent},
  {path: 'login', component: LoginComponent},
  {path: 'piatto/:nome', component: PiattoDetailsComponent},
];
