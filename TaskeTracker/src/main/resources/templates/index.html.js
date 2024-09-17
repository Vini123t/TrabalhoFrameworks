// index.js
document.addEventListener('DOMContentLoaded', function() {
    const novaTarefaBtn = document.querySelector('#novaTarefa');
    const novoHabitoBtn = document.querySelector('#novoHabito');
    const listaTarefas = document.querySelector('#listaTarefas');
    const listaHabitos = document.querySelector('#listaHabitos');

    function fetchTarefas() {
        fetch('/api/tarefas', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(response => response.json())
            .then(tarefas => {
                listaTarefas.innerHTML = '';
                tarefas.forEach(tarefa => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                    <span class="${tarefa.concluida ? 'text-decoration-line-through' : ''}">
                        ${tarefa.descricao} (Início: ${new Date(tarefa.dataInicio).toLocaleDateString()} - 
                        Limite: ${new Date(tarefa.dataLimite).toLocaleDateString()})
                    </span>
                    <div>
                        <button onclick="alternarConclusaoTarefa(${tarefa.id})">
                            ${tarefa.concluida ? 'Desmarcar' : 'Marcar'}
                        </button>
                        <button onclick="deletarTarefa(${tarefa.id})">Excluir</button>
                    </div>
                `;
                    listaTarefas.appendChild(li);
                });
            })
            .catch(error => console.error('Erro ao buscar tarefas:', error));
    }

    function fetchHabitos() {
        fetch('/api/habitos', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(response => response.json())
            .then(habitos => {
                listaHabitos.innerHTML = '';
                habitos.forEach(habito => {
                    const li = document.createElement('li');
                    li.innerHTML = `
                    <span>${habito.descricao}</span>
                    <div>
                        <button onclick="marcarHabitoComoFeito(${habito.id})">Marcar como feito</button>
                    </div>
                `;
                    listaHabitos.appendChild(li);
                });
            })
            .catch(error => console.error('Erro ao buscar hábitos:', error));
    }

    window.alternarConclusaoTarefa = function(tarefaId) {
        fetch(`/api/tarefas/${tarefaId}/toggle`, {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(() => fetchTarefas())
            .catch(error => console.error('Erro ao alternar conclusão da tarefa:', error));
    };

    window.deletarTarefa = function(tarefaId) {
        if (confirm('Tem certeza que deseja excluir esta tarefa?')) {
            fetch(`/api/tarefas/${tarefaId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                }
            })
                .then(() => fetchTarefas())
                .catch(error => console.error('Erro ao deletar tarefa:', error));
        }
    };

    window.marcarHabitoComoFeito = function(habitoId) {
        fetch(`/api/habitos/${habitoId}/marcar`, {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            }
        })
            .then(() => fetchHabitos())
            .catch(error => console.error('Erro ao marcar hábito como feito:', error));
    };

    novaTarefaBtn.addEventListener('click', function() {
        window.location.href = '/add-tarefa.html';
    });

    novoHabitoBtn.addEventListener('click', function() {
        window.location.href = '/add-habito.html';
    });

    // Buscar tarefas e hábitos quando a página carregar
    fetchTarefas();
    fetchHabitos();
});