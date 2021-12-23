package covidstatics.covidstats.Controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import covidstatics.covidstats.Dao.statedao;
import covidstatics.covidstats.Entity.State;
import covidstatics.covidstats.Services.Message;


@Controller
@RestController
@EnableWebMvc
public class Restcontroller {
    @Autowired
    private statedao statedao;
   //HOME PAGE 
    @RequestMapping("/")
   public ModelAndView home (Model model) {
    ModelAndView modelAndView = new ModelAndView();
    List<State> allStates=statedao.findAll();
    long totalReportedCases = allStates.stream().mapToLong(stat -> stat.getTotalcases()).sum();
    model.addAttribute("allStates",allStates);
    model.addAttribute("totalReportedCases", totalReportedCases);
    // for chart
    List<String> stateList= statedao.findAll().stream().map(x-> x.getState()).collect(Collectors.toList());
	List<Long> VaccinationList = statedao.findAll().stream().map(x-> x.getVaccinated()).collect(Collectors.toList());
	model.addAttribute("State", stateList);
	model.addAttribute("Vaccinated", VaccinationList);
    modelAndView.setViewName("home");
    return modelAndView;


}
    
   @RequestMapping("/admin/statedata")
    public ModelAndView admin(Model model ,Principal principal){
        String username=principal.getName();
    ModelAndView modelAndView = new ModelAndView();
    List<State> allStates=statedao.findAll();
    model.addAttribute("allStates",allStates);
    model.addAttribute("username", username);
    modelAndView.setViewName("admin");
    return modelAndView;
  }


/*    @GetMapping(value="/statedata")
    public List<State> findAll(){
        return statedao.findAll();
    }
    @GetMapping("/statedata/{id}")
    public State findById(@PathVariable int id){
        return statedao.findById(id);

    }*/
    
    // ADD STATE CONTROLLER 
 @RequestMapping("/admin/addstateform")
    public ModelAndView showaddstateForm(Model model) {
        // create model attribute to bind form data
        State S=new State();
        model.addAttribute("S",S);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addstate");
        return modelAndView;    
    }

    @PostMapping("/addstatedata")
  public RedirectView  Insert( @ModelAttribute("S") State S ,HttpSession session) {
      //save to database,
      boolean flag=false;
      List<State> data=statedao.findAll();
     for( State s:data){
         if(s.getStateid()==S.getStateid() || s.getState().equals(S.getState() ) ) {
             flag=true;  
          break;
        }
      }
      if(flag==true){
        session.setAttribute("message",new Message("Duplicate State ID or State Name Not Allowed please recheck data....", "danger")); 

      }
      
     else{ statedao.Insert(S);
      session.setAttribute("message",new Message("State Sucessfully Added ....", "success"));
     }
      RedirectView rv=new RedirectView("/admin/statedata ");
      return rv;
  }

  //UPDATE STATE CONTROLLER 

  @RequestMapping("/admin/updatestateform{id}")
  public ModelAndView updatestateForm( @PathVariable(value = "id") int id ,Model model) {
      // create model attribute to bind form data
    State S=statedao.findById(id);
      model.addAttribute("S",S);
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("updatestate");
      return modelAndView;    }

      @PostMapping("/Updatestate")
      public RedirectView Update( @ModelAttribute("S") State S,HttpSession session  ) {
          //save to database,
          statedao.Update(S);
          session.setAttribute("message",new Message("State Updated Sucessfully....", "info"));

       RedirectView rv=new RedirectView("/admin/statedata");
       return rv;
     
      }

    // DELETION CONTROLLER 
    
 @GetMapping("/deletestate")
    public RedirectView delete(@RequestParam int id,HttpSession session ){
        statedao.deleteById(id);
        session.setAttribute("message",new Message("State Deleted Sucessfully  ....", "warning"));
        RedirectView rv=new RedirectView("/admin/statedata");
        return rv;
    
    }

    //BARCAHRT PRODUCTION
   @RequestMapping("/barChart")
	public ModelAndView getAllEmployee(Model model) {		
	List<String> stateList= statedao.findAll().stream().map(x-> x.getState()).collect(Collectors.toList());
	List<Long> activeList = statedao.findAll().stream().map(x-> x.getActive()).collect(Collectors.toList());
	model.addAttribute("State", stateList);
	model.addAttribute("active", activeList);
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.setViewName("barChart");
    return modelAndView;
	
	}







/*  @PostMapping("/updatestate/{id}")
  public String update(@RequestBody State s ,@PathVariable int id){
      return statedao.Update(s, id) +"State(s) Updated sucessfully";
  }    
  */

   
 /* @PutMapping("/statedata/{id}")
public String update(@RequestBody State s ,@PathVariable int id){
    return statedao.Update(s, id) +"State(s) Updated sucessfully";
}    

      
  }*/

    
}
