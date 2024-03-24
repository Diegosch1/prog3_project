package co.edu.uptc.the_project.model;

import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subject {
    String name;
    String code;

    public Subject(String name, String code){
        this.name = name;
        this.code = code;
    }

    public static void isValidSubject(Subject subject) throws ProjectExeption{
        if (subject == null||subject.getCode()==null||subject.getName()==null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }
}
