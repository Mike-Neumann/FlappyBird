package me.xra1ny.flappybird.entities;

import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.objects.Sprite;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Background extends Entity {
    private final Sprite sprite = new Sprite("background.png");

    @Override
    public void tick(@NotNull Game game) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void renderTick(@NotNull Game game, @NotNull Graphics2D gtd) {
        gtd.drawImage(getSprite().getBufferedImage(), 0, 0, game.getWidth(), game.getHeight(), null);
    }

    @Override
    public @NotNull Sprite getSprite() {
        return sprite;
    }
}
