<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PRIM@UTeM</title>

<link href="styles/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="styles/jquery-ui-1.10.1.custom.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css" media="all" />

<script type="text/javascript" src="scripts/jquery-1.9.1.js"></script>
<script type="text/javascript" src="scripts/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="scripts/jquery-ui-1.10.1.custom.min.js"></script>
<script type="text/javascript" src="scripts/jquery.validationEngine.js"></script>
<script type="text/javascript"
	src="scripts/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="scripts/jquery.highchartTable.js"></script>

<script type="text/javascript" src="alertifyjs/alertify.js"></script>
<link href="alertifyjs/css/alertify.css" rel="stylesheet" />
<link href="alertifyjs/css/themes/default.css" rel="stylesheet" />

<script type="text/javascript">
	$(function() {
		$(".date").datepicker({
			maxDate : 0
		});
		$('.pickmonth')
				.datepicker(
						{
							maxDate : 0,
							changeMonth : true,
							changeYear : true,
							showButtonPanel : true,
							dateFormat : 'MM yy',
							onClose : function(dateText, inst) {
								var month = $(
										"#ui-datepicker-div .ui-datepicker-month :selected")
										.val();
								var year = $(
										"#ui-datepicker-div .ui-datepicker-year :selected")
										.val();
								$(this).datepicker('setDate',
										new Date(year, month, 1));
							}
						});

		$('.date2').each(function() {
			$(this).removeClass('date2').datepicker();
		});
	});

	$(document).ready(function() {
		$("#LogInForm").validationEngine();
		$("#AddAnnouncementForm").validationEngine();
		$("#SendAnnouncementsForm").validationEngine();
		$("#SendOutgoingSMSForm").validationEngine();
		$("#SearchForm").validationEngine();
	});

	$(function() {
		$("#accordion").accordion();
	});

	function symbol() {
		alert("/ : Hadir @ Aktiviti Sekolah\nO  : Tidak Hadir\nL : Lewat\nAK: Ancaman Keselamatan\nBA: Bencana Alam\nS  : Cuti Sakit\nG : Digantung Sekolah\nMP: Masalah Peribadi\nLL: Lain-lain Sebab\nUK: Urusan Keluarga");
	}
</script>