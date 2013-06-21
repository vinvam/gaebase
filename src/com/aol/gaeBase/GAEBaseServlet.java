package com.aol.gaeBase;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aol.gaeBase.model.Thing;

@SuppressWarnings("serial")
public class GAEBaseServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Thing thing = new Thing();
		try{
			pm.makePersistent(thing);
		}finally{
			pm.close();
		}
		
		pm = PMF.get().getPersistenceManager();
		List<Thing> things;
		try {
			Query q = pm.newQuery(Thing.class);
			things = (List<Thing>) q.execute();
		} finally {
			pm.close();
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("Saved a new thing " + thing.id + " -- total: " + things.size());
	}
}
