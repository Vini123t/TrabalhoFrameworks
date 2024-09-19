// index.js
document.addEventListener('DOMContentLoaded', function() {
    const newTaskBtn = document.querySelector('.btn-outline-primary[href="#"]');
    const newHabitBtn = newTaskBtn.nextElementSibling;
    const taskList = document.querySelector('#tasks');
    const habitList = document.querySelector('#habits');

    function fetchTasks() {
        fetch('http://localhost:8080/api/tasks', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            },
            credentials: 'include'
        })
            .then(response => response.json())
            .then(tasks => {
                taskList.innerHTML = '';
                tasks.forEach(task => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                    <span class="${task.completed ? 'text-decoration-line-through' : ''}">${task.title}</span>
                    <div>
                        <button class="btn btn-sm btn-outline-secondary toggle-task" data-id="${task.id}">
                            ${task.completed ? 'Desmarcar' : 'Marcar'}
                        </button>
                        <button class="btn btn-sm btn-outline-danger delete-task" data-id="${task.id}">Excluir</button>
                    </div>
                `;
                    taskList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching tasks:', error));
    }

    function fetchHabits() {
        fetch('http://localhost:8080/api/habits', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            },
            credentials: 'include'
        })
            .then(response => response.json())
            .then(habits => {
                habitList.innerHTML = '';
                habits.forEach(habit => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                    <span>${habit.title}</span>
                    <div>
                        <button class="btn btn-sm btn-outline-success toggle-habit" data-id="${habit.id}">
                            Marcar
                        </button>
                    </div>
                `;
                    habitList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching habits:', error));
    }

    taskList.addEventListener('click', function(e) {
        if (e.target.classList.contains('toggle-task')) {
            const taskId = e.target.getAttribute('data-id');
            fetch(`http://localhost:8080/api/tasks/${taskId}/toggle`, {
                method: 'POST',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                },
                credentials: 'include'
            })
                .then(() => fetchTasks())
                .catch(error => console.error('Error toggling task:', error));
        } else if (e.target.classList.contains('delete-task')) {
            const taskId = e.target.getAttribute('data-id');
            fetch(`http://localhost:8080/api/tasks/${taskId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                },
                credentials: 'include'
            })
                .then(() => fetchTasks())
                .catch(error => console.error('Error deleting task:', error));
        }
    });

    habitList.addEventListener('click', function(e) {
        if (e.target.classList.contains('toggle-habit')) {
            const habitId = e.target.getAttribute('data-id');
            fetch(`http://localhost:8080/api/habits/${habitId}/toggle`, {
                method: 'POST',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                },
                credentials: 'include'
            })
                .then(() => fetchHabits())
                .catch(error => console.error('Error toggling habit:', error));
        }
    });

    newTaskBtn.addEventListener('click', function(e) {
        e.preventDefault();
        window.location.href = 'add-task.html';
    });

    newHabitBtn.addEventListener('click', function(e) {
        e.preventDefault();
        const habitTitle = prompt('Enter new habit title:');
        if (habitTitle) {
            fetch('http://localhost:8080/api/habits', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                },
                body: JSON.stringify({ title: habitTitle }),
                credentials: 'include'
            })
                .then(() => fetchHabits())
                .catch(error => console.error('Error adding habit:', error));
        }
    });

    // Fetch tasks and habits when the page loads
    fetchTasks();
    fetchHabits();
});