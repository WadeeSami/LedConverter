import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		String input = "Tleds[0] = CRGB(0, 0, 0);";
		Matcher matcher = Pattern.compile("[0-9]+").matcher(input);

		while (matcher.find()) {
			// Do something with the matched text
			System.out.println(matcher.group(0));
		}
	}
}
