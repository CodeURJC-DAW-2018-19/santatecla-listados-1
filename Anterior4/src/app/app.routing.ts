import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainpageComponent} from './mainpage/mainpage.component';

import { TeacherConceptViewComponent } from './teacher-concept-view/teacher-concept-view.component';
import {StudentConceptViewComponent} from './student-concept-view/student-concept-view.component';

const appRoutes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: LoginComponent},
  { path: 'main', component: MainpageComponent},
  { path: 'teacherConceptView', component: TeacherConceptViewComponent },
  { path: 'studentConceptView', component: StudentConceptViewComponent}
]

export const routing = RouterModule.forRoot(appRoutes);
