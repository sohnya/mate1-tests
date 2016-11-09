import org.scalatest._
import selenium._
import org.scalatest.concurrent.Eventually._

class LoginTests extends FunSuite with HtmlUnit with Matchers with GivenWhenThen {

	test("Should be able to login"){

	go to "http://www.mate1.com"

	Thread.sleep(1000)

	When("I click on the link 'Login here'")
	click on linkText("Login here")
	
	Thread.sleep(3000)

	Then("I should see the Login iframe")
	switch to frame(cssSelector("#ajax_flow_holder>.iframe-container>iframe"))

	When("I write in my email in the email field")
	textField("email").value="sohnya@gmail.com"

	And("write in my password in the password field")
	pwdField("password").value="verysecret"	

	And("click on the button")
	submit()

	Thread.sleep(5000)

	Then("I should switch to index page")
	currentUrl should include("http://www.mate1.com/nw/index")
	}

	test("Select 'My Profile' - > 'View Profile' and check that I go to My Profile"){
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='View Profile']")
		currentUrl should include("http://www.mate1.com/nw/index#~/ref/profile/portrait")	
	}

	test("Select 'My Profile' - > 'Edit Profile' and check that I go to the Edit Profile page"){
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='Edit Profile']")
		currentUrl should include("http://www.mate1.com/nw/index#~/ref/page/profile/edit")	
	}

}
