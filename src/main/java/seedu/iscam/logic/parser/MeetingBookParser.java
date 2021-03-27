package seedu.iscam.logic.parser;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.iscam.logic.commands.AddMeetingCommand;
import seedu.iscam.logic.commands.Command;
import seedu.iscam.logic.commands.DeleteMeetingCommand;
import seedu.iscam.logic.commands.DoneMeetingCommand;
import seedu.iscam.logic.commands.EditMeetingCommand;
import seedu.iscam.logic.commands.ExitCommand;
import seedu.iscam.logic.commands.FindMeetingsCommand;
import seedu.iscam.logic.commands.HelpCommand;
import seedu.iscam.logic.commands.ListMeetingsCommand;
import seedu.iscam.logic.commands.RelocateMeetingCommand;
import seedu.iscam.logic.commands.RescheduleMeetingCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MeetingBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        /*case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);*/

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case FindMeetingsCommand.COMMAND_WORD:
            return new FindMeetingsCommandParser().parse(arguments);

        case ListMeetingsCommand.COMMAND_WORD:
            return new ListMeetingsCommand();

        case RescheduleMeetingCommand.COMMAND_WORD:
            return new RescheduleMeetingCommandParser().parse(arguments);

        case RelocateMeetingCommand.COMMAND_WORD:
            return new RelocateMeetingCommandParser().parse(arguments);

        case DoneMeetingCommand.COMMAND_WORD:
            return new DoneMeetingCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}