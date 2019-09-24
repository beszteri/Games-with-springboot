package com.codecool.services;

import com.codecool.commands.GameForm;
import com.codecool.domain.Game;

import java.util.List;


public interface GameService {

    List<Game> listAll();

    Game getById(Long id);

    Game saveOrUpdate(Game game);

    void delete(Long id);

    Game saveOrUpdateProductForm(GameForm gameForm);
}
