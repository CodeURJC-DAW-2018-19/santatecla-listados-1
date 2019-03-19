import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import { TeacherConceptViewComponent } from './teacher-concept-view/teacher-concept-view.component';

const appRoutes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: LoginComponent},
  { path: 'teacherConceptView', component: TeacherConceptViewComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
