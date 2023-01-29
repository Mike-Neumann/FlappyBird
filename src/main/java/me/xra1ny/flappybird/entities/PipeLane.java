package me.xra1ny.flappybird.entities;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class PipeLane extends Entity {
    @Getter(onMethod = @__(@NotNull))
    private final Pipe top = new Pipe(), bottom = new Pipe();
    @Getter
    @Setter
    private boolean passed;

    private final int gap;
    private final int gapY;

    public PipeLane(int gap, int gameHeight) {
        this.gap = gap;
        gapY = new Random().nextInt(gameHeight-gap);
    }

    @Override
    public TickResult onTick(@NotNull GameScreen gameScreen) {
        final FlappyBird flappyBird = (FlappyBird) gameScreen.getGame();

        if(flappyBird.getMainScreen().getBird().isAlive()) {
            setX(getX()-3);
            setY(0);
            top.tick(gameScreen);
            bottom.tick(gameScreen);
        }


        if(!passed) {
            if(flappyBird.getMainScreen().getBird().collidesWith(this, flappyBird.getCollisionTolerance())) {
                flappyBird.getMainScreen().getBird().score(flappyBird, this);
            }
        }

        return TickResult.DEFAULT;
    }

    @Override
    public double getWidth() {
        return 80;
    }

    @Override
    public double getHeight() {
        return 480;
    }

    @Override
    public RenderResult onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        top.onRender(gameScreen, gtd);
        bottom.onRender(gameScreen, gtd);

        return RenderResult.DEFAULT;
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        top.setX(x-(getWidth()/3));
        bottom.setX(x-(getWidth()/3));
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        top.setY(gapY-top.getHeight());
        bottom.setY(gapY+gap);
    }
}
