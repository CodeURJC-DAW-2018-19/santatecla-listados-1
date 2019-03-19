import { Injectable } from '@angular/core';
import { Concept } from "../model/concept.model";


@Injectable()
export class ConceptService {

  private concepts = [
    new Concept(21, "Angular CLI"),
    new Concept(22, "Angular Material"),
    new Concept(23, "AngularJS")
  ];

  getConcepts() {
    return this.concepts;
  }

  getConcept(id: number | string) {
    return this.concepts.find(concept => concept.id === +id);
  }
}
