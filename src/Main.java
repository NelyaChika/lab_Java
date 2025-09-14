import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Клас, що описує число Люка з його номером (індексом).
 * Містить методи для обчислення n-го числа Люка та генерації послідовності.
 */
class LucasNumber {
    private int index;
    private BigInteger value;

    /**
     * Конструктор для створення числа Люка за заданим індексом.
     * @param index індекс (може бути від’ємним)
     */
    public LucasNumber(int index) {
        this.index = index;
        this.value = calculateLucas(index);
    }

    /**
     * Обчислює n-те число Люка.
     * Формула: L0 = 2, L1 = 1, L_n = L_{n-1} + L_{n-2}.
     * Для від’ємних індексів використовується: L_{-n} = (-1)^n * L_n.
     *
     * @param n індекс
     * @return n-те число Люка
     */
    public static BigInteger calculateLucas(int n) {
        if (n == 0) return BigInteger.valueOf(2);
        if (n == 1) return BigInteger.valueOf(1);

        boolean negative = n < 0;
        int m = Math.abs(n);

        BigInteger a = BigInteger.valueOf(2); // L0
        BigInteger b = BigInteger.valueOf(1); // L1
        for (int i = 2; i <= m; i++) {
            BigInteger c = a.add(b);
            a = b;
            b = c;
        }
        BigInteger result = b;

        if (negative) {
            result = result.negate();
        }
        return result;
    }

    /**
     * Генерує послідовність чисел Люка до вказаного індекса.
     * @param n індекс (може бути додатній або від’ємний)
     * @return масив значень чисел Люка
     */
    public static BigInteger[] generateSequence(int n) {
        if (n == 0) {
            return new BigInteger[]{BigInteger.valueOf(2)};
        }
        if (n > 0) {
            BigInteger[] seq = new BigInteger[n + 1];
            seq[0] = BigInteger.valueOf(2);
            seq[1] = BigInteger.valueOf(1);
            for (int i = 2; i <= n; i++) {
                seq[i] = seq[i - 2].add(seq[i - 1]);
            }
            return seq;
        } else {
            int m = Math.abs(n);
            BigInteger[] seq = new BigInteger[m + 1];
            for (int i = 0; i <= m; i++) {
                BigInteger val = calculateLucas(-m + i);
                seq[i] = val.equals(BigInteger.TWO) ? val.negate() : val;;
            }
            return seq;
        }
    }

    /** @return індекс числа Люка */
    public int getIndex() {
        return index;
    }

    /** @return значення числа Люка */
    public BigInteger getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "L(" + index + ") = " + value;
    }
}

/**
 * Головний клас програми.
 * Дозволяє ввести номер числа Люка з командного рядка або з клавіатури,
 * виводить обране число та послідовність до нього.
 */
public class Main {
    public static void main(String[] args) {
        int n;

        if (args.length > 0) {
            // якщо є аргумент командного рядка
            n = Integer.parseInt(args[0]);
        } else {
            // якщо немає аргументів, читаємо з клавіатури
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введіть номер числа Люка (N): ");
            n = scanner.nextInt();
        }

        // створюємо об’єкт числа Люка
        LucasNumber lucas = new LucasNumber(n);

        System.out.println("Обчислення числа Люка:");
        System.out.println("Вхідні дані: N = " + lucas.getIndex());
        System.out.println("Результат: " + lucas);

        // демонструємо генерацію послідовності
        BigInteger[] sequence = LucasNumber.generateSequence(n);
        System.out.println("Послідовність Люка до N:");
        System.out.println(Arrays.toString(sequence));
    }
}
