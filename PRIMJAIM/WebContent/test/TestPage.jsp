<html>  
  <head>  
    <title>test</title>  
  </head>  
  <body>  
    <form name="form1" id="form1" action="T.jsp">  
      <input type="text" name="ta1" id="ta1" disabled="disabled" value="HSHSHS">
      <!-- <textarea name="ta1" id="ta1" cols ="10" rows="10" disabled="disabled"></textarea>   -->
      <input type="button" name="btn1" onclick="document.form1.ta1.disabled = false; return false;" value="test1" />  
      <input type="submit" name="btn2" value="Test" />  
    </form>  
  </body>  
</html>  