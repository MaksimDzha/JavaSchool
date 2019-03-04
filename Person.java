public class Person {
    private final boolean man;
    private final String name;
    private Person spouse;

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */
    public boolean marry(Person person) {
        if (man ^ person.man) {
            if ((spouse == null) & (person.spouse == null)) {
                spouse = person;
                person.spouse = this;
                System.out.println(this.name + " and " + person.name + " got married");
                return true;
            }
            if ((spouse == null) & (person.spouse != null)) {
                person.divorce();
                spouse = person;
                person.spouse = this;
                System.out.println(this.name + " and " + person.name + " got married");
                return true;
            }
            if ((spouse != null) & (person.spouse == null)) {
                this.divorce();
                spouse = person;
                person.spouse = this;
                System.out.println(this.name + " and " + person.name + " got married");
                return true;
            }
            if (spouse.equals(person)) {
                System.out.println(this.name + " and " + person.name + " are married");
                return false;
            } else {
                this.divorce();
                person.divorce();
                spouse = person;
                person.spouse = this;
                System.out.println(this.name + " and " + person.name + " got married");
                return true;
            }
        } else {
            System.out.println(this.name + " is the same sex as " + person.name);
            return false;
        }
    }

    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (spouse != null) {
            System.out.println(this.name + " and " + spouse.name + " were divorced");
            spouse.spouse = null;
            spouse = null;
            return true;
        } else {
            System.out.println(this.name + " is not married");
            return false;
        }
    }
}
