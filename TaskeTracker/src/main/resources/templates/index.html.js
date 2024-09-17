// index.js
document.addEventListener('DOMContentLoaded', function() {
    const newTaskBtn = document.querySelector('.btn-outline-primary[href="#"]');
    const newHabitBtn = newTaskBtn.nextElementSibling;
    const taskList = document.querySelector('#tasks');
    const habitList = document.querySelector('#habits');

    function fetchTasks() {
        fetch('/api/tasks', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(response => response.json())
            .then(tasks => {
                taskList.innerHTML = '';
                tasks.forEach(task => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                    <span class="${task.completed ? 'text-decoration-line-through' : ''}">${task.title}</span>
                    <div>
                        <button class="btn btn-sm btn-outline-secondary" onclick="toggleTaskCompletion(${task.id})">
                            ${task.completed ? 'Desmarcar' : 'Marcar'}
                        </button>
                        <button class="btn btn-sm btn-outline-danger" onclick="deleteTask(${task.id})">Excluir</button>
                    </div>
                `;
                    taskList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching tasks:', error));
    }

    function fetchHabits() {
        fetch('/api/habits', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(response => response.json())
            .then(habits => {
                habitList.innerHTML = '';
                habits.forEach(habit => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                    <span>${habit.title}</span>
                    <div>
                        <button class="btn btn-sm btn-outline-success" onclick="toggleHabitCompletion(${habit.id})">
                            Marcar
                        </button>
                    </div>
                `;
                    habitList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching habits:', error));
    }

    window.toggleTaskCompletion = function(taskId) {
        fetch(`/api/tasks/${taskId}/toggle`, {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(response => response.json())
            .then(() => fetchTasks())
            .catch(error => console.error('Error toggling task:', error));
    };

    window.deleteTask = function(taskId) {
        if (confirm('Tem certeza que deseja excluir esta tarefa?')) {
            fetch(`/api/tasks/${taskId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                }
            })
                .then(() => fetchTasks())
                .catch(error => console.error('Error deleting task:', error));
        }
    };

    window.toggleHabitCompletion = function(habitId) {
        fetch(`/api/habits/${habitId}/toggle`, {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(response => response.json())
            .then(() => fetchHabits())
            .catch(error => console.error('Error toggling habit:', error));
    };

    newTaskBtn.addEventListener('click', function(e) {
        e.preventDefault();
        window.location.href = '/add-task';
    });

    newHabitBtn.addEventListener('click', function(e) {
        e.preventDefault();
        // Implement new habit functionality or redirect to a new habit page
        alert('New habit functionality to be implemented');
    });

    // Fetch tasks and habits when the page loads
    fetchTasks();
    fetchHabits();
});