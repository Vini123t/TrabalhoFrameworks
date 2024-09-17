// habit-history.js
document.addEventListener('DOMContentLoaded', function() {
    const backButton = document.querySelector('.btn-outline-secondary');
    const habitHistoryTable = document.getElementById('habitHistoryTable');

    backButton.addEventListener('click', function(e) {
        e.preventDefault();
        window.location.href = '/index';
    });

    function fetchHabitHistory() {
        fetch('/api/habits/history', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(response => response.json())
            .then(data => {
                const tbody = habitHistoryTable.querySelector('tbody');
                tbody.innerHTML = '';

                data.forEach(entry => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                    <td>${entry.habit}</td>
                    <td>${new Date(entry.date).toLocaleDateString()}</td>
                    <td>${entry.completed ? 'Concluído' : 'Não Concluído'}</td>
                `;
                    tbody.appendChild(row);
                });
            })
            .catch(error => console.error('Error fetching habit history:', error));
    }

    // Carregar o histórico de hábitos quando a página for carregada
    fetchHabitHistory();
});