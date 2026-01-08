
package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import java.util.function.Supplier;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Constants.ClawConstants;


public class ClawSubsystem extends SubsystemBase{
    //private static final double LOWER_JOINT_MAX_SPEED = 1.0; // Maximum speed for the lower joint

    //private final UpperJointSubsystem upperJoint;
    private final SparkMax motor;

    private SparkMaxConfig motorConfig;

    public ClawSubsystem(){
        //this.upperJoint = upperJoint;
        motorConfig = new SparkMaxConfig();
        motorConfig
                .inverted(ClawConstants.kMotorInverted)
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(ClawConstants.kCurrentLimit);

        motor = new SparkMax(ClawConstants.kMotorID, MotorType.kBrushless);
        motor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
      }

    public void setMotorSpeed(double speed) {
        motor.set(speed);
        System.out.println(speed);
    }   

    public double getMotorCurrent() {
        return motor.getOutputCurrent();
    }

    @Override
    public void periodic(){
        //System.out.println(motor.get());
    }
    
  }

