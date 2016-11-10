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

	implicitlyWait(Span(30,Seconds))

	login()

	test("'Click here' link on Edit Profile page takes you to the View Profile page")(pending)

	test("'Edit or complete' link on View Profile page takes you to the Edit Profile page")(pending)

	test("Clicking on 'edit or update photo' takes you to the update photo page")(pending)

	test("Clicking on 'update photo' button enables you to choose from your file system")(pending)

	test("Clicking on 'Use my Facebook picture' button opens a popup with title Facebook")(pending)

	test("Edit image") (pending)

	test("Edit profile title"){
		var newDescription = "Profile title " +r.getInt.toString
		When("I go to edit profile")
		goToEditProfile()
		And("enter a valid description")
		textField("title").value = newDescription
		And("click on Save")
		click on cssSelector("#submit_basic_information")
		And("go to View Profile")
		click on linkText("Click here")

		var profileHeadline = cssSelector(".profileInformationHeadline").element.text
		Then("the profile headline should the the value I entered")
		assert(profileHeadline===newDescription)
        }

	test("Edit Looking For") {
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose one of the fields of Looking For")

		var optionName = selectRandomOption("profileLooking.gender")
		
		click on cssSelector("#submit_basic_information")

		// TODO : How to verify?
	}

	test("Edit Birth Date") {

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

		click on linkText("Click here")

		Then("the information should be as I set it")	
		var profileDescription = ""
		eventually { profileDescription = cssSelector(".profileInformation>.bold").element.text }
		assert(profileDescription.contains(age.toString))
	}

test("Edit Aged Between") (pending)

test("Edit Country and enter city in Afghanistan"){

		When("I go to edit profile")
		goToEditProfile()

		And("Choose a country from the list")
		singleSel("country").value = "4"		
		country = cssSelector("#country>optgroup[label=Other]>option[value=4]").element.text

		val city = "Kabul "+r.getInt.toString
		textField("input_city").value = city
			
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that country and Postal Code")	
		xpath("//*[text()='" + city + "']")
		xpath("//*[text()='" + country + "']")
}

test("Edit Ethnicity") {
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose an ethnicity")
		val newEthnicity = selectRandomOption("ethnicity")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that ethnicity")	
		xpath("//*[text()='" + newEthnicity + "']")
}

test("Edit Height") {
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a height")
		val newHeight = selectRandomOption("height")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that height")	
		xpath("//*[text()='" + newHeight + "']")	
}

test("Edit Body Type"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose an ethnicity")
		val newBodyType = selectRandomOption("bodyType")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that body type")	
		xpath("//*[text()='" + newBodyType + "']")
}

test("Edit Hair Color"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a hair color")
		val newHairColor = selectRandomOption("hairColor")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that hair color")	
		xpath("//*[text()='" + newHairColor + "']")
}

test("Edit Relationship") {
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a relationship")
		val newRelationship = selectRandomOption("relationship")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that relationship")	
		xpath("//*[text()='" + newRelationship + "']")
}

test("Edit Have Children"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a 'Have Children' option")
		val newHaveChildren = selectRandomOption("children")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that 'Have Children'")	
		xpath("//*[text()='" + newHaveChildren + "']")
}

test("Edit Want (more) Children"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a 'Want more children' option")
		val newWantChildren = selectRandomOption("wantChildren")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that 'Want children'")	
		xpath("//*[text()='" + newWantChildren + "']")
}

test("Edit Religion"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose an religion")
		val newReligion = selectRandomOption("religion")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that religion")	
		xpath("//*[text()='" + newReligion + "']")
}

test("Edit Field of Study"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a field of study")
		val newStudy = selectRandomOption("educationField")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that field of study")	
		xpath("//*[text()='" + newStudy + "']")
}

test("Edit Occupation"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose an occupation")
		val newOccupation = selectRandomOption("occupation")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that occupation")	
		xpath("//*[text()='" + newOccupation + "']")
}

test("Edit Education"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose an education")
		val newEducation = selectRandomOption("education")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that education")	
		xpath("//*[text()='" + newEducation + "']")
}

