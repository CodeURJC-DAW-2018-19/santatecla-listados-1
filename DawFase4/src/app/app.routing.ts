import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainpageComponent} from './mainpage/mainpage.component';

const appRoutes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: LoginComponent},
  { path: 'main', component: MainpageComponent},
]

export const routing = RouterModule.forRoot(appRoutes);
