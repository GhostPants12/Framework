package util;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class TestRunner extends BlockJUnit4ClassRunner {
    private TestListener listener;


    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws InitializationError if the test class is malformed.
     */
    public TestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        listener = new TestListener();
    }

    public void run(final RunNotifier notifier) {
        notifier.addListener(listener);
        super.run(notifier);
    }
}
