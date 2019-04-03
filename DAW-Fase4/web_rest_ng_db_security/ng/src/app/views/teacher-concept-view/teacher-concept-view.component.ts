import { Component, OnInit } from '@angular/core';
import {ItemService } from "../../service/item-service"
import {LoginService} from "../../auth/login.service";
import {PageItems} from "../../model/page.item";
import { ActivatedRoute, Router } from '@angular/router';
import {Item} from "../../model/item.model";
import {AnswerService} from "../../service/answer-service";
import {Answer} from "../../model/answer.model";
import {Question} from "../../model/question.model";

@Component({
    selector: 'app-teacher-concept-view',
    templateUrl: './teacher-concept-view.component.html',
    styleUrls: ['./teacher-concept-view.component.css']
})
export class TeacherConceptViewComponent implements OnInit {

    public items: Item[] = [];
    public page: PageItems;
    public answers: Answer[] = [];

    constructor(private itemService: ItemService,
                public loginService: LoginService,
                public router: Router,
                public answerService: AnswerService,
                public activatedRoute: ActivatedRoute,) {
    }
    ngOnInit() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.itemService.getItems(id).subscribe(
            (res : any)=>{
                this.page=res;
                this.items=this.page.content;
            },
            error => console.log(error)
        );

        this.answerService.getAnswersByConcept(id).subscribe(
            (res : any) =>{
                console.log(res);
                this.answers=res;
            },
            error1 => console.log(error1)
        );
    }

    correctMan(id: number, info:boolean){
        console.log(id);
       this.answerService.correctManually(id,info).subscribe();
    }



}
