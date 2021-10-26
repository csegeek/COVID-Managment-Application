package covidstatics.covidstats;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import covidstatics.covidstats.Dao.statedao;
import covidstatics.covidstats.Entity.State;

@SpringBootApplication
public class CovidstatsApplication implements CommandLineRunner{
	@Autowired
 private statedao statedao;
	public static void main(String[] args) {
		SpringApplication.run(CovidstatsApplication.class, args);
		System.out.println("SERVR STARTED ........");
	}
	@Override
	public void run(String... args) throws Exception {
		
		 
		//State s=new State();
		/*s.setStateid(02);
		s.setState("Uttarakhand");
		s.setTotalcases(1452869);
		s.setRecovered(825654);
		s.setActive(856);
		s.setDeath(7895);
		s.setVaccinated(546784245);*/
//  	System.out.println(this.statedao.createTable());
	 // List<State> s=this.statedao.findAll();
	  
		//System.out.println(s.get(0).toString() );
		//System.out.println(s.get(1).toString() );

		//s.forEach(item -> System.out.println(item.toString()));

	}


}
