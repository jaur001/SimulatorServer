let eventWorker;
let personWorker;
let companyWorker;

function receiveData() {
    if(isRunning)startWorkers();
    else stopWorkers();
}

function startSimulableWorkers() {
    startPersonWorker();
    startCompanyWorker();
}

function startWorkers() {
    startEventWorker();
    startSimulableWorkers();
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

function startPersonWorker() {
    if (typeof (personWorker) == "undefined") {
        personWorker = new Worker("JS/personWorker.js");
    }
    personWorker.onmessage = function (response) {
        $('#personTable').html(response.data);
    };
    personWorker.postMessage("Work");
}


function startCompanyWorker() {
    if (typeof (companyWorker) == "undefined") {
        companyWorker = new Worker("JS/companyWorker.js");
    }
    companyWorker.onmessage = function (response) {
        $('#companyTable').html(response.data);
    };
    companyWorker.postMessage("Work");
}

function stopWorkers(){
    if(eventWorker !== undefined){
        eventWorker.terminate();
        eventWorker = undefined;
    }
    stopSimulableWorkers();
}

function stopSimulableWorkers(){
    if(personWorker !== undefined){
        personWorker.terminate();
        personWorker = undefined;
    }
    if(companyWorker !== undefined){
        companyWorker.terminate();
        companyWorker = undefined;
    }
}

function updateTables() {
    if (isRunning) {
        stopSimulableWorkers();
        let NIF = $(this).find("td")[0].innerHTML;
        $.post('FrontControllerServlet', {
            command: "UnfollowSimulableCommand",
            NIF: NIF
        });
        startSimulableWorkers();
    } else {
        let NIF = $(this).find("td")[0].innerHTML;
        $.post('FrontControllerServlet', {
            command: "UnfollowSimulableCommand",
            NIF: NIF
        });
        startSimulableWorkers();
        setTimeout(stopSimulableWorkers, 500);
    }
}

$(document).ready(function() {
    $('#speed').on('change',function() {
        let speed = $('#speed').val();
        $('#currentSpeed').text(speed + "%");
        $.post('FrontControllerServlet', {
            speed : speed,
            command: "ChangeSpeedCommand"
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
            $('#divSearchTable').html(response);
        });
    });
    $('#deleteSearch').click(function() {
        $('#divSearchTable').html("");
    });
    $('#divSearchTable').on('click', '#searchTable tr' ,function() {
        let NIF = $(this).find("td")[0].innerHTML;
        $.post('FrontControllerServlet', {
            command: "FollowSimulableCommand",
            NIF: NIF
        });
        startSimulableWorkers();
        setTimeout(stopSimulableWorkers,500);
    });
    $('#divPersonTable').on('click', '#personTable tr' ,function() {
        updateTables();
    });
    $('#divCompanyTable').on('click', '#companyTable tr' ,function() {
        updateTables();
    });
});