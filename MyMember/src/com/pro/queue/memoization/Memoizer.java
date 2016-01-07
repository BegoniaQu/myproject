package com.pro.queue.memoization;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memoizer<A, V> implements Computable<A, V> {

	
	private final ConcurrentHashMap<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
	
	private final Computable<A,V> c;
	
	public Memoizer(Computable<A,V> c){
		this.c = c;
	}
	
	@Override
	public  V compute(final A arg) throws InterruptedException { //帮助计算并缓存结果
		while(true){
			Future<V> f = cache.get(arg);
			if(f == null){
				FutureTask<V> ft = new FutureTask<>(new Callable<V>() {
					@Override
					public V call() throws InterruptedException {
						return c.compute(arg);
					}
				});
				f = cache.putIfAbsent(arg, ft);  //确保原子性
				if(f == null){
					f = ft;
					ft.run();
				}
			}
			try {
				return f.get();
			} catch (CancellationException e) {
				cache.remove(arg,f);
			}catch (ExecutionException e) {
				throw launderThrowable(e.getCause());
			}
		}
	}

	
	
	private  RuntimeException launderThrowable(Throwable t){
		if(t instanceof RuntimeException){
			return (RuntimeException)t;
		}
		else if(t instanceof Error){
			throw (Error)t;
		}
		else {
			throw new IllegalStateException("Not unchecked",t); 
		}
			
	}
	
	
}
