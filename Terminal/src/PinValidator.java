import java.util.InputMismatchException;
import java.util.Scanner;

public class PinValidator {
    private static final int blockTime = 10000;

    private static int getCountsOfPin(int pin) {
        int count = (pin == 0) ? 1 : 0;
        while (pin != 0) {
            count++;
            pin /= 10;
        }
        return count;
    }

    public static boolean pinValid(TerminalServer server, Integer card) throws AccountIsLockedException {
        if (server.accountIsLocked(card) == 0) {
            int k = 0;
            int pin;
            do {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите пин-код: ");
                try{
                    pin = in.nextInt();
                } catch (InputMismatchException e){
                    System.out.println();
                    System.out.println("Неправильный формат. Пин-код должен состоять из четырех цифр.");
                    k++;
                    continue;
                }
                k++;
                if ((getCountsOfPin(pin) < 4) || (getCountsOfPin(pin) > 4) || (pin < 0)) {
                    System.out.println("Неправильный формат. Пин-код должен состоять из четырех цифр.");
                    continue;
                }
                if (server.getPin4Valid(card) == pin) {
                    System.out.println("Корректный пин!");
                    return true;
                } else {
                    System.out.println("Пин-код не подходит!");
                }
            } while (k < 3);
            server.accountLock(card, blockTime);
            throw new AccountIsLockedException("Аккаунт заблокирован на " + blockTime / 1000 + " секунд");
//            return false;
        }

        throw new AccountIsLockedException("Аккаунт заблокирован! До конца блокировки осталось " + server.accountIsLocked(card) + "сек");
//        return false;
    }
}
