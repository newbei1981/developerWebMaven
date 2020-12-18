package com.newbei.developerInfoWeb.controller.servlets;

import com.newbei.developerInfoWeb.controller.servlets.adapter.ParametersAdapter;
import com.newbei.developerInfoWeb.model.Account;
import com.newbei.developerInfoWeb.model.AccountStatus;
import com.newbei.developerInfoWeb.model.Developer;
import com.newbei.developerInfoWeb.repository.DeveloperRepository;
import com.newbei.developerInfoWeb.repository.HibernateDeveloperRepositoryImpl;
import com.newbei.developerInfoWeb.repository.HibernateSkillRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveDeveloper extends HttpServlet {

    private HibernateSkillRepositoryImpl skillRepository = new HibernateSkillRepositoryImpl();
    private final HibernateDeveloperRepositoryImpl developerRepository = new HibernateDeveloperRepositoryImpl();
    private ParametersAdapter parametersAdapter = new ParametersAdapter(new Developer());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Developer newDeveloper = parametersAdapter.getDeveloperParameter(req);

        newDeveloper = developerRepository.save(newDeveloper);
        HttpSession session = req.getSession();
        session.setAttribute("developer",newDeveloper);
        session.setAttribute("skills",skillRepository.getAll());
        req.setAttribute("skills",skillRepository.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("developer.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("skills",skillRepository.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("registration.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        skillRepository.closeSessionFactory();
        developerRepository.closeSessionFactory();
        super.destroy();

    }
}
