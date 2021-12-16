package dev.hond0n.rogue.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.hond0n.rogue.world.Transition;

public class DrawUnity {
  private final SpriteBatch batch;
  private final OrthographicCamera camera;

  public DrawUnity(SpriteBatch batch, OrthographicCamera camera) {
    this.batch = batch;
    this.camera = camera;
    this.camera.setToOrtho(false, 200, 100);
  }

  public void draw(Transition transition) {
    // tell the camera to update its matrices.
    camera.update();

    // tell the SpriteBatch to render in the
    // coordinate system specified by the camera.
    batch.setProjectionMatrix(camera.combined);

    // begin a new batch and draw thingies
    batch.begin();
    batch.end();
  }

  public void dispose() {
    batch.dispose();
  }
}
