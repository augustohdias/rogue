package dev.hond0n.rogue.core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
  public static void main(String[] args) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setIdleFPS(60);
    config.useVsync(false);

    config.setWindowedMode(800, 600);
    new Lwjgl3Application(new Rogue(), config);
  }
}
