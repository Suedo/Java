### Thread Coordination

1. if the methods in the thread fires Interrupted Exception, then we can simply wrap it in a try-catch, and interrupt it from another thread
2. If method code doesn't throw Interrupted Exception, but we would still like graceful termination, we need to manually check if the thread has been interrupted
3. If we do not need the code to gracefully terminate when interrupted, we can set the thread to be a daemon thread. These are unceremoniously killed when the thread executes.