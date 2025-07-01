package org.apache.commons.p000io;

/* loaded from: Titan.jar:org/apache/commons/io/ThreadMonitor.class */
class ThreadMonitor implements Runnable {
    private final Thread thread;
    private final long timeout;

    public static Thread start(long timeout) {
        return start(Thread.currentThread(), timeout);
    }

    public static Thread start(Thread thread, long timeout) {
        Thread monitor = null;
        if (timeout > 0) {
            ThreadMonitor timout = new ThreadMonitor(thread, timeout);
            monitor = new Thread(timout, ThreadMonitor.class.getSimpleName());
            monitor.setDaemon(true);
            monitor.start();
        }
        return monitor;
    }

    public static void stop(Thread thread) {
        if (thread != null) {
            thread.interrupt();
        }
    }

    private ThreadMonitor(Thread thread, long timeout) {
        this.thread = thread;
        this.timeout = timeout;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            sleep(this.timeout);
            this.thread.interrupt();
        } catch (InterruptedException e) {
        }
    }

    private static void sleep(long ms) throws InterruptedException {
        long finishAt = System.currentTimeMillis() + ms;
        long remaining = ms;
        do {
            Thread.sleep(remaining);
            remaining = finishAt - System.currentTimeMillis();
        } while (remaining > 0);
    }
}
