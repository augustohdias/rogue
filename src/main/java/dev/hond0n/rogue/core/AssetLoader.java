package dev.hond0n.rogue.core;

import com.badlogic.gdx.graphics.Texture;
import org.lwjgl.system.CallbackI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AssetLoader {
  public enum TextureName {
    PLAYER_IDLE("char_idle.png"),
    PATH("path.png"),
    WALL("bg.png");

    private String path;

    TextureName(String path) {
      this.path = path;
    }
  }

  private final Map<String, Texture> textureMap;

  public AssetLoader() {
    textureMap = new HashMap<>();
    Arrays.stream(TextureName.values())
        .forEach(t -> textureMap.put(t.name(), new Texture(t.path)));
  }

  public Texture getTexture(TextureName textureName) {
    return textureMap.get(textureName.name());
  }

  public void disposeAll() {
    textureMap.values().forEach(Texture::dispose);
  }
}
