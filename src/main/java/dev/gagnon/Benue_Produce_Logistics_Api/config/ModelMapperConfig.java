package dev.gagnon.Benue_Produce_Logistics_Api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.Conditions.isNotNull;
import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(STRICT)
                .setPropertyCondition(isNotNull());
        return modelMapper;
    }


}
