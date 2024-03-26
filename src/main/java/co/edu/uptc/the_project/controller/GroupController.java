package co.edu.uptc.the_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.ejercicio1.models.UptcList;
import co.edu.uptc.the_project.dtos.GroupDto;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.model.Group;
import co.edu.uptc.the_project.services.GroupService;

@RestController
@RequestMapping("/groups")
public class GroupController {

    GroupService groupService = new GroupService();

    @GetMapping()
    public UptcList<Group> getPlaces() {
        UptcList<Group> groupsAux = groupService.getGroupList();
        return groupsAux;
    }

    @PostMapping("/addGroup")
    public ResponseEntity<Object> addGroup(@RequestBody GroupDto group) {
        try {

            GroupDto.isValidGroup(group);
            groupService.gruopIdIsUnique(group.getGroupId());
            groupService.placeIsExclusive(group.getPlaceId());
            groupService.schedulesAreValid(group.getSchedules());

            UptcList<String> schedulesAux = group.turnSchedulesToStrings(group.getSchedules());

            groupService.schedulesAreUnique(schedulesAux);

            Group finalGroup = new Group(group.getGroupId(), group.getSubjectCode(), group.getPlaceId(), schedulesAux);
            groupService.addGroup(finalGroup);
            
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
