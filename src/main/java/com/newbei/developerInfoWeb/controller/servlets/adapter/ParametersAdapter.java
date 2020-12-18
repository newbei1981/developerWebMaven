package com.newbei.developerInfoWeb.controller.servlets.adapter;

import com.newbei.developerInfoWeb.model.Account;
import com.newbei.developerInfoWeb.model.AccountStatus;
import com.newbei.developerInfoWeb.model.Developer;
import com.newbei.developerInfoWeb.model.Skill;
import com.newbei.developerInfoWeb.repository.HibernateSkillRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class ParametersAdapter {

    private HibernateSkillRepositoryImpl skillRepository = new HibernateSkillRepositoryImpl();
    private final Developer developer;

    public ParametersAdapter(Developer developer) {
        this.developer = developer;
    }


    private final Set<Skill> getSkillsFromRequest(String[] skillsFromForm, String newSkill){
        Set<Skill> developerSkills = new HashSet<>();
        if (developer.getId()!= null) developerSkills = developer.getSkills();
        if ((newSkill != null) && (!newSkill.equals(""))) {
            Skill newAddSkill = new Skill(newSkill);
            newAddSkill = skillRepository.save(newAddSkill);
            developerSkills.add(newAddSkill);
        }
        if (skillsFromForm!=null)
            for(String skillName:skillsFromForm){
                System.out.println(skillName);
                for(Skill skill:skillRepository.getAll()){
                    if (skill.getName().equals(skillName)){
                        developerSkills.add(skill);
                    }
                }
            }
        return developerSkills;
    }

    public Developer getUpdatedDeveloperParameter(HttpServletRequest req){
        Account account = developer.getAccount();
        account.setLogin(req.getParameter("login"));
        account.setPassword(req.getParameter("password"));
        developer.setName(req.getParameter("name"));
        developer.setAccount(account);
        String[] skills = req.getParameterValues("skills");
        String newSkill = req.getParameter("newSkill");
        developer.setSkills(getSkillsFromRequest(skills,newSkill));
        return developer;
    }

    public Developer getDeveloperParameter(HttpServletRequest req){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String[] skills = req.getParameterValues("skills");
        String newSkill = req.getParameter("newSkill");
        Account account = new Account(login, password, AccountStatus.ACTIVE);
        Developer developer = new Developer(name,account,getSkillsFromRequest(skills,newSkill));
        return developer;
    }
}
