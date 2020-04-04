let worker;

function receiveEvents() {
    if(isRunning)startWorker();
    else stopWorker();
}

function startWorker() {
    if (typeof (worker) == "undefined") {
        worker = new Worker("JS/eventWorker.js");
    }
    worker.onmessage = function (response) {
        $('#text-area').val(response.data);
    };
    worker.postMessage("Work");
}

function stopWorker(){
    worker.terminate();
    worker = undefined;
}

$(document).ready(function() {
    $('#changeSpeed').click(function() {
        let speed = $('#speed').val();
        let command = $('#speedCommand').val();
        $.post('FrontControllerServlet', {
            speed : speed,
            command: command
        });
    });
    $('#start').click(function() {
        let command = $('#startCommand').val();
        $.post('FrontControllerServlet', {
            command: command
        }, function() {
            isRunning = !isRunning;
            receiveEvents();
        });
    });
    $('#restart').click(function() {
        let command = $('#restartCommand').val();
        $.post('FrontControllerServlet', {
            command: command
        }, function() {
            isRunning = false;
            document.getElementById('#text-area').value = "";
        });
    });
});