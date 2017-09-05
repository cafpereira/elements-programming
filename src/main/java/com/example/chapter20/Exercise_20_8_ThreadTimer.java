package com.example.chapter20;

import java.text.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        timer.start();
        sleep(2);

        TestUser1 user1 = new TestUser1(timer);
        user1.start();

        TestUser2 user2 = new TestUser2(timer);
        user2.start();

        user1.join();
        user2.join();
    }

    private static void sleep(int secs){
        try {
            Thread.sleep(secs * Timer.SEC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Timer {
    static long SEC = 1000L;
    static long HOUR = 3600 * SEC;

    PriorityQueue<Cron> minHeap;
    Map<String, Cron> tasks;
    Dispatcher dispatcher;

    public synchronized void add(Date start, Thread thread) {
        System.out.println("adding: " + thread.getName());
        Cron task = new Cron(start.getTime(), thread);
        tasks.put(thread.getName(), task);
        minHeap.add(task);
        dispatcher.interrupt();
    }

    public synchronized void cancel(String name) {
        System.out.println("cancelling: " + name);
        Cron task = tasks.get(name);
        if (task != null) {
            delete(task);
            dispatcher.interrupt();
        }
    }

    public synchronized void delete(Cron cron) {
        minHeap.remove(cron);
        tasks.remove(cron.getThread().getName());
    }

    public synchronized PriorityQueue<Cron> getMinHeap() {
        return minHeap;
    }

    public void start() throws Exception {
        minHeap = new PriorityQueue<>(16, Comparator.comparing(Cron::getScheduledAt));
        tasks = new HashMap<>();
        dispatcher = new Dispatcher(this);
        dispatcher.start();
    }
}

class Cron {
    final Long scheduledAt;
    final Thread thread;

    Cron(Long scheduledAt, Thread thread) {
        this.scheduledAt = scheduledAt;
        this.thread = thread;
    }

    public Long getScheduledAt() {
        return scheduledAt;
    }

    public Thread getThread() {
        return thread;
    }
}

class Dispatcher extends Thread {
    final Timer timer;

    Dispatcher(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        while (true) {
            Long wait = 7 * 24 * Timer.HOUR;
            synchronized (timer) {
                while (!timer.getMinHeap().isEmpty()) {
                    Cron next = timer.getMinHeap().peek();
                    wait = next.getScheduledAt() - System.currentTimeMillis();
                    if (wait <= 0) {
                        // Launch thread
                        timer.delete(next);
                        next.getThread().start();
                    } else {
                        break;
                    }
                }
            }
            try {
                long now = System.currentTimeMillis();
                System.out.println("dispatcher going to sleep until: " + new Date(now + wait));
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                System.out.println("dispatcher awaken...");
            }
        }
    }
}

class TestUser1 extends Thread {
    final SimpleDateFormat sdf = new SimpleDateFormat();
    final Timer timer;

    TestUser1(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        sleep(4);

        Date tue = parse("05-09-2017 12:00");
        timer.add(tue, new Thread("Tuesday"));
        sleep(16);

        timer.cancel("Wednesday");
    }

    private Date parse(String str) {
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void sleep(int secs){
        try {
            Thread.sleep(secs * Timer.SEC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TestUser2 extends Thread {
    final SimpleDateFormat sdf = new SimpleDateFormat();
    final Timer timer;

    TestUser2(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        sleep(2);

        Date wed = parse("06-09-2017 12:00");
        timer.add(wed, new Thread("Wednesday"));
        sleep(8);

        timer.cancel("Tuesday");
    }

    private Date parse(String str) {
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void sleep(int secs){
        try {
            Thread.sleep(secs * Timer.SEC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
