import org.scalatest._
import selenium._
import org.scalatest.concurrent.Eventually._
import java.util.Calendar
import org.scalatest.time.Span
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import java.util.logging._

/** Test suite to do selenium tests of View Profile / Edit Profile in www.mate1.com
 * 
 *  Interact with each and all form fields and links in the “Edit Profile” page. 
 *  Entering new information in “Edit my profile” and validating that each change took place by going into “View
 *  Profile” and confirming that the new information is there being displayed to the end user.	
 *  
 */

class ProfileTests extends FunSuite with HtmlUnit with Matchers with GivenWhenThen with BeforeAndAfterAll {

	override def afterAll() {
		quit()
		info("Quitting all browser windows")
	}

	val r = scala.util.Random

	java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 

        implicit val patienceConfig = PatienceConfig(timeout = scaled(Span(60, Seconds)), interval = scaled(Span(1, Seconds)))

	implicitlyWait(Span(30,Seconds))

	login("sohnya@gmail.com","verysecret")

	ignore("Clicking on 'edit or update photo' takes you to the update photo page")(pending)

	ignore("Clicking on 'update photo' button enables you to choose from your file system")(pending)

	ignore("Clicking on 'Use my Facebook picture' button opens a popup with title Facebook")(pending)

	ignore("Edit profile title"){ // Bug: Element '.profileInformationHeadline' not found.
		
		val textFieldId = "title"
		val submitButtonId = "submit_basic_information"
		val viewProfileCssSelector = ".profileInformationHeadline"

		editAndValidateTextField(textFieldId,submitButtonId,viewProfileCssSelector)
        }

