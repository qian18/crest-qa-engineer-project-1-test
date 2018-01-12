package crestqapackage;

import java.util.concurrent.TimeUnit;
import java.util.List;	
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


/**
 * This class will contain a series of test skeletons for
 * exercising the following Github features.
 *
 * 1. A test to see if a registered user can successfully log in
 * 2. A test to see if an unregistered user cannot log in
 * 3. A test to see if a registered user can create a new repository
 * 4. A test to see if a registered user cannot create a new repository
 *    when it has the same name as an existing repository.
 *
 */
public class GitHubTest {

  private WebDriver driver;

  private static final String GIT_HUB_URL = "https://github.com"; //"https://github.com/login";

  //Enter the username for the Github account you create here
  private static final String USERNAME = "qian18";

  //Enter the password for the Github account you create here.
  //Note: Do not use any of your real-life passwords!
  private static final String PASSWORD = "Qianjob@2018";

  @Before
  public void setup() {
	  
      //Note： Added System.setProperty() method in setup()
	  System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
	  
	  driver = new ChromeDriver();

	  // And now use this to visit Github
	  driver.get(GIT_HUB_URL);
  }

  /**
   * Test to see if an user can successfully login to Github.
   *
   * @TODO - Complete this test
   */
  @Test
  public void testGitHubLoginSuccess() throws 
  InterruptedException {
	  Thread.sleep(2000);  

	 // Verify if the correct page is launched
	 // String pageTitle = driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[2]/h1")).getText();
     // if (!pageTitle.equals("Sign in to GitHub"))
     // {
     //     System.out.println("Launched the incorrect webpage");
	  //   tearDown();
     // }
	   
	  //click on Sign in link in the right top corner			
	  driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/span/div/a[1]")).click();
	  System.out.println("title of page is: " + driver.getTitle());
	  Thread.sleep(1000); 
	  
	  //Locate the user name text box, clear it first, then send the current user name
	  WebElement userNameTextBox = driver.findElement(By.id("login_field"));
	  userNameTextBox.clear();
	  userNameTextBox.sendKeys(USERNAME);
	  
	//Locate the password text box, clear it first, then send the current password
	  WebElement userPasswordTextBox = driver.findElement(By.id("password")); 
      userPasswordTextBox.clear();
      userPasswordTextBox.sendKeys(PASSWORD);
	  
      //click the Sign In button
	  WebElement userSignInButtonClick = driver.findElement(By.name("commit"));
	  userSignInButtonClick.click();
	  
	  // Wait for authentication process
        Thread.sleep(3000); 
	      
        //Verify "Start a project" button is available in the web page
        String startStr = driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[1]/div/div/a[2]")).getText();
        if (startStr.contains("Start a project")) {
        	System.out.println("GitHub Login Succeed!");
        } else {
        	System.out.println("GitHub Login Failed!");
        }		    
  }

  /**
   * Test to see if an unknown user cannot login to Github.
   *
   * @TODO - Complete this test
   */
  @Test
  public void testGitHubLoginFailure()  throws InterruptedException  {

	 // Verify if the correct page is launched
	 // String pageTitle = driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[2]/h1")).getText();
     // if (!pageTitle.equals("Sign in to GitHub"))
     // {
     //   System.out.println("Launched the incorrect webpage");
	 //   tearDown();
     // }    
      
	  //click on Sign in link in the right top corner			
	  driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/span/div/a[1]")).click();
	  System.out.println("title of page is: " + driver.getTitle());
	  Thread.sleep(1000); 
      
	  //Case 1: a known user with wrong password 
	  WebElement userNameTextBox = driver.findElement(By.id("login_field"));
	  userNameTextBox.clear();
	  userNameTextBox.sendKeys(USERNAME);
	  
	//Locate the password text box, clear it first, then send a known password
	  WebElement userPasswordTextBox = driver.findElement(By.id("password")); 
      userPasswordTextBox.clear();
      userPasswordTextBox.sendKeys("wrongpassword");
	  
      //click the Sign In button
	  WebElement userSignInButtonClick = driver.findElement(By.name("commit"));
	  userSignInButtonClick.click();
	  
	  // Wait for authentication process
        Thread.sleep(3000);
        
        // verify message "Incorrect username or password" is received
        String InvalidLoginMessage = driver.findElement(By.xpath("//*[@id=\"js-flash-container\"]/div/div")).getText();
        if (InvalidLoginMessage.equals("Incorrect username or password."))
        {
            System.out.println("Correct message is displayed");
        }
        else
        {
            System.out.println("Incorrect message is displayed");
        } 
  }

