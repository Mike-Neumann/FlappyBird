package me.xra1ny.flappybird.entities;

import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Pipe extends Entity {
    @Override
    public TickResult onTick(@NotNull GameScreen gameScreen) {
        final FlappyBird flappyBird = (FlappyBird) gameScreen.getGame();

        if(flappyBird.getMainScreen().getBird().collidesWith(this, flappyBird.getCollisionTolerance())) {
            flappyBird.getMainScreen().getBird().kill(flappyBird);
        }

        return TickResult.DEFAULT;
    }

    @Override
    public double getWidth() {
        return 90;
    }

    @Override
    public double getHeight() {
        return 480;
    }

    @Override
    public RenderResult onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.setColor(Color.GREEN);
        gtd.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        return RenderResult.DEFAULT;
    }
}