	ignore("Edit Looking For") {
		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose one of the fields of Looking For")

		var optionName = selectRandomOption("profileLooking.gender")
		
		click on cssSelector("#submit_basic_information")

		// TODO : How to verify?
	}

test("Edit Birth Date") { // OK

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

		Then("the age should be " + age.toString )	
		var profileDescription = ""
		eventually { profileDescription = cssSelector(".profileInformation>.bold").element.text }
		assert(profileDescription.contains(age.toString))
	}

ignore("Edit Aged Between") (pending)

ignore("Edit Country and enter city in Afghanistan"){ // Does not seem to save changes

		When("I go to edit profile")
		goToEditProfile()

		And("Choose a country from the list")
		singleSel("country").value = "4"
		
		val country = cssSelector("#country>optgroup[label='Other']>option[value='4']").element.text

		val city = "Kabul" + r.alphanumeric.take(5).mkString

		textField("input_city").value = city

		And("Set the city to " + textField("input_city").value)
			
		And("click on the save button")
		click on cssSelector("#submit_basic_information")
		Thread.sleep(5000)

		When("I go to view profile")
		click on linkText("Click here")

		Then("I should be able to find an element with that country and city")	
		click on xpath("//*[text()='" + city + "']")
		click on xpath("//*[text()='" + country + "']")
}

ignore("Edit Height") {
		editAndValidateDropDown("height")
}

ignore("Edit Ethnicity") {
		editAndValidateDropDown("ethnicity")
}

ignore("Edit Body Type"){
		editAndValidateDropDown("bodyType")
}

ignore("Edit Hair Color"){
		editAndValidateDropDown("hairColor")
}

ignore("Edit Relationship") {
		editAndValidateDropDown("relationship")
}

ignore("Edit Have Children"){
		editAndValidateDropDown("children")
}

ignore("Edit Want (more) Children"){
		editAndValidateDropDown("wantChildren")
}

ignore("Edit Religion"){
		editAndValidateDropDown("religion")
}

ignore("Edit Field of Study"){
		editAndValidateDropDown("educationField")
}

ignore("Edit Occupation"){
		editAndValidateDropDown("occupation")
}

ignore("Edit Education"){
		editAndValidateDropDown("education")
}

ignore("Edit Annual Income"){
		editAndValidateDropDown("income")
}

ignore("Edit Smokes"){
		editAndValidateDropDown("smokes")
}

ignore("Edit Drinks"){
		editAndValidateDropDown("drinks")
}

ignore("Edit What I'm looking for with valid input"){

		val textAreaId = "lookingFor"
		val submitButtonId = "submit_who_im_looking_for"
		val viewProfileCssSelector = "#who_im_looking_for>p"

		editAndValidateTextArea(textAreaId,submitButtonId,viewProfileCssSelector)
}

ignore("Edit About myself with empty text and save")(pending)
ignore("Edit About myself with valid input and cancel")(pending)
ignore("Edit About myself with email and save")(pending)
ignore("Edit About myself with url and save")(pending)

ignore("Edit About myself with valid input"){

		val textAreaId = "aboutMyself"
		val submitButtonId = "submit_about_myself"
		val viewProfileCssSelector = "#about_myself>p"

		editAndValidateTextArea(textAreaId,submitButtonId,viewProfileCssSelector)
}

ignore("Edit What I'm looking for with empty text and save")(pending)
ignore("Edit What I'm looking for with valid input and cancel")(pending)
ignore("Edit What I'm looking for with email and save")(pending)

ignore("Edit What I'm looking for with url"){

		val textAreaId = "aboutMyself"
		val submitButtonId = "submit_about_myself"
		val viewProfileCssSelector = "#about_myself>p"
		val myUrl = "http://www.google.com"

		When("I go to edit profile")
		goToEditProfile()

		var newText = "TextArea " + r.nextInt(100).toString + ", " + myUrl
		And("set the textArea to: " + newText)
		textArea(id(textAreaId)).value = newText
		And("click on Save")
		click on cssSelector("#"+submitButtonId)
		Thread.sleep(5000)

		And("go to View Profile")
		click on linkText("Click here")
		assert(currentUrl == "http://www.mate1.com/nw/index#~/ref/profile/portrait")

		var textInViewProfile = cssSelector(viewProfileCssSelector).element.text

		Then("the textArea text should not contain " + myUrl)
		assert(!textInViewProfile.contains(newText))



}

ignore("Tick a checkbox, save, and see the corresponding field in View Profile"){

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
		Thread.sleep(5000)

		And("Go to view profile")
		click on linkText("Click here")
	
		Then("Diet text should contain the Diet choices")

		var dietText = ""
		dietText = cssSelector("#lifestyle_interests>ul.oneColumn.left>li").element.text
		assert(dietText.contains(label1))
		assert(dietText.contains(label2))
		assert(dietText.contains(label3))
}

ignore("Untick checkboxes, save, and see the corresponding field is missing in View Profile"){

		When("I go to Edit Profile")
		goToEditProfile()

		// Untick the three boxes selected before
		if(checkbox("#diet_3").isSelected){
			checkbox(cssSelector("#diet_3")).clear()
		}
		if(checkbox("#diet_7").isSelected){
			checkbox(cssSelector("#diet_7")).clear()
		}
		if(checkbox("#diet_11").isSelected){
			checkbox(cssSelector("#diet_11")).clear()
		}

		// Save their labels
		var label1 = cssSelector("#label_diet[for=diet_3]").element.text
		var label2 = cssSelector("#label_diet[for=diet_7]").element.text
		var label3 = cssSelector("#label_diet[for=diet_11]").element.text
	
		// Click on save
                click on cssSelector("#submit_lifestyle_interests")

		// Go to View Profile
		click on linkText("Click here")

		// Check that the corresponding section does not contain this text
		var dietText = ""
		dietText = cssSelector("#lifestyle_interests>ul.oneColumn.left>li").element.text
		assert(!dietText.contains(label1))
		assert(!dietText.contains(label2))
		assert(!dietText.contains(label3))
}

ignore("Add a field at random in Favorite things, and validate them in View Profile"){

	When("I go to Edit Profile")
	goToEditProfile()

	val favoriteThing = "New favorite thing" + r.nextInt(100).toString
	val numberOfFields = 36 // TODO: Make this flexible
	val listItem = r.nextInt(numberOfFields-1).toString
	val xPathToListItem = "//div[@id='favorite_things']/ul[@class='specifics']/li["+ listItem
	val listItemLabel = xpath(xPathToListItem+"]/label").element.text

	And("set the textField to " + favoriteThing)
	textField(xpath(xPathToListItem+"]/input")).value = favoriteThing

	And("make sure that the textfield's value is now : " + textField(xpath(xPathToListItem+"]/input")).value)

	click on id("submit_favorite_things")
	Thread.sleep(5000)

	click on linkText("Click here")

	Then("the View Profile page should have an element with the corresponding text")
	click on xpath("//*[text()=' " + favoriteThing + "']")
}


ignore("Remove everything from Favorite things, and validate them in View Profile")(pending)

def calculateAge(year: Int, month:String, day: Int) : Int = {

	val now = Calendar.getInstance()
	val currentDay = now.get(Calendar.DAY_OF_MONTH)
	var currentMonth = now.get(Calendar.MONTH)
	val currentYear = now.get(Calendar.YEAR)
	return 0;
}

def goToEditProfile() : Unit = {
		click on cssSelector("#profile_dd")
		click on cssSelector("#profile_dd_li>ul>li>a[title='Edit Profile']")
		click on id("title")
}

def editAndValidateTextArea(textAreaId : String,submitButtonId : String,viewProfileCssSelector : String) : Unit ={

		When("Edit and validate with textArea = "+textAreaId + ", submitButtonId = " + submitButtonId + " and viewProfileCssSelector " + viewProfileCssSelector)

		When("I go to edit profile")
		goToEditProfile()

		var newText = "TextArea " + r.nextInt(100).toString
		And("set the textArea to: " + newText)
		textArea(id(textAreaId)).value = newText
		And("click on Save")
		click on cssSelector("#"+submitButtonId)
		Thread.sleep(5000)

		And("go to View Profile")
		click on linkText("Click here")
		assert(currentUrl == "http://www.mate1.com/nw/index#~/ref/profile/portrait")

		var textInViewProfile = cssSelector(viewProfileCssSelector).element.text

		Then("the textArea text should be " + newText)
		assert(textInViewProfile===newText)
}

def editAndValidateTextField(textFieldId : String,submitButtonId : String,viewProfileCssSelector : String) : Unit ={

		When("Edit and validate with textfieldID = "+textFieldId + ", submitButtonId = " + submitButtonId + " and viewProfileCssSelector " + viewProfileCssSelector)

		var newText = "Textfield " + r.nextInt(100).toString
		When("I go to edit profile")
		goToEditProfile()
		And("set the textfield to: " + newText)
		textField(id(textFieldId)).value = newText
		And("click on Save")
		click on cssSelector("#"+submitButtonId)
		Thread.sleep(5000)

		And("go to View Profile")
		click on linkText("Click here")
		assert(currentUrl == "http://www.mate1.com/nw/index#~/ref/profile/portrait")

		var textInViewProfile = cssSelector(viewProfileCssSelector).element.text

		Then("the textField text should be " + newText)
		assert(textInViewProfile===newText)
}

def editAndValidateDropDown(dropdownName : String) : Unit = {

		When("I go to edit profile")
		goToEditProfile()

		And("randomly choose a value")
		val newText = selectRandomOption(dropdownName)
		And("click on the save button")
		click on cssSelector("#submit_basic_information")
		Thread.sleep(5000)

		When("I go to view profile")
		click on linkText("Click here")
		assert(currentUrl == "http://www.mate1.com/nw/index#~/ref/profile/portrait")
		Then("I should be able to find an element with the text " + newText)	
		click on xpath("//*[text()='" + newText + "']")

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

	// Pick a random option between 1 and options.size, since xpath [] starts from 1. 
	And("Pick a random option name")
	var optionNumber = r.nextInt(options.size)+1
	var optionName = options(optionNumber-1) // List count starts from 0.
	var optionValue = xpath("//select[@name='" + selectName + "']/option["+optionNumber +"]").element.attribute("value").getOrElse("Not found")

	// Select this option
	And("Select the option "+ optionName + " with optionValue " + optionValue)
	singleSel(selectName).value = optionValue
	
	return optionName

}

  /** Calculates the age
   * 
   *  @param day birthday day
   *  @param month entire birthday month as a String, for example "January"
   *  @param birthyear
   *  @return age
   */

def getAge(day : Int, month : String, year : Int) : Int = {


			info("day:" +day + ", month:" + month + ",year:"+year)

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
   * Logs in using by going to the website, clicking on the 'Login here' link, 
   * writing login information in the following iframe, and clicking "Save"
   * @param email the email adress
   * @param password the password
   */

   def login(email: String, password: String) : Unit = {
	go to "http://www.mate1.com"

	// Click on the link 'Login here'
	click on linkText("Login here")

	// Switch to the login iframe
	switch to frame(cssSelector("#ajax_flow_holder>.iframe-container>iframe"))

	// Write my email in the email field
	textField("email").value=email

	// Write my password in the password field
	pwdField("password").value=password

	// And click on the Login button

	submit()
   }

}








