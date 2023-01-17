package me.xra1ny.flappybird.entities;

import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.objects.Entity;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Pipe extends Entity {
    @Override
    public void tick(@NotNull Game game) {
        final FlappyBird flappyBird = (FlappyBird) game;

        if(flappyBird.getMainScreen().getBird().collidesWith(this)) {
            flappyBird.getMainScreen().getBird().kill(flappyBird);
        }
    }

    @Override
    public int getWidth() {
        return 90;
    }

    @Override
    public int getHeight() {
        return 480;
    }

    @Override
    public void renderTick(@NotNull Game game, @NotNull Graphics2D gtd) {
        gtd.setColor(Color.GREEN);
        gtd.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
