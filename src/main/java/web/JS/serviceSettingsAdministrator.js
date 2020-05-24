let timeout;

function getProductSalePrices() {
    let serviceSalePrices = [];
    let size = document.getElementById("serviceSalePrice").rows.length;
    if(size <= 1) return null;
    for(let i = 1; i < size; i++){
        serviceSalePrices.push(document.getElementById("serviceSalePrice").rows[i].innerText.replace("\t",","));
    }
    return serviceSalePrices;
}

function updateData() {
    let initialSocialCapital = $('#initialSocialCapital').val();
    let priceChange = $('#priceChange').val();
    $('#currentPriceChange').text(priceChange*100.0 + "%");
    let closeLimit = $('#closeLimit').val();
    let serviceSalePrices = getProductSalePrices();
    $.post('FrontControllerServlet', {
        initialSocialCapital : initialSocialCapital,
        priceChange : priceChange,
        closeLimit : closeLimit,
        serviceSalePrices : serviceSalePrices,
        command: "UpdateServiceDataCommand"
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
    $('#closeLimit').change(function() {
        updateData();
    });
    $('#priceChange').change(function() {
        updateData();
    });
});