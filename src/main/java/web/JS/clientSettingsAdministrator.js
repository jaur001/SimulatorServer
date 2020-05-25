let timeout;

function getRestaurantGroups() {
    let restaurantGroups = [];
    let size = document.getElementById("restaurantGroups").rows.length;
    if(size <= 1) return null;
    for(let i = 1; i < size; i++){
        restaurantGroups.push(document.getElementById("restaurantGroups").rows[i].innerText.replace("\t",","));
    }
    return restaurantGroups;
}

function updateData() {
    let salaryMean = $('#salaryMean').val();
    let salarySd = $('#salarySd').val();
    $('#currentSalarySd').text(salarySd + "%");
    let minSalary = $('#minSalary').val();
    let invitedPeopleMin = $('#invitedPeopleMin').val();
    let invitedPeopleMax = $('#invitedPeopleMax').val();
    let numOfRestaurantMin = $('#numOfRestaurantMin').val();
    let numOfRestaurantMax = $('#numOfRestaurantMax').val();
    let plateMean = $('#plateMean').val();
    let plateSd = $('#plateSd').val();
    $('#currentPlateSd').text(plateSd + "%");
    let restaurantGroups = getRestaurantGroups();
    $.post('FrontControllerServlet', {
        salaryMean : salaryMean,
        salarySd : (salaryMean * salarySd)/100.0,
        minSalary : minSalary,
        invitedPeopleMin : invitedPeopleMin,
        invitedPeopleMax : invitedPeopleMax,
        numOfRestaurantMin : numOfRestaurantMin,
        numOfRestaurantMax : numOfRestaurantMax,
        restaurantGroups : restaurantGroups,
        plateMean : plateMean,
        plateSd : (plateMean * plateSd)/100.0,
        command: "UpdateClientDataCommand"
    });
}

function updateDataWithControl(){
    clearTimeout(timeout);
    timeout = setTimeout(updateData,1000);
}

$(document).ready(function() {
    $('#salaryMean').change(function() {
        updateData();
    });
    $('#salarySd').change(function() {
        updateData();
    });
    $('#minSalary').change(function() {
        updateData();
    });
    $('#invitedPeopleMin').change(function() {
        updateData();
    });
    $('#invitedPeopleMax').change(function() {
        updateData();
    });
    $('#numOfRestaurantMin').change(function() {
        updateData();
    });
    $('#numOfRestaurantMax').change(function() {
        updateData();
    });
    $('#plateMean').change(function() {
        updateData();
    });
    $('#plateSd').change(function() {
        updateData();
    });
});