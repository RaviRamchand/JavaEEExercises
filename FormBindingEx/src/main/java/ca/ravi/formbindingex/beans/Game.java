package ca.ravi.formbindingex.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

//Lombok annotations to generate public getters and setters and no arg constructors
@Data
@NoArgsConstructor
public class Game implements Serializable {

    //field variables
    private Long gameId;
    private String gameTitle;
    private String publisher;
    private String platform;
    private BigDecimal gamePrice;

    //Array to allow users pick console for the game
    private String[] platforms = {"Playstation", "XBox", "PC"};
}
