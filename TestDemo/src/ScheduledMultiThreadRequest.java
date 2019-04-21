import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Cdu
 * @discription: 周期性线程池创建多线程
 * @create 2019-04-21 23:57
 */
public class ScheduledMultiThreadRequest {

    public BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
    public TaskThread mTaskThread;


    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    public void extract() {
        mScheduledThreadPoolExecutor.scheduleAtFixedRate(() ->
        {

            int totalPageNum = (int) (Math.random() * 10 + 1);
            //每隔60s执行
            Date date = new Date();
            System.out.println("===current time:" + date.toString());
            System.out.println("taskNum:" + totalPageNum);
            //执行子任务
            executeTaskThread(this, totalPageNum, date.toString());

        }, 0, 1, TimeUnit.SECONDS);
    }

    private static void executeTaskThread(ScheduledMultiThreadRequest scheduledMultiThreadRequestClass, int totalPageNum, String s) {
        for (int i = 0; i < totalPageNum; i++) {
            TaskThread taskThread = new TaskThread(scheduledMultiThreadRequestClass, s);
            taskThread.start();
        }
    }

    public void putData(String data) {
        blockingDeque.add(data);
    }

    public BlockingDeque<String> getQueue() {
        return blockingDeque;
    }

    public static void main(String[] args) {
        ScheduledMultiThreadRequest scheduledMultiThreadRequest = new ScheduledMultiThreadRequest();
        scheduledMultiThreadRequest.extract();
        while (true) {
            BlockingDeque<String> queue = scheduledMultiThreadRequest.getQueue();
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                try {
                    System.out.println("que" + i + " data:" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("get Queue data Error:" + e.getLocalizedMessage());
                }
            }
        }
    }
}