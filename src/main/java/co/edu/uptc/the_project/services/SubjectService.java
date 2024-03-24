package co.edu.uptc.the_project.services;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import co.edu.uptc.the_project.model.Subject;
import lombok.Getter;

@Getter
public class SubjectService {
    private UptcList<Subject> subjectsList = new UptcList<>();

    public void addSubject(Subject subject) {
        subjectsList.add(subject);
    }

    public void deleteSubject(Subject subject) {
        subjectsList.remove(subject);
    }

    public void codeIsRepeated(String code) throws ProjectExeption{
        for (Subject subject : this.subjectsList) {
            if (subject.getCode().equals(code)) {
                throw new ProjectExeption(TypeMessage.NOT_SAVED);
            }
        }
    }

    public Subject getSubjectByCode(String code) throws ProjectExeption {
        for (Subject subject : this.subjectsList) {
            if (subject.getCode().equals(code)) {
                return subject;
            }
        }
        throw new ProjectExeption(TypeMessage.NOT_FOUND);
    }
}
