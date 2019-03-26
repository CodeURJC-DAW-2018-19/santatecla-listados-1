import { Component, OnInit } from '@angular/core';
import { ItemService} from "../service/item-service";
import { Item } from '../model/item.model';

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
