
class Account {
    private String name = new String();
    private int pin = 0;
    private double deposit = 0;
    private long endLock = 0;
    private int blockTime = 0;
    private boolean pinUnlock = false;

    Account() {
//Добавить исключение
    }

    Account(String name, int pin, double deposit) {
        this.name = name;
        this.pin = pin;
        this.deposit = deposit;
    }

    protected String getName() {
        return name;
    }

    protected int getPin() {
        return pin;
    }

    protected void pinUnlockInit(int pin){
        if (this.pin == pin){
            pinUnlock = true;
        } else {
            System.out.println("Попытка несанкционированного доступа!");
            lockThis(10_000);
        }
    }

    protected void pinLockInit(){
        pinUnlock = false;
    }

    protected double getDeposit() {
        if (pinUnlock) {
            return deposit;
        } else {
            System.out.println("Попытка несанкционированного доступа");
            lockThis(10_000);
            return 0;
        }
    }

    protected void setDepositIn(double sum) {
        if (pinUnlock){
            this.deposit += sum;
        } else {
            System.out.println("Попытка несанкционированного доступа");
            lockThis(10_000);
        }
    }

    protected void setDepositOut(double sum) throws DepositException {
        if (pinUnlock) {
            if (this.deposit >= sum) {
                this.deposit -= sum;
            } else {
                throw new DepositException("Сумма не должна быть больше депозита");
            }
        } else {
            System.out.println("Попытка несанкционированного доступа");
            lockThis(10_000);
        }
    }

    protected void lockThis(int blockTime) {
        this.blockTime = blockTime;
        // устанавливаем время окончания блокировки объекта на текущее + blockTime/1000 сек
        endLock = System.currentTimeMillis() + blockTime;
    }

    protected int isLocked() {
        if (endLock > System.currentTimeMillis()) {
//            System.out.println("Аккаунт заблокирован! До конца блокировки осталось " + (endLock - System.currentTimeMillis())/1000 + "сек");
            return (int) ((endLock - System.currentTimeMillis()) / 1000);
        } else return 0;
    }

}
