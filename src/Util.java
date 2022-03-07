import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static String arrayListJoin(List<Integer> list, String sep) {
        return list.stream().map(Object::toString).collect(Collectors.joining(sep));
    }

    public static String arrayListJoin(Integer[] arr, String sep) {
        return Arrays.stream(arr).map(Object::toString).collect(Collectors.joining(sep));
    }

    public static String arrayListJoin(int[] arr, String sep) {
        Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        return Arrays.stream(boxed).map(Object::toString).collect(Collectors.joining(sep));
    }
}