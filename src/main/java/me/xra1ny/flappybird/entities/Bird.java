package me.xra1ny.flappybird.entities;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.objects.AnimatedSprite;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.objects.Sprite;
import me.xra1ny.gameapi.objects.SpriteSheet;
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
    public void tick(@NotNull Game game) {
        setYVelocity((float) (getYVelocity()+.2));

        if(getY() > game.getHeight()) {
            kill((FlappyBird) game);
        }
    }

    @Override
    public int getWidth() {
        return 60;
    }

    @Override
    public int getHeight() {
        return 50;
    }

    @Override
    public void renderTick(@NotNull Game game, @NotNull Graphics2D gtd) {
        gtd.drawImage(getSprite().getBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public @NotNull Sprite getSprite() {
        return animatedSprite.getCurrentSprite();
    }
}
