package cat.babot.pasive.generic;

import java.util.HashMap;

public class SessionVars {
  private static final HashMap<String, Object> state = new HashMap<>();

  @SuppressWarnings("unchecked")
  public static <T> T getValue(String key) {
    if (state.containsKey(key)) {
      return (T) state.get(key);
    }
    throw new RuntimeException(String.format("The session variable %s does not exist", key));
  }

  public static <T> void setValue(String key, T value) {
    state.put(key, value);
  }
}
