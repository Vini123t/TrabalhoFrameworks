// add-tarefa.js
document.addEventListener('DOMContentLoaded', function() {
    const formAddTarefa = document.getElementById('formAddTarefa');

    formAddTarefa.addEventListener('submit', function(e) {
        e.preventDefault();

        const descricao = document.getElementById('descricao').value;
        const dataInicio = document.getElementById('dataInicio').value;
        const dataLimite = document.getElementById('dataLimite').value;

        fetch('/api/tarefas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('authToken')
            },
            body: JSON.stringify({
                descricao,
                dataInicio: new Date(dataInicio).toISOString(),
                dataLimite: new Date(dataLimite).toISOString()
            })
        })
            .then(response => {
                if (response.ok) {
                    alert('Tarefa adicionada com sucesso!');
                    window.location.href = '/index.html';
                } else {
                    throw new Error('Falha ao adicionar tarefa');
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Falha ao adicionar tarefa. Por favor, tente novamente.');
            });
    });
});