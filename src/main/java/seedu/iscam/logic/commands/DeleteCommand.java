package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.events.DeleteClientEvent;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;

/**
 * Deletes a client identified using it's displayed index from the iscam book.
 */
public class DeleteCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CLIENT_SUCCESS = "Deleted Client: %1$s";

    private final Client clientToDelete;
    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        clientToDelete = null;
        this.targetIndex = targetIndex;
    }

    public DeleteCommand(Client client) {
        clientToDelete = client;
        targetIndex = null;
    }

    public Index getTargetIndex() {
        return targetIndex;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Client> lastShownList = model.getFilteredClientList();
        Client clientToBeDeleted;

        if (clientToDelete != null) {
            clientToBeDeleted = clientToDelete;

        } else if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);

        } else {
            clientToBeDeleted = lastShownList.get(targetIndex.getZeroBased());
        }

        model.deleteClient(clientToBeDeleted);
        return new CommandResult(String.format(MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
