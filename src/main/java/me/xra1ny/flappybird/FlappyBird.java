package me.xra1ny.flappybird;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.flappybird.screens.MainScreen;
import me.xra1ny.flappybird.screens.SplashScreen;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.utils.FileUtils;
import me.xra1ny.gameapi.utils.PropertyUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class FlappyBird extends Game {
    @Getter
    @Setter
    private int highscore;
    @Getter(onMethod = @__(@NotNull))
    private MainScreen mainScreen;
    @Getter(onMethod = @__(@NotNull))
    private SplashScreen splashScreen;

    @Override
    public void onPropertiesCreation() {
        getGameProperties().setProperty("pipeGap", "125");
        getGameProperties().setProperty("highscore", "0");
        PropertyUtils.save(getGameProperties(), FileUtils.getOutputStream(getGamePropertiesFile()));
    }

    @Override
    public void onEnable() {
        setTitle("Flappy Bird by xRa1ny");
        highscore = PropertyUtils.getInt(getGameProperties(), "highscore");
        mainScreen = new MainScreen(this);
        splashScreen = new SplashScreen(this);
        show(splashScreen);
    }

    public static void main(String[] args) {
        new FlappyBird();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClick(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void mousePress(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void mouseRelease(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEnter(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExit(@NotNull MouseEvent mouseEvent) {

    }
}
