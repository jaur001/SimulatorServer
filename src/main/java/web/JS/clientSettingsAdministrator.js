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
    let salaryMin = $('#salaryMin').val();
    let invitedPeopleMin = $('#invitedPeopleMin').val();
    let invitedPeopleMax = $('#invitedPeopleMax').val();
    let numOfRestaurantMin = $('#numOfRestaurantMin').val();
    let numOfRestaurantMax = $('#numOfRestaurantMax').val();
    let restaurantGroups = getRestaurantGroups();
    $.post('FrontControllerServlet', {
        salaryMean : salaryMean,
        salarySd : (salaryMean * salarySd)/100.0,
        salaryMin : salaryMin,
        invitedPeopleMin : invitedPeopleMin,
        invitedPeopleMax : invitedPeopleMax,
        numOfRestaurantMin : numOfRestaurantMin,
        numOfRestaurantMax : numOfRestaurantMax,
        restaurantGroups : restaurantGroups,
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
    $('#salaryMin').change(function() {
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
});