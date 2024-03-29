package co.edu.uptc.the_project.services;

import org.springframework.stereotype.Service;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import co.edu.uptc.the_project.model.Place;
import lombok.Getter;

@Getter
@Service
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
    public void placeExists(String id) throws ProjectExeption {
        boolean found = false;
        for (Place place: this.placesList) {
            if (place.getId().equals(id)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ProjectExeption(TypeMessage.NOT_FOUND);
        }
    }
}
