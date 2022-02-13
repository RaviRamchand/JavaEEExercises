package ca.ravi.thymeleafex.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long courseId;
    private String prefix;
    private String code;
    private String name;

}
