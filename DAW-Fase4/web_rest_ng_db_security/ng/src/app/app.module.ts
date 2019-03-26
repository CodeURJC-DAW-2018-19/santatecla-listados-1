import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { JsonpModule, HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { MatIconRegistry } from '@angular/material/icon';

import {MatExpansionModule} from '@angular/material/expansion';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';

import {
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatInputModule,
    MatButtonToggleModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatTabsModule,
    MatSidenavModule,
    MatTooltipModule,
    MatRippleModule,
    MatRadioModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSliderModule,
    MatAutocompleteModule,
} from '@angular/material';

import {
    CovalentCommonModule,
    CovalentLayoutModule,
    CovalentMediaModule,
    CovalentExpansionPanelModule,
    CovalentStepsModule,
    CovalentLoadingModule,
    CovalentDialogsModule,
    CovalentSearchModule,
    CovalentPagingModule,
    CovalentNotificationsModule,
    CovalentMenuModule,
    CovalentDataTableModule,
    CovalentMessageModule,
} from '@covalent/core';

import { NgxChartsModule } from '@swimlane/ngx-charts';
import { DomSanitizer } from '@angular/platform-browser';
import { BookService } from './book.service';
import { BookListComponent } from './book-list.component';
import { BookFormComponent } from './book-form.component';

import {LoginComponent} from "./login.component";
import { routing } from './app.routing';
import { MainpageComponent } from './views/mainpage/mainpage.component';
import { TeacherConceptViewComponent } from './views/teacher-concept-view/teacher-concept-view.component';
import { StudentConceptViewComponent } from './views/student-concept-view/student-concept-view.component';
import { Lesson } from './model/lesson.model';
import { Concept } from './model/concept.model';
import { HeaderComponent } from './views/header/header.component';
import { FooterComponent } from './views/footer/footer.component';

import {UserService} from './service/user-service';
import {LessonService} from './service/lesson-service';
import {BookDetailComponent} from "./book-detail.component";
import {LoginService} from "./auth/login.service";
<<<<<<< HEAD
=======
import {ItemService} from "./service/item-service";
import {Item} from "./model/item.model";
import {ConceptService} from "./service/concept-service";
>>>>>>> parent of 952f0ab... Revert "Merge branch 'master' of https://github.com/CodeURJC-DAW-2018-19/santatecla-listados-1"

@NgModule({
    declarations: [AppComponent,
        BookDetailComponent,
        BookListComponent,
        BookFormComponent,
        LoginComponent,
        MainpageComponent,
        TeacherConceptViewComponent,
        StudentConceptViewComponent,
        HeaderComponent,
        FooterComponent],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        RouterModule.forRoot([]),
        HttpClientModule,
        JsonpModule,
        /** Material Modules */
        MatButtonModule,
        MatListModule,
        MatIconModule,
        MatCardModule,
        MatMenuModule,
        MatInputModule,
        MatSelectModule,
        MatButtonToggleModule,
        MatSlideToggleModule,
        MatProgressSpinnerModule,
        MatDialogModule,
        MatSnackBarModule,
        MatToolbarModule,
        MatTabsModule,
        MatSidenavModule,
        MatTooltipModule,
        MatRippleModule,
        MatRadioModule,
        MatGridListModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSliderModule,
        MatAutocompleteModule,
        MatExpansionModule,
        /** Covalent Modules */
        CovalentCommonModule,
        CovalentLayoutModule,
        CovalentMediaModule,
        CovalentExpansionPanelModule,
        CovalentStepsModule,
        CovalentDialogsModule,
        CovalentLoadingModule,
        CovalentSearchModule,
        CovalentPagingModule,
        CovalentNotificationsModule,
        CovalentMenuModule,
        CovalentDataTableModule,
        CovalentMessageModule,
        /** Additional **/
        NgxChartsModule,

        HttpModule, //Remove when migrated to HttpClient
        routing
    ],
    bootstrap: [AppComponent],
<<<<<<< HEAD
    providers: [BookService, LoginService, UserService, LessonService]
=======
    providers: [BookService, LoginService, UserService, LessonService, ItemService,ConceptService]
>>>>>>> parent of 952f0ab... Revert "Merge branch 'master' of https://github.com/CodeURJC-DAW-2018-19/santatecla-listados-1"
})
export class AppModule {
    constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {
        matIconRegistry.addSvgIconSet(domSanitizer.bypassSecurityTrustResourceUrl('/assets/symbol-defs.svg'));
    }
}
