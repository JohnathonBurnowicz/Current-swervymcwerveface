// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;


public class ArmDown extends Command {
  
  private DoubleSupplier angle;
  /** Creates a new MoveArmToPosition. */
   private final Arm arm;
 
  private boolean inPosition = false;
  private boolean hasProcessed = false;
  private double armPosition = Constants.MOVEPOS1;
  //
  public ArmDown(Arm arm) {
    this.arm = arm;

    addRequirements(arm);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (arm.isArmInPosition(Constants.MOVEPOS1))  {
      arm.setArmAnglePID(Constants.MOVEPOS1);
     
      armPosition = Constants.MOVEPOS1;
     
      hasProcessed = true;
    }
    
    else if (arm.isArmInPosition(Constants.MOVEPOS1)) {
      arm.setArmAnglePID(Constants.MOVEPOS1);
     
      armPosition = Constants.MOVEPOS1;
     
      hasProcessed = true;
    }
    
    else{
      hasProcessed = true;
      inPosition = true;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(hasProcessed == true && arm.isArmInPosition(armPosition)) {
      inPosition = true;
    }
  }

  // Called once the command ends or is interrupted.

 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return inPosition;
  }
}