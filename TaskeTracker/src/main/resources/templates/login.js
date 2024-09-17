// login.js
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const email = document.getElementById('email').value;
        const senha = document.getElementById('senha').value;

        fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, senha })
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Login falhou');
            })
            .then(data => {
                localStorage.setItem('authToken', data.token);
                window.location.href = '/index.html';
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Login falhou. Por favor, verifique suas credenciais e tente novamente.');
            });
    });
});