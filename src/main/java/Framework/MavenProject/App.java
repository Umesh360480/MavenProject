package Framework.MavenProject;
import resources.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {    	
        System.out.println(ReadProperty.getBaseURL() );
        System.out.println(ReadProperty.getBrowserName().equalsIgnoreCase(browserName.Firefox.toString()));
        System.out.println(ReadProperty.getFirefoxExecutablePath() );
        System.out.println(ReadProperty.getTestDataExcelPath());
    }
}
