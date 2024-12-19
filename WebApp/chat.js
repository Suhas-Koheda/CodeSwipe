function toggleMenu() {
    document.getElementById('navMenu').classList.toggle('hidden');
}

function loadMatches() {
    const matchList = JSON.parse(localStorage.getItem('matches')) || [];
    const matchContainer = document.getElementById('matches');

    if (matchList.length === 0) {
        matchContainer.innerHTML = '<p>No matches yet. Swipe right on profiles to match!</p>';
        return;
    }

    matchList.forEach(match => {
        const matchElement = document.createElement('div');
        matchElement.classList.add('match');
        matchElement.innerHTML = `
            <h3>${match.name}</h3>
            <p>${match.techStack}</p>
            <p>${match.favoriteProject}</p>
        `;
        matchContainer.appendChild(matchElement);
    });
}

// Chat functionality
function sendMessage() {
    const messageInput = document.getElementById('chatInput');
    const messageText = messageInput.value.trim();
    
    if (messageText === "") return;

    // Display the user's message
    displayMessage(messageText, 'user');

    // Simulate bot response after a delay
    setTimeout(() => {
        const botResponse = "Bot: " + getBotResponse(messageText);
        displayMessage(botResponse, 'bot');
    }, 1000);

    // Clear the input field
    messageInput.value = '';
}

// Function to display messages
function displayMessage(message, sender) {
    const chatBox = document.getElementById('chatBox');
    const messageElement = document.createElement('div');
    messageElement.classList.add('message', sender);
    messageElement.textContent = message;
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// Function to generate bot responses
function getBotResponse(userMessage) {
    if (userMessage.toLowerCase().includes('hello')) {
        return 'Hello! How can I assist you today?';
    }
    return 'Sorry, I didn’t understand that. Please try again.';
}

// Load matches and chat on page load
window.onload = function() {
    loadMatches();
};
