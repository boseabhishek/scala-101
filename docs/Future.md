https://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html

```scala
/** Contains factory methods for creating execution contexts.
 */
object ExecutionContext {
  /**
   * The explicit global `ExecutionContext`. Invoke `global` when you want to provide the global
   * `ExecutionContext` explicitly.
   *
   * The default `ExecutionContext` implementation is backed by a work-stealing thread pool.
   * It can be configured via the following [[scala.sys.SystemProperties]]:
   *
   * `scala.concurrent.context.minThreads` = defaults to "1"
   * `scala.concurrent.context.numThreads` = defaults to "x1" (i.e. the current number of available processors * 1)
   * `scala.concurrent.context.maxThreads` = defaults to "x1" (i.e. the current number of available processors * 1)
   * `scala.concurrent.context.maxExtraThreads` = defaults to "256"
   *
   * The pool size of threads is then `numThreads` bounded by `minThreads` on the lower end and `maxThreads` on the high end.
   *
   * The `maxExtraThreads` is the maximum number of extra threads to have at any given time to evade deadlock,
   * see [[scala.concurrent.BlockContext]].
   *
   * @return the global `ExecutionContext`
   */
  def global: ExecutionContextExecutor = Implicits.global.asInstanceOf[ExecutionContextExecutor]

  object Implicits {
    /**
     * The implicit global `ExecutionContext`. Import `global` when you want to provide the global
     * `ExecutionContext` implicitly.
     *
     * The default `ExecutionContext` implementation is backed by a work-stealing thread pool. By default,
     * the thread pool uses a target number of worker threads equal to the number of
     * [[https://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#availableProcessors-- available processors]].
     */
    implicit lazy val global: ExecutionContext = impl.ExecutionContextImpl.fromExecutor(null: Executor)
  }
  
```
