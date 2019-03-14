import java.io.IOException;
import java.util.*;

class TerminalImpl {
    private static final TerminalServer server = new TerminalServer();



    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        boolean workWith = true;
        do {
            System.out.print("Введите номер Вашей карты: ");
            int card = in.nextInt();
            server.login(card);
            //обработка исключения
            if (PinValidator.pinValid(server, card)) {
                boolean login = true;
                do {
                    System.out.println("Для выбора действия используйте:");
                    System.out.println("1 - пополнить баланс");
                    System.out.println("2 - снять наличные");
                    System.out.println("3 - проверить баланс");
                    System.out.println("4 - выход из системы");

                    System.out.print("Выберите необходимую операцию: ");
                    int operation = in.nextInt();
                    switch (operation) {
                        case 1:
                            System.out.print("Укажите сумму для пополнения: ");
                            double sumIn = in.nextDouble();
                            server.inDeposit(card, sumIn);
                            System.out.println();
                            break;
                        case 2:
                            System.out.print("Укажите сумму для снятия: ");
                            double sumOut = in.nextDouble();
                            server.outDeposit(card, sumOut);
                            System.out.println();
                            break;
                        case 3:
                            System.out.println("На вашем счету " + server.getDeposit(card) + " руб.");
                            System.out.println();
                            break;
                        case 4:
                            login = false;
                            break;
                        default:
                            System.out.println("Недопустимое значение. Пожалуйста, введите номер операции еще раз:");
                            break;
                    }
                } while (login);
            }

//            System.out.print("\n\n\nПродолжить работу? (Введите 0 для продолжения): ");
//            int nextUser = in.nextInt();
//            if (nextUser != 0) workWith = false;
        } while (workWith);
    }
}