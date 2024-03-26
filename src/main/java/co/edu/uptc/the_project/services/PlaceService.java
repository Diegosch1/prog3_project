package co.edu.uptc.the_project.services;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import co.edu.uptc.the_project.model.Place;
import lombok.Getter;

@Getter
public class PlaceService {
    private UptcList<Place> placesList = new UptcList<>();

    public void addPlace(Place place) {
        placesList.add(place);
    }

    public void deletePlace(Place place) {
        placesList.remove(place);
    }

    public void idIsRepeated(String id) throws ProjectExeption{
        for (Place place : this.placesList) {
            if (place.getId().equals(id)) {
                throw new ProjectExeption(TypeMessage.NOT_SAVED);
            }
        }
    }

    public Place getPlaceById(String id) throws ProjectExeption {
        for (Place place : this.placesList) {
            if (place.getId().equals(id)) {
                return place;
            }
        }
        throw new ProjectExeption(TypeMessage.NOT_FOUND);
    }    
}
