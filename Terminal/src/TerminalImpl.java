class TerminalImpl implements Terminal{
    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public double getDeposit() {
        return 0;
    }

    @Override
    public boolean inDeposit() {
        return false;
    }

    @Override
    public boolean outDeposit() {
        return false;
    }

    public static void main(String[] args) {
    }
}