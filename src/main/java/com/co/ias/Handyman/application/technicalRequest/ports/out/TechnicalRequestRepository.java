package com.co.ias.Handyman.application.technicalRequest.ports.out;






import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TechnicalRequestRepository extends CrudRepository<TechnicalRequestDBO,Integer> {

    boolean existsTechnicalRequestDTOByStartDayAndEndDay(LocalDateTime startDay, LocalDateTime endDay);


    @Query(
            value =  " select * from technical_request " +
                    " where  extract(week from start_date ) = :week " +
                    " and id_technical = :idTechnical order by start_date asc ",
            nativeQuery = true
    )
    List<TechnicalRequestDBO> findServiceByWeekAndTechical(
            @Param("week") Integer week, @Param("idTechnical") Integer idTechnical);




}
