package co.edu.uptc.the_project.model;

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
}
