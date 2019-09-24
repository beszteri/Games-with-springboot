package com.codecool.services;

import com.codecool.commands.GameForm;
import com.codecool.converters.GameFormToGame;
import com.codecool.domain.Game;
import com.codecool.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private GameFormToGame gameFormToGame;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GameFormToGame gameFormToGame) {
        this.gameRepository = gameRepository;
        this.gameFormToGame = gameFormToGame;
    }


    @Override
    public List<Game> listAll() {
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add);
        return games;
    }

    @Override
    public Game getById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public Game saveOrUpdate(Game game) {
        gameRepository.save(game);
        return game;
    }

    @Override
    public void delete(Long id) {
        gameRepository.deleteById(id);

    }

    @Override
    public Game saveOrUpdateProductForm(GameForm gameForm) {
        Game savedGame = saveOrUpdate(gameFormToGame.convert(gameForm));

        System.out.println("Saved Product Id: " + savedGame.getId());
        return savedGame;
    }
}
