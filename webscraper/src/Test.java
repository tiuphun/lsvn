import org.json.JSONObject;

public class Test {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "John Doe");
        jsonObject.put("age", 30);
        jsonObject.put("city", "New York");

        // Pretty print JSON with 4 spaces indentation
        String prettyJson = jsonObject.toString(4);
        System.out.println(prettyJson);
    }
}
