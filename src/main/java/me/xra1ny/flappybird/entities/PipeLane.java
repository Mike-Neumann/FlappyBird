package me.xra1ny.flappybird.entities;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.objects.Entity;
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
    public void tick(@NotNull Game game) {
        final FlappyBird flappyBird = (FlappyBird) game;

        if(flappyBird.getMainScreen().getBird().isAlive()) {
            setX(getX()-3);
            setY(0);
            top.tick(game);
            bottom.tick(game);
        }


        if(!passed) {
            if(flappyBird.getMainScreen().getBird().collidesWith(this)) {
                flappyBird.getMainScreen().getBird().score(flappyBird, this);
            }
        }
    }

    @Override
    public int getWidth() {
        return 80;
    }

    @Override
    public int getHeight() {
        return 480;
    }

    @Override
    public void renderTick(@NotNull Game game, @NotNull Graphics2D gtd) {
        top.renderTick(game, gtd);
        bottom.renderTick(game, gtd);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        top.setX(x-(getWidth()/2));
        bottom.setX(x-(getWidth()/2));
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        top.setY(gapY-top.getHeight());
        bottom.setY(gapY+gap);
    }
}
