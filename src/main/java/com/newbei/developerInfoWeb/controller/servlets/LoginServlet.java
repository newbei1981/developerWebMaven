package com.newbei.developerInfoWeb.controller.servlets;

import com.newbei.developerInfoWeb.model.Developer;
import com.newbei.developerInfoWeb.model.Skill;
import com.newbei.developerInfoWeb.repository.HibernateDeveloperRepositoryImpl;
import com.newbei.developerInfoWeb.repository.HibernateSkillRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private final HibernateSkillRepositoryImpl skillRepository = new HibernateSkillRepositoryImpl();
    private List<Skill> skillList;
    private final HibernateDeveloperRepositoryImpl developerRepository = new HibernateDeveloperRepositoryImpl();
    private Developer developer = new Developer();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        developer = requestIsValid(req);
        if (developer.getId() == null) doGet(req,resp);
         else {
            skillList = skillRepository.getAll();
            HttpSession session = req.getSession();
            session.setAttribute("developer",developer);
            session.setAttribute("skills",skillRepository.getAll());
            req.getRequestDispatcher("/updateDeveloper").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        developerRepository.closeSessionFactory();
        skillRepository.closeSessionFactory();
    }

    private final Developer requestIsValid(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        long id = developerRepository.authentication(login, password);
        if (id != -1) developer = developerRepository.getById(id);
        return developer;
    }

}
