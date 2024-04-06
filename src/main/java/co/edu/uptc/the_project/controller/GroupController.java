package co.edu.uptc.the_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import co.edu.uptc.UptcList.models.UptcList;
import co.edu.uptc.the_project.dtos.GroupDto;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.model.Group;
import co.edu.uptc.the_project.services.GroupService;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping()
    public UptcList<Group> getPlaces() {
        UptcList<Group> groupsAux = groupService.getGroupList();
        return groupsAux;
    }

    @PostMapping("/add")
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
    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> editGroup(@PathVariable String id, @RequestBody GroupDto updatedGroup) {
        try {
            GroupDto.isValidGroup(updatedGroup);
            
            Group targetGroup = groupService.getGroupById(id);

            groupService.validateSubjectAndPlaceExists(updatedGroup.getSubjectCode(), updatedGroup.getPlaceId());

            targetGroup.setSubjectCode(updatedGroup.getSubjectCode());
            targetGroup.setPlaceId(updatedGroup.getPlaceId());

            UptcList<String> schedulesAux = updatedGroup.turnSchedulesToStrings(updatedGroup.getSchedules());
            
            groupService.schedulesAreValid(updatedGroup.getSchedules());

            targetGroup.setSchedules(schedulesAux);

            return ResponseEntity.status(HttpStatus.OK).body(targetGroup);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable String id) {
        try {
            Group targetGroup = groupService.getGroupById(id);
            groupService.deleteGroup(targetGroup);
            return ResponseEntity.status(HttpStatus.OK).body(targetGroup);
        } catch (ProjectExeption e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
