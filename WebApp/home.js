function toggleMenu() {
    const menu = document.getElementById('navMenu');
    menu.classList.toggle('hidden');
}

// Sample data for profiles
let profiles = [
    { name: 'Jane Smith', techStack: 'Java, Kotlin', favoriteProject: 'Expense Tracker App', codeSnippet: 'System.out.println("Hello, Java!");' },
    { name: 'Mike Johnson', techStack: 'Python, Django', favoriteProject: 'E-commerce Backend', codeSnippet: 'print("Hello, Python!")' },
    { name: 'Emily Davis', techStack: 'JavaScript, React', favoriteProject: 'Portfolio Website', codeSnippet: 'console.log("Hello, React!");' },
    { name: 'Chris Lee', techStack: 'Ruby, Rails', favoriteProject: 'Inventory System', codeSnippet: 'puts "Hello, Ruby!"' },
    { name: 'Sarah Connor', techStack: 'C++, OpenGL', favoriteProject: '3D Game Engine', codeSnippet: 'std::cout << "Hello, C++!" << std::endl;' },
    { name: 'Liam Wilson', techStack: 'Swift, iOS', favoriteProject: 'Fitness Tracker App', codeSnippet: 'print("Hello, Swift!")' }
];

let currentIndex = 0;
let matchList = JSON.parse(localStorage.getItem('matches')) || [];

function displayProfile() {
    const profile = profiles[currentIndex];
    document.getElementById('name').innerText = profile.name;
    document.getElementById('techStack').innerText = `Tech Stack: ${profile.techStack}`;
    document.getElementById('favoriteProject').innerText = `Favorite Project: ${profile.favoriteProject}`;
    document.getElementById('codeSnippet').innerText = profile.codeSnippet;
}

function swipe(direction) {
    if (direction === 'right') {
        alert(`You matched with ${profiles[currentIndex].name}!`);
        matchList.push(profiles[currentIndex]);
        localStorage.setItem('matches', JSON.stringify(matchList));
    } else {
        alert(`You swiped left on ${profiles[currentIndex].name}.`);
    }

    currentIndex = (currentIndex + 1) % profiles.length;
    displayProfile();
}

// Initialize the first profile on page load
window.onload = displayProfile;
