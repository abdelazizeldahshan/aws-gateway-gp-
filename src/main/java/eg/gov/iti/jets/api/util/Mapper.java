package eg.gov.iti.jets.api.util;

import eg.gov.iti.jets.api.resource.branch.BranchRequest;
import eg.gov.iti.jets.api.resource.branch.BranchResponse;
import eg.gov.iti.jets.api.resource.intake.IntakeRequest;
import eg.gov.iti.jets.api.resource.intake.IntakeResponse;
import eg.gov.iti.jets.api.resource.supervisor.SupervisorRequest;
import eg.gov.iti.jets.api.resource.supervisor.SupervisorResponse;
import eg.gov.iti.jets.api.resource.track.TrackRequest;
import eg.gov.iti.jets.api.resource.track.TrackResponse;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramRequest;
import eg.gov.iti.jets.api.resource.trainingProgram.TrainingProgramResponse;
import eg.gov.iti.jets.persistence.entity.Branch;
import eg.gov.iti.jets.persistence.entity.Intake;
import eg.gov.iti.jets.persistence.entity.Track;
import eg.gov.iti.jets.persistence.entity.TrainingProgram;
import eg.gov.iti.jets.service.model.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Branch mapFromBranchRequestToBranch( BranchRequest branchRequest){
        return null;
    }

    public BranchResponse mapFromBranchToBranchResponse(Branch branch){
        return null;
    }
    public TrainingProgram mapFromTrainingProgramRequestToTrainingProgram( TrainingProgramRequest trainingProgramRequest){
        return null;
    }

    public TrainingProgramResponse mapFromTrainingProgramToTrainingProgramResponse( TrainingProgram trainingProgram){
        return null;
    }



    public IntakeResponse mapFromIntakeToIntakeResponse( Intake intake) {
        return null;
    }

    public Intake mapFromIntakeRequestToIntake(IntakeRequest intakeRequest){return null;}

//    public Supervisor mapFromSupervisorRequestToSupervisor(SupervisorRequest supervisorRequest) {
//        return  null;
//    }
//
//    public SupervisorResponse mapFromSupervisorToSupervisorResponse(Supervisor supervisor) {
//        return null;
//    }

    public Track mapFromTrackRequestToTrack( TrackRequest trackRequest) {
        return null;
    }

    public TrackResponse mapFromTrackToTrackResponse(Track track) {
        return null;
    }
}