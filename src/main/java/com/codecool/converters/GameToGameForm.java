package com.codecool.converters;

import com.codecool.commands.GameForm;
import com.codecool.domain.Game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class GameToGameForm implements Converter<Game, GameForm> {
    @Override
    public GameForm convert(Game game) {
        GameForm gameForm = new GameForm();
        gameForm.setId(game.getId());
        gameForm.setDescription(game.getDescription());
        gameForm.setPrice(game.getPrice());
        gameForm.setImageUrl(game.getImageUrl());
        return gameForm;
    }
}
