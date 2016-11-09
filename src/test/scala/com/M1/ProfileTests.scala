
import org.scalatest._
import selenium._
import org.scalatest.concurrent.Eventually._

class ProfileTests extends FunSuite with HtmlUnit with Matchers with GivenWhenThen {

	val r = scala.util.Random

	login()

	test("Logged in correctly"){
		// TODO: If this fails, the test suite should fail
		currentUrl should include("http://www.mate1.com/nw/index")
	}

	test("'Click here' link on Edit Profile page takes you to the View Profile page")(pending)

	test("'Edit or complete' link on View Profile page takes you to the Edit Profile page")(pending)

	test("Clicking on 'edit or update photo' takes you to the update photo page")(pending)

	test("Clicking on 'update photo' button enables you to choose from your file system")(pending)

	test("Clicking on 'Use my Facebook picture' button opens a popup with title Facebook")(pending)

	test("Edit image") (pending)

	ignore("Edit profile title"){
		var newDescription = "My new TEST profile headline"
		When("I go to edit profile")
		goToEditProfile()
		Thread.sleep(30000)
		And("enter a valid description")
		textField("title").value = newDescription
		And("click on Save")
		click on cssSelector("#submit_basic_information")
		And("go to View Profile")
		goToViewProfile
		Thread.sleep(30000)
		var profileHeadline = cssSelector(".profileInformationHeadline").element.text
		Then("the profile headline should the the value I entered")
		assert(profileHeadline===newDescription)
        }

	test("Edit Looking For") {
		When("I go to edit profile")
		goToEditProfile()
		Thread.sleep(20000)

		And("randomly choose one of the fields of Looking For")

		var optionName = selectRandomOption("profileLooking.gender")
		
		click on cssSelector("#submit_basic_information")

		var selectorText = cssSelector("#profileLooking_gender>option:checked").element.text

		assert(selectorText === optionName)
	}

test("Edit Aged Between") (pending)
test("Edit Birth Date") (pending)
test("Edit Country") (pending)
test("Edit Postal Code") (pending)
test("Edit City") (pending)
test("Edit Ethnicity") (pending)
test("Edit Height") (pending)	
test("Edit Body Type") (pending)
test("Edit Hair Color") (pending)
test("Edit Relationship") (pending)
test("Edit Have Children") (pending)
test("Edit Want (more) Children") (pending)
test("Edit Religion") (pending)
test("Edit Field of Study") (pending)
test("Edit Occupation")(pending)
test("Edit Education") (pending)
test("Edit Annual Income") (pending)
test("Edit Smokes") (pending)
test("Edit Drinks") (pending)

test("Edit About myself with valid input and save")(pending)
test("Edit About myself with empty text and save")(pending)
test("Edit About myself with valid input and cancel")(pending)
test("Edit About myself with email and save")(pending)
test("Edit About myself with url and save")(pending)

test("Edit What I'm looking for with valid input and save")(pending)
test("Edit What I'm looking for with empty text and save")(pending)
test("Edit What I'm looking for with valid input and cancel")(pending)
test("Edit What I'm looking for with email and save")(pending)
test("Edit What I'm looking for with url and save")(pending)

test("Tick a checkbox, save, and see the corresponding field in View Profile")(pending)
test("Untick a checkbox, save, and see the corresponding field in View Profile")(pending)

test("Add three fields in Favorite things, and validate them in View Profile")(pending)
test("Remove the three fields from Favorite things, and validate them in View Profile")(pending)

/** def getOptionsBySelectName(name: String) = findAll(xpath("//select[@name='" + name + "']/option")).map{ _.text }.toList */

def getOptionsBySelectName(name: String) : List[String] = {

	// Check that the select element is there to give an appropriate error when it's not there
        click on xpath("//select[@name='" + name + "']")

	return findAll(xpath("//select[@name='" + name + "']/option")).map{ _.text }.toList
	}

def goToViewProfile() : Unit = {
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='View Profile']")
}	

def goToEditProfile() : Unit = {
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='Edit Profile']")
}	

def selectRandomOption(selectName: String) : String = {

	// Input: name attribute of a <select>
	// Get the list of options
        var options = getOptionsBySelectName(selectName)

	// Pick a random option between 0 and options.size-1
	var optionNumber = r.nextInt(options.size)
	var optionName = options(optionNumber)
	return optionName

}

   def login( ) : Unit = {
	go to "http://www.mate1.com"

	Thread.sleep(1000)

	// Click on the link 'Login here'
	click on linkText("Login here")
	
	Thread.sleep(3000)

	// Switch to the login iframe
	switch to frame(cssSelector("#ajax_flow_holder>.iframe-container>iframe"))

	// Write my email in the email field
	textField("email").value="sohnya@gmail.com"

	// Write my password in the password field
	pwdField("password").value="verysecret"	

	// And click on the Login button

	submit()

	Thread.sleep(5000)
   }

}
