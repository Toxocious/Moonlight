package net.swordie.ms.handlers;

import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Sjonnie
 * Created on 7/27/2018.
 *
 * Class that wraps every callable/runnable inside a try/catch block to ensure that Exceptions are caught.
 * http://code.nomad-labs.com/2011/12/09/mother-fk-the-scheduledexecutorservice/
 */
public class CatchingScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    private static final Logger log = Logger.getLogger(CatchingScheduledThreadPoolExecutor.class);

    public CatchingScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit timeUnit) {
        return super.schedule(wrapCallable(callable), delay, timeUnit);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return super.schedule(wrapRunnable(command), delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return super.scheduleAtFixedRate(wrapRunnable(command), initialDelay, period, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return super.scheduleWithFixedDelay(wrapRunnable(command), initialDelay, delay, unit);
    }

    private Runnable wrapRunnable(Runnable command) {
        return new LogOnExceptionRunnable(command);
    }

    private <V> Callable<V> wrapCallable(Callable<V> command) {
        return new LogOnExceptionCallable<>(command);
    }

    private class LogOnExceptionRunnable implements Runnable {
        private Runnable runnable;

        public LogOnExceptionRunnable(Runnable runnable) {
            super();
            this.runnable = runnable;
        }

        @Override
        public void run() {
            try {
                runnable.run();
            } catch (Exception e) {
                log.error(String.format("error in executing: %s. It will no longer be run!", runnable));
                e.printStackTrace();

                // and re throw it so that the Executor also gets this error so that it can do what it would
                // usually do
                throw new RuntimeException(e);
            }
        }
    }

    private class LogOnExceptionCallable<V> implements Callable<V> {
        private Callable<V> callable;

        public LogOnExceptionCallable(Callable<V> callable) {
            super();
            this.callable = callable;
        }

        @Override
        public V call() throws Exception {
            try {
                return callable.call();
            } catch (Exception e) {
                log.error(String.format("error in executing: %s. It will no longer be run!", callable));
                e.printStackTrace();

                // and re throw it so that the Executor also gets this error so that it can do what it would
                // usually do
                throw new RuntimeException(e);
            }
        }
    }
}
