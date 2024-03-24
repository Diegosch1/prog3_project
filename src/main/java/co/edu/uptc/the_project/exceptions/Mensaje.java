package co.edu.uptc.the_project.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {
    private int code;
    private String message;
    private int codeHttp;

}