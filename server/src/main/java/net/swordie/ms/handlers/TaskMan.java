package net.swordie.ms.handlers;

import net.swordie.ms.util.FileoutputUtil;
import net.swordie.ms.util.Randomizer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskMan {

    private static final Logger log = LogManager.getLogger(TaskMan.class);

    private ScheduledThreadPoolExecutor scheduler;
    private static final AtomicInteger threadNum = new AtomicInteger(0);
    protected String name, file;

    private ScheduledThreadPoolExecutor getScheduler() {
        return scheduler;
    }

    public void start() {
        if (scheduler != null && !scheduler.isShutdown() && !scheduler.isTerminated()) {
            return;
        }
        log.info("Started " + name + " task.");
        file = "Log_" + name + "_Exception.txt";
        scheduler = new ScheduledThreadPoolExecutor(5, new EventThreadFactory());
        scheduler.setKeepAliveTime(10, TimeUnit.MINUTES);
        //scheduler.setMaximumPoolSize(Runtime.getRuntime().availableProcessors());
        scheduler.allowCoreThreadTimeOut(true);
        scheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
    }

    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    public class EventThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber2 = new AtomicInteger(0);
        private final String tname;

        public EventThreadFactory() {
            tname = name + Randomizer.nextInt();
        }

        @Override
        public Thread newThread(Runnable r) {
            final Thread t = new Thread(r);
            t.setName(tname + "-W-" + threadNum.getAndIncrement() + "-" + threadNumber2.getAndIncrement());
            return t;
        }
    }

    /**
     * Adds and returns an event that executes after a given delay.
     *
     * @param callable The method that should be called
     * @param delay    The delay (in ms) after which the call should start
     * @param <V>      Return type of the given callable
     * @return The created event (ScheduledFuture)
     */
    public <V> ScheduledFuture<V> addEvent(Callable<V> callable, long delay) {
        return addEvent(wrapCallable(callable, file), delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Adds and returns an event that executes after a given delay.
     *
     * @param callable The method that should be called
     * @param delay    The delay after which the call should start
     * @param timeUnit The time unit of the delay
     * @param <V>      The return type of the given callable
     * @return The created event (ScheduledFuture)
     */
    public <V> ScheduledFuture<V> addEvent(Callable<V> callable, long delay, TimeUnit timeUnit) {
        if (scheduler == null) {
            return null;
        }
        return scheduler.schedule(wrapCallable(callable, file), delay, timeUnit);
    }


    /**
     * Adds and returns an event that executes after a given delay.
     *
     * @param runnable The method that should be run
     * @param delay    The delay (in ms) after which the call should start
     * @return The created event (ScheduledFuture)
     */
    public ScheduledFuture addEvent(Runnable runnable, long delay) {
        return addEvent(wrapRunnable(runnable, file), delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Adds and returns an event that executes after a given delay.
     *
     * @param runnable The method that should be run
     * @param delay    The delay after which the call should start
     * @param timeUnit The time unit of the delay
     * @return The created event (ScheduledFuture)
     */
    public ScheduledFuture addEvent(Runnable runnable, long delay, TimeUnit timeUnit) {
        if (scheduler == null) {
            return null;
        }
        return scheduler.schedule(wrapRunnable(runnable, file), delay, timeUnit);
    }

    /**
     * Adds and returns an event that executes after a given initial delay, and then after every delay.
     * See https://stackoverflow.com/questions/24649842/scheduleatfixedrate-vs-schedulewithfixeddelay for difference
     * between this method and addFixedDelayEvent.
     *
     * @param runnable     The method that should be run
     * @param initialDelay The time that it should take before the first execution should start
     * @param delay        The time it should (in ms) take between the start of execution n and execution n+1
     * @return The created event (ScheduledFuture)
     */
    public ScheduledFuture addFixedRateEvent(Runnable runnable, long initialDelay, long delay) {
        return addFixedRateEvent(wrapRunnable(runnable, file), initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Adds and returns an event that executes after a given initial delay, and then after every delay.
     * See https://stackoverflow.com/questions/24649842/scheduleatfixedrate-vs-schedulewithfixeddelay for difference
     * between this method and addFixedDelayEvent.
     *
     * @param runnable     The method that should be run
     * @param initialDelay The time that it should take before the first execution should start
     * @param delay        The time it should (in ms) take between the start of execution n and execution n+1
     * @param executes     The amount of times the
     * @return The created event (ScheduledFuture)
     */
    public ScheduledFuture addFixedRateEvent(Runnable runnable, long initialDelay, long delay, int executes) {
        ScheduledFuture sf = addFixedRateEvent(runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
        addEvent(() -> sf.cancel(false), 10 + initialDelay + delay * executes);
        return sf;
    }

    /**
     * Adds and returns an event that executes after a given initial delay, and then after every delay.
     * See https://stackoverflow.com/questions/24649842/scheduleatfixedrate-vs-schedulewithfixeddelay for difference
     * between this method and addFixedDelayEvent.
     *
     * @param runnable     The method that should be run
     * @param initialDelay The time that it should take before the first execution should start
     * @param delay        The time it should take between the start of execution n and execution n+1
     * @param timeUnit     The time unit of the delays
     * @return The created event (ScheduledFuture)
     */
    public ScheduledFuture addFixedRateEvent(Runnable runnable, long initialDelay, long delay, TimeUnit timeUnit) {
        if (scheduler == null) {
            return null;
        }
        return scheduler.scheduleAtFixedRate(wrapRunnable(runnable, file), initialDelay, delay, timeUnit);
    }

    /**
     * Adds and returns an event that executes after a given initial delay, and then after every delay after the task has finished.
     * See https://stackoverflow.com/questions/24649842/scheduleatfixedrate-vs-schedulewithfixeddelay for difference
     * between this method and addFixedDelayEvent.
     *
     * @param runnable     The method that should be run
     * @param initialDelay The time that it should take before the first execution should start
     * @param delay        The time it should (in ms) take between the start of execution n and execution n+1
     * @return The created event (ScheduledFuture)
     */
    public ScheduledFuture addFixedDelayEvent(Runnable runnable, long initialDelay, long delay) {
        return addFixedDelayEvent(wrapRunnable(runnable, file), initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Adds and returns an event that executes after a given initial delay, and then after every delay.
     * See https://stackoverflow.com/questions/24649842/scheduleatfixedrate-vs-schedulewithfixeddelay for difference
     * between this method and addFixedDelayEvent.
     *
     * @param runnable     The method that should be run
     * @param initialDelay The time that it should take before the first execution should start
     * @param delay        The time it should take between the start of execution n and execution n+1
     * @param timeUnit     The time unit of the delay
     * @return The created event (ScheduledFuture)
     */
    public ScheduledFuture addFixedDelayEvent(Runnable runnable, long initialDelay, long delay, TimeUnit timeUnit) {
        if (scheduler == null) {
            return null;
        }
        return scheduler.scheduleWithFixedDelay(wrapRunnable(runnable, file), initialDelay, delay, timeUnit);
    }

    private Runnable wrapRunnable(Runnable command, String file) {
        return new LogOnExceptionRunnable(command, file);
    }

    private <V> Callable<V> wrapCallable(Callable<V> command, String file) {
        return new LogOnExceptionCallable<>(command, file);
    }

    private static class LogOnExceptionRunnable implements Runnable {

        Runnable r;
        String file;

        public LogOnExceptionRunnable(final Runnable r, final String file) {
            this.r = r;
            this.file = file;
        }

        @Override
        public void run() {
            try {
                r.run();
            } catch (Exception e) {
                log.error(String.format("error in executing: %s. It will no longer be run!", r));
                e.printStackTrace();
                FileoutputUtil.outputFileError(file, e);
                throw new RuntimeException(e);
            }
        }
    }

    private static class LogOnExceptionCallable<V> implements Callable<V> {
        Callable<V> callable;
        String file;

        public LogOnExceptionCallable(final Callable<V> callable, final String file) {
            this.callable = callable;
            this.file = file;
        }

        @Override
        public V call() throws Exception {
            try {
                return callable.call();
            } catch (Exception e) {
                log.error(String.format("error in executing: %s. It will no longer be run!", callable));
                e.printStackTrace();
                FileoutputUtil.outputFileError(file, e);
                throw new RuntimeException(e);
            }
        }
    }

    public static final class FieldUpdate extends TaskMan {
        private static final FieldUpdate instance = new FieldUpdate();

        private FieldUpdate() {
            name = "Field-Update";
        }

        public static FieldUpdate getInstance() {
            return instance;
        }
    }
}