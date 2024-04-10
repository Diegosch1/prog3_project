package co.edu.uptc.the_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.UptcList.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.model.Subject;
import co.edu.uptc.the_project.services.QueryService;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService qService;

    @GetMapping("/samePlace/{id}")
    public ResponseEntity<Object> getSamePlaceSubjects(@PathVariable String id) {
        try {
            UptcList<Subject> subjects = qService.getSamePlaceSubjectsList(id);
            return ResponseEntity.ok(subjects);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMenssage());
        }
    }

    @GetMapping("/overOneGroup")
    public ResponseEntity<Object> getOverOneGroupSubjects() {
        try {
            UptcList<Subject> subjects = qService.getOverOneGroupSubjects();
            return ResponseEntity.ok(subjects);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMenssage());
        }
    }

    @GetMapping("/sameSchedule/{schedule}")
    public ResponseEntity<Object> getSameScheduleSubjects(@PathVariable String schedule) {
        try {
            UptcList<Subject> subjects = qService.getSameSchedulesSubjectsList(schedule);
            return ResponseEntity.ok(subjects);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMenssage());
        }
    }

    @GetMapping("/coincidence/{coincidence}")
    public ResponseEntity<Object> getSimilarSubjects(@PathVariable String coincidence) {
        try {
            UptcList<Subject> subjects = qService.getSubjectsByCoincidence(coincidence);
            return ResponseEntity.ok(subjects);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMenssage());
        }
    }
}
