import { Routes, RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';
import {MainpageComponent} from "./views/mainpage/mainpage.component";
import {TeacherConceptViewComponent} from "./views/teacher-concept-view/teacher-concept-view.component";
import {LessonAddFormComponent} from "./views/lesson-add-form/lesson-add-form.component";

const appRoutes = [
  { path: 'mainPage', component: MainpageComponent, useAsDefault: true },
  { path: 'lesson/new', component: LessonAddFormComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'mainPage', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
