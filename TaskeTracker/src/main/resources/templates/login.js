// login.js
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const rememberMeCheckbox = document.getElementById('rememberMe');

    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const username = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password, rememberMe: rememberMeCheckbox.checked })
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Login failed');
            })
            .then(data => {
                // Assuming the server sends back a token
                localStorage.setItem('authToken', data.token);
                window.location.href = '/index';
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Login failed. Please check your credentials and try again.');
            });
    });
});