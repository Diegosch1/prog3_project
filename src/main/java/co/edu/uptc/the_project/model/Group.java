package co.edu.uptc.the_project.model;

import co.edu.uptc.UptcList.models.UptcList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Group {
    String groupId;
    String subjectCode;
    String placeId;
    UptcList<String> schedules;    
}
