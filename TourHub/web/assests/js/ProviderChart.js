/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Function to generate a random color in rgba format
function getRandomColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    return `rgba(${r}, ${g}, ${b}, 1)`; // Fully opaque color
}
function fetchMonthlyBookings() {
    // Fetch the monthly bookings from the servlet
    fetch('/Project_SWP/charts') // Adjust the path based on your web application's context
            .then(response => {
                if (!response.ok) {
                    console.error('Fetch error: ', response.status, response.statusText);
                    throw new Error('Network response was not ok');
                }
                return response.json(); // Parse response only if it's ok
            })
            .then(data => {
                // Extract months and total bookings from the response
                const months = data.monthlyBookings.map(entry => entry.month);
                const totalBookings = data.monthlyBookings.map(entry => entry.totalBookings);

                // Log the data for each month
                months.forEach((month, index) => {
                    console.log(`Month: ${month}, Total Bookings: ${totalBookings[index]}`);
                });

                // Create the chart with the fetched data
                createChart(months, totalBookings);
            })
            .catch(error => console.error('Error fetching booking data:', error));
}

function createChart(months, totalBookings) {
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: months.map(month => `Month ${month}`), // Adjust labels if necessary
            datasets: [{
                    label: 'Monthly Bookings',
                    data: totalBookings,
                    borderColor: getRandomColor(), // Random border color
                    backgroundColor: getRandomColor().replace('1)', '0.2)'), // Random background color with transparency
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function fetchMonthlyProfits() {
    fetch('/Project_SWP/charts')
            .then(response => {
                if (!response.ok) {
                    console.error('Fetch error: ', response.status, response.statusText);
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log(data); // Log the entire response for inspection

                const monthlyProfitsThisYear = data.monthlyProfitsThisYear;
                const monthlyProfitsLastYear = data.monthlyProfitsLastYear;

                // Labels for each month
                const months = [
                    'January', 'February', 'March', 'April', 'May', 'June',
                    'July', 'August', 'September', 'October', 'November', 'December'
                ];

                // Prepare data for chart for this year
                const profitsThisYear = [];
                monthlyProfitsThisYear.forEach(entry => {
                    console.log(entry); // Log each entry to see its structure
                    const totalProfit = entry.profit || 0; // Access the totalProfit property, default to 0
                    profitsThisYear.push(totalProfit);
                });

                // Prepare data for chart for last year
                const profitsLastYear = [];
                monthlyProfitsLastYear.forEach(entry => {
                    console.log(entry); // Log each entry to see its structure
                    const totalProfit = entry.profit || 0; // Access the totalProfit property, default to 0
                    profitsLastYear.push(totalProfit);
                });

                // Create the chart with the fetched data
                createProfitChart(months, profitsThisYear, profitsLastYear);
            })
            .catch(error => {
                console.error('Error fetching profit data:', error);
            });
}

function createProfitChart(months, profitsThisYear, profitsLastYear) {
    // Get the canvas element by its ID
    var ctx = document.getElementById('multiLineChart').getContext('2d');
    // Create a new chart instance
    window.multiLineChart = new Chart(ctx, {
        type: 'line', // Line chart for multiple datasets
        data: {
            labels: months, // X-axis labels
            datasets: [
                {
                    label: 'This Year', // Label for current year profits
                    data: profitsThisYear, // Data points for this year's profits
                    borderColor: getRandomColor(), // Random border color
                    backgroundColor: getRandomColor().replace('1)', '0.2)'), // Random background color with transparency
                    borderWidth: 2,
                    fill: true // Filling the area under the line
                },
                {
                    label: 'Last Year', // Label for last year's profits
                    data: profitsLastYear, // Data points for last year's profits
                    borderColor: getRandomColor(), // Random border color
                    backgroundColor: getRandomColor().replace('1)', '0.2)'), // Random background color with transparency
                    borderWidth: 2,
                    fill: true // Filling the area under the line
                }
            ]
        },
        options: {
            responsive: true, // Ensures that the chart scales properly on different screen sizes
            scales: {
                y: {
                    beginAtZero: true // Starts the Y-axis at zero
                }
            },
            plugins: {
                legend: {
                    position: 'top', // Positions the legend at the top
                }
            }
        }
    });
}

function fetchPieChartData() {
    fetch('/Project_SWP/charts')
            .then(response => {
                if (!response.ok) {
                    console.error('Fetch error: ', response.status, response.statusText);
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Parsed JSON Data:', data);

                const hotDestinations = data.hotDestinations || [];
                const categoryLabels = hotDestinations.map(dest => dest.location);
                const categoryData = hotDestinations.map(dest => dest.count);

                if (categoryLabels.length > 0 && categoryData.length > 0) {
                    createPieChart(categoryLabels, categoryData);
                } else {
                    console.warn('No data available for the pie chart.');
                }
            })
            .catch(error => {
                console.error('Error fetching pie chart data:', error);
            });
}

function createPieChart(labels, data) {
    var ctx = document.getElementById('circleChart').getContext('2d');

    if (window.circleChart) {
        window.circleChart.destroy();
    }

    window.circleChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                    label: 'Category Proportion',
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top'
                },
                tooltip: {
                    enabled: true
                }
            }
        }
    });
}


document.addEventListener('DOMContentLoaded', function () {
    fetchMonthlyBookings();
    fetchMonthlyProfits();
    fetchPieChartData();
});
