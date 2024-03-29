package co.edu.uptc.the_project.model;

import co.edu.uptc.the_project.exceptions.ProjectExeption;
import co.edu.uptc.the_project.exceptions.TypeMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {
    String day;
    int startHour;
    int endHour;


    public static void isValidSchedule(Schedule schedule) throws ProjectExeption {
        if (schedule == null || schedule.getDay() == null 
         || schedule.getStartHour() < 0  || schedule.getStartHour() >= 24 
         || schedule.getEndHour() < 0 || schedule.getEndHour() >= 24
         || schedule.getEndHour() <= schedule.getStartHour()) {
            throw new ProjectExeption(TypeMessage.INFORMATION_INCOMPLETE);
        }        
    }

    @Override
    public String toString() {
        return day+", "+startHour+":00 - "+endHour+":00";
    }


}
