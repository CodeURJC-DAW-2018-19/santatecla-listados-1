import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material";
import { single } from './data-student-diagram';

@Component({
    selector: 'app-student-concept-view',
    templateUrl: './student-concept-view.component.html',
    styleUrls: ['./student-concept-view.component.css']
})
export class StudentConceptViewComponent implements OnInit {

    single: any[];

    view: any[] = [700, 400];

    // options
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'Concepto';
    showYAxisLabel = true;

    colorScheme = {
        domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
    };

    @ViewChild('diagramDialog') diagramDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('questionDialog') questionDialog: TemplateRef<any>;
    questionRef: MatDialogRef<any, any>;



    constructor(public dialog: MatDialog) {
        Object.assign(this, { single })
    }

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
