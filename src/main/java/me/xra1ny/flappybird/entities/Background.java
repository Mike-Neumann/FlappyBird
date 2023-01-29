package me.xra1ny.flappybird.entities;

import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.objects.Sprite;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Background extends Entity {
    private final Sprite sprite = new Sprite("background.png");

    @Override
    public TickResult onTick(@NotNull GameScreen gameScreen) {
        return TickResult.DEFAULT;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public RenderResult onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.drawImage(getSprite().getBufferedImage(), 0, 0, gameScreen.getGame().getWidth(), gameScreen.getGame().getHeight(), null);

        return RenderResult.DEFAULT;
    }

    @Override
    public @NotNull Sprite getSprite() {
        return sprite;
    }
}
