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
import { Concept } from 'src/app/model/concept.model';
import { HttpEventType } from '@angular/common/http';

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
    concept: Concept;
    private selectedFile: File;

    @ViewChild('addItemDialog') addItemDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('alertDialog') alertDialog: TemplateRef<any>;
    dialogAlert: MatDialogRef<any, any>;
    progres: number = 0;
    

    constructor(private itemService: ItemService,
                public loginService: LoginService,
                public router: Router,
                public conceptService: ConceptService,
                public answerService: AnswerService,
                public activatedRoute: ActivatedRoute,
                public dialog: MatDialog,
                public alert: MatDialog,
                private _dialogService: TdDialogService) {
        this.itemNew = {info: "", state: false};
    }

    ngOnInit() {
        if(this.loginService.getRolUserLoged()!=2){
            this.router.navigate(['mainPage']);
        }else {
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

            this.conceptService.getConcepts(id).subscribe(
                (res: any) => {
                    this.concept = res;
                },
                error2 => console.log(error2)
            )

            this.activatedRoute.paramMap.subscribe(
                (params: any) => {
                    let id: number = +params.get('id');
                    if (id) {
                        this.conceptService.getConcepts(id).subscribe(
                            (concept: any) => {
                                this.concept = concept;
                            });
                    }
                });
        }

    }

    correctMan(id: number, info: boolean) {
        console.log(id);
        let i=0;
        this.answerService.correctManually(id, info).subscribe();
        this.answers.forEach((value,index) => {
            if (value.idAnswer == id) {
                i=index;
                this.answers.splice(i,1);
                this.answers.push();
            }
        });

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



    selectPicture(event){
        this.selectedFile = event.target.files[0];
        this.progres = 0;
        console.log(this.selectedFile);
        if(this.selectedFile.type.indexOf('image') < 0){
            this.selectedFile = null;
        }
    }

    uploadPicture(){
        if(!this.selectedFile){
            console.log;
        }else{
            this.conceptService.uploadFile(this.selectedFile, this.concept.idConcept)
                .subscribe(event =>{
                if (event.type === HttpEventType.UploadProgress) {
                    this.progres = Math.round((event.loaded / event.total) * 100);
              } else if (event.type === HttpEventType.Response) {
                    let response: any = event.body;
                    this.concept = response.concept as Concept;
                  }
            });
        }
    }
}

