package co.edu.uptc.the_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import co.edu.uptc.the_project.model.Group;
import co.edu.uptc.the_project.model.Place;
import co.edu.uptc.the_project.model.Schedule;
import co.edu.uptc.the_project.model.Subject;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Service
public class GroupService {
    private UptcList<Group> groupList = new UptcList<>();

    @Autowired
    SubjectService subjectService;
    @Autowired
    PlaceService placeService;

    UptcList<Subject> subjectsList;
    UptcList<Place> placesList;

    public GroupService(SubjectService subjectService, PlaceService placeService) {
        this.subjectService = subjectService;
        this.placeService = placeService;

        this.subjectsList = this.subjectService.getSubjectsList();
        this.placesList = this.placeService.getPlacesList();
    }

    @PostConstruct
    public void addtestObjects() {
        subjectService.addSubject(new Subject("matematicas", "math"));
        subjectService.addSubject(new Subject("naturales", "nat"));
        placeService.addPlace(new Place("A", "building a", "behind u"));
        placeService.addPlace(new Place("R", "building R", "behind them"));
        UptcList<String> schedules = new UptcList<>();
        schedules.add("monday, 2:00 - 4:00");
        groupList.add(new Group("01", "math", "A", schedules));
    }

    public void validateSubjectAndPlaceExists(String subjectCode, String placeId) throws ProjectExeption{
        subjectService.subjectExists(subjectCode);
        placeService.placeExists(placeId);
    }

    public void addGroup(Group group) throws ProjectExeption {
        validateSubjectAndPlaceExists(group.getSubjectCode(),group.getPlaceId());
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
        if (schedulesList == null) {

            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }
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

    public Group getGroupById(String id) throws ProjectExeption {
        for (Group group : this.groupList) {
            if(group.getGroupId().equals(id))
            return group;
        }
        throw new ProjectExeption(TypeMessage.NOT_FOUND);
    }

}
