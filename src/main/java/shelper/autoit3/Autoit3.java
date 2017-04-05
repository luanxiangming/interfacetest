package shelper.autoit3;

import org.apache.commons.io.FileUtils;
import shelper.environment.Environment;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Autoit3 {
	public static void run(String command) {
		try {
			try {
				String filename = command.split(" ")[0];
				File endtag = new File(Environment.get("Selenium.auto3")
						+ filename + ".end");
				if (endtag.exists()) {
					FileUtils.forceDelete(endtag);
				}
				Runtime.getRuntime()
						.exec("cmd /C start AutoIt3.exe " + command, null,
								new File(Environment.get("Selenium.auto3")))
						.waitFor();
				int count = 0;
				while (!endtag.exists()
						&& count < Integer.parseInt(Environment
								.get("Selenium.auto3.timeout"))) {

					Thread.sleep(1000);
					count++;
				}
				if (endtag.exists()) {
					FileUtils.forceDelete(endtag);
					Logger.getLogger(Autoit3.class.getName())
							.log(Level.INFO,
									"Run Autoit3 script" + command
											+ ". endtag removed");
				} else {
					Logger.getLogger(Autoit3.class.getName()).log(Level.SEVERE,
							null,
							"Run Autoit3 script" + command + ". no endtag");
				}

			} catch (InterruptedException ex) {
				Logger.getLogger(Autoit3.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		} catch (IOException ex) {
			Logger.getLogger(Autoit3.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	public static void runAsync(String command) {
		try {
			Runtime.getRuntime()
					.exec("cmd /C start AutoIt3.exe " + command, null,
							new File(Environment.get("Selenium.auto3")))
					.waitFor();
		} catch (Exception ex) {
			Logger.getLogger(Autoit3.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}
}
