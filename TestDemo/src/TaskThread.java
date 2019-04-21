public class TaskThread extends Thread {


    private final ScheduledMultiThreadRequest mExtractService;
    private final String mRequestJson;

    public TaskThread(ScheduledMultiThreadRequest request, String requestJson) {
        this.mExtractService = request;
        this.mRequestJson = requestJson;
        Thread.currentThread().setName(requestJson);
    }

    @Override
    public void run() {
        super.run();

        mExtractService.putData(mRequestJson + System.currentTimeMillis());
    }
}
