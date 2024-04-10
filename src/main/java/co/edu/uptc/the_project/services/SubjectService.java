package co.edu.uptc.the_project.services;

import org.springframework.stereotype.Service;

import co.edu.uptc.UptcList.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import co.edu.uptc.the_project.model.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Service
public class SubjectService {
    private UptcList<Subject> subjectsList = new UptcList<>();

    public void addSubject(Subject subject) {
        subjectsList.add(subject);
    }

    public void deleteSubject(Subject subject) {
        subjectsList.remove(subject);
    }

    public Subject getSubjectByName(String Name){
        for (Subject subject : subjectsList) {
            if (subject.getName().equals(Name)) {
                return subject;
            }
        }
        return null;
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

    public void subjectExists(String code) throws ProjectExeption {
        boolean found = false;
        for (Subject subject : this.subjectsList) {
            if (subject.getCode().equals(code)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ProjectExeption(TypeMessage.NOT_FOUND);
        }
    }
}
