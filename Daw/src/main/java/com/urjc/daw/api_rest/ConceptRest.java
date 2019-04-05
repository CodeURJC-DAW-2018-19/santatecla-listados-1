package com.urjc.daw.api_rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptService;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.lessons.LessonService;
import com.urjc.daw.models.question.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("api/concept")
public class ConceptRest extends OperationsRest<Concept> {

    interface ConceptDetails extends Concept.BasicInfo,Concept.ItemList,Concept.QuestionList,
            Item.BasicInfo,Question.BasicInfo {}
            
    @Autowired
    ConceptService conceptService;
    @Autowired
    LessonService lessonService;

    private final Logger log = LoggerFactory.getLogger(ConceptRest.class);

    @GetMapping(value ="/{id}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> getConcept(@PathVariable long id){
        Optional<Concept> concept= conceptService.findByOneId(id);
        return checkIfExist(concept);
    }

    @GetMapping(value ="/lesson/{idLesson}")
    @JsonView(ConceptDetails.class)
    public Page<Concept> getConcepts(@PathVariable long idLesson,@PageableDefault(size = 10)Pageable page){
        return conceptService.findByLesson(lessonService.findOne(idLesson).get(),page);
    }

    @DeleteMapping(value ="/{id}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> deleteConcept(@PathVariable long id){
        return safeDelete(conceptService.findByOneId(id),conceptService.repository);
    }

    @PostMapping("/lesson/{idLesson}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> saveConcept(@RequestBody Concept concept, @PathVariable long idLesson){
        Optional<Lesson> optionalLesson = lessonService.findOne(idLesson);
        if(optionalLesson.isPresent()){
            Lesson lesson = optionalLesson.get();

            concept.setIdLesson(lesson);
            ResponseEntity<Concept> responseEntity = safeCreate(concept,conceptService.repository);

            lesson.addConcept(concept);

            lessonService.addLesson(lesson);
            return responseEntity;

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/upload")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<?> uploadsImage(@RequestParam long id, @RequestParam("file") MultipartFile file){
        Map<String, Object> response = new HashMap<>();
        Concept conceptNewFile = conceptService.findByOneId(id).get();
        if(!file.isEmpty()){
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get("upload").resolve(fileName).toAbsolutePath();
            log.info(filePath.toString());
            try {
                Files.copy(file.getInputStream(), filePath);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen: " + fileName);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String oldName = conceptNewFile.getPicture();
            if(oldName != null && oldName.length() > 0){
                Path oldFileLocation = Paths.get("upload").resolve(oldName).toAbsolutePath();
                File oldFile = oldFileLocation.toFile();
                if(oldFile.exists() && oldFile.canRead()){
                    oldFile.delete();
                }
            }

            conceptNewFile.setPicture(fileName);
            conceptService.addConcept(conceptNewFile);
            response.put("concept", conceptNewFile);
            response.put("mensaje", "Has subido correctamente la imagen: " + fileName);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/upload/img/{fileName:.+}")
    public ResponseEntity<Resource> seeFile(@PathVariable String fileName ){
        Path filePath = Paths.get("upload").resolve(fileName).toAbsolutePath();
        log.info(filePath.toString());  
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(!resource.exists() && !resource.isReadable()){
            throw new RuntimeException("Can load image" + fileName);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"");
        return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
    }
}
