import org.scalatest._
import selenium._
import org.scalatest.concurrent.Eventually._
import java.util.Calendar
import org.scalatest.time.Span
import org.scalatest.time.Millis
import org.scalatest.time.Seconds

class ProfileTests extends FunSuite with HtmlUnit with Matchers with GivenWhenThen {

	val r = scala.util.Random

        implicit val patienceConfig = PatienceConfig(timeout = scaled(Span(60, Seconds)), interval = scaled(Span(1, Seconds)))

	login()

	test("Logged in correctly"){
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
		//Thread.sleep(30000)
		And("enter a valid description")
		textField("title").value = newDescription
		And("click on Save")
		click on cssSelector("#submit_basic_information")
		And("go to View Profile")
		goToViewProfile
		//Thread.sleep(30000)
		var profileHeadline = cssSelector(".profileInformationHeadline").element.text
		Then("the profile headline should the the value I entered")
		assert(profileHeadline===newDescription)
        }

	ignore("Edit Looking For") {
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose one of the fields of Looking For")

		var optionName = selectRandomOption("profileLooking.gender")
		
		click on cssSelector("#submit_basic_information")

		// TODO : How to verify?
	}

	ignore("Edit Birth Date") {

		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose month")
		val month = selectRandomOption("month")
		And("randomly choose day")
		val day = selectRandomOption("daysOfMonth").toInt 
		And("randomly choose year")		
		val year = selectRandomOption("year").toInt
		And("click on the save button")
		click on cssSelector("#submit_basic_information")
		And("compute the age")
		var age = getAge(day,month,year)

		When("I go to View Profile")
		goToEditProfile()

		Then("the information should be as I set it")	
		var profileDescription = ""
		eventually { profileDescription = cssSelector(".profileInformation>.bold").element.text }
		assert(profileDescription.contains(age.toString))
	}

test("Edit Aged Between") (pending)
test("Edit Country") (pending)
test("Edit Postal Code") (pending)
test("Edit City") (pending)
test("Edit Ethnicity") (pending)

ignore("Edit Height") {
		When("I go to edit profile")
		goToEditProfile()
		//Thread.sleep(30000)

		And("randomly choose a height")
		val newHeight = selectRandomOption("height")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")
		Thread.sleep(5000)

		When("I go to view profile")
		goToViewProfile()
		//Thread.sleep(30000)

		Then("the height that I find there should be the one I just set")	
		val heightInViewProfile = cssSelector(".profileInformation> ???").element.text	
		assert(heightInViewProfile == newHeight)
}

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

test("Tick a checkbox, save, and see the corresponding field in View Profile"){

		When("I go to Edit Profile")
		goToEditProfile()

		And("tick three textboxes")
		eventually {
		checkbox(cssSelector("#diet_3")).select()
		checkbox(cssSelector("#diet_7")).select()
		checkbox(cssSelector("#diet_11")).select()
		}

		And("Get the text corresponding to these checkboxes")
		var label1 = cssSelector("#label_diet[for=diet_3]").element.text
		var label2 = cssSelector("#label_diet[for=diet_7]").element.text
		var label3 = cssSelector("#label_diet[for=diet_11]").element.text

		info(label1 + label2 + label3)

		And("Click on save")
                click on cssSelector("#submit_lifestyle_interests")

		And("Go to view profile")
		//goToViewProfile()
		eventually { click on linkText("Click here") }
	
		// Check that the corresponding section contains these choices.	
		
		var dietText
		eventually {var dietText = cssSelector("#lifestyle_interests>ul.oneColumn.left>li").element.text	}
		assert(dietText.contains(label1))
}

ignore("Untick a checkbox, save, and see the corresponding field in View Profile"){


		When("I go to Edit Profile")
		goToEditProfile()

		// Untick the three boxes selected before
		eventually {
		checkbox(cssSelector("#diet_7")).select()
		checkbox(cssSelector("#diet_11")).select()
		checkbox(cssSelector("#diet_3")).select()
		}

		// Get the text corresponding to these checkboxes
		var label1 = cssSelector("#label_diet[for=diet_3]").element.text
		info(label1)
	
		// Click on save
                click on cssSelector("#submit_lifestyle_interests")

		// Go to View Profile
		//When("I go to view profile")
		//goToViewProfile()
		eventually { click on linkText("Click here") }

		// Check that the corresponding section contains
}

test("Add three fields at random in Favorite things, and validate them in View Profile"){

	// Find the div that contains the Favorite things

	// Select three at random, and enter text in them. Save the labels.

	// Go to View profile

	// Check that there are three fields with the corresponding labels.

}

test("Remove the three fields from Favorite things, and validate them in View Profile")(pending)

def calculateAge(year: Int, month:String, day: Int) : Int = {

	val now = Calendar.getInstance()
	val currentDay = now.get(Calendar.DAY_OF_MONTH)
	var currentMonth = now.get(Calendar.MONTH)
	val currentYear = now.get(Calendar.YEAR)
	return 0;

}

def goToViewProfile() : Unit = {
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='View Profile']")
		eventually { click on cssSelector("profileInformationHeadline")}
}	

def goToEditProfile() : Unit = {
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='Edit Profile']")
		eventually { click on id("title") } 
}	

def getOptionsBySelectName(name: String) : List[String] = {

	// Check that the select element is there to give an appropriate error when it's not there
        eventually { click on xpath("//select[@name='" + name + "']/option[1]") }

	return findAll(xpath("//select[@name='" + name + "']/option")).map{ _.text }.toList
	}

def selectRandomOption(selectName: String) : String = {

	// Input: name attribute of a <select>
	// Get the list of options
	And("Get a list of options by selectName")
        var options = getOptionsBySelectName(selectName)
	
	assert(options.size > 0)

	// Pick a random option between 0 and options.size-1
	And("Pick a random option name")
	var optionNumber = r.nextInt(options.size) 
	var optionName = options(optionNumber)

	var optionValue = xpath("//select[@name='" + selectName + "']/option["+optionNumber +"]").element.attribute("value").getOrElse("Not found")

	// Select this option
	And("Select the option"+optionValue)
	singleSel(selectName).value = optionValue
	
	return optionName

}

		def getAge(day : Int, month : String, year : Int) : Int = {

			val monthNameToNumber = Map(
    				"January"  -> 0,
    				"February"  -> 1,
    				"March"  -> 2,
    				"April"  -> 3,
    				"May"  -> 4,
    				"June" -> 5,
  				"July"  -> 6,
				"August"  -> 7,
  				"September"  -> 8,
   				"October" -> 9,
				"November" -> 10,
   				"December" -> 11 )

			// Get today's date
			val now = Calendar.getInstance()
			val currentDay = now.get(Calendar.DAY_OF_MONTH)
			val currentMonth = now.get(Calendar.MONTH)
			val currentYear = now.get(Calendar.YEAR)
			val monthInt = monthNameToNumber(month)
			var age = 0	

			if(monthInt < currentMonth || (monthInt==currentMonth && day <= currentDay)){
				age = currentYear - year
			}else{
				age = currentYear - year - 1
			}
			return age
		}

   def login( ) : Unit = {
	go to "http://www.mate1.com"

	//Thread.sleep(1000)

	// Click on the link 'Login here'
	eventually { click on linkText("Login here") }
	
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
