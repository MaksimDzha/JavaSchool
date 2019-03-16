import java.util.*;

public class TerminalServer implements Terminal {
    private static Map<Integer, Account> accounts = new HashMap();
    private static Account person_01 = new Account("Михаил Михайлов", 1234, 10_000);
    private static Account person_02 = new Account("Анна Петрова", 1232, 20_000);
    private boolean loginInit = false;

    public TerminalServer() {
        accounts.put(12345678, person_01);
        accounts.put(87654321, person_02);
        System.out.println("Подключение к терминальному серверу выполнено");
    }

    @Override
    public boolean login(TerminalServer server, Integer card) throws AccountNotFound {
        loginInit = true;
        if (accounts.containsKey(card)) {
            System.out.println("Здравствуйте, " + accounts.get(card).getName() + "!");
        } else {
            throw new AccountNotFound("Некорректный ввод данных: карта не зарегистрирована");
//            return false;
        }
        try{
            PinValidator.pinValid(server, card);
        } catch (AccountIsLockedException e) {
//            e.printStackTrace();
            System.out.println("Доступ закрыт");
            return false;
        }
        accounts.get(card).pinUnlockInit(getPin4Valid(card));
        loginInit = false;
        return true;
    }

    public void logout(int card){
        accounts.get(card).pinLockInit();
        loginInit = false;
        System.out.println("Выход из учетной записи осуществлен");
    }

    @Override
    public double getDeposit(Integer card) {
        return accounts.get(card).getDeposit();
    }

    @Override
    public boolean inDeposit(Integer card, int sum) throws DepositException {
        if ((sum > 0) && ((sum % 100) == 0)) {
            accounts.get(card).setDepositIn(sum);
            System.out.println("Операция успешно выполнена. Ваш баланс: " + accounts.get(card).getDeposit());
            return true;
        } else throw new DepositException("Некорректная сумма");
    }

    @Override
    public boolean outDeposit(Integer card, int sum) throws DepositException, DepositOutException {
        if ((sum > 0) && ((sum % 500) == 0)) {
            try {
                accounts.get(card).setDepositOut(sum);
            } catch (DepositException e) {
                throw new DepositOutException("Указанная сумма больше депозита");
            }
            System.out.println("Операция успешно выполнена. Ваш баланс: " + accounts.get(card).getDeposit());
            return true;
        } else throw new DepositException("Некорректная сумма");
        //нужно добавить исключение
    }

    protected int getPin4Valid(Integer card) {
        if (loginInit){
            return accounts.get(card).getPin();
        } else {
            System.out.println("Попытка несанкционированного доступа");
            accountLock(card, 10000);
            return 0;
        }
    }

    protected void accountLock(Integer card, int blockTime) {
        accounts.get(card).lockThis(blockTime);
    }

    protected int accountIsLocked(Integer card) {
        return accounts.get(card).isLocked();
    }


    public static void main(String[] args) {
    }
}