  @Test
  public void testGitHubLoginFailure2()  throws InterruptedException  {
	  
		 // Verify if the correct page is launched
		 // String pageTitle = driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[2]/h1")).getText();
	     // if (!pageTitle.equals("Sign in to GitHub"))
	     // {
	     //   System.out.println("Launched the incorrect webpage");
		 //   tearDown();
	     // }    
	      
	  //click on Sign in link in the right top corner			
	   driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/span/div/a[1]")).click();
	   System.out.println("title of page is: " + driver.getTitle());
	   Thread.sleep(1000);     

	  //Case 2: an unknown user with a known (or wrong) password 
	  WebElement userNameTextBox = driver.findElement(By.id("login_field"));
	  userNameTextBox.clear();
	  userNameTextBox.sendKeys("unknownuser123");
	  
	//Locate the password text box, clear it first, then send the password
	  WebElement userPasswordTextBox = driver.findElement(By.id("password")); 
      userPasswordTextBox.clear();
      userPasswordTextBox.sendKeys(PASSWORD);//or use any other wrong password
	  
      //click the Sign In button
	  WebElement userSignInButtonClick = driver.findElement(By.name("commit"));
	  userSignInButtonClick.click();
	  
	  // Wait for authentication process
        Thread.sleep(3000);
        
        // verify message "Incorrect username or password" is received
        String InvalidLoginMessage = driver.findElement(By.xpath("//*[@id=\"js-flash-container\"]/div/div")).getText();
        if (InvalidLoginMessage.equals("Incorrect username or password."))
        {
            System.out.println("Correct message is displayed");
        }
        else
        {
            System.out.println("Incorrect message is displayed");
        } 
  }
  
  
  /**
   * Test to see that a registered user can successfully create
   * a new repository.
   *
   * @TODO - Complete this test
   */
  @Test
  public void testStartAProjectSuccess() throws InterruptedException {
	  
	  //call testGitHubLoginSuccess()
	  
	  // Verify if the correct page is launched
	 //String pageTitle = driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[2]/h1")).getText();
     //if (!pageTitle.equals("Sign in to GitHub"))
     // {
     //    System.out.println("Launched the incorrect webpage");
	 //   tearDown();
     // }     
	    
	  //click on Sign in link in the right top corner			
	   driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/span/div/a[1]")).click();
	   System.out.println("title of page is: " + driver.getTitle());
	   Thread.sleep(1000);
	   
	  //Locate the user name text box, clear it first, then send the current user name
	  WebElement userNameTextBox = driver.findElement(By.id("login_field"));
	  userNameTextBox.clear();
	  userNameTextBox.sendKeys(USERNAME);
	  
	//Locate the password text box, clear it first, then send the current password
	  WebElement userPasswordTextBox = driver.findElement(By.id("password")); 
      userPasswordTextBox.clear();
      userPasswordTextBox.sendKeys(PASSWORD);
	  
      //click the Sign In button
	  WebElement userSignInButtonClick = driver.findElement(By.name("commit"));
	  userSignInButtonClick.click();
	  
	  // Wait for authentication process
        Thread.sleep(3000);  
	  
	  /*
	   * Click either "Start a project" button or the HeaderNavLink in the right top corner to create a new repository.
	   * For a complete test of this function, QA should write test scripts to test both "Start a project" button and "HeaderNavLink".
	   * Here I choose to click "Start a project" button.
	   */
      driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[1]/div/div/a[2]")).click();
	  
	  //click the Owner dropdown box 
	  driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[3]/dl[1]/dd/div/button/span")).click();
	  
	  // choose the owner (that is the username that you used to login)
	  driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[3]/dl[1]/dd/div/div/div/div[2]/div/div[2]")).click();
	  
	  // locate the "Repository name" text box, send a repository name the text box
	  String str = "qatest2";
	  WebElement repositoryNameTextBox =  driver.findElement(By.xpath("//*[@id=\"repository_name\"]"));
	  repositoryNameTextBox.clear();
	  repositoryNameTextBox.sendKeys(str);
	  
	  // Wait for a while
      Thread.sleep(6000);  
	  
	  //click "Create repository" button
      driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button")).click();
      
      Thread.sleep(6000);  
      
      String msg = driver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/div[1]/div/h1/strong/a")).getText();
      System.out.println(msg);
       
	  // Wait for authentication process
      Thread.sleep(3000);  
      
      if (msg.equals(str)){
    	  System.out.println("Create Repository Successfully");
      } else {
    	  System.out.println("Failed to Create Repository");
      }
      

      /* Another way to verify the repository is created successfully
	  List<WebElement> linkElements = driver.findElements(By.tagName("a"));	
	  String[] linkTexts = new String[linkElements.size()];
	  int i=0;
	  
	  System.out.println("All links found on web page are: " + linkElements.size() + " links");
	   
	  //extract the link texts of each link element		
		for (WebElement e : linkElements) {							
		linkTexts[i] = e.getText();	
		System.out.println(e.getText());
		System.out.println(e.getAttribute("href"));
		i++;			
		}	
		
		//test each link		
		for (String t : linkTexts) {							
			driver.findElement(By.linkText(t));
			//print the links text

			if (t.contains("qatest")) {
				//driver.findElement(By.linkText(t)).click();
				System.out.println("Repository is created successfully");
			} 
	
			else
			{
				System.out.println("Failed to create the repository");
			} 
	   }
	   */
	
  }

