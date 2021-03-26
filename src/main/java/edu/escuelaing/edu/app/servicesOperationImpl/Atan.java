package edu.escuelaing.edu.app.servicesOperationImpl;

import edu.escuelaing.edu.app.servicesOperation.Operations;

public class Atan implements Operations{

    public double getResult(double numero) {
        return Math.atan(numero);
    }
}
