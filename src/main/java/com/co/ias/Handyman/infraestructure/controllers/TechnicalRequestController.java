package com.co.ias.Handyman.infraestructure.controllers;

import com.co.ias.Handyman.application.technicalRequest.model.ConsultDTO;
import com.co.ias.Handyman.application.technicalRequest.model.DayWeekDTO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import com.co.ias.Handyman.application.technicalRequest.ports.in.ConsultDayOfWeekUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.in.ConsultTechnicalRequestUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.in.CreateTechnicalRequestUseCase;
import com.co.ias.Handyman.shared.errors.ApplicationError;
import com.co.ias.Handyman.shared.errors.BadRequestException;
import com.co.ias.Handyman.shared.messages.MessageHandyman;
import com.co.ias.Handyman.shared.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
public class TechnicalRequestController {


    public static final String NO_SE_ENCUENTRA_EL_ID_DEL_TECNICO_ESPECIFICADO = "No se encuentra el ID del técnico especificado.";
    public static final String EL_TÉCNCO_QUE_CONSULTA_NO_TIENE_HORAS_PARA_LA_SEMANA_SELECCIONADA = "El técnico que consulta no tiene horas para la semana seleccionada";
    @Autowired
    private CreateTechnicalRequestUseCase createTechnicalRequestUseCase;
    @Autowired
    private ConsultTechnicalRequestUseCase consultTechnicalRequestUseCase;

    @Autowired
    private ConsultDayOfWeekUseCase consultDayOfWeekUseCase;




    public TechnicalRequestController(CreateTechnicalRequestUseCase createTechnicalRequestUseCase, ConsultTechnicalRequestUseCase consultTechnicalRequestUseCase, ConsultDayOfWeekUseCase consultDayOfWeekUseCase){
        this.createTechnicalRequestUseCase = createTechnicalRequestUseCase;
        this.consultTechnicalRequestUseCase = consultTechnicalRequestUseCase;
        this.consultDayOfWeekUseCase = consultDayOfWeekUseCase;
    }

    @RequestMapping(value = "/technical/request", method = RequestMethod.POST)
    public ResponseEntity<?> createTechRequest(@RequestBody TechnicalRequestDBO technicalRequestDBO){

        try{
             TechnicalRequestDBO techRequest = createTechnicalRequestUseCase.execute(technicalRequestDBO);
             return ResponseEntity.ok(techRequest);


        } catch (SQLException exception){
            ApplicationError appError = new ApplicationError(
                    "No se encuentra el ID del técnico especificado.",
                    exception.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appError);

        } catch (FileNotFoundException exception){
            ApplicationError appError = new ApplicationError(
                    "No se encuentra el ID del servicio especificado.",
                    exception.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appError);

        } catch (NoSuchFieldException exception){

            ApplicationError appError = new ApplicationError(
                    "No se puede crear una petición de servicio técnico para una fecha ya utilizada.",
                    exception.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appError);

        } catch (DateTimeException exception){

            ApplicationError appError = new ApplicationError(
                    "No se puede crear una petición de servicio técnico con más de 7 días atrás, " +
                            "o después de la fecha actual.",
                    exception.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appError);

        }
        catch (IllegalArgumentException | NullPointerException exception ){
            ApplicationError appError = new ApplicationError(
                    "Input no Validation",
                    "Bad Input data"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appError);
        } catch (Exception exception){
            ApplicationError appError = new ApplicationError(
                    "SystemError",
                    exception.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appError);
        }


    }

    @RequestMapping(value = Constant.TECHNICAL_CONSULT, method = RequestMethod.GET)
    public ResponseEntity<?> consultWeekTechnical(@RequestParam Integer week, @RequestParam Integer idTechnical)
            throws BadRequestException {
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setWeek(week);
        consultDTO.setIdTechnical(idTechnical);
        try {
            ConsultDTO response = consultTechnicalRequestUseCase.execute(consultDTO);
            return  ResponseEntity.ok(response);
        } catch (IllegalArgumentException | NullPointerException  e) {
            ApplicationError aplicationError = new ApplicationError(
                    MessageHandyman.INPUT_VALIDATION,
                    MessageHandyman.BAD_INPUT_DATA
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(aplicationError);
        } catch (SQLException exception){
        ApplicationError appError = new ApplicationError(
                NO_SE_ENCUENTRA_EL_ID_DEL_TECNICO_ESPECIFICADO,
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(appError);

    }    catch (BadRequestException exception ) {
            ApplicationError appError = new ApplicationError(
                    EL_TÉCNCO_QUE_CONSULTA_NO_TIENE_HORAS_PARA_LA_SEMANA_SELECCIONADA,
                    exception.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appError);
        }catch (Exception exception) {
            ApplicationError aplicationError = new ApplicationError(
                    MessageHandyman.SYSTEM_ERROR,
                    exception.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(aplicationError);
        }


    }

    @RequestMapping(value = Constant.TECHNICAL_DAY_WEEK, method = RequestMethod.GET)
    public ResponseEntity<?> consultDayOfTheWeek(@RequestParam Integer week, @RequestParam Integer idTechnical) throws SQLException, FileNotFoundException, NoSuchFieldException {
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setWeek(week);
        consultDTO.setIdTechnical(idTechnical);
       List<DayWeekDTO> list= consultDayOfWeekUseCase.execute(consultDTO);
        return  ResponseEntity.ok(list);

    }



}
