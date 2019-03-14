interface Terminal {
    boolean login(Integer card);
    double getDeposit(Integer card);
    boolean inDeposit(Integer card, double sum);
    boolean outDeposit(Integer card, double sum);
}
