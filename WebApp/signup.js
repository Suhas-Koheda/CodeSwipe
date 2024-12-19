// Handle signup logic
document.getElementById('signupForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const newEmail = document.getElementById('newEmail').value;
    const newPassword = document.getElementById('newPassword').value;

    // Store the new user credentials (in a real app, you'd send this data to the server)
    localStorage.setItem('user', newEmail);
    alert('Account created successfully!');
    window.location.href = 'login.html';  // Redirect to login page
});
