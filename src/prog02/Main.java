package prog02;

/**
 * A program to query and modify the phone directory stored in csc220.txt.
 *
 * @author vjm
 */
public class Main {

    /**
     * Processes user's commands on a phone directory.
     *
     * @param fn The file containing the phone directory.
     * @param ui The UserInterface object to use
     *           to talk to the user.
     * @param pd The PhoneDirectory object to use
     *           to process the phone directory.
     */
    public static void processCommands(String fn, UserInterface ui, PhoneDirectory pd) {
        pd.loadData(fn);
        boolean changed = false;
        boolean valid = true;

        String[] commands = {"Add/Change Entry", "Look Up Entry", "Remove Entry", "Save Directory", "Exit"};

        String name, number, oldNumber;

        while (true) {
            int c = ui.getCommand(commands);
            switch (c) {
                case -1:
                    ui.sendMessage("You shut down the program, restarting.  Use Exit to exit.");
                    break;
                case 0:
                    name = ui.getInfo("Enter name");
                    if (name == null) {
                        break;
                    }
                    if (name.length() == 0)  {
                        do {
                            name = ui.getInfo("Enter name please");
                            if (name.length() > 0)
                                valid = false;
                        } while (valid);
                    }
                    number = ui.getInfo("Enter your number");
                    oldNumber = pd.addOrChangeEntry(name, number);
                    if (number == null) {
                        break;
                    }
                    if ((oldNumber == null) && (number.equals(""))) {
                        ui.sendMessage("Added new entry for " + name + " without a number ");
                        changed = true;
                    }
                    else if (oldNumber == null){
                        ui.sendMessage("Added new entry for " + name + " with number " + number);
                        changed = true;
                    }
                    else {
                        ui.sendMessage("Changed entry for " + name + " from " + oldNumber + " to " + number);
                        changed = true;
                    }
                    break;
                case 1:
                    name = ui.getInfo("What is the name?");
                    number = pd.lookupEntry(name);
                    if (name == null) {
                        break;
                    }
                    if (name.equals("")) {
                        ui.sendMessage("You did not enter a name");
                    }
                    else if (number == null) {
                        ui.sendMessage("\"" + name + "\" is not in the directory");
                    }
                    else if (number.equals("")) {
                        ui.sendMessage(name + " does not have a number");
                    }
                    else if (number.equals("null")) {
                        pd.removeEntry(name);
                        ui.sendMessage("\"" + name + "\" is not in the directory");
                    }
                    else {
                        ui.sendMessage(name + "'s number: " + number);
                    }
                    break;
                case 2:
                    name = ui.getInfo("What is the name?");
                    number = pd.removeEntry(name);
                    if (name == null) {
                        break;
                    }
                    if (number == null) {
                        ui.sendMessage("\"" + name + "\" is not in the directory");
                    }
                    else if (number.equals("null")) {
                        ui.sendMessage("\"" + name + "\" is not in the directory");
                    }
                    else if (name.equals("")) {
                        ui.sendMessage("You did not enter a name");
                    }
                    else if (number.equals("")) {
                        ui.sendMessage(name + " does not have a number, is now removed");
                        changed = true;
                    }
                    else {
                        ui.sendMessage(name + "'s number: " + number + ", is now removed");
                        changed = true;
                    }
                    break;
                case 3:
                    pd.save();
                    changed = false;
                    break;
                case 4:
                    if (changed) {
                        name = ui.getInfo("Are you sure you want to exit without saving?\nClick \"ok\" " +
                                "to exit or click cancel to save and exit");
                        number = pd.removeEntry(name);
                        if (name == null) {
                            break;
                        }
                    }
                    return;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fn = "csc220.txt";
        //PhoneDirectory pd = new ArrayBasedPD();
        PhoneDirectory pd = new SortedPD();
        UserInterface ui = new GUI("Phone Directory");
        //UserInterface ui = new TestUI("Phone Directory");
        processCommands(fn, ui, pd);
    }
}
