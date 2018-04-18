package servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.ControllerWrapper;

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("imgID") != null )
		  {
		   
		    String iNumPhoto = request.getParameter("imgID");   
		  
		    try
		    {  
		    	ControllerWrapper controller = new ControllerWrapper();
		  
		       // get the image from the database
		       byte[] imgData = controller.getPhoto(iNumPhoto) ;   
		       // display the image
		       response.setContentType("image/jpg");
		       OutputStream o = response.getOutputStream();
		       o.write(imgData);
		       o.flush(); 
		       o.close();
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		    }  
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
