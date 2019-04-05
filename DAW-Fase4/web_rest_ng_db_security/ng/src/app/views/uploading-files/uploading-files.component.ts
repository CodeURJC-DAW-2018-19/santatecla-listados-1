import { Component, OnInit } from '@angular/core';
import { Concept } from 'src/app/model/concept.model';
import { ConceptService } from 'src/app/service/concept-service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'mainpage',
  templateUrl: './uploading-files.component.html',
  styleUrls: ['./uploading-files.component.css']
})
export class UploadingFilesComponent implements OnInit {

  concept: Concept;
  private selectImage: File;
  
  constructor(public conceptService: ConceptService, 
    public activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(param =>{
      let id:number = +param.get('id');
      if(id){
        this.conceptService.getConcepts(id).subscribe(concept => {
          //this.concept = concept;
        });
      }
    })
  }

  uploadPicture(event){
    this.selectImage = event.target.files[0];
    console.log(this.selectImage);
  }

}