test("Edit Annual Income"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose an income")
		val newIncome = selectRandomOption("income")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that annual income")	
		xpath("//*[text()='" + newIncome + "']")
}

test("Edit Smokes"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a 'Smokes' option")
		val newSmokes = selectRandomOption("smokes")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that 'Smokes' option")	
		xpath("//*[text()='" + newSmokes + "']")
}

test("Edit Drinks"){
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a 'Drinks' option")
		val newDrinks = selectRandomOption("drinks")
		And("click on the save button")
		click on cssSelector("#submit_basic_information")

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that 'Drinks' option")	
		xpath("//*[text()='" + newDrinks + "']")
}


test("Edit About myself with valid input and save"){

		var newAboutMyself = "This is me :" + r.nextInt().toString
		When("I go to edit profile")
		goToEditProfile()
		And("enter valid text")
		textField("#aboutMyself").value = newDescription
		And("click on Save")
		click on cssSelector("#submit_about_myself")
		And("go to View Profile")
		click on linkText("Click here")

		var aboutMyself = cssSelector("about_myself>p").element.text
		Then("the profile headline should the the value I entered")
		assert(aboutMyself===newAboutMyself)


}

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
		checkbox(cssSelector("#diet_3")).select()
		checkbox(cssSelector("#diet_7")).select()
		checkbox(cssSelector("#diet_11")).select()

		And("Get the text corresponding to these checkboxes")
		var label1 = cssSelector("#label_diet[for=diet_3]").element.text
		var label2 = cssSelector("#label_diet[for=diet_7]").element.text
		var label3 = cssSelector("#label_diet[for=diet_11]").element.text

		info(label1 + label2 + label3)

		And("Click on save")
                click on cssSelector("#submit_lifestyle_interests")

		And("Go to view profile")
		click on linkText("Click here")
	
		Then("Diet text should contain the Diet choices")

		var dietText = ""
		dietText = cssSelector("#lifestyle_interests>ul.oneColumn.left>li").element.text
		assert(dietText.contains(label1))
}

		//eventually { dietText = xpath("//div[@id='lifestyle_interests']/ul/li[0]").element.text }

ignore("Untick a checkbox, save, and see the corresponding field in View Profile"){


		When("I go to Edit Profile")
		goToEditProfile()

		// Untick the three boxes selected before
		checkbox(cssSelector("#diet_7")).select()
		checkbox(cssSelector("#diet_11")).select()
		checkbox(cssSelector("#diet_3")).select()

		// Get the text corresponding to these checkboxes
		var label1 = cssSelector("#label_diet[for=diet_3]").element.text
		info(label1)
	
		// Click on save
                click on cssSelector("#submit_lifestyle_interests")

		// Go to View Profile
		click on linkText("Click here")

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

/**def goToViewProfile() : Unit = {
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='View Profile']")
		click on cssSelector("profileInformationHeadline")
}*/	

def goToEditProfile() : Unit = {
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='Edit Profile']")
		click on id("title")
}	

def getOptionsBySelectName(name: String) : List[String] = {

	// Check that the select element is there to give an appropriate error when it's not there
        click on xpath("//select[@name='" + name + "']/option[1]")

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

	And("get its value using xpath")
	var optionValue = xpath("//select[@name='" + selectName + "']/option["+optionNumber +"]").element.attribute("value").getOrElse("Not found")

	// Select this option
	And("Select the option "+optionName)
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

  /**
   * @return Logs in using (for the moment) fixed email and password,
   * by going to the website, clicking on the 'Login here' link, 
   * writing login information in the following iframe, and clicking "Save"
   */

   def login( ) : Unit = {
	go to "http://www.mate1.com"

	// Click on the link 'Login here'
	click on linkText("Login here")

	// Switch to the login iframe
	switch to frame(cssSelector("#ajax_flow_holder>.iframe-container>iframe"))

	// Write my email in the email field
	textField("email").value="sohnya@gmail.com"

	// Write my password in the password field
	pwdField("password").value="verysecret"	

	// And click on the Login button

	submit()
   }

}
