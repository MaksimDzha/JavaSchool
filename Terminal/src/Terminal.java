interface Terminal {
    boolean login(Integer card) throws AccountNotFound;
    double getDeposit(Integer card);
    boolean inDeposit(Integer card, int sum) throws DepositException;
    boolean outDeposit(Integer card, int sum) throws DepositException, DepositOutException;
}
