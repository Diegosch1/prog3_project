package co.edu.uptc.the_project.model;

import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place {
    String id;
    String name;
    String location;

    public Place(String id, String name, String location){
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public static void isValidPlace(Place place) throws ProjectExeption{
        if (place == null||place.getId()==null||place.getName()==null||place.getLocation()==null) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }
}
