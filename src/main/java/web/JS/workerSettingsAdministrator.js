function updateData() {
    let minSalary = $('#minSalary').val();
    let salaryChange = $('#salaryChange').val();
    $('#currentSalaryChange').text(salaryChange + "%");
    let salaryDesiredChange = $('#salaryDesiredChange').val();
    $('#currentSalaryDesiredChange').text(salaryDesiredChange + "‰");
    let retireAge = $('#retireAge').val();
    let percentageRetirement = $('#percentageRetirement').val();
    $('#currentPercentageRetirement').text(percentageRetirement + "%");
    $.post('FrontControllerServlet', {
        minSalary : minSalary,
        salaryChange : salaryChange/100.0,
        salaryDesiredChange : salaryDesiredChange/1000.0,
        retireAge : retireAge,
        percentageRetirement : percentageRetirement/100.0,
        command: "UpdateWorkerDataCommand"
    });
}

$(document).ready(function() {
    $('#minSalary').change(function() {
        updateData();
    });
    $('#salaryChange').change(function() {
        updateData();
    });
    $('#salaryDesiredChange').change(function() {
        updateData();
    });
    $('#retireAge').change(function() {
        updateData();
    });
    $('#percentageRetirement').change(function() {
        updateData();
    });
});