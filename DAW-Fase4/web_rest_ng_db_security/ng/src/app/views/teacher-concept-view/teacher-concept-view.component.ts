import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD:DAW-Fase4/web_rest_ng_db_security/ng/src/app/teacher-concept-view/teacher-concept-view.component.ts
import { ItemService} from "../service/item-service";
import { Item } from '../model/item.model';
=======
import {ItemService } from "../../"
import {LoginService} from "../auth/login.service";
import {PageItems} from "../model/page-items";
import { ActivatedRoute, Router } from '@angular/router';
import {ItemService} from "../../service/item-service";
import {Item} from "../../model/item.model";
>>>>>>> parent of 952f0ab... Revert "Merge branch 'master' of https://github.com/CodeURJC-DAW-2018-19/santatecla-listados-1":DAW-Fase4/web_rest_ng_db_security/ng/src/app/views/teacher-concept-view/teacher-concept-view.component.ts

@Component({
    selector: 'app-teacher-concept-view',
    templateUrl: './teacher-concept-view.component.html',
    styleUrls: ['./teacher-concept-view.component.css']
})
export class TeacherConceptViewComponent implements OnInit {

    private  items: Item[] = [];

    constructor(private itemService: ItemService) { }

    ngOnInit() {
    }



}
