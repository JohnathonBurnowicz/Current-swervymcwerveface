// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
 private CANSparkMax ArmMotor = new CANSparkMax(Constants.ArmCanId, MotorType.kBrushless);
 private SparkMaxPIDController ArmPID;

 
  /** Creates a new Arm. */
  public Arm() {
    ArmMotor.restoreFactoryDefaults();
    ArmMotor.setIdleMode(IdleMode.kCoast);
    ArmMotor.setSmartCurrentLimit(30);
    ArmMotor.setInverted(false);
    ArmMotor.enableVoltageCompensation(10);
    ArmMotor.setOpenLoopRampRate(0.4);
    ArmMotor.setClosedLoopRampRate(0.4);
    ArmMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
    ArmMotor.setSoftLimit(SoftLimitDirection.kForward, 2);
    ArmMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    ArmMotor.setSoftLimit(SoftLimitDirection.kReverse, -30);
    ArmPID = ArmMotor.getPIDController();
    ArmPID.setP(Constants.returnArmPGain);//0.05
    ArmPID.setI(0.0);
    ArmPID.setD(0.0);
    ArmPID.setIZone(0.0);
    ArmPID.setFF(0.0);
    ArmPID.setOutputRange(-0.75, 0.75); //0.7
    setArmEncoder();
    setArmAnglePID(Constants.MOVEPOS1);

  }

  @Override
  public void periodic() {

    
  }

  public void setArmPID(double angle) {
  ArmPID.setReference(angle, CANSparkMax.ControlType.kPosition, 0);
 setArmPID(Constants.ArmupPID);
}

public void setArmEncoder() {
  ArmMotor.getEncoder().setPosition(0.0);
}

 public double ArmAngle() {
    return ArmMotor.getEncoder().getPosition();
  }


  public boolean isArmInPosition(double position) {
    if(position <= ArmMotor.getEncoder().getPosition()+1) {
      return true;
    }else{
      return false;
    }
  }

  public boolean isArmPastPosition(double position, boolean greaterThen) {
    if(greaterThen && position <= ArmMotor.getEncoder().getPosition()){
      return true;
    }else if(!greaterThen && position >= ArmMotor.getEncoder().getPosition()) {
      return true;
    }else{
      return false;
    }
  }

  public void setArmAnglePID(double angle) {
    ArmPID.setReference(angle, CANSparkMax.ControlType.kPosition, 0);
  }

  public void setArmPIDValue(double pGain) {
    ArmPID.setP(pGain);
  }

  //}



    // This method will be called once per scheduler run
  
}
