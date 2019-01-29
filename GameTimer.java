/* This class creates a database using SQlite. The database hold the points and scores of the player
 * 
 * Name: Ahmed Mansour
 * Date: 12/10/18
 */
package JavaFXProject;

public class GameTimer extends Thread {
	
	private int currentTime;
	private boolean finished;
	
	public void run() {
		try {
			System.out.println("Timer starting...");
			for(currentTime = 5; currentTime > 0; currentTime--) {
				Thread.sleep(1000);
				System.out.println("Timer at: " + currentTime);
			}
			System.out.println("Timer finished!");
			finished = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean isFinished() { //checks if timer is finished
		return finished;
		
	}
	public String getCurrentTime() {
		String timeString;
		
		timeString = currentTime + "";
		return timeString;
	}
}
