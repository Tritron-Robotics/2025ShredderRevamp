package frc.robot.subsystems;

import java.util.function.Supplier;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.UpperJointConstants;

public class UpperJointSubsystem extends SubsystemBase {
    private final SparkMax motorA;
    private final SparkMax motorB;
    private final SparkMaxConfig motorAConfig;
    private final SparkMaxConfig motorBConfig;
    private final DutyCycleEncoder encoder;

    public UpperJointSubsystem() {

        motorAConfig = new SparkMaxConfig();
        motorAConfig
                .inverted(UpperJointConstants.kMotorAInverted)
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(UpperJointConstants.kCurrentLimit);

        motorBConfig = new SparkMaxConfig();
        motorBConfig
                .inverted(UpperJointConstants.kMotorBInverted)
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(UpperJointConstants.kCurrentLimit);

        motorA = new SparkMax(UpperJointConstants.kMotorAID, MotorType.kBrushless);
        motorA.configure(motorAConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        motorB = new SparkMax(UpperJointConstants.kMotorBID, MotorType.kBrushless);
        motorB.configure(motorBConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        encoder = new DutyCycleEncoder(UpperJointConstants.kEncoderDIOPort);
    }

    public void setSpeed(double speed) {
        motorA.set(speed);
        motorB.set(speed);
    }

    public void setVoltage(double voltage) {
        motorA.setVoltage(-voltage);
        motorB.setVoltage(voltage);
    }

    public double getUpperJointAngle() {
        double raw = encoder.get();
        if (raw <= UpperJointConstants.rawAt90) {
            return map(raw, UpperJointConstants.rawAtMinAngle, UpperJointConstants.rawAt90, 0, 90);
        } else {
            return map(raw, UpperJointConstants.rawAt90, UpperJointConstants.rawAtMaxAngle, 90, 330);
        }
    }

    private double map(double x, double inMin, double inMax, double outMin, double outMax) {
        return outMin + (x - inMin) * (outMax - outMin) / (inMax - inMin);
    }

    @Override
    public void periodic() {
        System.out.println(encoder.get());
    }

    // Manual control of the upper joint using a voltage supplier controlled by the Xbox Controller's Left 
    public Command manualControlCommand(Supplier<Double> voltageSupplier) {
        return runEnd(
                () -> setVoltage(voltageSupplier.get()), // Apply voltage when held
                () -> setVoltage(-0.350) // Apply holding voltage when released
        ).withName("upperJoint.manualControl");
    }

}
