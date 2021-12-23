package covidstatics.covidstats.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import covidstatics.covidstats.Entity.State;

@Repository
public class statedao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate ;

    public statedao() {
  
    }

   public int createTable(){
    String query=" CREATE TABLE IF NOT EXISTS StateData(stateid int primary key ,state varchar(50),totalcases int,recovered int ,active int,death int, vaccinated int )" ;
       int update= this.jdbcTemplate.update(query);
        //creating table 
        return update;
}

public List<State> findAll() {
    return jdbcTemplate.query("SELECT * FROM StateData", new BeanPropertyRowMapper<State>(State.class));
}

public State findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM StateData WHERE stateid=?", new BeanPropertyRowMapper<State>(State.class), id);
}
// iserting data into database
public int Insert(State s) {
    
    return jdbcTemplate.update("INSERT INTO StateData (stateid, state,totalcases,recovered,active,death,vaccinated) VALUES (?, ?, ?,?,?,?,?)",
     new Object[] {s.getStateid(),s.getState(),s.getTotalcases(),s.getRecovered(),s.getActive(),
    s.getDeath(),s.getVaccinated() });
}
//update data into database

public int Update(State s){
    //**  implement error handling */
    return jdbcTemplate.update("UPDATE StateData SET stateid=?, state=?,totalcases=?,recovered=?,active=?,death=?,vaccinated=? WHERE stateid=?",
new  Object[] {s.getStateid(), s.getState(),s.getTotalcases(),s.getRecovered(),s.getActive(),
s.getDeath() , s.getVaccinated() ,s.getStateid()});

}

//delete entity
public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM StateData WHERE stateid=?", id);
}

public State getStateId(String state) {
    return jdbcTemplate.queryForObject("SELECT stateid FROM StateData WHERE state=?",new BeanPropertyRowMapper<State>(State.class), state);
}



    
} 
