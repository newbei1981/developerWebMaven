package com.newbei.developerInfoWeb.controller.servlets;

import com.newbei.developerInfoWeb.controller.servlets.adapter.ParametersAdapter;
import com.newbei.developerInfoWeb.model.Account;
import com.newbei.developerInfoWeb.model.Developer;
import com.newbei.developerInfoWeb.model.Skill;
import com.newbei.developerInfoWeb.repository.HibernateDeveloperRepositoryImpl;
import com.newbei.developerInfoWeb.repository.HibernateSkillRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UpdateDeveloper extends HttpServlet {

    private final HibernateDeveloperRepositoryImpl developerRepository = new HibernateDeveloperRepositoryImpl();
    private final HibernateSkillRepositoryImpl skillRepository = new HibernateSkillRepositoryImpl();
    private boolean isUpdated;
    private Developer developer;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (!isUpdated) {
         HttpSession session = req.getSession();
         developer = (Developer)session.getAttribute("developer");
         isUpdated = true;
       }
        req.setAttribute("developer",developer);
        req.setAttribute("skills",skillRepository.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("developer.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (!isUpdated) doGet(req,resp);
        ParametersAdapter parametersAdapter = new ParametersAdapter(developer);
        long id = developer.getId();
        developer = parametersAdapter.getUpdatedDeveloperParameter(req);
        developer.setId(id);
        developer = developerRepository.update(developer);
        doGet(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        developerRepository.closeSessionFactory();
        skillRepository.closeSessionFactory();
    }


}
