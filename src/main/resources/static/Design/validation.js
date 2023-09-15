document.addEventListener("DOMContentLoaded", function() {

	var currentDate = new Date();
	// Get the date of birth input set its maximum value to the current date 
	var dateOfBirthInput = document.getElementById("dateOfBirth");
	dateOfBirthInput.max = currentDate.toISOString().split("T")[0];

	// Get the mobile input field and add an event listener to clear errors on input
	var mobileInput = document.querySelector(".mobile-input");
	mobileInput.addEventListener("input", function() {
		var mobileErrorElement = document.getElementById("mobileError");
		var mobileNotUniqueErrorElement = document.getElementById("mobileNotUniqueError");
		if (mobileErrorElement) {
			clearError(mobileErrorElement);
		}
		if (mobileNotUniqueErrorElement) {
			clearError(mobileNotUniqueErrorElement);
		}
	});

	// Get the email input field and add an event listener to clear errors on input
	var emailInput = document.querySelector(".email-input");
	emailInput.addEventListener("input", function() {
		var emailErrorElement = document.getElementById("emailError");
		var emailNotUniqueErrorElement = document.getElementById("emailNotUniqueError");
		if (emailErrorElement) {
			clearError(emailErrorElement);
		}
		if (emailNotUniqueErrorElement) {
			clearError(emailNotUniqueErrorElement);
		}
	});

	// Get the form element and add a submit event listener
	var form = document.querySelector("form");
	form.addEventListener("submit", function(event) {
		event.preventDefault(); // Prevent form submission for now
		validateField("firstName", "First Name is required.", /^[A-Za-z]+$/, "Must contain only alphabetic characters.");
		validateField("lastName", "Last Name is required.", /^[A-Za-z]+$/, "Must contain only alphabetic characters.");
		validateField("dateOfBirth", "Date of Birth is required.");
		validateField("mobile", "Mobile Number is required.", /^\d{10}$/, "Must be exactly 10 digits.");
		validateField("address1", "Address 1 is required.");
		validateField("email", "Email is required.", /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/, "Must be a valid email address.");
		validateGender();
		// Check if all validation passed and submit the form
		if (checkAllValidations()) {
			form.submit();
		}
	});

	// Get all input fields and add input event listener for pattern validation
	var inputFields = document.querySelectorAll("input");
	inputFields.forEach(function(inputField) {
		inputField.addEventListener("input", function() {
			var fieldName = inputField.getAttribute("name");
			var errorElement = inputField.parentElement.querySelector(".error-message");
			var errorMessage = "";
			if (fieldName === "firstName" || fieldName === "lastName") {
				// Validate First Name and Last Name for alphabetic characters only
				if (!/^[A-Za-z]+$/.test(inputField.value)) {
					errorMessage = "Must contain only alphabetic characters.";
				}
			} else if (fieldName === "mobile") {
				// Validate Mobile for 10 digits
				if (!/^\d{10}$/.test(inputField.value)) {
					errorMessage = "Must be exactly 10 digits.";
				}
			} else if (fieldName === "email") {
				// Validate Email for a valid email pattern
				if (!/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(inputField.value)) {
					errorMessage = "Must be a valid email address.";
				}
			}
			if (errorMessage) {
				showError(errorElement, errorMessage);

			} else {
				clearError(errorElement);
			}
		});
	});

	function validateGender() {
		var radioButtons = document.getElementsByName("gender");
		var errorElement = document.getElementById("genderError");
		var isSelected = false;
		for (var i = 0; i < radioButtons.length; i++) {
			if (radioButtons[i].checked) {
				isSelected = true;
				break;
			}
		}
		if (!isSelected) {
			errorElement.textContent = "Please select a gender.";
		}
	}

	function showError(errorElement, message) {
		errorElement.textContent = message;
		errorElement.style.color = "red";
	}

	function clearError(errorElement) {
		errorElement.textContent = "";
	}

	// Function to validate a specific input field based on regex pattern
	function validateField(fieldName, requiredErrorMessage, pattern, patternErrorMessage) {
		var field = document.querySelector("input[name='" + fieldName + "']");
		var errorElement = field.parentElement.querySelector(".error-message");
		if (!field.value.trim()) {
			showError(errorElement, requiredErrorMessage);
		} else if (pattern && !pattern.test(field.value)) {
			showError(errorElement, patternErrorMessage);
		} else {
			clearError(errorElement);
		}
	}

	// Function to check if all validation passed
	function checkAllValidations() {
		var errorElements = document.querySelectorAll(".error-message");
		for (var i = 0; i < errorElements.length; i++) {
			if (errorElements[i].textContent !== "") {
				return false; // If any error message is present, return false
			}
		}
		return true; // All fields are valid
	}
});

function calculateAge(dateOfBirthInput) {
    const currentDate = new Date();
    const selectedDate = new Date(dateOfBirthInput.value);
    const ageInput = document.querySelector("input[name='age']");
    const ageErrorElement = document.getElementById("ageError");
    const maxAge = 115; // Adjust the maximum age as needed
    const ageInMilliseconds = currentDate - selectedDate;
    const ageInYears = ageInMilliseconds / (365 * 24 * 60 * 60 * 1000);

    if (ageInYears < 1 || ageInYears > maxAge) {
        ageErrorElement.textContent = "Age must be between 1 and " + maxAge;
        ageInput.value = "";
    } else {
        ageErrorElement.textContent = "";
        ageInput.value = Math.floor(ageInYears);
    }
}

function checkRadio() {
	var errorElement = document.getElementById("genderError");
	errorElement.textContent = "";
}

function resetMobileField() {
	var mobileField = document.getElementById("mobile");
	mobileField.value = ""; // Clear the mobile field
}

function resetEmailField() {
	var emailField = document.getElementById("email");
	emailField.value = ""; // Clear the mobile field
}