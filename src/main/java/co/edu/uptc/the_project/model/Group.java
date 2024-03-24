package co.edu.uptc.the_project.model;

import co.edu.uptc.ejercicio1.models.UptcList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Group {
    String code;
    String id;
    UptcList<String> schedules;
}
