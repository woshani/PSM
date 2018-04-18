
<html>
<head>
<title>(Type a title for your page here)</title>
<script type="text/javascript">
(function() {
    $('form > input').keyup(function() {

        var empty = false;
        $('form > input').each(function() {
            if ($(this).val() == '') {
                empty = true;
            }
        });

        if (empty) {
            $('#register').attr('disabled', 'disabled');
        } else {
            $('#register').removeAttr('disabled');
        }
    });
})()
</script>
</head>

<body>


<form>
    Username<br />
    <input type="text" id="user_input" name="username" /><br />
    Password<br />
    <input type="password" id="pass_input" name="password" /><br />
    Confirm Password<br />
    <input type="password" id="v_pass_input" name="v_password" /><br />
    Email<br />
    <input type="text" id="email" name="email" /><br />     
    <input type="submit" id="register" value="Register" disabled="disabled" />
</form>
<div id="test">
</div>

</body>
</html>