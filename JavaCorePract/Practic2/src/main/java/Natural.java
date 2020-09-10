import java.util.Arrays;

public class Natural {
    /**
     * Ищем количество натуральных чисел
     * @param n Количество чисел
     * @return массив натуральных чисел
     */
    public static int[] natur(int n) {
        if (n < 2)  {
            return new int[0];
        }

        int[] massNature = new int[n];
        massNature[0] = 2;
        int pos = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < pos; j++) {
                if (isBoolean(i % massNature[j], 0)) {
                    break;
                } else if (isBoolean(j, pos - 1)) {
                    massNature[pos] = i;
                    pos++;
                }
            }
        }
        return Arrays.copyOf(massNature, pos);
    }

    private static boolean isBoolean(int j, int i) {
        return j == i;
    }
}
