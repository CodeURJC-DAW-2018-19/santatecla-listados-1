import { Component, OnInit } from '@angular/core';
import { ItemService} from "../service/item-service";
import { Item } from '../model/item.model';
import {LoginService} from "../auth/login.service";
import {PageItems} from "../model/page-items";
import { ActivatedRoute, Router } from '@angular/router';


@Component({
    selector: 'app-teacher-concept-view',
    templateUrl: './teacher-concept-view.component.html',
    styleUrls: ['./teacher-concept-view.component.css']
})
export class TeacherConceptViewComponent implements OnInit {

    private items: Item[] = [];
    private page: PageItems;

    constructor(private itemService: ItemService,
                public loginService: LoginService,
                private router: Router,
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

    }




}
