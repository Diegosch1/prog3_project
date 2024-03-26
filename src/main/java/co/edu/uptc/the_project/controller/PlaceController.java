package co.edu.uptc.the_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.model.Place;
import co.edu.uptc.the_project.services.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {
    PlaceService placeService = new PlaceService();

    @GetMapping()
    public UptcList<Place> getPlaces() {
        UptcList<Place> placesAux = placeService.getPlacesList();
        return placesAux;
    }

    @PostMapping("/addPlace")
    public ResponseEntity<Object> addPlace(@RequestBody Place place) {
        try {
            Place.isValidPlace(place);
            placeService.idIsRepeated(place.getId());
            placeService.addPlace(place);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PutMapping("/editPlace/{id}")
    public ResponseEntity<Object> editPlace(@PathVariable String id, @RequestBody Place updatedPlace) {
        try {
            Place.isValidPlace(updatedPlace);
            Place targetPlace = placeService.getPlaceById(id);

            targetPlace.setId(updatedPlace.getId());
            targetPlace.setName(updatedPlace.getName());

            return ResponseEntity.status(HttpStatus.OK).body(targetPlace);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @DeleteMapping("/deletePlace/{id}")
    public ResponseEntity<Object> deletePlace(@PathVariable String id) {
        try {
            Place targetPlace = placeService.getPlaceById(id);
            placeService.deletePlace(targetPlace);            
            return ResponseEntity.status(HttpStatus.OK).body(targetPlace);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
