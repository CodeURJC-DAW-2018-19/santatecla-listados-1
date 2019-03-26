import { Routes, RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';
import {MainpageComponent} from "./mainpage/mainpage.component";
import {TeacherConceptViewComponent} from "./teacher-concept-view/teacher-concept-view.component";

const appRoutes = [
  { path: 'mainPage', component: MainpageComponent, useAsDefault: true },
  { path: 'concept/:id', component: TeacherConceptViewComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'books', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
