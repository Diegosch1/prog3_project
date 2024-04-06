package co.edu.uptc.the_project.dtos;

import co.edu.uptc.UptcList.models.UptcList;
import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import co.edu.uptc.the_project.model.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupDto {

    String groupId;
    String subjectCode;
    String placeId;
    UptcList<Schedule> schedules;

    public UptcList<String> turnSchedulesToStrings(UptcList<Schedule> schedules) {
        UptcList<String> result = new UptcList<>();
        for (Schedule schedule : schedules) {
            result.add(schedule.toString());
        }
        return result;
    }

    public static void isValidGroup(GroupDto group) throws ProjectExeption {

        if (group == null || group.getGroupId() == null || group.getSubjectCode() == null || group.getPlaceId() == null
                || group.getSchedules() == null || group.getSchedules().size() < 1 || group.getSchedules().size() > 3) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }

    }
}
