package bd.lab;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) {
		LoginFrame lg;
		DBInterface.init();
		lg = new LoginFrame();	
		
		Timer t = new Timer( );
		t.scheduleAtFixedRate(new TimerTask() {
		    @Override
		    public void run() {
		    	if (lg.logged) {
		    		lg.close();
		    		MainFrame mf = new MainFrame();
		    		this.cancel();
		    	}
		    }
		}, 200, 100);
		
		
		
	}
}

