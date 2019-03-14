import java.io.IOException;
import java.util.*;

class TerminalImpl {
    private static final TerminalServer server = new TerminalServer();
    private static int card;

    private static boolean cardIn() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Введите номер Вашей карты (0 - выход): ");
            card = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nНеправильный формат номера карты. Номер должен состоять из восьми цифр.");
            return false;
        }
        if (card == 0) {
            System.out.println("Выход из программы");
            System.exit(0);
        }
        if ((getCountsOfCard(card) < 8) || (getCountsOfCard(card) > 8) || (card < 0)) {
            System.out.println("Неправильный формат номера карты. Номер должен состоять из восьми цифр.");
            return false;
        }
        return true;
    }

    private static int getCountsOfCard(int card) {
        int count = (card == 0) ? 1 : 0;
        while (card != 0) {
            count++;
            card /= 10;
        }
        return count;
    }

    private static int menu() {
        System.out.println("\nДля выбора действия используйте:");
        System.out.println("1 - пополнить баланс");
        System.out.println("2 - снять наличные");
        System.out.println("3 - проверить баланс");
        System.out.println("0 - выход из аккаунта");
        System.out.print("\nВыберите необходимую операцию: ");
        Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nНедопустимое значение. Пожалуйста, введите номер операции еще раз:");
            return -1;
        }
    }

    public static void main(String[] args) {
        while (true) {
//Ввод номера карты
            if (!cardIn()) continue;
//Проверка возможности подключения к аккаунту
            try {
                server.login(card);
            } catch (AccountNotFound e) {
//                e.printStackTrace();
                System.out.println("Карта не зарегистрирована в системе");
                continue;
            }
//Проверка пин-кода
            try {
                PinValidator.pinValid(server, card);
            } catch (AccountIsLockedException e) {
//                e.printStackTrace();
                System.out.println("Доступ закрыт");
                continue;
            }
//Если все проверки пройдены, то начинаем работать со счетом
            boolean login = true;
            while (login) {
                Scanner in = new Scanner(System.in);
                switch (menu()) {
                    case 1:
                        System.out.print("Укажите сумму для пополнения: ");
                        try {
                            int sumIn = in.nextInt();
                            try {
                                server.inDeposit(card, sumIn);
                            } catch (DepositException e) {
                                System.out.println("Сумма должна быть положительна и кратна 100 руб.");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("\nНедопустимое значение. Сумма должна быть положительным числом и кратна 100 руб.");
                            continue;
                        }
                        break;
                    case 2:
                        System.out.print("Укажите сумму для снятия: ");
                        try {
                            int sumOut = in.nextInt();
                            try {
                                server.outDeposit(card, sumOut);
                            } catch (DepositException e) {
                                System.out.println("Сумма должна быть положительна и кратна 500 руб.");
                            } catch (DepositOutException e){
                                System.out.println("На счете недостаточно средств");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nНедопустимое значение. Сумма должна быть положительным числом и кратна 500 руб.");
                            continue;
                        }
                        break;
                    case 3:
                        System.out.println("На вашем счету " + server.getDeposit(card) + " руб.");
                        break;
                    case 0:
                        login = false;
                        break;
                    default:
                        System.out.println("Недопустимое значение. Пожалуйста, введите номер операции еще раз:");
                        break;
                }
            }
        }
    }
}