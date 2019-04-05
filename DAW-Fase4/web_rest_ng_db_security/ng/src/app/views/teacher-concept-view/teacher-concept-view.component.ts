import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ItemService} from "../../service/item-service"
import {LoginService} from "../../auth/login.service";
import {PageItems} from "../../model/page.item";
import {ActivatedRoute, Router} from '@angular/router';
import {Item} from "../../model/item.model";
import {AnswerService} from "../../service/answer-service";
import {Answer} from "../../model/answer.model";
import {MatDialog, MatDialogRef} from "@angular/material";
import {TdDialogService} from '@covalent/core';
import {LessonService} from "../../service/lesson-service";
import {ConceptService} from "../../service/concept-service";
import {MainpageComponent} from "../mainpage/mainpage.component";

@Component({
    selector: 'app-teacher-concept-view',
    templateUrl: './teacher-concept-view.component.html',
    styleUrls: ['./teacher-concept-view.component.css']
})
export class TeacherConceptViewComponent implements OnInit {

    public items: Item[] = [];
    public page: PageItems;
    public answers: Answer[] = [];
    public itemNew: Item;

    @ViewChild('addItemDialog') addItemDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
    dialogAlert: MatDialogRef<any, any>;

    constructor(private itemService: ItemService,
                public loginService: LoginService,
                public router: Router,
                public answerService: AnswerService,
                public activatedRoute: ActivatedRoute,
                public dialog: MatDialog,
                public alert: MatDialog,
                private _dialogService: TdDialogService) {
        this.itemNew = {info: "", state: false};
    }

    ngOnInit() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.itemService.getItems(id).subscribe(
            (res: any) => {
                this.page = res;
                this.items = this.page.content;
            },
            error => console.log(error)
        );

        this.answerService.getAnswersByConcept(id).subscribe(
            (res: any) => {
                console.log(res);
                this.answers = res;
            },
            error1 => console.log(error1)
        );
    }

    correctMan(id: number, info: boolean) {
        console.log(id);
        let i=0;
        this.answerService.correctManually(id, info).subscribe();
        this.answers.forEach((value,index) => {
            if (value.id == id) {
                i=index;
            }
        });
        this.answers.splice(i,1);

    }

    addItem() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.itemService.addItem(this.itemNew, id).subscribe();
        this.items.push(this.itemNew);
        this.itemNew = {info: "", state: false};
        this.dialog.closeAll();
    }

    deleteItem(id: number) {
        this._dialogService.openConfirm({
            message: '¿Estás seguro de que desea eliminarlo?',
            title: 'Confirmarción',
            width: '500px',
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.itemService.deleteItem(id).subscribe();
                let i=0;
                this.items.forEach((value,index) => {
                    if (value.idItem == id) {
                        i=index;
                    }
                });
                 this.items.splice(i,1);
            }
        });
    }

    openAddDialog() {
        this.dialogRef = this.dialog.open(this.addItemDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openAlertDialog() {
        this.dialogAlert = this.alert.open(this.alertDialog, {
            width: '50%',
            height: '50%',
        });
    }

    itemPagination(){
     /*   this.itemService.getItemsByPage(0, this.numberPag).subscribe(
            (res: any) => {
                this.pageItem = res;
                this.items = (this.pageItem.content);
                this.itemsInfo = [];
                this.items.forEach((value, index) => {
                        this.itemsInfo.push(value.info)
                        this.state = true;
                    }
                );
            },
            error => console.log(error)
        );*/
    }

    reloadItems(){
      /*  this.numberPag++;
        this.itemService.getItemsByPage(10, this.numberPag).subscribe(
            (res: any) => {
                this.pageItem = res;
                this.pageItem.content.forEach((value, index) =>
                    this.items.push(value));

                this.itemsInfo = [];
                this.items.forEach(value => this.itemsInfo.push(value.info));
                console.log(this.items);
            },
            error => console.log(error)
        );*/
    }

}

