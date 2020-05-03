let eventWorker;
let simulableWorker;
let simulableList = [];

function receiveData() {
    if(isRunning)startWorkers();
    else stopWorkers();
}

function startWorkers() {
    startEventWorker();
    //startSimulableWorker();
}

function startEventWorker() {
    if (typeof (eventWorker) == "undefined") {
        eventWorker = new Worker("JS/eventWorker.js");
    }
    eventWorker.onmessage = function (response) {
        $('#text-area').val(response.data);
    };
    eventWorker.postMessage("Work");
}

function startSimulableWorker() {
    if (typeof (simulableWorker) == "undefined") {
        simulableWorker = new Worker("JS/simulableWorker.js");
    }
    simulableWorker.onmessage = function (response) {
        $('#table').html(response.data);
    };
    simulableWorker.postMessage("Work");
}


function stopWorkers(){
    if(eventWorker !== undefined){
        eventWorker.terminate();
        eventWorker = undefined;
    }
    if(simulableWorker !== undefined){
        simulableWorker.terminate();
        simulableWorker = undefined;
    }
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
            receiveData();
        });
    });
    $('#restart').click(function() {
        let command = $('#restartCommand').val();
        $.post('FrontControllerServlet', {
            command: command
        }, function() {
            isRunning = false;
            $('#text-area').val("");
        });
    });
    $('#search').click(function() {
        let command = $('#searchCommand').val();
        let searchText = $('#searchText').val();
        let searchBy = $('#searchOptions').val();
        let type = $('#simulableOptions').val();
        $.post('FrontControllerServlet', {
            command: command,
            searchText: searchText,
            searchBy: searchBy,
            type: type
        }, function(response) {
            $('#table').html(response);
        });
    });
    $('#table').on('click', '#searchTable tr' ,function() {
        console.log($(this).find("td"));
        let NIF = $(this).find("td")[0].innerHTML;
        $.post('FrontControllerServlet', {
            command: "FollowSimulableCommand",
            NIF: NIF
        });
    });
});