document.addEventListener("DOMContentLoaded", function() {
    const optionsVisitors = {
        series: [{
            name: "Visitors",
            data: [31, 40, 28, 51, 42, 109, 100]
        }],
        chart: {
            type: 'line',
            height: '250'
        },
        xaxis: {
            categories: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
        }
    };

    const optionsRegistrations = {
        series: [{
            name: "Registrations",
            data: [10, 20, 15, 30, 20, 40, 30]
        }],
        chart: {
            type: 'area',
            height: '250'
        },
        xaxis: {
            categories: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
        }
    };

    const visitorsChart = new ApexCharts(document.querySelector("#visitorsChart"), optionsVisitors);
    visitorsChart.render();

    const registrationsChart = new ApexCharts(document.querySelector("#registrationsChart"), optionsRegistrations);
    registrationsChart.render();
});
