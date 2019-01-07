import java.util.concurrent.TimeUnit;

/**R
 * 线程安全Demo
 *
 * @author Cdu
 * @date 2019-01-07 下午1:28
 */
public class ThreadSafeDemo {

    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException{
        ThreadSafeDemo threadSafeDemo = new ThreadSafeDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (threadSafeDemo.flag){
                    i++;
                }
                System.out.println(i);
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        threadSafeDemo.flag = false;
        System.out.println("置为false");

    }
}
