import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';

const appRoutes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: LoginComponent},
]
export const routing = RouterModule.forRoot(appRoutes);
