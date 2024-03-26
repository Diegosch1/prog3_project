package co.edu.uptc.the_project.services;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import co.edu.uptc.the_project.model.Group;
import co.edu.uptc.the_project.model.Place;
import co.edu.uptc.the_project.model.Schedule;
import co.edu.uptc.the_project.model.Subject;
import lombok.Getter;

@Getter
public class GroupService {
    private UptcList<Group> groupList = new UptcList<>();

    SubjectService subjectService = new SubjectService();
    PlaceService placeService = new PlaceService();

    private UptcList<Subject> subjectList = subjectService.getSubjectsList();
    private UptcList<Place> placeList = placeService.getPlacesList();

    public void addGroup(Group group) {
        groupList.add(group);
    }

    public void deleteGroup(Group group) {
        groupList.remove(group);
    }

    public void gruopIdIsUnique(String id) throws ProjectExeption {
        for (Group group : groupList) {
            if (group.getGroupId().equals(id)) {
                throw new ProjectExeption(TypeMessage.NOT_SAVED);
            }
        }
    }

    public void placeIsExclusive(String placeId) throws ProjectExeption {
        for (Group group : groupList) {
            if (group.getPlaceId().equals(placeId)) {
                throw new ProjectExeption(TypeMessage.NOT_SAVED);
            }
        }
    }

    public void schedulesAreUnique(UptcList<String> schedulesList) throws ProjectExeption {
        for (Group group : groupList) {
            UptcList<String> existingSchedules = group.getSchedules();
            if (existingSchedules.size() == schedulesList.size() && areListsEqual(existingSchedules, schedulesList)) {
                throw new ProjectExeption(TypeMessage.NOT_SAVED);
            }
        }
    }
    
    public void schedulesAreValid(UptcList<Schedule> schedulesList) throws ProjectExeption {
        for (Schedule schedule : schedulesList) {
            Schedule.isValidSchedule(schedule);
        }
    }
    // Método para comparar si dos listas son iguales (contienen los mismos objetos)
    private boolean areListsEqual(UptcList<String> list1, UptcList<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
    
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
    
        return true;
    }

    // public Group getGroup(String id) throws ProjectExeption {
    // for (Place place : this.placesList) {
    // if (place.getId().equals(id)) {
    // return place;
    // }
    // }
    // throw new ProjectExeption(TypeMessage.NOT_FOUND);
    // }
}
