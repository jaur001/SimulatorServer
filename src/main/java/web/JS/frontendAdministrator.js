let eventWorker;
let personWorker;
let companyWorker;
let simulableCountWorker;
let isRunning = false;
startWorkers();
setTimeout(stopWorkers, 500);

function changeOptions(){
    let val = $('#simulableOptions').val();
    if (val === "person") {
        $("#searchOptions").html("<option value='NIF'>NIF</option>" +
            "<option value='Name'>Name</option>" +
            "<option value='Job'>Job</option>" +
            "<option value='Salary'>Salary</option>");
    } else {
        $("#searchOptions").html("<option value='NIF'>NIF</option>" +
            "<option value='Name'>Name</option>" +
            "<option value='Benefits'>Benefits</option>" +
            "<option value='Treasury'>Treasury</option>");
    }
}

function receiveData() {
    if(isRunning)startWorkers();
    else stopWorkers();
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

function startSimulableWorkers() {
    startPersonWorker();
    startCompanyWorker();
    startSimulableCountWorker()
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

function startSimulableCountWorker() {
    if (typeof (simulableCountWorker) == "undefined") {
        simulableCountWorker = new Worker("JS/simulableCountWorker.js");
    }
    simulableCountWorker.onmessage = function (response) {
        let counts = response.data.split(" ");
        document.getElementById("simulableCountTable").rows[1].cells[0].innerHTML = counts[0];
        document.getElementById("simulableCountTable").rows[1].cells[1].innerHTML = counts[1];
        document.getElementById("simulableCountTable").rows[1].cells[2].innerHTML = counts[2];
        document.getElementById("simulableCountTable").rows[1].cells[3].innerHTML = counts[3];
        document.getElementById("simulableCountTable").rows[1].cells[4].innerHTML = counts[4];
    };
    simulableCountWorker.postMessage("Work");
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
    if(simulableCountWorker !== undefined){
        simulableCountWorker.terminate();
        simulableCountWorker = undefined;
    }
}


function updateQuickSettings() {
    let speed = $('#speed').val();
    $('#currentSpeed').text(speed + "%");
    let clientProb = $('#clientProb').val();
    $('#currentClientProb').text(clientProb + "%");
    let restaurantProb = $('#restaurantProb').val();
    $('#currentRestaurantProb').text(restaurantProb + "%");
    let providerProb = $('#providerProb').val();
    $('#currentProviderProb').text(providerProb + "%");
    let serviceProb = $('#serviceProb').val();
    $('#currentServiceProb').text(serviceProb + "%");
    let workerProb = $('#workerProb').val();
    $('#currentWorkerProb').text(workerProb + "%");
    let taxes = $('#taxes').val();
    $('#currentTaxes').text(taxes + "%");
    $.post('FrontControllerServlet', {
        speed: speed,
        clientProb : clientProb,
        restaurantProb : restaurantProb,
        providerProb : providerProb,
        serviceProb : serviceProb,
        workerProb : workerProb,
        taxes : taxes,
        command: "UpdateQuickSettingsCommand"
    });
}

function updateChanges() {
    startSimulableWorkers();
    setTimeout(stopSimulableWorkers, 500);
}

$(document).ready(function() {
    $('#speed').on('change',function() {
        updateQuickSettings();
    });
    $('#clientProb').on('change',function() {
        updateQuickSettings();
    });
    $('#restaurantProb').on('change',function() {
        updateQuickSettings();
    });
    $('#providerProb').on('change',function() {
        updateQuickSettings();
    });
    $('#serviceProb').on('change',function() {
        updateQuickSettings();
    });
    $('#workerProb').on('change',function() {
        updateQuickSettings();
    });
    $('#taxes').on('change',function() {
        updateQuickSettings();
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
        updateChanges();
    });
    $('#divPersonTable').on('click', '#personTable tr' ,function() {
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
            console.log("xd");
            $.post('FrontControllerServlet', {
                command: "UnfollowSimulableCommand",
                NIF: NIF
            });
            updateChanges();
        }
    });
    $('#divCompanyTable').on('click', '#companyTable tr' ,function() {
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
            console.log("xd");
            $.post('FrontControllerServlet', {
                command: "UnfollowSimulableCommand",
                NIF: NIF
            });
            updateChanges();
        }
    });
});