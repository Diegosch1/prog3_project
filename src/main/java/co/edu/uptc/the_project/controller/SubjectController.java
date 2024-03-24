package co.edu.uptc.the_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.model.Subject;
import co.edu.uptc.the_project.services.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    SubjectService subjectService = new SubjectService();

    @GetMapping()
    public UptcList<Subject> getSubjects() {
        UptcList<Subject> subjectsAux = subjectService.getSubjectsList();
        return subjectsAux;
    }

    @PostMapping("/addSubject")
    public ResponseEntity<Object> addSubject(@RequestBody Subject subject) {
        try {
            Subject.isValidSubject(subject);
            subjectService.codeIsRepeated(subject.getCode());
            subjectService.addSubject(subject);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PutMapping("/editSubject/{code}")
    public ResponseEntity<Object> editNormalPerson(@PathVariable String code, @RequestBody Subject updatedSubject) {
        try {
            Subject targetSubject = subjectService.getSubjectByCode(code);

            targetSubject.setName(updatedSubject.getName());
            targetSubject.setCode(updatedSubject.getCode());

            return ResponseEntity.status(HttpStatus.OK).body(targetSubject);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @DeleteMapping("/deleteSubject/{code}")
    public ResponseEntity<Object> deleteSubject(@PathVariable String code) {
        try {
            Subject targetSubject = subjectService.getSubjectByCode(code);
            subjectService.deleteSubject(targetSubject);            
            return ResponseEntity.status(HttpStatus.OK).body(targetSubject);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
