package model._framework;

// Nota e' importantissimo che perform sia synchronized in Executor!
// Serve per avere un accesso controllato agli oggetti del diagramma delle classi
public final class TaskExecutor {
    private static TaskExecutor theExecutor = new TaskExecutor();

    private TaskExecutor() {
    }

    public synchronized void perform(Task t) {
        t.esegui();
    }

    public synchronized static TaskExecutor getInstance() {
        return theExecutor;
    }
}
