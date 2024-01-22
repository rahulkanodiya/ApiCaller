import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiValueMapExample {
    public static void main(String[] args) {
        // Create a map where the key is a String and the values are stored in a List
        Map<String, List<String>> multiValueMap = new HashMap<>();

        // Add values for a key
        addValue(multiValueMap, "Key1", "Value1");
        addValue(multiValueMap, "Key1", "Value2");
        addValue(multiValueMap, "Key2", "Value3");
        addValue(multiValueMap, "Key2", "Value4");

        // Retrieve values for a key
        List<String> valuesForKey1 = getValues(multiValueMap, "Key1");
        List<String> valuesForKey2 = getValues(multiValueMap, "Key2");

        // Print the values
        System.out.println("Values for Key1: " + valuesForKey1);
        System.out.println("Values for Key2: " + valuesForKey2);
    }

    // Method to add a value to the map
    private static void addValue(Map<String, List<String>> map, String key, String value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    // Method to get values for a key from the map
    private static List<String> getValues(Map<String, List<String>> map, String key) {
        return map.getOrDefault(key, new ArrayList<>());
    }
}