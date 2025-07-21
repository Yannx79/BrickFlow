package com.nk.realstateapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean("companyModelMapper")
    public ModelMapper companyModelMapper(){
        return new ModelMapper();
    }

    @Bean("housingModelMapper")
    public ModelMapper housingModelMapper(){
        return new ModelMapper();
    }

    @Bean("populationModelMapper")
    public ModelMapper populationModelMapper(){
        return new ModelMapper();
    }

    @Bean("promotionModelMapper")
    public ModelMapper promotionModelMapper(){
        return new ModelMapper();
    }

    @Bean("realStateModelMapper")
    public ModelMapper realStateModelMapper(){
        return new ModelMapper();
    }


}
