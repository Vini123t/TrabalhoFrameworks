// add-task.js
document.addEventListener('DOMContentLoaded', function() {
    const backButton = document.querySelector('.btn-outline-secondary');
    const addTaskForm = document.getElementById('addTaskForm');

    backButton.addEventListener('click', function(e) {
        e.preventDefault();
        window.location.href = 'index.html';
    });

    addTaskForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const taskTitle = document.getElementById('taskTitle').value;
        const taskDescription = document.getElementById('taskDescription').value;

        fetch('http://localhost:8080/api/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            },
            body: JSON.stringify({ title: taskTitle, description: taskDescription }),
            credentials: 'include'
        })
            .then(response => {
                if (response.ok) {
                    alert('Tarefa adicionada com sucesso!');
                    window.location.href = 'index.html';
                } else {
                    throw new Error('Falha ao adicionar tarefa');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Falha ao adicionar tarefa. Por favor, tente novamente.');
            });
    });
});