package ca.ravi.formbindingex.controllers;

import ca.ravi.formbindingex.beans.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class GameController {
    //List to store the game information
    List<Game> gamesList = new CopyOnWriteArrayList<Game>();

    //Run getIndex() when users go to the home page
    @GetMapping("/")
    public String getIndex(Model model){
        //Send an empty games object and gamesList to index
        model.addAttribute("games", new Game());
        model.addAttribute("gameList", gamesList);
        return "index";
    }

    //Run addGame() when the user submits a form which will link to /addGame
    @PostMapping("/addGame")
    public String addGame(Model model, @ModelAttribute Game game){
        //Add game info to gamesList
        gamesList.add(game);
        //Send games object and added gamesList to index
        model.addAttribute("games", new Game());
        model.addAttribute("gameList", gamesList);
        return "index";
    }
}
