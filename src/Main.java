import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	static void handleIssues(String fileName) {
		// read the file
		// String fileName = "g9";
		String line = null;
		String finalResult = "";
		try {

			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String fileData = "";
			while ((line = bufferedReader.readLine()) != null) {
				fileData += line;
			}
			// System.out.println(fileData);
			String splittedItems[] = fileData.split(";");

			int result[] = new int[4];

			for (int i = 0; i < splittedItems.length; i++) {
				Matcher matcher = Pattern.compile("[0-9]+").matcher(
						splittedItems[i]);
				int j = 0;
				while (matcher.find()) {
					// Do something with the matched text
					result[j++] = Integer.parseInt(matcher.group(0));
				}

				int colIndex = result[0] % 10;
				int rowIndex = result[0] / 10;
				int r = result[1] / 16;
				int g = result[2] / 16;
				int b = result[3] / 16;
				finalResult += "LED( " + rowIndex + ", " + colIndex + ", " + r
						+ ", " + g + ", " + b + ");\n";
			}

			System.out.println(finalResult);

			try {
				String newFileName = "";
				if (fileName.indexOf(".") > 0)
					newFileName = fileData.substring(0,
							fileName.lastIndexOf("."));
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName
						+ "-res.txt"));
				out.write(finalResult);
				out.close();
			} catch (IOException e) {
				System.out.println("Exception ");
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		File folder = new File("./");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if(listOfFiles[i].getName().endsWith(".txt")){
					System.out.println(listOfFiles[i].getName());

				handleIssues(listOfFiles[i].getName());
				}
			} else if (listOfFiles[i].isDirectory()) {
//				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		
	}
}
