package me.xra1ny.flappybird.screens;

import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.screens.GameScreen;
import me.xra1ny.gameapi.utils.Fonts;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends GameScreen {
    public SplashScreen(@NotNull Game game) {
        super(game);
    }

    @Override
    public void renderTick(@NotNull Graphics2D gtd) {
        gtd.setFont(Fonts.PROXIMA_NOVA);
        gtd.setColor(Color.WHITE);
        final String text = "A Game by xRa1ny";
        final int textWidth = gtd.getFontMetrics().stringWidth(text);
        gtd.drawString(text, getGame().getWidth()/2-(textWidth/2), getGame().getHeight()/2);
    }

    @Override
    public void onEnable() {
        setBackground(Color.DARK_GRAY);
        getGame().getSoundEngine().playSound("splashboop.wav");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                cancel();
                getGame().show(((FlappyBird) getGame()).getMainScreen());
            }
        }, 3000L, 1000L);
    }

    @Override
    public void onDisable() {

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
