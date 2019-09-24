package com.codecool.converters;

import com.codecool.commands.GameForm;
import com.codecool.domain.Game;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class GameFormToGame implements Converter<GameForm, Game> {

    @Override
    public Game convert(GameForm gameForm) {
        Game game = new Game();
        if (gameForm.getId() != null  && !StringUtils.isEmpty(gameForm.getId())) {
            game.setId(new Long(gameForm.getId()));
        }
        game.setDescription(gameForm.getDescription());
        game.setPrice(gameForm.getPrice());
        game.setImageUrl(gameForm.getImageUrl());
        return game;
    }
}
