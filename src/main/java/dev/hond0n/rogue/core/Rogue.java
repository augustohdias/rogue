package dev.hond0n.rogue.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.hond0n.rogue.action.Direction;
import dev.hond0n.rogue.action.Move;
import dev.hond0n.rogue.world.Environment;
import dev.hond0n.rogue.world.Transition;
import dev.hond0n.rogue.world.WorldCreationHelper;

import java.util.Objects;

public class Rogue extends ApplicationAdapter {

  DrawUnity drawUnity;
  AssetLoader assetLoader;
  Environment environment;
  Transition transition;

  @Override
  public void create() {
    if (Objects.isNull(assetLoader)) assetLoader = new AssetLoader();
    if (Objects.isNull(environment))
      environment = new Environment(WorldCreationHelper.createBodies(300, 25));
    if (Objects.isNull(drawUnity))
      drawUnity = new DrawUnity(new SpriteBatch(), new OrthographicCamera(800, 640));
    if (Objects.isNull(transition)) transition = environment.getStaticTransition();
  }

  @Override
  public void render() {
    drawUnity.draw(transition);

    // Game Loop
    if (transition.isFinished()) {
      if (Gdx.input.isKeyPressed(Input.Keys.W)) {
        transition = environment.iterate(new Move<>(Direction.UP));
      } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        transition = environment.iterate(new Move<>(Direction.LEFT));
      } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        transition = environment.iterate(new Move<>(Direction.DOWN));
      } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        transition = environment.iterate(new Move<>(Direction.RIGHT));
      } else {
        transition = environment.getStaticTransition();
      }
    }
  }

  @Override
  public void dispose() {
    // dispose of all the native resources
    assetLoader.disposeAll();
  }
}
