package com.github.leo_scream.java_se_course.unit_07.task_03;

import java.util.Random;

class IntegerSetterGetter extends Thread {

    private final SharedResource resource;
    private final int MAX_TRIES = 3;
    private boolean run;
    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }

    @Override
    public void run() {
        int action;
        try {
            while (run) {
                action = rand.nextInt(1000);
                if (action % 2 == 0) {
                    getIntegersFromResource();
                } else {
                    setIntegersIntoResource();
                }
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;
        synchronized (resource) {
            System.out.println("Поток " + getName() + " хочет извлечь число.");
            number = resource.getELement();
            int tries = 0;
            while (number == null) {
                if (tries++ > MAX_TRIES) return;
                System.out.println("Поток " + getName() + " ждет пока очередь заполнится.");
                resource.wait(100);
                System.out.println("Поток " + getName() + " возобновил работу.");
                number = resource.getELement();
            }
            System.out.println("Поток " + getName() + " извлек число " + number);
        }
    }

    private void setIntegersIntoResource() throws InterruptedException {
        Integer number = rand.nextInt(500);
        synchronized (resource) {
            resource.setElement(number);
            System.out.println("Поток " + getName() + " записал число " + number);
            resource.notify();
        }
    }
}
