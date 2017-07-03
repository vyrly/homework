package rekrutacjaqa.Utils;

import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTime {
    public String getDateTime(String input) {
        String pattern = "\\w+ \\w+ \\d+, \\d+ \\d+:\\d+ \\w+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        Assert.assertTrue(m.find());
        return m.group(0);
    }

}
