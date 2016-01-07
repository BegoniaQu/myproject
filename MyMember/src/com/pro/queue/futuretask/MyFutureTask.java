package com.pro.queue.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.mapinfo.tab.e;

/**
 * FutureTask是Future和Callable的结合体。传统的代码是这样写的 Future f = executor.submit(new
 * Callable());
 * 然后通过Future来取得计算结果。但是，若开启了多个任务，我们无从知晓哪个任务最先结束，因此，若要实现“当某任务结束时，立刻做一些事情
 * ，例如记录日志”这一功能，就需要写一些额外的代码。
 * 
 * FutureTask正是为此而存在，他有一个回调函数protected void
 * done()，当任务结束时，该回调函数会被触发。因此，只需重载该函数，即可实现在线程刚结束时就做一些事情。
 * 
 * 一般FutureTask多用于耗时的计算
 * 
 * FutureTask提供了对 Future 的基本实现。仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get
 * 方法。一旦计算完成，就不能再重新开始或取消计算。 可使用 FutureTask 包装 Callable 或 Runnable 对象。因为
 * FutureTask 实现了 Runnable，所以可将 FutureTask 提交给 Executor 执行。
 * 
 * 
 * @author Administrator
 * 
 */
public class MyFutureTask extends FutureTask<String> {

	public MyFutureTask(Callable<String> callable) {
		super(callable);
	}

	@Override
	protected void done() {
		System.out.println(Thread.currentThread().getName() + " 线程执行完毕！~");
	}

}
