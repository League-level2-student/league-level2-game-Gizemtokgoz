import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
	public int countdown;
	static Timer timer;

	public void Init(int timeAllowed) {
		int delay = 1000;
		int period = 1000;
		timer = new Timer();
		countdown = timeAllowed;
		System.out.println(countdown);
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				System.out.println(setInterval());

			}
		}, delay, period);
	}

	private final int setInterval() {
		if (countdown == 1)
		{
			timer.cancel();
		}
		return --countdown;
	}
	
	public void Stop() {
		timer.cancel();
	}
}