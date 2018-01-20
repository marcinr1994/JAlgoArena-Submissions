package com.jalgoarena

import com.jalgoarena.data.SubmissionsRepository
import com.jalgoarena.ranking.*
import com.jalgoarena.web.ProblemsClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
open class AppConfiguration {

    @Bean
    open fun rankingCalculator(submissionsRepository : SubmissionsRepository, problemsClient: ProblemsClient): RankingCalculator {
        val scoreCalculator = BasicScoreCalculator()
        val rankingCalculator = BasicRankingCalculator(submissionsRepository, scoreCalculator)

        return BonusPointsForBestTimeRankingCalculator(
                submissionsRepository, rankingCalculator
        )
    }

    @Bean
    open fun restTemplate(): RestOperations = RestTemplate()


    @Bean
    open fun jalGoArenaApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.jalgoarena"))
                .paths(PathSelectors.any()).build()
    }
}
