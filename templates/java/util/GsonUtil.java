package model.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {

  private static Gson gson = new Gson();
  private static Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
  private static JsonParser parser = new JsonParser();

  /**
   * object convert to json
   *
   * @param src
   * @return
   */
  public static String toJson(Object src) {
    if (src == null) {
      return null;
    }
    return gson.toJson(src);
  }

  /**
   * parse json string to Json Object
   *
   * @param json
   * @return
   */
  public static JsonObject parseObject(String json) {
    if (json == null) {
      return null;
    }
    return parser.parse(json).getAsJsonObject();
  }

  /**
   * get json object's attr, as a string
   *
   * @param json json object
   * @param attr attr name
   * @return attr string value
   */
  public static String getString(JsonObject json, String attr) {
    return json != null && json.has(attr) ? json.get(attr).getAsString() : null;
  }

  /**
   * @param json
   * @param attr
   * @return
   */
  public static String searchString(JsonObject json, String attr) {
    SearchResult preObj = searchPreObject(json, attr);
    return getString(preObj.json, preObj.lastAttr);
  }

  /**
   * search parent object in attr
   *
   * @param json
   * @param attr
   * @return attr's parent object
   */
  public static SearchResult searchPreObject(JsonObject json, String attr) {
    if (json == null || attr == null) {
      return null;
    }
    if (json == null || attr == null || !attr.contains(".")) {
      return new SearchResult(json, attr);
    }

    String[] strs = attr.split("\\.");
    if (strs.length < 2) {
      return new SearchResult(json, attr);
    }
    JsonObject result = json;
    for (int i = 0; i < strs.length - 1; i++) {
      if (result.has(strs[i])) {
        result = result.get(strs[i]).getAsJsonObject();
      } else {
        return new SearchResult(null, null);
      }
    }
    return new SearchResult(result, strs[strs.length - 1]);
  }

  public static class SearchResult {
    public JsonObject json;
    public String lastAttr;

    public SearchResult(JsonObject json, String lastAttr) {
      this.json = json;
      this.lastAttr = lastAttr;
    }
  }
}
