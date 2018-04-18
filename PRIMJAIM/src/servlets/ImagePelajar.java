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
 * Servlet implementation class ImagePelajar
 */
@WebServlet("/ImagePelajar")
public class ImagePelajar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImagePelajar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("studentID") != null )
		  {
		   
		    String iNumPhoto = request.getParameter("studentID");   
		  
		    try
		    {  
		    	
		    	ControllerWrapper controller = new ControllerWrapper();
		  
		       // get the image from the database
		       byte[] imgData = controller.getPhotoPelajar(iNumPhoto);   
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
