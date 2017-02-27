package com.github.leo_scream.java_se_course.unit_02.task_06;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class NuclearSubmarine
{
    public final Engine engine;

    public NuclearSubmarine() {
        engine = new Engine();
        engine.start();
    }

    public class Engine {
        public void start() {
            System.out.println("Nuclear submarine is now sailing");
        }

        public void stop() {
            System.out.println("Engine is stop working but inertia is not " +
                "been canceled");
        }
    }
}
