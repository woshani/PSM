<!--   Core JS Files   -->
<script src="../assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../assets/js/material.min.js" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="../assets/js/chartist.min.js"></script>
<!--  Dynamic Elements plugin -->
<script src="../assets/js/arrive.min.js"></script>
<!--  PerfectScrollbar Library -->
<script src="../assets/js/perfect-scrollbar.jquery.min.js"></script>
<!--  Notifications Plugin    -->
<script src="../assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<!-- <script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script> -->
<!-- Material Dashboard javascript methods -->
<script src="../assets/js/material-dashboard.js?v=1.2.0"></script>
<!-- XLSX javascript methods -->
<script src="../assets/js/xlsx.full.min.js"></script>
<!-- <script src="assets/js/xls.js"></script> -->
<script>
function shownoti(messagee,typee){
	$.notify({
        icon: "",
        message: messagee

    }, {
        type: typee,
        timer: 4000,
        placement: {
            from: "top",
            align: "center"
        }
    });
}
function showbarchart(dataChart,idDiv){
    var optionbarchart = {
            axisX: {
                showGrid: false
            },
            low: 0,
            high: 1000,
            chartPadding: {
                top: 0,
                right: 5,
                bottom: 0,
                left: 0
            }
        };
        var responsiveOptions = [
            ['screen and (max-width: 640px)', {
                seriesBarDistance: 5,
                axisX: {
                    labelInterpolationFnc: function(value) {
                        return value[0];
                    }
                }
            }]
        ];
        var barcharts = Chartist.Bar(idDiv, dataChart, optionbarchart, responsiveOptions);

        //start animation for the charts
        md.startAnimationForBarChart(barcharts);
}


</script>

