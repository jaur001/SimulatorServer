let timeout;

function getProductSalePrices() {
    let productSalePrices = [];
    let size = document.getElementById("productSalePrice").rows.length;
    if(size <= 1) return null;
    for(let i = 1; i < size; i++){
        productSalePrices.push(document.getElementById("productSalePrice").rows[i].innerText.replace("\t",","));
    }
    return productSalePrices;
}

function updateData() {
    let initialSocialCapital = $('#initialSocialCapital').val();
    let priceChange = $('#priceChange').val();
    $('#currentPriceChange').text(priceChange*100.0 + "%");
    let closeLimit = $('#closeLimit').val();
    let productSalePrices = getProductSalePrices();
    $.post('FrontControllerServlet', {
        initialSocialCapital : initialSocialCapital,
        priceChange : priceChange,
        closeLimit : closeLimit,
        productSalePrices : productSalePrices,
        command: "UpdateProviderDataCommand"
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