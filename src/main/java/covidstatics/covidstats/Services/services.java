package covidstatics.covidstats.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import covidstatics.covidstats.Dao.statedao;

@Service
public class services {
    @Autowired
    private statedao statedao;
    
    
}
