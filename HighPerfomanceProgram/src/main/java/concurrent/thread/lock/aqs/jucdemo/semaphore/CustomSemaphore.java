package main.java.concurrent.thread.lock.aqs.jucdemo.semaphore;

import main.java.concurrent.thread.lock.aqs.CustomAqs;

/**
 * 自定义信号量实现
 *
 * @author Cdu
 * @date 2019-01-19 下午7:59
 */
public class CustomSemaphore {
    CustomAqs aqs = new CustomAqs() {
        @Override
        public int tryAcquireShared() { // 信号量获取， 数量 - 1
            for(;;) {
                int count =  getState().get();
                int n = count - 1;
                if(count <= 0 || n < 0) {
                    return -1;
                }
                if(getState().compareAndSet(count, n)) {
                    return 1;
                }
            }
        }

        @Override
        public boolean tryReleaseShared() { // state + 1
            return getState().incrementAndGet() >= 0;
        }
    };

    /** 许可数量 */
    public CustomSemaphore(int count) {
        aqs.getState().set(count); // 设置资源的状态
    }

    public void acquire() {
        aqs.acquireShared();
    } // 获取令牌

    public void release() {
        aqs.releaseShared();
    } // 释放令牌
}
