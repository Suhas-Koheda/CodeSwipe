// Handle login logic
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // For demonstration purposes, this is just a simple check
    if (email === 'user@example.com' && password === 'password') {
        localStorage.setItem('user', email);
        window.location.href = 'home.html';  // Redirect to home page
    } else {
        alert('Invalid email or password');
    }
});
