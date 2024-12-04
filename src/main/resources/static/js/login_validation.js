document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("loginForm");
    const checkboxes = document.querySelectorAll('input[name="role"]');

    const errorMessage = document.createElement("div");
    errorMessage.className = "error-message"; // Add a class to the div
    errorMessage.style.color = "red"; // Style the error message
    errorMessage.style.display = "none"; // Initially hide the error message
    form.insertBefore(errorMessage, form.firstChild);

    form.addEventListener("submit", function (event) {
        const checkedRoles = Array.from(checkboxes).filter(checkbox => checkbox.checked);

        if (checkedRoles.length !== 1) {
            event.preventDefault(); // Prevent form submission
            errorMessage.textContent = "Please select exactly one role.";
            errorMessage.style.display = "block";
        } else{
            errorMessage.textContent = "";
            errorMessage.style.display = "none";
        }
    });
});