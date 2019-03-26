import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material";

@Component({
    selector: 'app-student-concept-view',
    templateUrl: './student-concept-view.component.html',
    styleUrls: ['./student-concept-view.component.css']
})
export class StudentConceptViewComponent implements OnInit {
    @ViewChild('diagramDialog') diagramDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;
    @ViewChild('questionDialog') questionDialog: TemplateRef<any>;
    questionRef: MatDialogRef<any, any>;

    constructor(public dialog: MatDialog) { }

    ngOnInit() {
    }
    openDiagramDialog() {
        this.dialogRef = this.dialog.open(this.diagramDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openQuestionDialog() {
        this.dialogRef = this.dialog.open(this.questionDialog, {
            width: '50%',
            height: '50%',
        });
    }

}
