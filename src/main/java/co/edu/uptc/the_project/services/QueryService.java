package co.edu.uptc.the_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uptc.UptcList.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.model.Group;
import co.edu.uptc.the_project.model.Place;
import co.edu.uptc.the_project.model.Subject;
import lombok.Getter;

@Service
@Getter
public class QueryService {

    @Autowired
    SubjectService subjectService;
    @Autowired
    PlaceService placeService;
    @Autowired
    GroupService groupService;

    UptcList<Subject> subjectsList;
    UptcList<Place> placesList;
    UptcList<Group> groupsList;

    public QueryService(SubjectService subjectService, PlaceService placeService, GroupService groupService) {
        this.subjectService = subjectService;
        this.placeService = placeService;
        this.groupService = groupService;

        this.subjectsList = this.subjectService.getSubjectsList();
        this.placesList = this.placeService.getPlacesList();
        this.groupsList = this.groupService.getGroupList();
    }

    public UptcList<Subject> getSamePlaceSubjectsList(String placeId) throws ProjectExeption {
        UptcList<Subject> result = new UptcList<>();
        for (Group group : groupsList) {
            if (group.getPlaceId().equals(placeId)) {
                Subject subject = subjectService.getSubjectByCode(group.getSubjectCode());
                if (!result.contains(subject)) {
                    result.add(subject);
                }
            }
        }
        return result;
    }

    public UptcList<Subject> getOverOneGroupSubjects() throws ProjectExeption {
        UptcList<String> subjectCodes = new UptcList<>();
        for (Group group : groupsList) {
            subjectCodes.add(group.getSubjectCode());
        }
        return countGroups(subjectCodes);
    }

    // method that counts the occurrences of groups within a subject
    public UptcList<Subject> countGroups(UptcList<String> subjectCodes) throws ProjectExeption {
        UptcList<Subject> result = new UptcList<>();
        // iterate over each subjectCode
        for (String subjectCode : subjectCodes) {
            // occurrences counter
            int amount = 0;
            // compare subjectCode with each subjectCode on the list
            for (int i = 0; i < subjectCodes.size(); i++) {
                if (subjectCode.equals(subjectCodes.get(i))) {
                    amount++;
                }
            }
            if (amount > 1) {
                Subject subject = subjectService.getSubjectByCode(subjectCode);
                // if subject is already in the list, dont add it again
                if (!result.contains(subject)) {
                    result.add(subject);
                }
            }
        }
        return result;
    }

    public UptcList<Subject> getSameSchedulesSubjectsList(String schedule) throws ProjectExeption {
        UptcList<Subject> result = new UptcList<>();
        for (Group group : groupsList) {
            for (String scheduleString : group.getSchedules()) {
                if (scheduleString.equals(schedule)) {
                    Subject subject = subjectService.getSubjectByCode(group.getSubjectCode());
                    if (!result.contains(subject)) {
                        result.add(subject);
                    }
                }
            }
        }
        return result;
    }
}