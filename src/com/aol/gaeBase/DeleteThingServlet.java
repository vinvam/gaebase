package com.aol.gaeBase;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aol.gaeBase.model.Thing;

@SuppressWarnings("serial")
public class DeleteThingServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
		internalDelete(id);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Removed a thing " + id);
	}

	private void internalDelete(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Thing t = pm.getObjectById(Thing.class, Long.parseLong(id));
		pm.deletePersistent(t);
	}
}
