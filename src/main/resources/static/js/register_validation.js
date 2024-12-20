
document.getElementById("registerButton").addEventListener("click", async function (event) {
    event.preventDefault();  // Prevent the form from submitting the normal way

    const errorMessage = document.getElementById("error-message");
    const responseMessage = document.getElementById("response-message");

    const adminData = {
        "name": document.getElementById("name").value,
        "userName": document.getElementById("username").value,
        "password": document.getElementById("password").value,
        "email": document.getElementById("email").value,
    };

    function validateForm(adminData){
        const name = adminData.name;
        if (name.length < 3 || name.length > 20) {
            errorMessage.textContent = "Name must be between 3 and 20 characters.";
            errorMessage.style.display = "block";
            return false;
        }

        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;
        const password = adminData.password;
        if (password.length < 8 || (!passwordPattern.test(password))) {
            errorMessage.textContent = "Password must be between 8 and 20 characters, include at least one uppercase letter, one lowercase letter, one number, and one special character.";
            errorMessage.style.display = "block";
            return false;
        }

        const username = adminData.userName;
        if (username.length < 3 || username.length > 20) {
            errorMessage.textContent = "UserName must be between 3 and 20 characters.";
            errorMessage.style.display = "block";
            return false;
        }

        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const email = adminData.email;
        if (email.trim().length === 0 || !emailPattern.test(email)) {
            errorMessage.textContent = "Please enter a valid email address.";
            errorMessage.style.display = "block";
            return false;
        }

        errorMessage.style.display = "none";
        return true;
    }


    if (validateForm(adminData) === true){
        const response = await fetch('http://localhost:8080/signup-process', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(adminData) // Send the signup data in the request body
        });
        const data = await response.text();

        if (response.status === 201) {
            responseMessage.textContent = data;
            errorMessage.style.display = "none";
            responseMessage.style.display = "block";
            window.location.href = '/login';
        } else {
            errorMessage.textContent = data;
            errorMessage.style.display = "block";
            responseMessage.style.display = "none";
        }
    }
});