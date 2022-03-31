package seedu.command;

import java.util.ArrayList;

/**
 * Subclass of Command. Handles deleting of equipment from equipmentInventory.
 */
public class DeleteCommand extends Command {
    private final ArrayList<String> commandStrings;
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_DESCRIPTION = ": Deletes the equipment with the specified serial number. "
            + System.lineSeparator()
            + "Parameters: s/SERIAL_NUMBER" + System.lineSeparator()
            + "Example: "
            + "delete s/SM57-1";

    /**
     * constructor for DeleteCommand. Initialises successMessage and usageReminder from Command.
     *
     * @param commandStrings parsed user input which contains details of equipment to be deleted
     */
    public DeleteCommand(ArrayList<String> commandStrings) {
        this.commandStrings = commandStrings;
        successMessage = "Equipment successfully deleted: %1$s, serial number %2$s";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }

    /**
     * Deletes equipment specified by serial number.
     *
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute() {
        String equipmentName;
        try {
            equipmentName = equipmentManager.getEquipmentList().get(commandStrings.get(0)).getItemName();
        } catch (NullPointerException e) {
            return new CommandResult(INVALID_SERIAL_NUMBER);
        }

        equipmentManager.deleteEquipment(commandStrings.get(0));

        return new CommandResult(String.format(successMessage, equipmentName, commandStrings.get(0)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return this.commandStrings.equals(otherDeleteCommand.commandStrings);
    }
}
