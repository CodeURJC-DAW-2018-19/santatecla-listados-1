import { Injectable } from '@angular/core';
import { Item } from "../model/item.model";


@Injectable()
export class ItemService {

  private items = [
    new Item (31, "Item1",21),
    new Item (32, "Item2",21),
    new Item (33, "Item3",21),
    new Item (34, "Item4",22)
  ];

  getItems() {
    return this.items;
  }

  getItem(id: number | string) {
    return this.items.find(item => item.id === +id);
  }

  getItemByConcept(idConcept:number | string){
    return this.items.find(item => item.idConcept === +idConcept);
  }
}
