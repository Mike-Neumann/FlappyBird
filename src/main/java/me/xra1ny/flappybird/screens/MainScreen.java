package me.xra1ny.flappybird.screens;

import lombok.Getter;
import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.flappybird.entities.Background;
import me.xra1ny.flappybird.entities.Bird;
import me.xra1ny.flappybird.tasks.PipeTask;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.objects.GameObject;
import me.xra1ny.gameapi.screens.GameScreen;
import me.xra1ny.gameapi.utils.Fonts;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends GameScreen {
    private final Background background = new Background();
    @Getter(onMethod = @__(@NotNull))
    private Bird bird;
    private PipeTask pipeTask;
    private boolean started = false;
    private final List<GameObject> gameObjects = new ArrayList<>();

    public MainScreen(@NotNull Game game) {
        super(game);
    }

    @Override
    public void renderTick(@NotNull Graphics2D gtd) {
        final FlappyBird flappyBird = (FlappyBird) getGame();
        gtd.setFont(Fonts.PROXIMA_NOVA);
        gtd.setColor(Color.BLACK);
        if(!started) {
            final String highscore = "HIGHSCORE: " + flappyBird.getHighscore();
            gtd.drawString(highscore, getGame().getWidth()/2, getGame().getHeight()/2);
            gtd.drawString("Press any Button to start...", getGame().getWidth()/2, getGame().getHeight()/2+50);
        }else {
            if(bird.isAlive()) {
                gtd.drawString("Score: " + bird.getCurrentScore(), 20, 20);
            }else {
                final String highscore = "NEW HIGHSCORE!";
                final String score = "Your Score: " + bird.getCurrentScore();
                final String again = "Press any Button to play again!";
                final int highscoreWidth = gtd.getFontMetrics().stringWidth(highscore);
                final int scoreWidth = gtd.getFontMetrics().stringWidth(score);
                final int againWidth = gtd.getFontMetrics().stringWidth(again);
                if(getBird().getCurrentScore() > flappyBird.getHighscore()) {
                    gtd.drawString(highscore, getGame().getWidth()/2-(highscoreWidth/2), getGame().getHeight()/2);
                }
                gtd.drawString(score, getGame().getWidth()/2-(scoreWidth/2), getGame().getHeight()/2+75);
                gtd.drawString(again, getGame().getWidth()/2-(againWidth/2), getGame().getHeight()/2+95);
            }
        }
    }

    @Override
    public void onKeyPress(@NotNull KeyEvent keyEvent) {

    }

    @Override
    public void onKeyRelease(@NotNull KeyEvent keyEvent) {

    }

    @Override
    public void onEnable() {
        final FlappyBird flappyBird = (FlappyBird) getGame();
        started = false;
        if(pipeTask != null) {
            pipeTask.kill();
        }

        if(bird != null) {
            if(bird.getCurrentScore() > flappyBird.getHighscore()) {
                flappyBird.setHighscore(bird.getCurrentScore());
            }
        }

        bird = new Bird();

        bird.setAlive(true);
        bird.setAllowTick(false);

        bird.setCurrentScore(0);

        gameObjects.clear();

        gameObjects.add(background);
        gameObjects.add(bird);

        pipeTask = new PipeTask((FlappyBird) getGame());
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onMousePress(@NotNull MouseEvent e) {
        final int keycode = e.getButton();

        if(keycode == MouseEvent.BUTTON1) {
            if(started) {
                if(bird.isAlive()) {
                    bird.jump(getGame());
                }else {
                    onEnable();
                }
            }else {
                started = true;
                bird.setAllowTick(true);
                bird.jump(getGame());
                pipeTask.enable();
            }
        }
    }

    @Override
    public void onMouseRelease(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void onMouseEnterComponent(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void onMouseExitComponent(@NotNull MouseEvent mouseEvent) {

    }

    @NotNull
    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