  /**
   * Test to see that a registered user cannot create a new repository
   * when it has the same name as an existing repository.
   *
   * @TODO - Complete this test
   */
  @Test
  public void testStartAProjectFailure() throws InterruptedException {
	  
	  // Verify if the correct page is launched
	 //String pageTitle = driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[2]/h1")).getText();
     //if (!pageTitle.equals("Sign in to GitHub"))
     // {
     //    System.out.println("Launched the incorrect webpage");
	 //   tearDown();
     // }     
	    
	  //click on Sign in link in the right top corner			
	   driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/span/div/a[1]")).click();
	   System.out.println("title of page is: " + driver.getTitle());
	   Thread.sleep(1000);    
	    
	  //Locate the user name text box, clear it first, then send the current user name
	  WebElement userNameTextBox = driver.findElement(By.id("login_field"));
	  userNameTextBox.clear();
	  userNameTextBox.sendKeys(USERNAME);
	  
	//Locate the password text box, clear it first, then send the current password
	  WebElement userPasswordTextBox = driver.findElement(By.id("password")); 
      userPasswordTextBox.clear();
      userPasswordTextBox.sendKeys(PASSWORD);
	  
      //click the Sign In button
	  WebElement userSignInButtonClick = driver.findElement(By.name("commit"));
	  userSignInButtonClick.click();
	  
	  // Wait for authentication process
        Thread.sleep(3000);  
	  
	  /*
	   * Click either "Start a project" button or the HeaderNavLink in the right top corner to create a new repository.
	   * For a complete test of this function, QA should write test scripts to test both "Start a project" button and "HeaderNavLink".
	   * Here I choose to click "Start a project" button.
	   */
       driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[1]/div/div/a[2]")).click();
	  
	  //click the Owner dropdown box 
	  driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[3]/dl[1]/dd/div/button/span")).click();
	  
	  // choose the owner (that is the username that you used to login)
	  driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[3]/dl[1]/dd/div/div/div/div[2]/div/div[2]")).click();
	  
	  // locate the "Repository name" text box, send an existing repository name to the text box
	  WebElement repositoryNameTextBox =  driver.findElement(By.xpath("//*[@id=\"repository_name\"]"));
	  repositoryNameTextBox.clear();
	  repositoryNameTextBox.sendKeys("crest-qa-engineer-project-1-test");
	  
	// Need to wait for some time for the message box pop up
      Thread.sleep(6000);  
      
	  String msg = driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[3]/dl[2]/dd[2]")).getText();
	  //String msg = driver.findElement(By.className("error")).getText();
	  
	  System.out.println(msg);
  }

  @After
  public void tearDown() {
    driver.close();
  }

}
