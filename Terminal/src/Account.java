
class Account {
    private String name = new String();
    private int pin = 0;
    private double deposit = 0;
    private long endLock = 0;
    private int blockTime = 0;

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

    protected void setDepositOut(double sum) throws DepositException {
        if (this.deposit >= sum) {
            this.deposit -= sum;
        } else {
            throw new DepositException("Сумма не должна быть больше депозита");
        }
    }

    protected void lockThis(int blockTime) {
        this.blockTime = blockTime;
        // устанавливаем время окончания блокировки объекта на текущее + blockTime/1000 сек
        endLock = System.currentTimeMillis() + blockTime;
    }

    protected int isLocked(){
        if (endLock > System.currentTimeMillis()){
//            System.out.println("Аккаунт заблокирован! До конца блокировки осталось " + (endLock - System.currentTimeMillis())/1000 + "сек");
            return (int)((endLock - System.currentTimeMillis())/1000);
        }
        else return 0;
    }

}
