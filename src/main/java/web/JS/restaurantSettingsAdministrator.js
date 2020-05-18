let timeout;

function getJobsSalary() {
    let jobSalaries = [];
    let size = document.getElementById("jobSalary").rows.length;
    if(size <= 1) return null;
    for(let i = 1; i < size; i++){
        jobSalaries.push(document.getElementById("jobSalary").rows[i].innerText.replace("\t",","));
    }
    return jobSalaries;
}

function updateData() {
    let initialSocialCapital = $('#initialSocialCapital').val();
    let capacity = $('#capacity').val();
    $('#currentCapacity').text(capacity + "%");
    let priceChange = $('#priceChange').val();
    $('#currentPriceChange').text(priceChange);
    let closeLimit = $('#closeLimit').val();
    let lengthContractMin = $('#lengthContractMin').val();
    let lengthContractMax = $('#lengthContractMax').val();
    let jobSalaries = getJobsSalary();
    $.post('FrontControllerServlet', {
        initialSocialCapital : initialSocialCapital,
        capacity : capacity/100.0,
        priceChange : priceChange,
        closeLimit : closeLimit,
        lengthContractMin : lengthContractMin,
        lengthContractMax : lengthContractMax,
        jobSalaries : jobSalaries,
        command: "UpdateRestaurantDataCommand"
    });
}

function updateDataWithControl(){
    clearTimeout(timeout);
    timeout = setTimeout(updateData,1000);
}

$(document).ready(function() {
    $('#initialSocialCapital').change(function() {
        updateData();
    });
    $('#capacity').change(function() {
        updateData();
    });
    $('#closeLimit').change(function() {
        updateData();
    });
    $('#priceChange').change(function() {
        updateData();
    });
    $('#lengthContractMin').change(function() {
        updateData();
    });
    $('#lengthContractMax').change(function() {
        updateData();
    });
});