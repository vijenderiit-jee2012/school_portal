document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("signupForm");

    const checkboxes = document.querySelectorAll('input[name="role"]');

    const errorMessage = document.createElement("div");
    errorMessage.className = "error-message"; // Add a class to the div
    errorMessage.style.color = "red"; // Style the error message
    errorMessage.style.display = "none"; // Initially hide the error message
    form.insertBefore(errorMessage, form.firstChild);


}