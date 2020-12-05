## Thread Coordination

### Catching Interrupts
1. if the methods in the thread fires Interrupted Exception, then we can simply wrap it in a try-catch, and interrupt it from another thread
2. If method code doesn't throw Interrupted Exception, but we would still like graceful termination, we need to manually check if the thread has been interrupted
3. If we do not need the code to gracefully terminate when interrupted, we can set the thread to be a daemon thread. These are unceremoniously killed when the thread executes.

### Joining Threads

- Easy for the main thread to wait for completion of child threads
- However, any of the joined threads can end up becoming long running, causing the main thread to wait even if other childs have executed
    1. remedy 1: give a max wait time in millis, via `thread.join(time)` this means the main thread will wait only `time` ms for the thread to complete. 
                  While it will gives the results of the threads that completed in the given time, it will not terminate because the long running thread is still running. 
                  Check `pogrebinsky.ThreadJoinDemo.java`
    2. remedy 2: set the child thread to `daemon` via `thread.setDaemon(true)`. Thus, when the main thread terminates, these will also be terminated, and we get timely completion of `main`
    3. remedy 3: Using #2 of `Catching Interrupts` explained above, we can write custom logic for how to interrupt and catch long running threads
    
## Performance 

- Latency: amount of time to complete a task. unit: time
- Throughput: amount of tasks completed in given/unit time. unit: tasks/time
- lower latency === higher throughput

### Cost of parallelization & Aggregation

Total cost is a sum of the below: 
1. Breaking a task into multiple smaller ones
2. Creating threads, passing tasks to the created threads
3. Time gap between `thread.start()` and thread getting scheduled
4. Time to wait until the last of the spun-up threads complete, and signals the aggregating thread (ex, `main`)
5. Time for `main` to get the `signal`
6. Aggregation of thread results into a single artifact