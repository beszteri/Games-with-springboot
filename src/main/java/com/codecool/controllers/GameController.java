package com.codecool.controllers;

import com.codecool.commands.GameForm;
import com.codecool.converters.GameToGameForm;
import com.codecool.services.GameService;
import com.codecool.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class GameController {
    private GameService gameService;

    private GameToGameForm gameToGameForm;

    @Autowired
    public void setGameToGameForm(GameToGameForm gameToGameForm) {
        this.gameToGameForm = gameToGameForm;
    }

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/game/list";
    }

    @RequestMapping({"/game/list", "/game"})
    public String listProducts(Model model){
        model.addAttribute("games", gameService.listAll());
        return "game/list";
    }

    @RequestMapping("/game/show/{id}")
    public String getProduct(@PathVariable String id, Model model){
        model.addAttribute("game", gameService.getById(Long.valueOf(id)));
        return "game/show";
    }

    @RequestMapping("game/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Game game = gameService.getById(Long.valueOf(id));
        GameForm gameForm = gameToGameForm.convert(game);

        model.addAttribute("gameForm", gameForm);
        return "product/gameform";
    }

    @RequestMapping("/game/new")
    public String newProduct(Model model){
        model.addAttribute("gameForm", new GameForm());
        return "game/gameform";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid GameForm gameForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "game/gameform";
        }

        Game savedGame = gameService.saveOrUpdateProductForm(gameForm);

        return "redirect:/game/show/" + savedGame.getId();
    }

    @RequestMapping("/game/delete/{id}")
    public String delete(@PathVariable String id){
        gameService.delete(Long.valueOf(id));
        return "redirect:/game/list";
    }
}
