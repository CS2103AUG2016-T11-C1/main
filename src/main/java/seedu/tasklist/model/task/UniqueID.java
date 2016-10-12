package seedu.tasklist.model.task;


import seedu.tasklist.commons.exceptions.IllegalValueException;

/**
 * Represents a task's Unique ID in the to-do list.
 * Guarantees: immutable; is valid as declared in {@link #isValidUniqueID(String)}
 */
public class UniqueID {
    
    public static final String MESSAGE_UNIQUE_ID_CONSTRAINTS = "Unique ID must be an integer.";
    public static final String UNIQUE_ID_VALIDATION_REGEX = ".+";

    public final String value;

    /**
     * Validates given Unique ID.
     *
     * @throws IllegalValueException if given Unique ID is invalid.
     */
    public UniqueID(String uniqueID) throws IllegalValueException {
        assert uniqueID != null;
        if (!isValidUniqueID(uniqueID)) {
            throw new IllegalValueException(MESSAGE_UNIQUE_ID_CONSTRAINTS);
        }
        this.value = uniqueID;
    }

    /**
     * Returns true if a given string is a valid Unique ID.
     */
    public static boolean isValidUniqueID(String test) {
        return test.matches(UNIQUE_ID_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueID // instanceof handles nulls
                && this.value.equals(((UniqueID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}