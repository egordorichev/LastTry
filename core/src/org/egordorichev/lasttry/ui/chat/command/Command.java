package org.egordorichev.lasttry.ui.chat.command;

import org.egordorichev.lasttry.util.Log;

/**
 * Command with a name and handle. Single method for execution.
 */
public abstract class Command {
    /**
     * Single instance for an empty string array. This is optimal compared to
     * making a new string array per invocation of {@link #onRun(String[])}.
     */
    private static final String[] EMPTY_ARGS = new String[0];
    /**
     * Handle, what the user enters
     */
    private final String handle;
    /**
     * Handle, what the user enters.
     */
    private final String desc;
    /**
     * Category, used for sorting.
     */
    private final CMDCategory category;

    public Command(String handle, String desc, CMDCategory category) {
        this.handle = handle;
        this.desc = desc;
        this.category = category;
    }

    /**
     * Returns the command's handle.
     * 
     * @return String
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Returns the command's description.
     * 
     * @return String
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Returns the command's category.
     * 
     * @return CMDCategory
     */
    public CMDCategory getCategory() {
        return category;
    }

    /**
     * {@link #onRun(String[]) Runs the command} with no arguments.
     */
    protected final void onRun() {
        onRun(EMPTY_ARGS);
    }

    /**
     * Called when the command is run.
     * 
     * @param args
     *            Arguments for the command's execution.
     */
    public abstract void onRun(String[] args);

    /**
     * Called when the {@link #onRun(String[]) execution} encounters an error.
     * 
     * @param e
     *            Exception thrown.
     */
    public void onFail(Exception e) {
        if (e instanceof NumberFormatException) {
            Log.error("Failed to parse numeric input");
        } if (e instanceof IndexOutOfBoundsException) {
            Log.error("Failed IOOBE");
        } else {
            e.printStackTrace();
        }
    }

    /**
     * Generates a string warning that the command entered was not given enough
     * arguments.
     * 
     * @param argFmt
     *            The proper argument format.
     * @return Error message.
     */
    protected String lackArgsErr(String argFmt) {
        return "Not enough arguments:\n" + this.getHandle() + " " + argFmt;
    }
}
