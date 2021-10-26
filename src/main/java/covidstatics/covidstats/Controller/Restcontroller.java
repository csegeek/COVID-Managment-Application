package covidstatics.covidstats.Controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import covidstatics.covidstats.Dao.statedao;
import covidstatics.covidstats.Entity.State;

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
	List<Long> activeList = statedao.findAll().stream().map(x-> x.getActive()).collect(Collectors.toList());
	model.addAttribute("State", stateList);
	model.addAttribute("active", activeList);
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
    return modelAndView;  }


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
  public String Insert( @ModelAttribute("S") State S) {
      //save to database,
      statedao.Insert(S);
  return  "redirect:/admin/statedata";
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
      public String Update( @ModelAttribute("S") State S) {
          //save to database,
          statedao.Update(S);
      return "redirect:/admin/statedata";
      }

    // DELETION CONTROLLER 
    
 @GetMapping("/deletestate")
    public String delete(@RequestParam int id){
        statedao.deleteById(id);
        return "redirect:/";
    
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
