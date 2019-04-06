import { Routes, RouterModule } from '@angular/router';
import {MainpageComponent} from "./views/mainpage/mainpage.component";
import {TeacherConceptViewComponent} from "./views/teacher-concept-view/teacher-concept-view.component";
import {StudentConceptViewComponent} from "./views/student-concept-view/student-concept-view.component";

const appRoutes = [
  { path: 'mainPage', component: MainpageComponent, useAsDefault: true },
  { path: '', redirectTo: 'mainPage', pathMatch: 'full' },
  { path: 'studentConceptView/:id', component: StudentConceptViewComponent},
  { path: 'teacherConceptView/:id' , component: TeacherConceptViewComponent}
];

export const routing = RouterModule.forRoot(appRoutes);
