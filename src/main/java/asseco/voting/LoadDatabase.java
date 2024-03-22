package asseco.voting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import asseco.voting.entity.Station;
import asseco.voting.entity.Voter;
import asseco.voting.repository.StationRepository;
import asseco.voting.repository.VoterRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initStationDatabase(StationRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Station("Aveiro", "Espinho", "Espinho", "Escola Secundária do Laranjal")));
            log.info("Preloading " + repository.save(new Station("Lisboa", "Sintra", "Algueirão", "Escola EB/1PE Os Reguilas")));
        };
    }
    
    @Bean
    CommandLineRunner initVoterDatabase(VoterRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Voter(14214422, "André Claro Amaral Ventura", "1983-01-15")));
            log.info("Preloading " + repository.save(new Voter(12312399, "Luís Filipe Montenegro Cardoso de Morais Esteves", "1973-04-11")));
        };
    }
}
