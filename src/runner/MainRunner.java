package runner;

import cucumber.api.cli.Main;
import featureCreation.Excel2Feature;

public class MainRunner {

	public static void main(String[] args) throws Throwable {
		String featureFile = Excel2Feature.excelToFeaturConversion(args[0]);
		try {
			Main.main(new String[] {"-g","step.definition",featureFile});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
