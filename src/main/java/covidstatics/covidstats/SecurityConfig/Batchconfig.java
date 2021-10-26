package covidstatics.covidstats.SecurityConfig;
/*
import javax.sql.DataSource;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import covidstatics.covidstats.Entity.State;
import org.springframework.batch.core.Job;

@Configuration
@EnableBatchProcessing
public class Batchconfig {
   
    @Autowired
    private DataSource dataSource;
  
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
  
    @Bean
    public FlatFileItemReader<State> reader(){

        FlatFileItemReader<State> reader = new FlatFileItemReader<State>();
        reader.setResource(new ClassPathResource("covid-19.csv"));
        reader.setLineMapper(getLineMapper());  
        reader.setLinesToSkip(1);   
        return reader;

    }

    private LineMapper<State> getLineMapper() {
         DefaultLineMapper<State> lineMapper=new DefaultLineMapper<>();
        
         DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
          

          lineTokenizer.setNames(new String[] { "stateid","state","totalcases","recovered","active","death","vaccinated" });
          lineTokenizer.setIncludedFields(new int[]{0,1,2,3,4,5,6});

         BeanWrapperFieldSetMapper<State> fieldSetMapper=new 
         BeanWrapperFieldSetMapper<>();
         fieldSetMapper.setTargetType(State.class);
         lineMapper.setLineTokenizer(lineTokenizer);
         lineMapper.setFieldSetMapper(fieldSetMapper);
          lineMapper.setLineTokenizer(lineTokenizer);
         lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    
    @Bean
 public itemprocessor processor(){
     return new itemprocessor();
 }

   public JdbcBatchItemWriter<State> writer() {
       JdbcBatchItemWriter<State> writer=new JdbcBatchItemWriter<>();
       writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
   writer.setSql("insert into statedata(stateid,state,totalcases,active,recovered,death,vaccinated)  values(:statetid,:state,:totalcases ,:active,:recovered,:death,:vaccinated) ");
   writer.setDataSource(this.dataSource);
   return writer;
   
    }
 @Bean
public Job importStateJob(){
    return this.jobBuilderFactory.get("importStateJob")
    .incrementer(new RunIdIncrementer())
    .flow(step1())
    .end()
    .build();    

}

@Bean
public Step step1(){
    return  this.stepBuilderFactory.get("step1").<State, State> chunk(3)
    .reader(reader())
    .processor(processor())
    .writer(writer())
    .build();   
}

}
*/