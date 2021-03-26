package edu.escuelaing.edu.app.servicesOperationImpl;

import edu.escuelaing.edu.app.servicesOperation.Operations;

public class Exp implements Operations {

    public double getResult(double numero) {
        return Math.exp(numero);
    }
}
