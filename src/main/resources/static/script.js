document.addEventListener('DOMContentLoaded', () => {
    fetchTasks();
});

async function fetchTasks() {
    try {
        const response = await fetch('/list');
        const tasks = await response.json();
        const taskList = document.getElementById('taskList');
        taskList.innerHTML = '';

        tasks.forEach(task => {
            const li = document.createElement('li');
            li.innerHTML = `
                <span>${task.content} ${task.status ? '(Completed)' : ''}</span>
                <button class="update" onclick="updateTask(${task.id})">Update</button>
                <button class="remove" onclick="removeTask(${task.id})">Delete</button>
            `;
            taskList.appendChild(li);
        });
    } catch (error) {
        console.error('Error fetching tasks:', error);
    }
}

async function addTask() {
    const content = document.getElementById('taskContent').value;

    if (!content) {
        alert('Please enter a task content.');
        return;
    }

    try {
        await fetch('/list/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ content, status: false })
        });
        fetchTasks();
        document.getElementById('taskContent').value = '';
    } catch (error) {
        console.error('Error adding task:', error);
    }
}

async function removeTask(id) {
    try {
        await fetch(`/list/remove/${id}`, {
            method: 'DELETE'
        });
        fetchTasks();
    } catch (error) {
        console.error('Error removing task:', error);
    }
}

async function updateTask(id) {
    const content = prompt('Enter new task content:');
    const status = confirm('Mark as completed?');

    if (!content) {
        alert('Please enter a task content.');
        return;
    }

    try {
        await fetch('/list/update', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id, content, status })
        });
        fetchTasks();
    } catch (error) {
        console.error('Error updating task:', error);
    }
}
