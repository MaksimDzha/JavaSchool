import java.util.Scanner;

public class PinValidator {

    public static boolean pinValid(TerminalServer server, Integer card) {
        if (!server.accountIsLocked(card)) {
            Scanner in = new Scanner(System.in);
            int k = 0;
            do {
                System.out.print("Введите пин-код: ");
                int pin = in.nextInt();
                k++;
                if (server.getPin4Valid(card) == pin) {
                    System.out.println("Корректный пин!");
                    return true;
                } else {
                    System.out.println("Пин-код не подходит!");
                    //тут надо добавить исключения
                }
            } while (k < 3);
            server.accountLock(card);
            return false;
        } else {
            return false;
        }
    }
}
