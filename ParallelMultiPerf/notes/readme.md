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


## ParallelStreams

### Order
The order of elements in the output of a streamed calculation depends upon the spliterator implementation of the dataStructure/collection being used. 
For Example: ArrayList's spliterator is ordered, so stream processing of an arraylist returns elements in the same order.
However, A Set has no ordering, neither does it's spliterator, hence, the output from a stream operation is random.

### Performance
1. The choice of datastructure used in the stream operation contributes to speed. Example: A stream opeartion on an ArrayList will be faster than LinkedList. (empirically seen, reason unsure)

### Points to note
1. Careful with using reduce() with parallel streams. The identity must be correct, as it gets applied to every chunk of parallel processing, and can potentially lead to incorrect results


## CompletableFuture

### QuickPoints

- the 'async' versions of methods gives provisions to run the function on new threads. It also has overloaded methods that take in a threadpool to run the functions in, 
instead of the common forkjoin pool. Only use when visible slowness due to threads blocking up. Otherwise, recommended to use non-async methods, to save on thread context switch overhead

- `runAsync`/`supplyAsync` -> methods to use to start off the CompletableFuture, by providing either a `runnable` or a `supplier` respectively. Since most of the time, we intend to chain this call to some further processing, 
better to use `supplier` as `runnable` returns void.

### Chaining 

- `thenApply` -> applies a value to the result of the previous completionStage. It is like `map` from streams api : `CompletableFuture -> Value`
- `thenCompose` -> feeds/pipes the result of the previous completionStage to another CompletableFuture. It is like `flatmap` of the streams api :  `CompletableFuture -> CompletableFuture`
- `thenCombine` -> useful for chaining **Independent** futures together, and runs them in parallel

On the note of running independent Futures parallely, below is a simple idiom to collect their end results:

```
  String combined = Stream.of(future1, future2, future3)
      .map(CompletableFuture::join)                     // wait for all of them to finish.
      .collect(Collectors.joining(" "));
```

To Note: there is a `allOf()` method available, but that returns `CompletableFuture<Void>`, i.e void, so cannot be chained, so not that useful. Hence we prefer `join()`.

### Error Handling:

https://mincong.io/2020/05/30/exception-handling-in-completable-future/
  