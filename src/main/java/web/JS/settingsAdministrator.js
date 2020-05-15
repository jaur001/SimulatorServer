function updateData() {
    let receivedClient = $('#clients').val();
    $('#currentClient').text(receivedClient);
    let receivedRestaurant = $('#restaurants').val();
    $('#currentRestaurant').text(receivedRestaurant);
    let receivedProvider = $('#providers').val();
    $('#currentProvider').text(receivedProvider);
    let receivedService = $('#services').val();
    $('#currentService').text(receivedService);
    let receivedWorker = $('#workers').val();
    $('#currentWorker').text(receivedWorker);
    $.post('FrontControllerServlet', {
        clientCount : receivedClient,
        restaurantCount : receivedRestaurant,
        providerCount : receivedProvider,
        serviceCount : receivedService,
        workerCount : receivedWorker,
        command: "UpdateGeneralDataCommand"
    });
}

$(document).ready(function() {
    $('#clients').change(function() {
        updateData();
    });
    $('#restaurants').change(function() {
        updateData();
    });
    $('#providers').change(function() {
        updateData();
    });
    $('#services').change(function() {
        updateData();
    });
    $('#workers').change(function() {
        updateData();
    });
});
