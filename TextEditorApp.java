class TextState {
    String content;
    TextState next;
    TextState prev;

    public TextState(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }
}

class TextEditor {
    private TextState currentState;
    private int maxHistory;
    private int historySize;

    public TextEditor(int maxHistory) {
        this.maxHistory = maxHistory;
        this.historySize = 0;
        this.currentState = null;
    }

    public void addState(String content) {
        TextState newState = new TextState(content);
        if (currentState != null) {
            currentState.next = newState;
            newState.prev = currentState;
        }
        currentState = newState;

        if (historySize == maxHistory) {
            currentState.prev.next = null;
            currentState.prev = null;
        } else {
            historySize++;
        }
    }

    public String undo() {
        if (currentState != null && currentState.prev != null) {
            currentState = currentState.prev;
            return currentState.content;
        }
        return null;
    }

    public String redo() {
        if (currentState != null && currentState.next != null) {
            currentState = currentState.next;
            return currentState.content;
        }
        return null;
    }

    public void displayCurrentState() {
        if (currentState != null) {
            System.out.println("Current State: " + currentState.content);
        }
    }
}

// Example usage
public class TextEditorApp {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(5);
        editor.addState("Hello");
        editor.addState("Hello, world!");
        editor.displayCurrentState();
        editor.undo();
        editor.displayCurrentState();
        editor.redo();
        editor.displayCurrentState();
    }
}
