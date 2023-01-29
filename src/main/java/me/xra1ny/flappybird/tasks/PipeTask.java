package me.xra1ny.flappybird.tasks;

import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.flappybird.entities.PipeLane;
import me.xra1ny.gameapi.engines.tasks.RepeatableTask;
import me.xra1ny.gameapi.engines.tasks.RepeatableTaskInfo;
import me.xra1ny.gameapi.utils.PropertyUtils;
import org.jetbrains.annotations.NotNull;

@RepeatableTaskInfo(interval = 3000)
public class PipeTask extends RepeatableTask {
    private final FlappyBird flappyBird;
    private int pipeGap = 120;

    public PipeTask(@NotNull FlappyBird flappyBird) {
        this.flappyBird = flappyBird;
        pipeGap = PropertyUtils.getInt(flappyBird.getGameProperties(), "pipeGap");
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void tick() {
        final PipeLane pipeLane = new PipeLane(pipeGap, flappyBird.getHeight());
        pipeLane.setX(flappyBird.getX()+flappyBird.getWidth());
        flappyBird.getMainScreen().getGameObjects().add(pipeLane);
    }
}
