package com.example.demo.service.localcache;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步刷新CacheLoader
 */
public abstract class RefreshAsyncCacheLoader<K, V> extends CacheLoader<K, V> {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(RefreshAsyncCacheLoader.class);

    private ExecutorService worker = Executors.newSingleThreadExecutor();

    @Override
    public ListenableFuture<V> reload(final K key, final V oldValue) throws Exception {
        ListenableFutureTask<V> task = ListenableFutureTask.create(new Callable<V>() {
            public V call() {
                try {
                    if (Logger.isDebugEnabled()) {
                        Logger.debug("reload key:{}", key);
                    }
                    V load = load((K) key);
//                    Logger.info("thread call()==>{}",Thread.currentThread().getName());
                    if (load != null) {
                        return load;
                    }
                } catch (Exception e) {
                    Logger.error("RefreshAsyncCacheLoader exception", e);
                }
                return oldValue;
            }
        });
        //这里进行数据加载，task进行返回，
        worker.execute(task);
//        sema
        return task;
    }

}
