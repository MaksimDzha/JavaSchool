
class Account {
    private String name = new String();
    private int pin = 0;
    private double deposit = 0;
    private long endLock = 0;

    public Account() {
//Добавить исключение
    }

    public Account(String name, int pin, double deposit) {
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

    protected double getDeposit() {
        return deposit;
    }

    protected void setDepositIn(double sum) {
        this.deposit += sum;
        //Добавить исключение на отрицательные числа
    }

    protected void setDepositOut(double sum) {
        if (this.deposit >= sum) {
            this.deposit -= sum;
        } else {
            //Добавить ислючение на снятие суммы больше депозита
        }
    }

    protected void lockThis() {
        // устанавливаем время окончания блокировки объекта на текущее + 10 сек
        endLock = System.currentTimeMillis() + 10_000;
    }

    protected boolean isLocked(){
        if (endLock > System.currentTimeMillis()){
            System.out.println("Аккаунт заблокирован! До конца блокировки осталось " + (endLock - System.currentTimeMillis())/1000 + "сек");
            return true;
        }
        else return false;
    }

}
