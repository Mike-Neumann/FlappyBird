package me.xra1ny.flappybird.entities;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.objects.AnimatedSprite;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.objects.Sprite;
import me.xra1ny.gameapi.objects.SpriteSheet;
import me.xra1ny.gameapi.screens.GameScreen;
import me.xra1ny.gameapi.utils.FileUtils;
import me.xra1ny.gameapi.utils.PropertyUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Bird extends Entity {
    private final AnimatedSprite animatedSprite = new AnimatedSprite(new SpriteSheet(new Sprite("bird_spritesheet.png"), 121), 200);
    @Getter
    @Setter
    private boolean alive = true;
    @Getter
    @Setter
    private int currentScore;

    public Bird() {
        animatedSprite.enable();
        setX(75);
        setY(150);
    }

    public void jump(@NotNull Game game) {
        setYVelocity(-4);
        game.getSoundEngine().playSound("jump.wav");
    }

    public void kill(@NotNull FlappyBird flappyBird) {
        if(alive) {
            alive = false;
            animatedSprite.kill();

            flappyBird.getSoundEngine().playSound("hit.wav");
            flappyBird.getSoundEngine().playSound("fall.wav");

            if(currentScore > flappyBird.getHighscore()) {
                flappyBird.getGameProperties().setProperty("highscore", String.valueOf(currentScore));
                flappyBird.getSoundEngine().playSound("highscore.wav");

                PropertyUtils.save(flappyBird.getGameProperties(), FileUtils.getOutputStream(flappyBird.getGamePropertiesFile()));
            }
        }
    }

    public void score(@NotNull Game game, @NotNull PipeLane pipeLane) {
        pipeLane.setPassed(true);

        currentScore+=1;
        game.getSoundEngine().playSound("coin.wav");
    }

    @Override
    public TickResult onTick(@NotNull GameScreen gameScreen) {
        setYVelocity(getYVelocity()+.2);

        if(getY() > gameScreen.getGame().getHeight()) {
            kill((FlappyBird) gameScreen.getGame());
        }

        return TickResult.DEFAULT;
    }

    @Override
    public double getWidth() {
        return 60;
    }

    @Override
    public double getHeight() {
        return 50;
    }

    @Override
    public RenderResult onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.drawImage(getSprite().getBufferedImage(), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);

        return RenderResult.DEFAULT;
    }

    @Override
    public @NotNull Sprite getSprite() {
        return animatedSprite.getCurrentSprite();
    }
}
